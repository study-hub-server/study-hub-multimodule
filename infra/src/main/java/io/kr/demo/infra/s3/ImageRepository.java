package io.kr.demo.infra.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageRepository {

    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private static final String BASIC_PROFILE_IMAGE = "https://studyhub-s3.s3.ap-northeast-2.amazonaws.com/avatar_l%401x.png";

    public String saveImage(MultipartDto multipartDto) {
        if(multipartDto.getFileSize() >= 20 * 1024 * 1024) { throw new RuntimeException(); }

        String originalFilename = multipartDto.getOriginalFileName();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartDto.getFileSize());
        metadata.setContentType(multipartDto.getContentType());

        amazonS3.putObject(bucket, originalFilename, multipartDto.getInputStream(), metadata);
        return amazonS3.getUrl(bucket, originalFilename).toString();
    }

}
