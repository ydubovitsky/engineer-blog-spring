package ru.ydubovitsky.engineerBlog.dto.request;

import lombok.*;

@Data
public class FeedbackRequestDto {

    private String name;
    private String email;
    private String message;

}
