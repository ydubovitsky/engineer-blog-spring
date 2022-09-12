package ru.ydubovitsky.engineerBlog.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FeedbackResponseDto {

    private String name;
    private String message;
    private String email;

}
