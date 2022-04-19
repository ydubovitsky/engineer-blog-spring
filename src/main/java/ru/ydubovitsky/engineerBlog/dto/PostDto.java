package ru.ydubovitsky.engineerBlog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class PostDto {

    private Integer id;
    private String title;
    private String text;
    private String category;
    private String author;
    private String description;
    private byte[] byteImage;

    private List<SubPostDto> subPosts;

}
