package ru.ydubovitsky.engineerBlog.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ydubovitsky.engineerBlog.service.FileService;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/file")
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/{pathName}")
    public ResponseEntity<?> getFileByPathName(@PathVariable(name = "pathName") String pathName) throws IOException {
        return ResponseEntity.ok(fileService.getBytesFromLocalFile(pathName));
    }

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile multipartFile) {
        String pathName = fileService.saveImageLocally(multipartFile);
        return ResponseEntity.ok(pathName);
    }
}
