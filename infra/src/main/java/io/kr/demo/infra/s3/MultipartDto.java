package io.kr.demo.infra.s3;

import lombok.Getter;

import java.io.InputStream;

@Getter
public class MultipartDto {

    private final String originalFileName;
    private final long fileSize;
    private final String contentType;
    private final InputStream inputStream;

    public MultipartDto(String originalFileName, long fileSize, String contentType, InputStream inputStream) {
        this.originalFileName = originalFileName;
        this.fileSize = fileSize;
        this.contentType = contentType;
        this.inputStream = inputStream;
    }
}
