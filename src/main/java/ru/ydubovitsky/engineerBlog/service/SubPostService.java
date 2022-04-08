package ru.ydubovitsky.engineerBlog.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.engineerBlog.entity.SubPost;
import ru.ydubovitsky.engineerBlog.repository.SubPostRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SubPostService {

    private final SubPostRepository subPostRepository;

    public SubPost saveSubPost(SubPost subPost) {
       return subPostRepository.save(subPost);
    }

    public List<SubPost> saveSubPostList(List<SubPost> subPosts) {
        return subPostRepository.saveAll(subPosts);
    }
}
