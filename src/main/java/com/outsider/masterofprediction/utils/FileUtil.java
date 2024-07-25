package com.outsider.masterofprediction.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class FileUtil {


    private static String imgUrl;

    @Value("${file.imgUrl}")
    public void setImgUrl(String imgUrl) {
        FileUtil.imgUrl = imgUrl;
    }

    public static String generateUniqueFileName(String originalFilename) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timestamp = sdf.format(new Date());
        String uuid = UUID.randomUUID().toString();
        String extension = "";

        int dotIndex = originalFilename.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < originalFilename.length() - 1) {
            extension = originalFilename.substring(dotIndex);
        }

        return timestamp + "-" + uuid + extension;
    }
    public static boolean compareFiles(File file1, File file2) throws IOException, NoSuchAlgorithmException {
        String hash1 = getFileHash(file1);
        String hash2 = getFileHash(file2);
        return hash1.equals(hash2);
    }

    public static String getFileHash(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] byteArray = new byte[1024];
            int bytesCount = 0;
            while ((bytesCount = fis.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }
        }
        byte[] bytes = digest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    public static String checkFileOrigin(String path) {
        if (path == null)
            return imgUrl +"/"+ "logo2"; ;
        if (!path.startsWith("https://")) {
            // Resolve the address with imgUrl
            path = imgUrl +"/"+ path;
        }
        return path;
    }

//    public static String combinePaths(String path1, String path2) {
//        // 운영 체제에 맞는 경로 구분자를 사용하여 경로를 병합합니다.
//        String separator = File.separator;
//
//        // 끝에 구분자가 없는 첫 번째 경로와 시작에 구분자가 없는 두 번째 경로를 병합합니다.
//        if (!path1.endsWith(separator)) {
//            path1 += separator;
//        }
//        if (path2.startsWith(separator)) {
//            path2 = path2.substring(1);
//        }
//
//        return path1 + path2;
//    }
}
