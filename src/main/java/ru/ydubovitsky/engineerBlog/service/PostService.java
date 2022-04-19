package ru.ydubovitsky.engineerBlog.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ydubovitsky.engineerBlog.dto.PostDto;
import ru.ydubovitsky.engineerBlog.entity.Post;
import ru.ydubovitsky.engineerBlog.entity.SubPost;
import ru.ydubovitsky.engineerBlog.facade.PostFacade;
import ru.ydubovitsky.engineerBlog.repository.PostRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PostService {

    private static final Integer POST_BY_PAGE_COUNT = 5;

    private final PostRepository postRepository;

    //* Other services
    private final SubPostService subPostService;

    @Transactional
    public Post addPost(PostDto postDto)  {
        Post post = PostFacade.postDtoToPost(postDto);
        subPostService.saveSubPostList(post.getSubPosts());
        Post savedPost = postRepository.save(post);
        log.info(String.format("Post with name: %s - saved", savedPost.getTitle()));
        return savedPost;
    }

    public List<Post> getPostsPerPageWithSize(Integer page) {
        List<Post> posts = postRepository
                .findAll(PageRequest.of(page, POST_BY_PAGE_COUNT))
                .getContent();
        return posts;
    }

    public Integer getPostsCount() {
        return postRepository.getPostsCount();
    }

    public Post findPostById(Integer id) {
        return postRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Post with id: %s not found!", id))
        );
    }

    //TODO Переписать метод, уж больно страшный
    public Post updatePost(PostDto postDto) {
        Post postForUpdate = postRepository.findById(postDto.getId()).orElseThrow(
                () -> new RuntimeException(String.format("Post with id %s not found for update!", postDto.getId())));

        Post newPost = PostFacade.postDtoToPost(postDto);

        postForUpdate.setPostImage(newPost.getPostImage());
        postForUpdate.setText(newPost.getText());
        postForUpdate.setDisclosure(newPost.getDisclosure());
        postForUpdate.setDescription(newPost.getDescription());
        postForUpdate.setCategory(newPost.getCategory());
        postForUpdate.setAuthor(newPost.getAuthor());
        postForUpdate.setTitle(newPost.getTitle());

        subPostService.updateSubPostList(postForUpdate.getSubPosts(), postDto.getSubPosts());

        Post updatedPost = postRepository.save(postForUpdate);
        log.info(String.format("Post with id: %s updated!", updatedPost.getId()));
        return updatedPost;
    }

    public void deletePostById(Integer id) {
        postRepository.deleteById(id);
        log.info(String.format("Post with id: %s deleted", id));
    }
}
