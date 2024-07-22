package com.outsider.masterofprediction.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

// security 파일
@Getter
@ToString
public class CustomUserDetail implements UserDetails , OAuth2User {
    private final User user;
    private Map<String, Object> attributes;
    public CustomUserDetail(User user) {
        this.user = user;
    }
    public CustomUserDetail(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 유저 권한 추가해주고 전달
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new SimpleGrantedAuthority(this.user.getAuthority()));
        return collection;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getName();
    }

    //    userId 가져오기
    public Long getId() {
        return this.user.getId();
    }

    //    계정이 만료되었는지
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    // 계정이 잠겨있는지
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //    계정의 자격 증명(비밀번호)이 만료되엇는지
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //    계쩡이 활성화되어 있는지 여부
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return null;
    }
    // 반환 형식을 선택할 수 있는 메서드
    // 제네릭을 사용하여 반환 형식을 결정
    // Date 또는 String 형식으로 반환
    public <T> T getJoinDate(Class<T> type) {
        LocalDateTime joinDate = this.user.getJoinDate();

        if (type.isAssignableFrom(Date.class)) {
            Date date = Date.from(joinDate.atZone(ZoneId.systemDefault()).toInstant());
            return type.cast(date);
        } else if (type.isAssignableFrom(String.class)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return type.cast(joinDate.format(formatter));
        } else {
            throw new IllegalArgumentException("Invalid type: " + type.getName());
        }
    }

    // LocalDateTime 또는 String 형식으로 반환
    public <T> T getJoinDateTime(Class<T> type) {
        LocalDateTime joinDate = this.user.getJoinDate();

        if (type.isAssignableFrom(LocalDateTime.class)) {
            return type.cast(joinDate);
        } else if (type.isAssignableFrom(String.class)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return type.cast(joinDate.format(formatter));
        } else {
            throw new IllegalArgumentException("Invalid type: " + type.getName());
        }
    }

}
