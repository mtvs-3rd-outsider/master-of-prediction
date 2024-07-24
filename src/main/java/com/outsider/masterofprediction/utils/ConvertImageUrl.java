package com.outsider.masterofprediction.utils;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConvertImageUrl {

    public static <T extends AttachmentFileAddressable> void convert(List<T> items){
        for (T item : items){
            if (item.getAttachmentFileAddress() == null){
                item.setAttachmentFileAddress("logo2.png");
                System.out.println(item);
            }
            item.setAttachmentFileAddress(
                    FileUtil.checkFileOrigin(item.getAttachmentFileAddress()));
            System.out.println(item);
        }
    }

}
