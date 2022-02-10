package ru.ydubovitsky.engineerBlog.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ImageUtils {

    public static Image convertMultipartFileToImageEntity(MultipartFile file) {
        Image imageEntity = new Image();
        imageEntity.setName(file.getOriginalFilename());
        try {
            imageEntity.setByteImage(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageEntity;
    }
}

@Getter
@Setter
class Image {
    private String name;
    byte[] byteImage;
}
