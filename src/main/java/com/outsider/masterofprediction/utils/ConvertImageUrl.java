package com.outsider.masterofprediction.utils;

import com.outsider.masterofprediction.dto.TblAttachmentDTO;
import com.outsider.masterofprediction.dto.UserAttachmentDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConvertImageUrl {

    public static <T extends AttachmentFileAddressable> void convert(List<T> items){
        for (T item : items){
            if (item instanceof UserAttachmentDTO){
                ((UserAttachmentDTO) item).setAttachment(new TblAttachmentDTO());
            }
            if (item.getAttachmentFileAddress() == null){
                item.setAttachmentFileAddress("logo2.png");
            }
            item.setAttachmentFileAddress(
                    FileUtil.checkFileOrigin(item.getAttachmentFileAddress()));
        }
    }

}
