package com.studygram.service;


import com.studygram.domain.Attachment;
import com.studygram.domain.AttachmentType;
import com.studygram.mapper.AttachmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
    FileStoreService fileStoreService;

    public List<Attachment> saveAttachments(Map<AttachmentType, List<MultipartFile>> multipartFileListMap) throws IOException{
        List<Attachment> imageFiles = null;
        List<Attachment> generalFiles = null;

        try {
            imageFiles = fileStoreService.storeFiles(multipartFileListMap.get(AttachmentType.IMAGE), AttachmentType.IMAGE);
            generalFiles = fileStoreService.storeFiles(multipartFileListMap.get(AttachmentType.GENERAL), AttachmentType.GENERAL);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Stream.of(imageFiles, generalFiles)
                .flatMap(f -> f.stream())
                .collect(Collectors.toList());
    }

    public Map<AttachmentType, List<MultipartFile>> getAttachmentTypeListMap(List<MultipartFile> imageFiles, List<MultipartFile> generalFiles) {
        Map<AttachmentType, List<MultipartFile>> attachments = new ConcurrentHashMap<>();
        attachments.put(AttachmentType.IMAGE, imageFiles);
        attachments.put(AttachmentType.GENERAL, generalFiles);
        return attachments;
    }

    public Map<AttachmentType, List<Attachment>> findAttachments() {
        List<Attachment> attachments = attachmentMapper.findAll();
        return attachments.stream()
                .collect(Collectors.groupingBy(Attachment::getAttachmentType));
    }
}
