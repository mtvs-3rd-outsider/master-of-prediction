package com.outsider.masterofprediction.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FileUtil {



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
}
