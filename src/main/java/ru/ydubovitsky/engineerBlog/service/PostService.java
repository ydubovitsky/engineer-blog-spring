package ru.ydubovitsky.engineerBlog.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ydubovitsky.engineerBlog.dto.PostDto;
import ru.ydubovitsky.engineerBlog.entity.Post;
import ru.ydubovitsky.engineerBlog.facade.PostFacade;
import ru.ydubovitsky.engineerBlog.repository.PostRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    //* Other services
    private final SubPostService subPostService;

    @Transactional
    public Post addPost(PostDto postDto) {
        Post post = PostFacade.postDtoToPost(postDto);
        subPostService.saveSubPostList(post.getSubPosts());
        Post savedPost = postRepository.save(post);
        log.info(String.format("Post with name: %s - saved", savedPost.getTitle()));
        return savedPost;
    }

    //! Case ignores
    @Transactional
    public List<Post> searchPostsByTitle(String text) {
        List<Post> posts = postRepository.findAllNotDeletedPostsByTitleContainsIgnoreCase(text).orElseThrow(
                () -> new RuntimeException(String.format("Posts with title: %s - not found", text))
        );
        log.info(String.format("%s posts with %s found", posts.size(), text));
        return posts;
    }

    @Transactional
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

    @Transactional
    public Post getPostById(Integer id) {
        return postRepository.findNotDeletedPostById(id)
                .orElseThrow(
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
        postForUpdate.setSubPosts(newPost.getSubPosts());

        subPostService.updateSubPostList(postForUpdate.getSubPosts(), postDto.getSubPosts());

        Post updatedPost = postRepository.save(postForUpdate);
        log.info(String.format("Post with id: %s updated!", updatedPost.getId()));
        return updatedPost;
    }

    //! Этот метод не удаляет посты, а помечает их как удаленные
    public void deletePostById(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Post with id: %s not found!", id)));
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
