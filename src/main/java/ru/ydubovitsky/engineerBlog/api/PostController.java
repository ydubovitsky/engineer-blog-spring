package ru.ydubovitsky.engineerBlog.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ydubovitsky.engineerBlog.dto.PostDto;
import ru.ydubovitsky.engineerBlog.entity.Post;
import ru.ydubovitsky.engineerBlog.mapper.PostRequestMapper;
import ru.ydubovitsky.engineerBlog.payload.request.PostRequest;
import ru.ydubovitsky.engineerBlog.service.PostService;

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
        Post postById = postService.getPostById(id);

        //! Если клиент запрашивает пост, то нам нужно увеличить и число просмотров!
        postService.increasePostView(postById);
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

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updatePost(
            @ModelAttribute PostRequest postRequest
    ) {
        PostDto postDto = PostRequestMapper.postRequestToPostDto(postRequest);
        postService.updatePost(postDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deletePost(
            @PathVariable Integer id
    ) {
        postService.deletePostById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "/search", params = "title")
    public ResponseEntity<List<Post>> findPostsByTitle(@RequestParam(name = "title") String title) {
        List<Post> posts = postService.searchPostsByTitle(title);
        return ResponseEntity.ok(posts);
    }

    @GetMapping(value = "/categories")
    public ResponseEntity<List<String>> getCategoryList() {
        List<String> categories = postService.getCategoriesListOfAllPosts();
        return ResponseEntity.ok(categories);
    }

    //!FIXME Не работает! Status 500
    @PutMapping("/views/{id}")
    public ResponseEntity<?> increasePostView(@PathVariable(value = "id") Integer id) {
        postService.increasePostView(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}