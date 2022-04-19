package ru.ydubovitsky.engineerBlog.facade;

import ru.ydubovitsky.engineerBlog.dto.PostDto;
import ru.ydubovitsky.engineerBlog.entity.Image;
import ru.ydubovitsky.engineerBlog.entity.Post;
import ru.ydubovitsky.engineerBlog.entity.SubPost;

import java.util.Objects;
import java.util.stream.Collectors;

public class PostFacade {

    public static Post postDtoToPost(PostDto postDto) {
        Post post = Post.builder()
                .title(postDto.getTitle())
                .text(postDto.getText())
                .description(postDto.getDescription())
                .postImage(new Image(postDto.getByteImage())) //! Image, Attention!
                .category(postDto.getCategory())
                .author(postDto.getAuthor())
                .subPosts(postDto.getSubPosts().stream()
                        .filter(subPostDto -> Objects.nonNull(subPostDto))
                        .map(subPostDto -> SubPost.builder()
                                .subPostImage(new Image(subPostDto.getByteImage())) //! Image
                                .text(subPostDto.getText())
                                .imageDescription(subPostDto.getImageDescription())
                                .sourceCode(subPostDto.getSourceCode())
                                .build())
                        .collect(Collectors.toList())
                ).build();

        return post;
    }

}
