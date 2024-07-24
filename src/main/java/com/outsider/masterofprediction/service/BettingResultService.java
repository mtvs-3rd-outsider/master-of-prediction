package com.outsider.masterofprediction.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BettingResultService {

    private final SubjectService subjectService;


    public BettingResultService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Transactional
    public void updateBettingResult(Long subjectNo, String result) {
        if (!subjectService.setSubjectFinishResult(subjectNo, result) || !subjectService.BetSettlement(subjectNo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data provided");
        }
    }


}
