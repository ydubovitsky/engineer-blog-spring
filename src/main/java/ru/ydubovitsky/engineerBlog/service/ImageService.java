package ru.ydubovitsky.engineerBlog.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.engineerBlog.entity.Image;
import ru.ydubovitsky.engineerBlog.repository.ImageRepository;
import ru.ydubovitsky.engineerBlog.utils.CompressionUtils;

import java.io.IOException;
import java.util.zip.DataFormatException;

@Slf4j
@Service
@AllArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public Image saveImage(Image image) {
        Image savedImage = imageRepository.save(image);
        log.info(String.format("Image with id: %s - saved!", savedImage.getId()));
        return savedImage;
    }

    public Image getById(Integer id) {
       return imageRepository.getById(id);
    }


}
