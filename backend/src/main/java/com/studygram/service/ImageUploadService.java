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

    public static final long MAX_FILE_SIZE = 10240; //10kb 제한
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
//        newImage.setPost(post);

        int imageIdx = imageMapper.save(newImage);
        newImage.setIdx(imageIdx);
        imageMapper.savePostImageRel(imageIdx, post.getIdx());

    }

    // db에서는 자동으로 지워지구 aws 에서만 지우기
    public void deletePostImage(Post post)
    {
        Image postImage = findByPostIdx(post.getIdx());
        deleteImage(postImage);

        int imageIdx = postImage.getIdx();
        imageMapper.deletePostImageRel(imageIdx, post.getIdx());

        //aws 저장소에서 지우기
        String originalFileName = postImage.getOriginalFilename();
        amazonS3ResourceStorage.delete(originalFileName);
    }

    private Image findByPostIdx(int postIdx)
    {
        return imageMapper.findByPostIdx(postIdx);
    }

    private void deleteImage(Image image)
    {
        imageMapper.delete(image);
    }

}
