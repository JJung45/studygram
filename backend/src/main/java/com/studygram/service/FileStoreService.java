package com.studygram.service;

import com.studygram.domain.Attachment;
import com.studygram.domain.AttachmentType;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;

public class FileStoreService {
    @Value("${file.dir}")
    private String fileDirPath;

    private String extractExt(String originalFilename) {
        int idx = originalFilename.lastIndexOf(".");
        return originalFilename.substring(idx);
    }

    private String createStoreFilename(String originalFilename) {
        String uuid = UUID.randomUUID().toString();
        String ext = extractExt(originalFilename);
        return uuid + ext;
    }

    public String createPath(String storeFilename, AttachmentType attachmentType) {
        String viaPath = (attachmentType == AttachmentType.IMAGE) ? "images/" : "generals/";
        return fileDirPath+viaPath+storeFilename;
    }

    public Attachment storeFile(MultipartFile multipartFile, AttachmentType attachmentType) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFilename = createStoreFilename(originalFilename);
        try {
            multipartFile.transferTo(new File(createPath(storeFilename, attachmentType)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Attachment.builder()
                .originFilename(originalFilename)
                .storeFilename(storeFilename)
                .attachmentType(attachmentType)
                .build();

    }

    public List<Attachment> storeFiles(List<MultipartFile> multipartFiles, AttachmentType attachmentType) throws IOException {
        List<Attachment> attachments = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                attachments.add(storeFile(multipartFile, attachmentType));
            }
        }

        return attachments;
    }

}
