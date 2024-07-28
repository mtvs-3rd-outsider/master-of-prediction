package com.outsider.masterofprediction.controller;


import com.outsider.masterofprediction.service.FCMService;
import com.outsider.masterofprediction.service.FCMTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@Controller
public class FCMTokenController {
    private FCMTokenService tokenService;
    private FCMService fcmService;
    public FCMTokenController(FCMTokenService tokenService, FCMService fcmService) {
        this.tokenService = tokenService;
        this.fcmService = fcmService;
    }

    @PostMapping("/FCMToken")
    public ResponseEntity<Map<String, String>> registerToken(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String userId = request.get("userId");
        // Redis에 토큰과 이메일을 저장하는 로직
        System.out.println(token);
        System.out.println(userId);
        tokenService.saveToken(userId, token);
        Set<String> tokens=tokenService.getTokens(userId);
        tokens.forEach(token2 ->fcmService.sendNotificationToUser(userId, token2, "알림 설정 완료", "로그인시에만 유지 됩니다.") );
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }


}
