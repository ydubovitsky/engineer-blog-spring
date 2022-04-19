package ru.ydubovitsky.engineerBlog.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class PostDto {

    private String title;
    private String text;
    private String category;
    private String author;
    private String description;

    private List<SubPostDto> subPosts;

}