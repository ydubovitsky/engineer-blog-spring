package ru.ydubovitsky.engineerBlog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class PostResponseDTO {

    private Integer id;
    private String title;
    private String text;
    private String category;
    private Integer views;
    private String author;
    private String description;
    private String conclusion;
    private String postImageSrc;

}
