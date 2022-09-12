package ru.ydubovitsky.engineerBlog.facade;

import ru.ydubovitsky.engineerBlog.dto.request.FeedbackRequestDto;
import ru.ydubovitsky.engineerBlog.dto.response.FeedbackResponseDto;
import ru.ydubovitsky.engineerBlog.entity.Feedback;

public class FeedbackFacade {

    public static Feedback feedbackRequestDtoToFeedback(FeedbackRequestDto feedbackRequestDto) {
        return Feedback.builder()
                .name(feedbackRequestDto.getName())
                .message(feedbackRequestDto.getMessage())
                .email(feedbackRequestDto.getEmail())
                .build();
    }

    public static FeedbackResponseDto feedbackToFeedbackResponseDto(Feedback feedback) {
        return FeedbackResponseDto.builder()
                .name(feedback.getName())
                .message(feedback.getMessage())
                .email(feedback.getEmail())
                .build();
    }

}
