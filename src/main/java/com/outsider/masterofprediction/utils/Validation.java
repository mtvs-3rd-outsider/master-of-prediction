package com.outsider.masterofprediction.utils;


import com.outsider.masterofprediction.dto.TblSubjectDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

public class Validation {
    public static void subject(TblSubjectDTO tblSubjectDTO){
        if (tblSubjectDTO.getSubjectTitle().isBlank() ||
                tblSubjectDTO.getSubjectCategoryNo() == 0 ||
                tblSubjectDTO.getSubjectSettlementTimestamp() == null ||
                LocalDateTime.now().isAfter(tblSubjectDTO.getSubjectSettlementTimestamp().toLocalDateTime())){
            System.out.println("Exception Handler: " + tblSubjectDTO);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data provided");
        }
    }
}
