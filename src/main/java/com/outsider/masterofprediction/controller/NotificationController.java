package com.outsider.masterofprediction.controller;

import com.outsider.masterofprediction.service.FCMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private FCMService fcmService;

    @PostMapping("/send")
    public String sendNotification(@RequestBody Map<String, String> payload) {
        String token = payload.get("token");
        String title = payload.get("title");
        String body = payload.get("body");

        fcmService.sendNotification(token, title, body);
        return "Notification sent successfully";
    }
}