package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.*;
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

@Controller
public class BettingController {
    private final BettingOrderService bettingOrderService;
    private final SubjectService subjectService;
    private final UserManagementService userManagementService;
    private final CommentService commentService;
    private final ReplyService replyService;
    private final BuyItemService buyItemService;

    @Autowired
    public BettingController(BettingOrderService bettingOrderService, SubjectService subjectService, UserManagementService userManagementService, CommentService commentService, ReplyService replyService, BuyItemService buyItemService) {
        this.bettingOrderService = bettingOrderService;
        this.subjectService = subjectService;
        this.userManagementService = userManagementService;
        this.commentService = commentService;
        this.replyService = replyService;
        this.buyItemService = buyItemService;
    }


    @GetMapping("/betting")
    public String getBettingPage(Model model) {
        long subjectNo = 1l;
        TblSubjectDTO subject= subjectService.getSubjectBySubjectNo(subjectNo);
        String userAuthority = userManagementService.getAuthorityBySubjectUserNo(91);

        String returnYRate =  String.valueOf((int)((float)subject.getSubjectTotalNoPoint()/subject.getSubjectTotalYesPoint()*100))+"%";
        String returnNRate = String.valueOf((int)((float)subject.getSubjectTotalYesPoint()/subject.getSubjectTotalNoPoint()*100))+"%";


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
        Long subjectNo =1l;
        return bettingOrderService.getBettingOrdersBySubjectNo(subjectNo);
    }

    @GetMapping(value = "/ranking",produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<RankingDTO> getRankings() {
        Long subjectNo =1l;
        return bettingOrderService.getRankingBySubjectNo(subjectNo);
    }

    @GetMapping(value = "/comment",produces = "application/json; cahrset=UTF-8")
    @ResponseBody
    public List<CommentReDTO> getComments(){
        Long subjectNo=1l;
        return commentService.getCommentBySubjectNo(subjectNo);
    }

    @PostMapping(value = "/comment", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public void addComment(@RequestBody TblCommentDTO commentDTO) {
        commentDTO.setCommentUserNo(UserSession.getUserId());
        commentService.insertComment(commentDTO);
    }


    @PostMapping(value = "/comment/{commentNo}/reply", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public void addReply(@PathVariable long commentNo, @RequestBody TblReplyDTO replyDTO) {
        replyService.insertReply(replyDTO);
    }


    @PostMapping(value = "/buyItem", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Map<String, String>> buyItem(@RequestBody TblBettingOrderDTO bettingOrderDTO) {
        try {
            //임시로 id를 90으로 설정
            bettingOrderDTO.setOrderUserNo(UserSession.getUserId());
            bettingOrderDTO.setOrderSubjectNo(1L);
            buyItemService.buyItemByDTO(bettingOrderDTO);
            Map<String, String> response = new HashMap<>();
            response.put("message", "구매 성공");
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
}