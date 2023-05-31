package ru.ydubovitsky.engineerBlog.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ydubovitsky.engineerBlog.entity.Post;
import ru.ydubovitsky.engineerBlog.facade.PostFacade;
import ru.ydubovitsky.engineerBlog.dto.PostAddRequestDTO;
import ru.ydubovitsky.engineerBlog.dto.PostUpdateRequestDTO;
import ru.ydubovitsky.engineerBlog.dto.PostResponseDTO;
import ru.ydubovitsky.engineerBlog.service.PostService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getPostsByPage(
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size
    ) {
        Map<String, Object> postsPerPageWithSize = postService.getPostsPerPageWithSize(page, size);
        return ResponseEntity.ok(postsPerPageWithSize);
    }

    @GetMapping(params = "id")
    public ResponseEntity<PostResponseDTO> getPostById(@RequestParam(name = "id") Integer id) {
        Post postById = postService.getPostById(id);

        //! Если клиент запрашивает пост, то нам нужно увеличить и число просмотров!
        postService.increasePostView(postById);
        PostResponseDTO response = PostFacade.postToPostResponseDTO(postById);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getPostsCount() {
        return ResponseEntity.ok(postService.getPostsCount());
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewPost(@RequestBody PostAddRequestDTO postRequest) {
        postService.addPost(postRequest);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@RequestBody PostUpdateRequestDTO postRequest) {
        postService.updatePost(postRequest);
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable Integer id) {
        postService.deletePostById(id);
    }

    @GetMapping(value = "/search", params = "title")
    public ResponseEntity<List<PostResponseDTO>> findPostsByTitle(@RequestParam(name = "title") String title) {
        List<PostResponseDTO> response = postService.searchPostsByTitle(title)
                .stream()
                .map(post -> PostFacade.postToPostResponseDTO(post))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
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