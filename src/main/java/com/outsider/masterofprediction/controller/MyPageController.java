package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.config.SecurityConfigUser;
import com.outsider.masterofprediction.dto.*;
import com.outsider.masterofprediction.service.*;
import com.outsider.masterofprediction.utils.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.outsider.masterofprediction.dto.constatnt.IntConstants.ITEMS_PER_PAGE;

@RestController
@Controller
@RequestMapping("/mypage")
public class MyPageController {

    private final UserManagementService userManagementService;
    private final BettingOrderService bettingOrderService;
//    REST API
    private final ProcessFileService processFileService;
    private final UserInquiryService userInquiryService;
    private final TierService tierService;
    public MyPageController(UserManagementService userManagementService, BettingOrderService bettingOrderService, ProcessFileService processFileService, UserInquiryService userInquiryService, TierService tierService) {
        this.userManagementService = userManagementService;
        this.bettingOrderService = bettingOrderService;
        this.processFileService = processFileService;
        this.userInquiryService = userInquiryService;
        this.tierService = tierService;
    }
    @Value("${file.tierImgUrl}")
    private String tierImgUrl;

    @Value("${file.imgUrl}")
    private String imgUrl ;
    @GetMapping("api/purchase-history/{page}")
    public ResponseEntity<Map<String, Object>> getPurchaseHistory(@PathVariable int page, @AuthenticationPrincipal CustomUserDetail user) {
        int itemsPerPage = ITEMS_PER_PAGE;
        int start = (page - 1) * itemsPerPage;
        List<BettingOrderDTO> items = bettingOrderService.getBettingOrdersByUserId(new UserPaginationDTO(user.getId(), start, itemsPerPage));
        int totalItems = bettingOrderService.getOrderCountByUserId(user.getId());
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

        Map<String, Object> response = new HashMap<>();
        response.put("items", items);
        response.put("totalPages", totalPages);
        response.put("currentPage", page);
        response.put("itemsPerPage", itemsPerPage);
        response.put("totalItems", totalItems);

        return ResponseEntity.ok(response);
    }

    @GetMapping("api/comments/{page}")
    public ResponseEntity<Map<String, Object>> getComments(@PathVariable int page, @AuthenticationPrincipal CustomUserDetail user) {
        int itemsPerPage = ITEMS_PER_PAGE;
        int start = (page - 1) * itemsPerPage;
        Long userId = user.getId();
        List<CommentDTO> items = userManagementService.getCommentsByUserId(new UserPaginationDTO(userId, start, itemsPerPage));
        int totalComments = userManagementService.getCommentCountByUserId(userId);
        int totalPages = (int) Math.ceil((double) totalComments / itemsPerPage);

        Map<String, Object> response = new HashMap<>();
        response.put("items", items);
        response.put("totalPages", totalPages);
        response.put("currentPage", page);
        response.put("itemsPerPage", itemsPerPage);
        response.put("totalItems", totalComments);

        return ResponseEntity.ok(response);
    }

    @GetMapping("api/inquirys/{page}")
    public ResponseEntity<Map<String, Object>> getInquirys(@PathVariable int page, @AuthenticationPrincipal CustomUserDetail user) {
        int itemsPerPage = ITEMS_PER_PAGE;
        int start = (page - 1) * itemsPerPage;
        List<TblInquiryDTO> items = userInquiryService.getInquiriesByUserId(new UserPaginationDTO(user.getId(), start, itemsPerPage));
        int totalInquiries = userInquiryService.getTotalInquiries(user.getId());
        int totalPages = (int) Math.ceil((double) totalInquiries / itemsPerPage);

        Map<String, Object> response = new HashMap<>();
        response.put("items", items);
        response.put("totalPages", totalPages);
        response.put("currentPage", page);
        response.put("itemsPerPage", itemsPerPage);
        response.put("totalItems", totalInquiries);
        return ResponseEntity.ok(response);
    }
//페이지
    @GetMapping(value = {"", "{userId}"})
    public ModelAndView getMyPage( @PathVariable(required = false) Long  userId, @AuthenticationPrincipal CustomUserDetail user,ModelAndView mv) {
        if (userId ==null) {
            return new ModelAndView("redirect:/mypage/" + user.getId());
        }
        boolean isMine =  userManagementService.isUserSessionValid(userId,user.getId());
        mv.addObject("isMine",isMine);
        TblAttachmentDTO attachmentDTO = userManagementService.getAttachmentsByUserNo(user.getId());

        mv.setViewName("layout/my-page/index");
        mv.addObject("view", "content/my-page/my-page");
        mv.addObject("name",user.getUsername() );
        String attachmentAddress = attachmentDTO.getAttachmentFileAddress();
        attachmentAddress=FileUtil.checkFileOrigin(attachmentAddress);
        mv.addObject("attachmentAddress", attachmentAddress);
        String tierImgUrl = this.tierImgUrl +'/'+tierService.getImgById(user.getTierNo());
        TblTierDTO tblTierDTO = tierService.findByTierNo(user.getTierNo());
        mv.addObject("tierImgUrl", tierImgUrl);
        mv.addObject("tierName", tblTierDTO.getTierContent());
        mv.addObject("userJoinDate",user.getJoinDate(String.class));
//      현재 포지션 가치
        mv.addObject("positionValue",bettingOrderService.getTotalPositionValueByUserId(user.getId()).toString() +" P");
//      한달 손익률
        mv.addObject("monthProfit",bettingOrderService.getMonthTotalProfitRateByUserId(user.getId()).toString() +" %");
//      한달 거래 포인트
        mv.addObject("volumeTraded",bettingOrderService.getMonthTotalPointsByUser(user.getId()).toString() +" P");
//      거래수
        mv.addObject("marketsTraded",bettingOrderService.getOrderCountByUserId(user.getId()));
        
        return mv;
    }
//탈퇴
    @GetMapping("/withdrawal")
    public ModelAndView getWithdrawal(ModelAndView mv , @AuthenticationPrincipal CustomUserDetail user) {
        mv.setViewName("layout/my-page/withdrawal");
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
        mv.setViewName("layout/my-page/nofab");
        String attachmentAddress = attachmentDTO.getAttachmentFileAddress();

        attachmentAddress=FileUtil.checkFileOrigin(attachmentAddress);
        mv.addObject("attachmentAddress", attachmentAddress);
        mv.addObject("name", user.getUsername());
        mv.addObject("password", user.getPassword());
        mv.addObject("view", "content/my-page/change-personal-information");
        

        return mv;
    }

    @PostMapping("/change-personal-information")
    public RedirectView  submitChangePersonalInformationForm( @ModelAttribute User requestUserDto,@RequestParam("profileImage") MultipartFile profileImage, RedirectAttributes redirectAttributes,@AuthenticationPrincipal CustomUserDetail user) {
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
                   return new RedirectView("/mypage/change-personal-information");
                }
            }
        }

        return new RedirectView("/mypage");
    }


}
