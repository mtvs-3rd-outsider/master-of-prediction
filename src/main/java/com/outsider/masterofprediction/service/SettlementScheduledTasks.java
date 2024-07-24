package com.outsider.masterofprediction.service;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SettlementScheduledTasks {
    private final SubjectService subjectService;

    public SettlementScheduledTasks(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
    @Scheduled(fixedRate = 300000) // 300000ms = 5분
    public void updateSettlementState() {
        subjectService.updateSubjectStatus();
    }
}
