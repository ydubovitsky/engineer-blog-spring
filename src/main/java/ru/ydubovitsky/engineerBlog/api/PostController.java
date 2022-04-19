package ru.ydubovitsky.engineerBlog.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ydubovitsky.engineerBlog.dto.request.PostDto;
import ru.ydubovitsky.engineerBlog.entity.Post;
import ru.ydubovitsky.engineerBlog.service.PostService;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getPostsByPage(
            @RequestParam(name = "page") Integer page
    ) {
        List<Post> postsPerPageWithSize = postService.getPostsPerPageWithSize(page);
        return ResponseEntity.ok(postsPerPageWithSize);
    }

    @GetMapping(params = "id")
    public ResponseEntity<Post> getPostById(@RequestParam(name = "id") Integer id) {
        Post postById = postService.findPostById(id);
        return ResponseEntity.ok(postById);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getPostsCount() {
        return ResponseEntity.ok(postService.getPostsCount());
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
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


}