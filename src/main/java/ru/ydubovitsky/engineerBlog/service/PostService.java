package ru.ydubovitsky.engineerBlog.service;

import org.springframework.stereotype.Service;
import ru.ydubovitsky.engineerBlog.entity.Post;
import ru.ydubovitsky.engineerBlog.repository.PostRepository;

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
