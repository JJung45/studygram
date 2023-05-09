package com.studygram.service;

import com.studygram.domain.FileDetail;
import com.studygram.domain.Image;
import com.studygram.domain.Post;
import com.studygram.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ImageUploadService {

    private static final long MAX_FILE_SIZE = 10240; //10kb 제한
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private AmazonS3ResourceStorage amazonS3ResourceStorage;

    @Transactional
    public void createPostImage(Post post, MultipartFile mfile)
    {
        // 파일 사이즈 체크
        long fileSize = mfile.getSize();
        if (fileSize > MAX_FILE_SIZE) {
            throw new MaxUploadSizeExceededException(MAX_FILE_SIZE);
        }

        // 파일 저장
        FileDetail fileDetail = FileDetail.multipartOf(mfile);
        String imageUrl = amazonS3ResourceStorage.store(fileDetail.getPath(),mfile);
        String imageWithHttp = imageUrl.replace("https", "http"); //  TODO 추후 개선 필요. https 는 현재 엑박.

        Image newImage = new Image();
        newImage.setOriginalFilename(fileDetail.getPath());
        newImage.setStorePath(imageWithHttp);
        newImage.setCreatedDate(new Date());
        newImage.setPost(post);

        imageMapper.save(newImage);
    }
    public void deletePostImage(Post post, MultipartFile mfile)
    {
        FileDetail fileDetail = FileDetail.multipartOf(mfile); // mfile x -> file id

        // TODO
        // find file
        // find post by file
        // delete file

        amazonS3ResourceStorage.delete(fileDetail.getName());
    }

}
