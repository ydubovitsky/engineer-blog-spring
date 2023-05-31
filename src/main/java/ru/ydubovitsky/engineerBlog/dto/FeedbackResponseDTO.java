package ru.ydubovitsky.engineerBlog.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FeedbackResponseDTO {

    private String name;
    private String message;
    private String email;

}
