package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.config.SecurityConfigUser;
import com.outsider.masterofprediction.dto.*;
import com.outsider.masterofprediction.service.BettingOrderService;
import com.outsider.masterofprediction.service.ProcessFileService;
import com.outsider.masterofprediction.service.UserInquiryService;
import com.outsider.masterofprediction.service.UserManagementService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.outsider.masterofprediction.dto.constatnt.IntConstants.ITEMS_PER_PAGE;

@RestController
@Controller
@RequestMapping("/mypage")
public class MyPageController {

    private final UserInquiryService userInquiryService;
    private final UserManagementService userManagementService;
    private final BettingOrderService bettingOrderService;
//    REST API
    private final ProcessFileService processFileService;

    public MyPageController(UserInquiryService userInquiryService, UserManagementService userManagementService, BettingOrderService bettingOrderService, ProcessFileService processFileService) {
        this.userInquiryService = userInquiryService;
        this.userManagementService = userManagementService;
        this.bettingOrderService = bettingOrderService;
        this.processFileService = processFileService;
    }

    @Value("${file.imgUrl}")
    private String imgUrl;
    @GetMapping("/api/purchase-history/{page}")
    public ModelAndView getPurchaseHistory(@PathVariable int page, @AuthenticationPrincipal CustomUserDetail user , ModelAndView mv) {
        Long userId =user.getId();

        mv.setViewName("layout/my-page/layout");
        mv.addObject("view", "content/my-page/purchase-history");
        int itemsPerPage = ITEMS_PER_PAGE;
        List<BettingOrderDTO> items = bettingOrderService.getBettingOrdersByUserId(new UserPaginationDTO(user.getId(),page,itemsPerPage));
        int totalPages = (int) Math.ceil((double) bettingOrderService.getOrderCountByUserId(user.getId()) / itemsPerPage);
        mv.addObject("items", items);
        mv.addObject("totalPages", totalPages);
        return mv;
    }
    @GetMapping("/api/comments/{page}")
    public ModelAndView getComment(@PathVariable int page,@AuthenticationPrincipal CustomUserDetail user, ModelAndView mv) {
        int itemsPerPage = ITEMS_PER_PAGE;
        int start = (page - 1) * itemsPerPage;
        Long userId = user.getId();
        List<CommentDTO> items = userManagementService.getCommentsByUserId(new UserPaginationDTO(user.getId(),start,itemsPerPage));
        int totalInquiries = userManagementService.getCommentCountByUserId(user.getId());
        int totalPages = (int) Math.ceil((double) totalInquiries / itemsPerPage);
        mv.setViewName("layout/my-page/layout");
        mv.addObject("view", "content/my-page/comments");
        mv.addObject("items", items);
        mv.addObject("totalPages", totalPages);
        return mv;
    }
    @GetMapping("/api/inquirys/{page}")
    public ModelAndView getInquirys(@PathVariable int page, @AuthenticationPrincipal CustomUserDetail user, ModelAndView mv) {
        int itemsPerPage = ITEMS_PER_PAGE;
        int start = (page - 1) * itemsPerPage;
        List<CommentDTO> items = userManagementService.getCommentsByUserId(new UserPaginationDTO(user.getId(),start,itemsPerPage));
        int totalInquiries = userManagementService.getCommentCountByUserId(user.getId());
        int totalPages = (int) Math.ceil((double) totalInquiries / itemsPerPage);
        mv.setViewName("layout/my-page/layout");
        mv.addObject("view", "content/my-page/comments");
        mv.addObject("items", items);
        mv.addObject("totalPages", totalPages);
        return mv;
    }
//페이지
    @GetMapping()
    public ModelAndView getMyPage(@AuthenticationPrincipal CustomUserDetail user,ModelAndView mv) {
        TblAttachmentDTO attachmentDTO = userManagementService.getAttachmentsByUserNo(user.getId());

        mv.setViewName("/layout/my-page/index");
        mv.addObject("view", "content/my-page/my-page");
        mv.addObject("name", "Dummy User"); // Dummy username
        mv.addObject("name",user.getUsername() );
        Path file = Paths.get(imgUrl).resolve(attachmentDTO.getAttachmentFileAddress());
        mv.addObject("attachmentAddress", file.toString());
//      현재 베팅 포인트
//        mv.addObject("positionValue",);
//      총 손익률
//        mv.addObject("profitLoss",userManagementService. );
//      총 거래 포인트
//        mv.addObject("volumeTraded",userManagementService. );
//      거래수
        mv.addObject("marketsTraded",bettingOrderService.getOrderCountByUserId(user.getId()));
        return mv;
    }
//탈퇴
    @GetMapping("/withdrawal")
    public ModelAndView getWithdrawal(ModelAndView mv) {
        mv.setViewName("/layout/my-page/withdrawal");
        mv.addObject("view", "content/my-page/withdrawal");
        return mv;
    }
    @PostMapping("/withdrawal")
    public ModelAndView postWithdrawal(ModelAndView mv) {
        mv.setViewName("/logout");
        return mv;
    }
//회원정보변경
    @GetMapping("/change-personal-information")
    public ModelAndView getChangePersonalInformation(ModelAndView mv ,@AuthenticationPrincipal CustomUserDetail user) {
        TblAttachmentDTO attachmentDTO = userManagementService.getAttachmentsByUserNo(user.getId());
        mv.setViewName("layout/my-page/layout");
        mv.addObject("title", "Home Page");
        Path file = Paths.get(imgUrl).resolve(attachmentDTO.getAttachmentFileAddress());
        mv.addObject("attachmentAddress", file.toString());
        mv.addObject("name", user.getUsername());
        mv.addObject("password", user.getPassword());
        mv.addObject("view", "content/my-page/change-personal-information");
        return mv;
    }

    @PostMapping("/change-personal-information")
    public String submitChangePersonalInformationForm( @ModelAttribute User requestUserDto,@RequestParam("profileImage") MultipartFile profileImage, RedirectAttributes redirectAttributes,@AuthenticationPrincipal CustomUserDetail user) {
        User userDTO = user.getUser();
        userDTO.setName(requestUserDto.getName());
        if (!requestUserDto.getPassword().equals(userDTO.getPassword())) {

            userDTO.setPassword(SecurityConfigUser.passwordEncoder().encode(requestUserDto.getPassword()));
        }
        userManagementService.updateUser(userDTO);


        TblAttachmentDTO tblAttachmentDTO = userManagementService.getAttachmentsByUserNo(user.getId());

        // 파일 처리
        if (!profileImage.isEmpty()) {

            if (tblAttachmentDTO.getAttachmentFileAddress() != null && !tblAttachmentDTO.getAttachmentFileAddress().isEmpty()) {
                // 파일 처리 로직 (이전 파일 삭제 , 현재 파일 저장)
                try {
                    processFileService.execute(tblAttachmentDTO, profileImage, userManagementService::updateUserImage);

                } catch (IOException e) {
                    e.printStackTrace();
                    redirectAttributes.addFlashAttribute("flashMessage1", "파일 업로드 중 오류가 발생했습니다.");
                    return "redirect:/mypage/change-personal-information";
                }
            }
        }

        redirectAttributes.addFlashAttribute("flashMessage1", "변경 사항이 성공적으로 저장되었습니다.");
        return "redirect:/mypage";
    }


}
