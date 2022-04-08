package ru.ydubovitsky.engineerBlog.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.ydubovitsky.engineerBlog.dto.request.PostDto;
import ru.ydubovitsky.engineerBlog.entity.Image;
import ru.ydubovitsky.engineerBlog.entity.Post;
import ru.ydubovitsky.engineerBlog.entity.SubPost;
import ru.ydubovitsky.engineerBlog.facade.PostFacade;
import ru.ydubovitsky.engineerBlog.repository.PostRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@AllArgsConstructor
public class PostService {

    private static final Integer POST_BY_PAGE_COUNT = 5;

    private final PostRepository postRepository;

    //* Other services
    private final SubPostService subPostService;
    private final ImageService imageService;

    @Transactional
    public Post addPost(PostDto postDto, MultipartFile[] images) throws IOException {
        Post post = PostFacade.postDtoToPost(postDto, images[0].getBytes()); //FIXME Заменить на map?
        List<SubPost> subPosts = post.getSubPosts();

        //! Add to each subPost - image
        List<SubPost> subPostsWithImage = IntStream.range(0, subPosts.size())
                .mapToObj(idx -> {
                    try {
                        Image image = imageService.saveImage(new Image(images[idx].getName(), images[idx].getBytes()));
                        subPosts.get(idx).setSubPostImage(image);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return subPosts.get(idx);
                }).collect(Collectors.toList());

        //! Save list of subPost before save Post
        List<SubPost> savedSubPostList = subPostService.saveSubPostList(subPostsWithImage);

        //! Update post
        post.setSubPosts(savedSubPostList);

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

    public Integer getAllPostsCount() {
        return postRepository.aCountOfPosts();
    }

    public Post findPostById(Integer id) {
        return postRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Post with id: %s not found!", id))
        );
    }
}
