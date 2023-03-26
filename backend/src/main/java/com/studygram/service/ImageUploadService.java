package com.studygram.service;

import com.studygram.domain.FileDetail;
import com.studygram.domain.Image;
import com.studygram.domain.Post;
import com.studygram.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ImageUploadService {

    @Autowired
    private ImageMapper imageMapper;
    private final AmazonS3ResourceStorage amazonS3ResourceStorage;

    public void createPostImage(Post post, MultipartFile mfile)
    {
        FileDetail fileDetail = FileDetail.multipartOf(mfile);
        String imageUrl = amazonS3ResourceStorage.store(fileDetail.getPath(),mfile);

        Image newImage = new Image();
        newImage.setOriginalFilename(fileDetail.getPath());
        newImage.setStorePath(imageUrl);
        newImage.setCreatedDate(new Date());
        newImage.setPost(post);

        imageMapper.save(newImage);
    }

}
