package com.outsider.masterofprediction.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Setter
@Getter
@ToString
@Builder
public class User {
    private Long id;

    private String name;

    private String email;

    private String password;

    private LocalDateTime joinDate;

    private String authority;

    private BigDecimal point;

    private boolean withdrawalStatus;
    private String provider; //어떤 OAuth인지(google, naver 등)
    private String provideId; // 해당 OAuth 의 key(id)
    private Long tierNo;
    public User() {
    }

    public User(Long id, String name, String email, String password, LocalDateTime joinDate, String authority, BigDecimal point, boolean withdrawalStatus, String provider, String provideId,Long tierNo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.joinDate = joinDate;
        this.authority = authority;
        this.point = point;
        this.withdrawalStatus = withdrawalStatus;
        this.provider = provider;
        this.provideId = provideId;
        this.tierNo = tierNo;
    }

    public <T> T getJoinDate(Class<T> type) {

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
}
