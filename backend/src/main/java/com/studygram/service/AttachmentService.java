package com.studygram.service;


import com.studygram.domain.Attachment;
import com.studygram.domain.AttachmentType;
import com.studygram.mapper.AttachmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    AttachmentMapper attachmentMapper;
    @Autowired
    private FileStoreService fileStoreService;

    @Nullable
    public List<Attachment> saveAttachments(Map<AttachmentType, File> image) throws IOException {

        List<Attachment> imageFiles;
        try {
            imageFiles = fileStoreService.storeFiles(image.get(AttachmentType.IMAGE), AttachmentType.IMAGE);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Stream.of(imageFiles)
                .flatMap(f -> f.stream())
                .collect(Collectors.toList());
    }

    public Map<AttachmentType, File> getAttachmentTypeListMap(File image) {
        Map<AttachmentType, File> attachments = new ConcurrentHashMap<>();
        attachments.put(AttachmentType.IMAGE, image);
        return attachments;
    }

    public Map<AttachmentType, List<Attachment>> findAttachments() {
        List<Attachment> attachments = attachmentMapper.findAll();
        return attachments.stream()
                .collect(Collectors.groupingBy(Attachment::getAttachmentType));
    }
}
