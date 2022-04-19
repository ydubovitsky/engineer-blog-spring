package ru.ydubovitsky.engineerBlog.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ydubovitsky.engineerBlog.dto.PostDto;
import ru.ydubovitsky.engineerBlog.entity.Post;
import ru.ydubovitsky.engineerBlog.mapper.PostRequestMapper;
import ru.ydubovitsky.engineerBlog.payload.request.PostRequest;
import ru.ydubovitsky.engineerBlog.service.PostService;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Post>> getPostsByPage(
            @RequestParam(name = "page") Integer page
    ) {
        List<Post> pagingPosts = postService.getPostsPerPageWithSize(page);
        return ResponseEntity.ok(pagingPosts);
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
    public ResponseEntity<Post> addNewPost(
            @ModelAttribute PostRequest postRequest
            ) {
        PostDto postDto = PostRequestMapper.postRequestToPostDto(postRequest);
        Post savedPost = postService.addPost(postDto);
        return ResponseEntity.ok(savedPost);
    }


}