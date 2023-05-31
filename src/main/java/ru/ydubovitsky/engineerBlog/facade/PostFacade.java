package ru.ydubovitsky.engineerBlog.facade;

import ru.ydubovitsky.engineerBlog.dto.PostAddRequestDTO;
import ru.ydubovitsky.engineerBlog.dto.PostUpdateRequestDTO;
import ru.ydubovitsky.engineerBlog.entity.Post;
import ru.ydubovitsky.engineerBlog.dto.PostResponseDTO;

public class PostFacade {

    public static Post postAddRequestDTOtoPost(PostAddRequestDTO postDto) {
        Post post = Post.builder()
                .title(postDto.getTitle())
                .text(postDto.getText())
                .description(postDto.getDescription())
                .postImageSrc(postDto.getPostImageSrc())
                .category(postDto.getCategory())
                .author(postDto.getAuthor())
                .conclusion(postDto.getConclusion())
                .build();

        return post;
    }

    public static PostResponseDTO postToPostResponseDTO(Post post) {
        return PostResponseDTO.builder()
                .id(post.getId())
                .author(post.getAuthor())
                .views(post.getViews())
                .postImageSrc(post.getPostImageSrc())
                .category(post.getCategory())
                .conclusion(post.getConclusion())
                .description(post.getDescription())
                .text(post.getText())
                .title(post.getTitle())
                .build();
    }

    public static Post postUpdateRequestDTOtoPost(PostUpdateRequestDTO postRequest) {
        return Post.builder()
                .id(postRequest.getId())
                .title(postRequest.getTitle())
                .postImageSrc(postRequest.getPostImageSrc())
                .author(postRequest.getAuthor())
                .category(postRequest.getCategory())
                .text(postRequest.getText())
                .description(postRequest.getDescription())
                .conclusion(postRequest.getConclusion())
                .build();
    }

}
