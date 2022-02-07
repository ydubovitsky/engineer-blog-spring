package ru.ydubovitsky.engineerBlog.facade;

import ru.ydubovitsky.engineerBlog.dto.PostDto;
import ru.ydubovitsky.engineerBlog.entity.Post;
import ru.ydubovitsky.engineerBlog.entity.SubPost;

import java.util.Objects;
import java.util.stream.Collectors;

public class PostFacade {

    public static Post postDtoToPost(PostDto postDto, byte[] byteImage) {
        Post post = Post.builder()
                .title(postDto.getTitle())
                .date(postDto.getDate())
                .text(postDto.getText())
                .description(postDto.getDescription())
                .postImage(byteImage) //! Attention!
                .category(postDto.getCategory())
                .author(postDto.getAuthor())
                .subPosts(postDto.getSubPosts().stream()
                        .filter(subPostDto -> Objects.nonNull(subPostDto))
                        .map(subPostDto -> new SubPost(
                                subPostDto.getText(),
                                subPostDto.getSourceCode(),
                                subPostDto.getImageDescription()))
                        .collect(Collectors.toList())
                ).build();

        return post;
    }

}
