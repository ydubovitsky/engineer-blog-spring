package ru.ydubovitsky.engineerBlog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ydubovitsky.engineerBlog.entity.Post;
import ru.ydubovitsky.engineerBlog.service.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('UPDATE')")
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        Post savedPost = postService.addPost(post);
        return ResponseEntity.ok(savedPost);
    }
}
