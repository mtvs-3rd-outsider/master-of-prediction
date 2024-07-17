package com.outsider.masterofprediction.utils;


import com.outsider.masterofprediction.dto.TblAttachmentDTO;

public interface ProcessFileInterface {
    // 실행할 함수를 넣어주세요
    // myPageService::updateUserImg
    void execute(TblAttachmentDTO tblAttachmentDTO);
}
