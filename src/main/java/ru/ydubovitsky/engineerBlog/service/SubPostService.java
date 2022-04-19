package ru.ydubovitsky.engineerBlog.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.engineerBlog.dto.SubPostDto;
import ru.ydubovitsky.engineerBlog.entity.Image;
import ru.ydubovitsky.engineerBlog.entity.SubPost;
import ru.ydubovitsky.engineerBlog.repository.SubPostRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
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

    //TODO Сделать рефакторинг
    public List<SubPost> updateSubPostList(List<SubPost> oldSubPosts, List<SubPostDto> newSubPosts) {
        List<SubPost> updatedSubPosts = IntStream.range(0, oldSubPosts.size())
                .mapToObj(idx -> {
                    oldSubPosts.get(idx).setText(newSubPosts.get(idx).getText());
                    oldSubPosts.get(idx).setImageDescription(newSubPosts.get(idx).getImageDescription());
                    oldSubPosts.get(idx).setSourceCode(newSubPosts.get(idx).getSourceCode());
                    oldSubPosts.get(idx).setSubPostImage(new Image(newSubPosts.get(idx).getByteImage()));

                    return oldSubPosts.get(idx);
                })
                .collect(Collectors.toList());

        return subPostRepository.saveAll(updatedSubPosts);
    }

}
