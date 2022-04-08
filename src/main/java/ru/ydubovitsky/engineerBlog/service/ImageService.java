package ru.ydubovitsky.engineerBlog.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.engineerBlog.dto.request.ImageDto;
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

    public Image saveImage(Image image) throws IOException {
        byte[] compressionImgByte = CompressionUtils.compress(image.getByteImage());
        image.setByteImage(compressionImgByte);

        Image savedImage = imageRepository.save(image);
        return savedImage;
    }

    public Image getById(Integer id) throws IOException, DataFormatException {
        Image byId = imageRepository.getById(id);
        byId.setByteImage(CompressionUtils.decompress(byId.getByteImage()));

        return byId;
    }


}
