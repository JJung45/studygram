package com.studygram.service;

import com.studygram.domain.Image;
import com.studygram.domain.Post;
import com.studygram.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageUploadService {

    @Value("${file.dir}")
    private String IMAGE_URL_PATH;

    @Autowired
    private ImageMapper imageMapper;

    public void createPostImage(Post post, MultipartFile file)
    {
        String originFileName = file.getOriginalFilename();
        String fileName = System.currentTimeMillis() + originFileName;
        String storePath = IMAGE_URL_PATH + fileName;

        try {
            File image = new File(storePath);
            file.transferTo(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image newImage = new Image();
        newImage.setOriginalFilename(originFileName);
        newImage.setStorePath(storePath);
        newImage.setPost(post);

        imageMapper.save(newImage);
    }

}
