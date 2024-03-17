package com.example.core.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class UserImage {

    @Column(name = "image_url")
    private String imageUrl;

    @Builder
    public UserImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void updateImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
