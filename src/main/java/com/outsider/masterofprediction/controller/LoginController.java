package com.outsider.masterofprediction.controller;


import com.outsider.masterofprediction.dto.CustomUserDetail;
import com.outsider.masterofprediction.dto.TblAttachmentDTO;
import com.outsider.masterofprediction.dto.User;
import com.outsider.masterofprediction.dto.UserWithdrawalStatusDTO;
import com.outsider.masterofprediction.mapper.UserMapper;
import com.outsider.masterofprediction.service.ProcessFileService;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;


//스프링 보안 테스트
@Controller
public class LoginController {
    private UserManagementService userManagementService;
    private final ProcessFileService processFileService;

    public LoginController(UserManagementService userManagementService, ProcessFileService processFileService) {
        this.userManagementService = userManagementService;
        this.processFileService = processFileService;
    }

//    @PostMapping("/loginProc")
//    public String loginProc()
//    {
//        return "redirect:/";
//    }
    @PostMapping("/register")
    public RedirectView register(@ModelAttribute User user , @RequestParam("profileImage") MultipartFile profileImage, RedirectAttributes redirectAttributes) {
        userManagementService.createDefaultUserObject(user);
        long id = user.getId();
        System.out.println("/register "+ id);
        TblAttachmentDTO tblAttachmentDTO =new TblAttachmentDTO();
        tblAttachmentDTO.setAttachmentUserNo(id);
        tblAttachmentDTO.setAttachmentRegistUserNo(id);
        // 파일 처리
        if (!profileImage.isEmpty()) {
                try {
                    processFileService.execute(tblAttachmentDTO, profileImage, userManagementService::registerUserImage);

                } catch (IOException e) {
                    e.printStackTrace();
                    redirectAttributes.addFlashAttribute("flashMessage1", "파일 업로드 중 오류가 발생했습니다.");
                    return new RedirectView("/login?");
                }
        }
        return new RedirectView("/login");
    }
    @PostMapping("/deleteUser")
    public String deleteUser(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            CustomUserDetail user = (CustomUserDetail) userDetails; // 사용자명으로 ID 찾기
            userManagementService.updateUserWithdrawalStatus(new UserWithdrawalStatusDTO(user.getId(),true));
            // 로그아웃 처리
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView mv) {

        mv.setViewName("layout/index");
        mv.addObject("view", "content/login");
        return mv;
    }
}