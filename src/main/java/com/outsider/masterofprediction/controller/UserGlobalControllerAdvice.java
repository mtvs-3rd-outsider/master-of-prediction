package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.config.RedisConfig;
import com.outsider.masterofprediction.dto.CustomUserDetail;
import com.outsider.masterofprediction.service.CategoryService;
import com.outsider.masterofprediction.service.FCMTokenService;
import com.outsider.masterofprediction.service.UserManagementService;
import com.outsider.masterofprediction.service.UserSession;
import com.outsider.masterofprediction.utils.FileUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ControllerAdvice
public class UserGlobalControllerAdvice {
    private final UserManagementService userManagementService;
    private final CategoryService categoryService;
    private final FCMTokenService fcmTokenService;
    @Value("${file.imgUrl}")
    private  String imgUrl;



    public UserGlobalControllerAdvice(UserManagementService userManagementService, CategoryService categoryService, FCMTokenService fcmTokenService) {
        this.userManagementService = userManagementService;
        this.categoryService = categoryService;
        this.fcmTokenService = fcmTokenService;
    }
    @ModelAttribute
    public CustomUserDetail customPrincipal(Authentication a) {
        return  a == null ? null : (CustomUserDetail) a.getPrincipal();
    }

    @ModelAttribute
    public void addAttributes(@AuthenticationPrincipal CustomUserDetail customUserDetail, Model model) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestURI = request.getRequestURI();

        // /admin-page/** 패턴 제외
        if (requestURI.startsWith("/admin-page")) {
            // 공통 모델 속성 추가
            // model.addAttribute("headerInfo", "Some common information");
            return;
        }
        Long userId = UserSession.getUserId();
        if (userId!=null && userId != 0) {
            String userImage = null;
            if(userManagementService.getAttachmentsByUserNo(userId) == null)
            {
                userImage = imgUrl+"/"+ "logo2.png";
            }else
            {
                userImage = FileUtil.checkFileOrigin(userManagementService.getAttachmentsByUserNo(userId).getAttachmentFileAddress());
            }
            String userName = customUserDetail.getUsername();
            model.addAttribute("userId", userId);
            model.addAttribute("userImage",  userImage);
            model.addAttribute("userName", userName);
            model.addAttribute("userPoint", userManagementService.getUserPoint());
            model.addAttribute("fcmTokens", fcmTokenService.getTokens(userId.toString()));
        }
        model.addAttribute("categories", categoryService.findAll());
    }
}
