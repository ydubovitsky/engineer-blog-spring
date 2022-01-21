package ru.ydubovitsky.engineerBlog.service;

import org.springframework.stereotype.Service;
import ru.ydubovitsky.engineerBlog.repository.ImageRepository;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
}
