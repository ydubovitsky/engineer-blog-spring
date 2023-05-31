package ru.ydubovitsky.engineerBlog.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ydubovitsky.engineerBlog.dto.CommentDTO;
import ru.ydubovitsky.engineerBlog.entity.Comment;
import ru.ydubovitsky.engineerBlog.service.CommentService;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/post/{postId}/comments/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCommentForPost(@PathVariable Integer postId, @RequestBody CommentDTO commentDto) {
        commentService.createComment(postId, commentDto);
    }

    @DeleteMapping("/comments/delete/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCommentForPostByCommentId(@PathVariable Long commentId) {
        commentService.deleteCommentById(commentId);
    }

    @PutMapping("/comments/update/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCommentByCommentId(@PathVariable Long commentId,  @RequestBody CommentDTO commentDto) {
        commentService.updateComment(commentId, commentDto);
    }
}
