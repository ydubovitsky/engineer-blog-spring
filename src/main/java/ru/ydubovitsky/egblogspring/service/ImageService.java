package ru.ydubovitsky.egblogspring.service;

import org.springframework.stereotype.Service;
import ru.ydubovitsky.egblogspring.repository.ImageRepository;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
}
