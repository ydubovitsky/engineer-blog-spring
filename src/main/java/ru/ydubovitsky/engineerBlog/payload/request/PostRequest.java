package ru.ydubovitsky.engineerBlog.payload.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter @Setter
public class PostRequest {

    String newPost;
    List<MultipartFile> files;

}
