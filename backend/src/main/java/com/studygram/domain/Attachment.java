package com.studygram.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attachment {
    Long id;
    String originFilename;
    String storeFilename;
    AttachmentType attachmentType;

    private Post post;

    public Attachment(Long id, String originFileName, String storePath, AttachmentType attachmentType) {
        this.id = id;
        this.originFilename = originFileName;
        this.storeFilename = storePath;
        this.attachmentType = attachmentType;
    }
}
