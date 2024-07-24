package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.CustomUserDetail;
import com.outsider.masterofprediction.service.UserManagementService;
import com.outsider.masterofprediction.service.UserSession;
import com.outsider.masterofprediction.utils.FileUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ControllerAdvice
public class UserGlobalControllerAdvice {
    private final UserManagementService userManagementService;

    public UserGlobalControllerAdvice(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @ModelAttribute
    public void addAttributes(@AuthenticationPrincipal CustomUserDetail customUserDetail, Model model) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestURI = request.getRequestURI();

        // /admin-page/** 패턴 제외
        if (!requestURI.startsWith("/admin-page")) {
            // 공통 모델 속성 추가
            model.addAttribute("headerInfo", "Some common information");
        }
        Long userId = UserSession.getUserId();
        if (userId != 0) {
            String userImage = FileUtil.checkFileOrigin(userManagementService.getAttachmentsByUserNo(userId)
                            .getAttachmentFileAddress());
            String userName = customUserDetail.getUsername();
            model.addAttribute("userImage",  userImage);
            model.addAttribute("userName", userName);
            model.addAttribute("userPoint", userManagementService.getUserPoint());
        }
    }
}
