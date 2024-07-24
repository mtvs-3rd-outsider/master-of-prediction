package com.outsider.masterofprediction.utils;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConvertImageUrl {

    public static <T extends AttachmentFileAddressable> void convert(List<T> items){
        for (T item : items){
            if (item.getAttachmentFileAddress() == null){
                item.setAttachmentFileAddress("logo2.png");
            }
            item.setAttachmentFileAddress(
                    FileUtil.checkFileOrigin(item.getAttachmentFileAddress()));
        }
    }

}
