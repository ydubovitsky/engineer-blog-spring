package ru.ydubovitsky.engineerBlog.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.engineerBlog.dto.CommentDto;
import ru.ydubovitsky.engineerBlog.entity.Comment;
import ru.ydubovitsky.engineerBlog.facade.CommentFacade;
import ru.ydubovitsky.engineerBlog.repository.CommentRepository;
import ru.ydubovitsky.engineerBlog.repository.PostRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public Comment createComment(Integer postId, CommentDto commentDto) {
        Comment comment = CommentFacade.commentDtoToComment(commentDto);
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new NullPointerException("Not found Post with id = " + postId));
    }

    public void deleteCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public Comment updateComment(Long commentId, CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Not found comment with id = " + commentId));

        comment.setMessage(commentDto.getMessage());
        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());
        comment.setWebsite(commentDto.getWebsite());

        return commentRepository.save(comment);
    }
}
