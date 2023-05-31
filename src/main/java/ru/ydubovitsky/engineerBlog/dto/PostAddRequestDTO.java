package ru.ydubovitsky.engineerBlog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class PostAddRequestDTO {

    private String title;
    private String postImageSrc;
    private String text;
    private String category;
    private String author;
    private String description;
    private String conclusion;

}
