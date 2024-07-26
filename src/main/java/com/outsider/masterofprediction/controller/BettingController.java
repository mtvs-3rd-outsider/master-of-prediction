package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.dto.*;
import com.outsider.masterofprediction.mapper.AttachmentMapper;
import com.outsider.masterofprediction.mapper.SubjectMapper;
import com.outsider.masterofprediction.service.*;
import com.outsider.masterofprediction.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;


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
    private final SubjectMapper subjectMapper;



    @Autowired
    public BettingController(BettingOrderService bettingOrderService, SubjectService subjectService, UserManagementService userManagementService, CommentService commentService, ReplyService replyService, BuyItemService buyItemService, SellItemService sellItemService, AttachmentMapper attachmentMapper, SubjectMapper subjectMapper) {
        this.bettingOrderService = bettingOrderService;
        this.subjectService = subjectService;
        this.userManagementService = userManagementService;
        this.commentService = commentService;
        this.replyService = replyService;
        this.buyItemService = buyItemService;
        this.sellItemService = sellItemService;
        this.attachmentMapper = attachmentMapper;
        this.subjectMapper = subjectMapper;
    }

    @GetMapping("/betting/{subjectNo}")
    public ModelAndView getBettingPage(ModelAndView mv, @PathVariable("subjectNo") long subjectNo) {
        TblSubjectDTO subject = subjectService.getSubjectBySubjectNo(subjectNo);
        subject.setSubjectNo(subjectNo);
        String userAuthority = userManagementService.getAuthorityBySubjectNo(subjectNo);
        TblBettingOrderDTO dto = new TblBettingOrderDTO();
        dto.setOrderSubjectNo(subjectNo);
        dto.setOrderUserNo(UserSession.getUserId());

        long sumYPoint = userManagementService.getSumYPointByDTO(dto);
        long sumNPoint = userManagementService.getSumNPointByDTO(dto);
        String returnYRate = "0% Chance";
        String returnNRate = "0% Chance";
        if ((subject.getSubjectTotalNoPoint() + subject.getSubjectTotalYesPoint()) != 0) {
            returnYRate = (int) (subject.getSubjectTotalYesPoint() / (float) (subject.getSubjectTotalNoPoint() + subject.getSubjectTotalYesPoint()) * 100) + "% Chance";
            returnNRate = (int) (subject.getSubjectTotalNoPoint() / (float) (subject.getSubjectTotalNoPoint() + subject.getSubjectTotalYesPoint()) * 100) + "% Chance";
        }

        String attachmentFileAddress = FileUtil.checkFileOrigin(attachmentMapper.getAttachmentsBySubjectNo(subjectNo).getAttachmentFileAddress());
        subject.setSubjectRegisterUserNo(subjectMapper.getSubjectRegistUserNoBySubjectNo(subject.getSubjectNo()));

        mv.setViewName("layout/index");
//        mv.setViewName("content/betting-page/betting-page");
        mv.addObject("sumYPoint", sumYPoint);
        mv.addObject("sumNPoint", sumNPoint);
        mv.addObject("subjectImage", attachmentFileAddress);
        mv.addObject("loggedInUserId", UserSession.getUserId());
        mv.addObject("returnYRate", returnYRate);
        mv.addObject("returnNRate", returnNRate);
        mv.addObject("subject", subject);
        mv.addObject("userAuthority", userAuthority);

        Timestamp settleTime = subject.getSubjectSettlementTimestamp();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        mv.addObject("isAccountBtnOn", UserSession.getUserId().equals(subject.getSubjectRegisterUserNo()) && now.after(settleTime));
        mv.addObject("view", "content/betting-page");
        return mv;
    }

    @PostMapping("/accountResult")
    public String handleAccountResult(@RequestBody Map<String, String> payload, RedirectAttributes redirectAttributes) {
        String result = payload.get("result");
        long subNo =Long.parseLong(payload.get("subNo"));
        subjectService.BettingSettlement(subNo, result);
//        subjectService.setSubjectFinishResult(subNo,result);
//        subjectService.BetSettlement(subNo);
        redirectAttributes.addFlashAttribute("hideButton", true);
        System.out.println("!!!!!!!!!");
        return "redirect:/https://master-of-prediction.shop:8081/betting/" + subNo;
    }

    /**
     * 활동내역조회 컨트롤러
     * @return
     */
    @GetMapping(value = "/active/{subjectNo}",produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<ActiveDTO> getActives( @PathVariable("subjectNo") long subjectNo) {
        return bettingOrderService.getBettingOrdersBySubjectNo(subjectNo);
    }

    @GetMapping(value = "/ranking/{subjectNo}",produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<RankingDTO> getRankings(@PathVariable("subjectNo") long subjectNo) {

        return bettingOrderService.getRankingBySubjectNo(subjectNo);
    }

    @GetMapping(value = "/comment/{subjectNo}",produces = "application/json; cahrset=UTF-8")
    @ResponseBody
    public List<CommentReDTO> getComments(@PathVariable("subjectNo") long subjectNo){
        return commentService.getCommentBySubjectNo(subjectNo);
    }



    @PostMapping(value = "/comment/{subjectNo}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public void addComment(@RequestBody TblCommentDTO commentDTO,@PathVariable("subjectNo") long subjectNo) {

        commentDTO.setCommentSubjectNo(subjectNo);
        commentDTO.setCommentUserNo(UserSession.getUserId());
        commentService.insertComment(commentDTO);
    }


    @PostMapping(value = "/comment/{commentNo}/reply/{subjectNo}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public void addReply(@PathVariable long commentNo, @RequestBody TblReplyDTO replyDTO) {
        replyService.insertReply(replyDTO);
    }

    @PostMapping(value = "/sellItem/{subjectNo}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Map<String, String>> sellItem(@RequestBody TblBettingOrderDTO bettingOrderDTO ,@PathVariable("subjectNo") long subjectNo) {
        try {
            // 임시로 id를 90으로 설정
            TblSubjectDTO subjectDTO =subjectService.getSubjectBySubjectNo(subjectNo);
            if (LocalDateTime.now().isAfter(subjectDTO.getSubjectSettlementTimestamp().toLocalDateTime())){
                String errorMessage ="이미 끝난 게임입니다.";
                Map<String, String> responseBody = new HashMap<>();
                responseBody.put("message", errorMessage);
                responseBody.put("redirectUrl", "/betting/" + bettingOrderDTO.getOrderSubjectNo());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(responseBody);
            }else {
                bettingOrderDTO.setOrderUserNo(UserSession.getUserId());
                bettingOrderDTO.setOrderSubjectNo(subjectNo);
                sellItemService.sellItemByDTO(bettingOrderDTO);
                Map<String, String> response = new HashMap<>();
                response.put("message", "판매 성공");
                response.put("redirectUrl", "/betting/" + bettingOrderDTO.getOrderSubjectNo());
                return ResponseEntity.ok(response);
            }

        } catch (Exception e) {
            String errorMessage = e.getMessage();
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", errorMessage);
            responseBody.put("redirectUrl", "/betting/" + bettingOrderDTO.getOrderSubjectNo());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(responseBody);
        }
    }




    @PostMapping(value = "/buyItem/{subjectNo}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Map<String, String>> buyItem(@RequestBody TblBettingOrderDTO bettingOrderDTO ,@PathVariable("subjectNo") long subjectNo) {
        try {
            //임시로 id를 90으로 설정
//            bettingOrderDTO.setOrderUserNo(UserSession.getUserId());
            TblSubjectDTO subjectDTO =subjectService.getSubjectBySubjectNo(subjectNo);
            if (LocalDateTime.now().isAfter(subjectDTO.getSubjectSettlementTimestamp().toLocalDateTime())){
                String errorMessage ="이미 끝난 게임입니다.";
                Map<String, String> responseBody = new HashMap<>();
                responseBody.put("message", errorMessage);
                responseBody.put("redirectUrl", "/betting/" + bettingOrderDTO.getOrderSubjectNo());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(responseBody);
            }else{
                bettingOrderDTO.setOrderUserNo(UserSession.getUserId());
                bettingOrderDTO.setOrderSubjectNo(subjectNo);
                buyItemService.buyItemByDTO(bettingOrderDTO);
                Map<String, String> response = new HashMap<>();
                response.put("message", "구매 성공");
                response.put("redirectUrl","/betting/" + bettingOrderDTO.getOrderSubjectNo());
                return ResponseEntity.ok(response);
            }

        } catch (Exception e) {
            String errorMessage = e.getMessage();
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", errorMessage);
            responseBody.put("redirectUrl", "/betting/" + bettingOrderDTO.getOrderSubjectNo());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(responseBody);
        }
    }


    @PostMapping(value = "/graph/{subjectNo}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<GraphDTO> getGraph(@RequestBody GraphDTO graphDTO ,@PathVariable("subjectNo") long subjectNo) {
        return bettingOrderService.getGraphByDTO(graphDTO);
    }
}