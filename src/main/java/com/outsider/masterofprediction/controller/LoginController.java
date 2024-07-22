package com.outsider.masterofprediction.controller;


import com.outsider.masterofprediction.dto.CustomUserDetail;
import com.outsider.masterofprediction.dto.UserWithdrawalStatusDTO;
import com.outsider.masterofprediction.mapper.UserMapper;
import com.outsider.masterofprediction.service.UserManagementService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


//스프링 보안 테스트
@Controller
public class LoginController {
    @Autowired
    private UserManagementService userService;
    @PostMapping("/loginProc")
    public String loginProc()
    {
        return "redirect:/";
    }
    
    @PostMapping("/deleteUser")
    public String deleteUser(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            CustomUserDetail user = (CustomUserDetail) userDetails; // 사용자명으로 ID 찾기
            userService.updateUserWithdrawalStatus(new UserWithdrawalStatusDTO(user.getId(),true));
            // 로그아웃 처리
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }
}