package com.outsider.masterofprediction.service;

import com.outsider.masterofprediction.dto.CustomUserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserSession {

    /**
     * 로그인한 유저의 Id값을 반환합니다
     * @return userId or 0
     */
    public static Long getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object userDetails =  authentication.getPrincipal();
        if (userDetails instanceof CustomUserDetail){
            CustomUserDetail customUserDetail = (CustomUserDetail) userDetails;
            if (customUserDetail.getUser() == null) {
                return 0L;
            }
            return customUserDetail.getUser().getId();
        }
        return 0L;
    }

    public static boolean isUser(){
        return getUserId() >= 0;
    }

    public static boolean isAdmin() {
        // 현재 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            // 사용자의 권한 목록 가져오기
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if ("ROLE_ADMIN".equals(authority.getAuthority())) {
                    return true;
                }
            }
        }

        return false;
    }
}
