package ru.ydubovitsky.engineerBlog.facade;

import ru.ydubovitsky.engineerBlog.dto.SubPostDto;
import ru.ydubovitsky.engineerBlog.entity.Image;
import ru.ydubovitsky.engineerBlog.entity.SubPost;

public class SubPostFacade {

    public static SubPost subPostDtoToSubPost(SubPostDto subPostDto) {
        return SubPost.builder()
                .text(subPostDto.getText())
                .sourceCode(subPostDto.getSourceCode())
                .subPostImage(new Image(subPostDto.getByteImage()))
                .imageDescription(subPostDto.getImageDescription())
                .build();
    }

}
