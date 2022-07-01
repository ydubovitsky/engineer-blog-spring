package ru.ydubovitsky.engineerBlog.facade;

import ru.ydubovitsky.engineerBlog.dto.CommentDto;
import ru.ydubovitsky.engineerBlog.entity.Comment;

public class CommentFacade {

    public static Comment commentDtoToComment(CommentDto commentDto) {
        return Comment.builder()
                .name(commentDto.getName())
                .message(commentDto.getMessage())
                .email(commentDto.getEmail())
                .website(commentDto.getWebsite())
                .build();
    }

}
