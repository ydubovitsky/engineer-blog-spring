package ru.ydubovitsky.engineerBlog.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ydubovitsky.engineerBlog.dto.PostDto;
import ru.ydubovitsky.engineerBlog.entity.Post;
import ru.ydubovitsky.engineerBlog.requests.response.PagingPostWithAllPostCount;
import ru.ydubovitsky.engineerBlog.service.PostService;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<PagingPostWithAllPostCount> getPostsByPage(
            @RequestParam(name = "page") Integer page
    ) {
        PagingPostWithAllPostCount pagingPostWithAllPostCount
                = new PagingPostWithAllPostCount(postService.getPostOfSize(page), postService.getAllPostsCount());
        return ResponseEntity.ok(pagingPostWithAllPostCount);
    }

    @PostMapping("/add")
    //    @PreAuthorize("hasAnyAuthority('UPDATE')")
    public ResponseEntity<Post> saveImage(
            @RequestPart("newPost") String post,
            @RequestPart("files") MultipartFile[] images
    ) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        PostDto postDto = mapper.readValue(post, PostDto.class);

        Post savedPost = postService.addPost(postDto, images);
        return ResponseEntity.ok(savedPost);
    }

//    private Image convertMultipartFileToImageEntity(MultipartFile file) {
//        Image imageEntity = new Image();
//        imageEntity.setName(file.getOriginalFilename());
//        try {
//            imageEntity.setByteImage(file.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return imageEntity;
//    }
}