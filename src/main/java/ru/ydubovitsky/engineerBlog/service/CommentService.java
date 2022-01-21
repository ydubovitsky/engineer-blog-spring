package ru.ydubovitsky.engineerBlog.service;

import org.springframework.stereotype.Service;
import ru.ydubovitsky.engineerBlog.repository.CommentRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}
