package ru.ydubovitsky.engineerBlog.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Stream;

@Slf4j
@Service
public class FileService {

    public String saveImageLocally(MultipartFile multipartFile) {
        try {
            String realType = getRealFileType(multipartFile);
            if (isImage(realType)) {
                String savedImage = saveMultipartFileLocally(multipartFile);
                log.info(String.format("Image with name %s saved", multipartFile.getOriginalFilename()));
                return savedImage;
            } else {
                throw new RuntimeException("Not an image!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getBytesFromLocalFile(String pathName) throws IOException {
        byte[] bytes = new FileInputStream(new File(pathName)).readAllBytes();
        return bytes;
    }

    private String getRealFileType(MultipartFile multipartFile) throws IOException {
        Tika tika = new Tika();
        return tika.detect(multipartFile.getBytes());
    }

    private boolean isImage(String fileType) {
        return Stream.of("RAW", "JPEG", "JPG", "TIFF", "BMP", "GIF", "PNG")
                .anyMatch(type -> fileType.contains(type.toLowerCase()));
    }

    private String saveMultipartFileLocally(MultipartFile multipartFile) {
        File file = new File(getUniqueFileName(multipartFile.getOriginalFilename()));
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();
            return file.getPath();
        } catch (IOException e) {
            throw new RuntimeException("File save error!");
        }
    }

    private String getUniqueFileName(String originName) {
        return UUID.randomUUID().toString() + originName;
    }

    private void createDirIfNotExists(String path) {
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }
    }

}
