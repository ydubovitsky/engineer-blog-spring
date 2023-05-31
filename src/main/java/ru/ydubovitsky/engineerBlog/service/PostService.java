package ru.ydubovitsky.engineerBlog.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ydubovitsky.engineerBlog.dto.PostAddRequestDTO;
import ru.ydubovitsky.engineerBlog.dto.PostUpdateRequestDTO;
import ru.ydubovitsky.engineerBlog.entity.Post;
import ru.ydubovitsky.engineerBlog.facade.PostFacade;
import ru.ydubovitsky.engineerBlog.repository.PostRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;

    public Post addPost(PostAddRequestDTO postDto) {
        Post post = PostFacade.postAddRequestDTOtoPost(postDto);
        Post savedPost = postRepository.save(post);
        log.info(String.format("Post with name: %s - saved", savedPost.getTitle()));
        return savedPost;
    }

    //! Case ignores
    public List<Post> searchPostsByTitle(String text) {
        List<Post> posts = postRepository.findAllNotDeletedPostsByTitleContainsIgnoreCase(text).orElseThrow(
                () -> new RuntimeException(String.format("Posts with title: %s - not found", text))
        );
        log.info(String.format("%s posts with %s found", posts.size(), text));
        return posts;
    }

    public Map<String, Object> getPostsPerPageWithSize(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page - 1, size); //TODO Improve it!
        Integer countOfPosts = postRepository.getPostsCount();
        List<Post> posts = postRepository.findAllNotDeletedPosts(paging)
                .orElseThrow(() -> new RuntimeException(String.format("Posts for page %s not found!", page)))
                .getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("totalPostsCount", countOfPosts);
        response.put("posts", posts);
        return response;
    }

    public List<String> getCategoriesListOfAllPosts() {
        List<String> categoriesList = postRepository.getCategories().orElseThrow(
                () -> new RuntimeException("Categories not found")
        );
        log.info(String.format("%s categories found", categoriesList.size()));
        return categoriesList;
    }

    public Integer getPostsCount() {
        return postRepository.getPostsCount();
    }

    public Post getPostById(Integer id) {
        return postRepository.findNotDeletedPostById(id)
                .orElseThrow(
                        () -> new RuntimeException(String.format("Post with id: %s not found!", id))
                );
    }

    //TODO Переписать метод, уж больно страшный
    public Post updatePost(PostUpdateRequestDTO postDto) {
        Post postForUpdate = postRepository.findById(postDto.getId()).orElseThrow(
                () -> new RuntimeException(String.format("Post with id %s not found for update!", postDto.getId())));

        Post newPost = PostFacade.postUpdateRequestDTOtoPost(postDto);

        if(Objects.nonNull(newPost.getPostImageSrc())) {
            postForUpdate.setPostImageSrc(newPost.getPostImageSrc());
        }
        postForUpdate.setText(newPost.getText());
        postForUpdate.setDisclosure(newPost.getDisclosure());
        postForUpdate.setDescription(newPost.getDescription());
        postForUpdate.setCategory(newPost.getCategory());
        postForUpdate.setAuthor(newPost.getAuthor());
        postForUpdate.setTitle(newPost.getTitle());

        Post updatedPost = postRepository.save(postForUpdate);
        log.info(String.format("Post with id: %s updated!", updatedPost.getId()));
        return updatedPost;
    }

    //! Этот метод не удаляет посты, а помечает их как удаленные
    public void deletePostById(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Post with id: %s not found!", id)));
        if(post.getIsDeleted()) throw new RuntimeException(String.format("Post with id: %s already marked as deleted", id));
        post.setIsDeleted(true);
        postRepository.save(post);
        log.info(String.format("Post with id: %s marked liked deleted", id));
    }

    public void increasePostView(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RuntimeException(String.format("Post with id %s not found for increasePostView!", postId)));

        post.setViews(post.getViews() + 1);
        postRepository.save(post);
        log.info(String.format("Views of post with id: %s updated", postId));
    }

    public void increasePostView(Post post) {
        post.setViews(post.getViews() + 1);
        postRepository.save(post);
        log.info(String.format("Views of post with id: %s updated", post.getId()));
    }
}
