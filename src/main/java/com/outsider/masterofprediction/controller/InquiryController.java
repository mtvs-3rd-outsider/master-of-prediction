package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.CustomUserDetail;
import com.outsider.masterofprediction.dto.InquiryDetailDTO;
import com.outsider.masterofprediction.dto.TblInquiryDTO;
import com.outsider.masterofprediction.service.UserInquiryService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.time.Instant;

@Controller
@RequestMapping("/mypage/")
public class InquiryController {


    private final UserInquiryService userInquiryService;

    public InquiryController(UserInquiryService userInquiryService) {
        this.userInquiryService = userInquiryService;
    }



    @PostMapping("inquiry/register")
    public String postInquiryRegister(
            @ModelAttribute TblInquiryDTO tblInquiryDTO,
            @AuthenticationPrincipal CustomUserDetail userDetails,
            RedirectAttributes redirectAttributes) {

        tblInquiryDTO.setInquiryUserNo(userDetails.getId());
        // 현재 시간을 기반으로 Timestamp 객체 생성
        Timestamp inquiryTimestamp = Timestamp.from(Instant.now());
        tblInquiryDTO.setInquiryTimestamp(inquiryTimestamp);

        userInquiryService.insertInquiry(tblInquiryDTO);
        // 리다이렉션 시 전달할 데이터가 있으면 RedirectAttributes를 사용
        redirectAttributes.addFlashAttribute("message", "문의가 성공적으로 접수되었습니다.");

        return "redirect:/mypage";
    }

    @GetMapping("inquiry/register")
    public ModelAndView getInquiryRegister(ModelAndView mv) {
        mv.setViewName("layout/my-page/nofab");
        mv.addObject("view", "content/my-page/inquiry-register2");
        return mv;
    }

    @GetMapping("inquiry/detail/{inquiryNo}")
    public ModelAndView getInquiryDetail(@AuthenticationPrincipal CustomUserDetail user, @PathVariable int inquiryNo, ModelAndView mv) {
        TblInquiryDTO tblInquiryDTO = new TblInquiryDTO();
        tblInquiryDTO.setInquiryUserNo(user.getId());
        tblInquiryDTO.setInquiryNo(inquiryNo);
        int replyStatus = userInquiryService.getReplyStatus(tblInquiryDTO);
        tblInquiryDTO.setInquiryReplyStatus(replyStatus);
        InquiryDetailDTO inquiryDetailDTO = userInquiryService.getInquiryDetail(tblInquiryDTO);
        System.out.println(inquiryDetailDTO);
        mv.setViewName("layout/my-page/nofab");
        mv.addObject("view", "content/my-page/inquiry-detail");
        mv.addObject("inquiryDetail", inquiryDetailDTO);
        mv.addObject("replyStatus", replyStatus);
        return mv;
    }
}
