package com.studygram.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.studygram.utils.MultipartUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Component
@RequiredArgsConstructor
public class AmazonS3ResourceStorage {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3Client amazonS3Client;

    public String store(String fullPath, MultipartFile multipartFile) {
        try {
            amazonS3Client.putObject(new PutObjectRequest(bucket, fullPath, multipartFile.getInputStream(), null)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            return amazonS3Client.getUrl(bucket, fullPath).toString();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void delete(final String fileName) {
        amazonS3Client.deleteObject(bucket, fileName);
    }
}