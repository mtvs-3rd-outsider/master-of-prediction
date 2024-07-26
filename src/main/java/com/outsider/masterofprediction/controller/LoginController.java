package com.outsider.masterofprediction.controller;


import com.outsider.masterofprediction.dto.*;
import com.outsider.masterofprediction.dto.constatnt.StringConstants;
import com.outsider.masterofprediction.mapper.UserMapper;
import com.outsider.masterofprediction.service.ProcessFileService;
import com.outsider.masterofprediction.service.TierService;
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
import java.util.List;


//스프링 보안 테스트
@Controller
public class LoginController {
    private UserManagementService userManagementService;
    private final ProcessFileService processFileService;
    private final TierService tierService;

    public LoginController(UserManagementService userManagementService, ProcessFileService processFileService, TierService tierService) {
        this.userManagementService = userManagementService;
        this.processFileService = processFileService;
        this.tierService = tierService;
    }

//    @PostMapping("/loginProc")
//    public String loginProc()
//    {
//        return "redirect:/";
//    }
    @PostMapping("/register")
    public RedirectView register(@ModelAttribute User user , @RequestParam("profileImage") MultipartFile profileImage, RedirectAttributes redirectAttributes) {
        List<TblTierDTO> tiers = tierService.findAll();
        for (int i = 0; i < tiers.size(); i++) {
            if (StringConstants.USER_DEFAULT_TIER.equals(tiers.get(i).getTierName())){
                user.setTierNo(tiers.get(i).getTierNo());
            }
        }
//        기본 티어이름에 매핑되는 값이 없다면 0번째로 조회된 티어를 가져갑니다
        if (user.getTierNo() == null){
             user.setTierNo(tiers.get(0).getTierNo());
        }
//        user.setTierNo();
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
                    return new RedirectView("/login");
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
    @GetMapping(value="/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}