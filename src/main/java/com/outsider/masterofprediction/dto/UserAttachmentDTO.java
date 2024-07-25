package com.outsider.masterofprediction.dto;

import com.outsider.masterofprediction.utils.AttachmentFileAddressable;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserAttachmentDTO implements AttachmentFileAddressable {

    private int userNo;
    private String userName;
    private int userPoint;

    private TblAttachmentDTO attachment;


    public TblAttachmentDTO getAttachment() {
        if(attachment == null) {
            attachment = new TblAttachmentDTO(0, "logo2.png", 0, 0, 0, 0, 0, 0, 0);
        }
        return attachment;
    }

    public void setAttachment(TblAttachmentDTO attachment) {
        this.attachment = attachment;
    }

    @Override
    public void setAttachmentFileAddress(String address) {
        attachment.setAttachmentFileAddress(address);
    }

    @Override
    public String getAttachmentFileAddress() {
        return attachment.getAttachmentFileAddress();
    }
}
