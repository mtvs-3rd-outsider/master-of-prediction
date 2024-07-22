package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.*;
import com.outsider.masterofprediction.mapper.AttachmentMapper;
import com.outsider.masterofprediction.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PostExchange;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

@Controller
public class BettingController {
    private final BettingOrderService bettingOrderService;
    private final SubjectService subjectService;
    private final UserManagementService userManagementService;
    private final CommentService commentService;
    private final ReplyService replyService;
    private final BuyItemService buyItemService;
    private final SellItemService sellItemService;
    private final AttachmentMapper attachmentMapper;

    private long subjectNo;


    @Autowired
    public BettingController(BettingOrderService bettingOrderService, SubjectService subjectService, UserManagementService userManagementService, CommentService commentService, ReplyService replyService, BuyItemService buyItemService, SellItemService sellItemService, AttachmentMapper attachmentMapper) {
        this.bettingOrderService = bettingOrderService;
        this.subjectService = subjectService;
        this.userManagementService = userManagementService;
        this.commentService = commentService;
        this.replyService = replyService;
        this.buyItemService = buyItemService;
        this.sellItemService = sellItemService;
        this.attachmentMapper = attachmentMapper;
    }


    @GetMapping("/betting")
    public String getBettingPage(Model model ,@RequestParam("subjectNo") long subjectNo) {
        this.subjectNo = subjectNo;
        TblSubjectDTO subject= subjectService.getSubjectBySubjectNo(subjectNo);
        String userAuthority = userManagementService.getAuthorityBySubjectNo(subjectNo);
        TblBettingOrderDTO dto = new TblBettingOrderDTO();
        dto.setOrderSubjectNo(subjectNo);
        dto.setOrderUserNo(UserSession.getUserId());

        long sumYPoint = userManagementService.getSumYPointByDTO(dto);
        long sumNPoint = userManagementService.getSumNPointByDTO(dto);
        String returnYRate =  String.valueOf((int)((float)subject.getSubjectTotalNoPoint()/subject.getSubjectTotalYesPoint()*100))+"% Chance";
        String returnNRate = String.valueOf((int)((float)subject.getSubjectTotalYesPoint()/subject.getSubjectTotalNoPoint()*100))+"% Chance";

        String attachment_file_address = attachmentMapper.getAttachmentsBySubjectNo(subjectNo).getAttachmentFileAddress();


        model.addAttribute("sumYPoint", sumYPoint);
        model.addAttribute("sumNPoint", sumNPoint);
        model.addAttribute("subjectImage", attachment_file_address);
        model.addAttribute("loggedInUserId", UserSession.getUserId());
        model.addAttribute("returnYRate", returnYRate);
        model.addAttribute("returnNRate", returnNRate);
        model.addAttribute("subject", subject);
        model.addAttribute("userAuthority", userAuthority);
        return "/content/betting-page/betting-page";
    }

    /**
     * 활동내역조회 컨트롤러
     * @return
     */
    @GetMapping(value = "/active",produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<ActiveDTO> getActives() {
        return bettingOrderService.getBettingOrdersBySubjectNo(subjectNo);
    }

    @GetMapping(value = "/ranking",produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<RankingDTO> getRankings() {
        return bettingOrderService.getRankingBySubjectNo(subjectNo);
    }

    @GetMapping(value = "/comment",produces = "application/json; cahrset=UTF-8")
    @ResponseBody
    public List<CommentReDTO> getComments(){
        return commentService.getCommentBySubjectNo(subjectNo);
    }

    @PostMapping(value = "/comment", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public void addComment(@RequestBody TblCommentDTO commentDTO) {
        System.out.println(subjectNo);

        commentDTO.setCommentSubjectNo(subjectNo);
        commentDTO.setCommentUserNo(UserSession.getUserId());
        commentService.insertComment(commentDTO);
    }


    @PostMapping(value = "/comment/{commentNo}/reply", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public void addReply(@PathVariable long commentNo, @RequestBody TblReplyDTO replyDTO) {
        replyService.insertReply(replyDTO);
    }

    @PostMapping(value = "/sellItem", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Map<String, String>> sellItem(@RequestBody TblBettingOrderDTO bettingOrderDTO) {
        try {
            // 임시로 id를 90으로 설정
            bettingOrderDTO.setOrderUserNo(UserSession.getUserId());
            bettingOrderDTO.setOrderSubjectNo(subjectNo);
            sellItemService.sellItemByDTO(bettingOrderDTO);
            Map<String, String> response = new HashMap<>();
            response.put("message", "판매 성공");
            response.put("redirectUrl", "/betting");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", errorMessage);
            responseBody.put("redirectUrl", "/betting");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(responseBody);
        }
    }




    @PostMapping(value = "/buyItem", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Map<String, String>> buyItem(@RequestBody TblBettingOrderDTO bettingOrderDTO) {
        try {
            //임시로 id를 90으로 설정
//            bettingOrderDTO.setOrderUserNo(UserSession.getUserId());
            bettingOrderDTO.setOrderUserNo(UserSession.getUserId());
            bettingOrderDTO.setOrderSubjectNo(subjectNo);
            buyItemService.buyItemByDTO(bettingOrderDTO);
            Map<String, String> response = new HashMap<>();
            response.put("message", "구매 성공");
            response.put("redirectUrl", "/betting");
            System.out.println("구매 성공");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("구매 실패");
            String errorMessage = e.getMessage();
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", errorMessage);
            responseBody.put("redirectUrl", "/betting");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(responseBody);
        }
    }
}