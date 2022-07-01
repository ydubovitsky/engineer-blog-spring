package ru.ydubovitsky.engineerBlog.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ydubovitsky.engineerBlog.dto.CommentDto;
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
    public ResponseEntity<Comment> createCommentForPost(
            @PathVariable Integer postId,
            @RequestBody CommentDto commentDto
    ) {
        Comment comment = commentService.createComment(postId, commentDto);
        log.info(String.format("Comment for the post with id %s added", postId));
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/delete/{commentId}")
    public ResponseEntity<Long> deleteCommentForPostByCommentId(
            @PathVariable Long commentId
    ) {
        commentService.deleteCommentById(commentId);
        log.info(String.format("Comment with id %s had deleted", commentId));
        return ResponseEntity.ok(commentId);
    }

    @PutMapping("/comments/update/{commentId}")
    public ResponseEntity<Comment> updateCommentByCommentId(
            @PathVariable Long commentId,
            @RequestBody CommentDto commentDto
            ) {
        Comment updatedComment = commentService.updateComment(commentId, commentDto);

        return ResponseEntity.ok(updatedComment);
    }
}
