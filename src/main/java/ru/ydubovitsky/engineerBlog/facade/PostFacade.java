package ru.ydubovitsky.engineerBlog.facade;

import ru.ydubovitsky.engineerBlog.dto.PostDto;
import ru.ydubovitsky.engineerBlog.entity.Image;
import ru.ydubovitsky.engineerBlog.entity.Post;

public class PostFacade {

    public static Post postDtoToPost(PostDto postDto) {
        Post post = Post.builder()
                .title(postDto.getTitle())
                .text(postDto.getText())
                .description(postDto.getDescription())
                .postImage(new Image(postDto.getByteImage())) //! Image, Attention!
                .category(postDto.getCategory())
                .author(postDto.getAuthor())
                .conclusion(postDto.getConclusion())
                .build();

        return post;
    }

}
