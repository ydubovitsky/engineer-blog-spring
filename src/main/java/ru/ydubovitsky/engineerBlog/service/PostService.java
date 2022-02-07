package ru.ydubovitsky.engineerBlog.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.ydubovitsky.engineerBlog.dto.PostDto;
import ru.ydubovitsky.engineerBlog.entity.Post;
import ru.ydubovitsky.engineerBlog.entity.SubPost;
import ru.ydubovitsky.engineerBlog.facade.PostFacade;
import ru.ydubovitsky.engineerBlog.repository.PostRepository;
import ru.ydubovitsky.engineerBlog.repository.SubPostRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final SubPostRepository subPostRepository;
    private static final Integer POST_BY_PAGE_COUNT = 5;

    @Transactional
    public Post addPost(PostDto postDto, MultipartFile[] images) throws IOException {
        Post post = PostFacade.postDtoToPost(postDto, images[0].getBytes()); //FIXME Заменить на map?
        List<SubPost> subPosts = post.getSubPosts();

        //! Save subPost before Post
        IntStream.range(0, subPosts.size())
                .forEach(idx -> {
                    try {
                        subPosts.get(idx).setSubPostImage(images[idx].getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    subPostRepository.save(subPosts.get(idx));
                });

        Post savedPost = postRepository.save(post);
        log.info(String.format("Post with name: %s - saved", savedPost.getTitle()));
        return savedPost;
    }

    @Transactional
    public List<Post> getPostOfSize(Integer page) { //TODO Переименовать
        List<Post> posts = postRepository
                .findAll(PageRequest.of(page, POST_BY_PAGE_COUNT))
                .getContent();
        return posts;
    }
}
