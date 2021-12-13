package ru.ydubovitsky.egblogspring.service;

import org.springframework.stereotype.Service;
import ru.ydubovitsky.egblogspring.entity.Post;
import ru.ydubovitsky.egblogspring.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post addPost(Post post) {
        Post save = postRepository.save(post);
        return save;
    }
}
