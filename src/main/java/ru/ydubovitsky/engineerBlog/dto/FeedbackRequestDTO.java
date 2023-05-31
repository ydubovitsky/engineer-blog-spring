package ru.ydubovitsky.engineerBlog.dto;

import lombok.*;

@Data
public class FeedbackRequestDTO {

    private String name;
    private String email;
    private String message;

}
