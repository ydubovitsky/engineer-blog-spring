package ru.ydubovitsky.egblogspring.service;

import org.springframework.stereotype.Service;
import ru.ydubovitsky.egblogspring.repository.CommentRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}
