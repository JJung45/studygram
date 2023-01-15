package com.studygram.service;

import com.studygram.domain.Attachment;
import com.studygram.domain.AttachmentType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileStoreService {
    @Value("${file.dir}")
    private String fileDirPath;
    private String createStoreFilename(String originalFilename) {
        String uuid = UUID.randomUUID().toString();
        return uuid + "_" + originalFilename;
    }

    public String createPath(String storeFilename, AttachmentType attachmentType) {
        String viaPath = (attachmentType == AttachmentType.IMAGE) ? "images/" : "generals/";
        return fileDirPath+viaPath+storeFilename;
    }

    public Attachment storeFile(File file, AttachmentType attachmentType) throws IOException {

        String originalFilename = file.getName();
        String storeFilename = createStoreFilename(originalFilename);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Attachment.builder()
                .originFilename(originalFilename)
                .storeFilename(storeFilename)
                .attachmentType(attachmentType)
                .build();

    }

    public List<Attachment> storeFiles(File image, AttachmentType attachmentType) throws IOException {
        List<Attachment> attachments = new ArrayList<>();
        attachments.add(storeFile(image, attachmentType));
        return attachments;
    }

}
