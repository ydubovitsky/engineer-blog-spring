package ru.ydubovitsky.engineerBlog.facade;

import ru.ydubovitsky.engineerBlog.dto.FeedbackRequestDTO;
import ru.ydubovitsky.engineerBlog.dto.FeedbackResponseDTO;
import ru.ydubovitsky.engineerBlog.entity.Feedback;

public class FeedbackFacade {

    public static Feedback feedbackRequestDtoToFeedback(FeedbackRequestDTO feedbackRequestDto) {
        return Feedback.builder()
                .name(feedbackRequestDto.getName())
                .message(feedbackRequestDto.getMessage())
                .email(feedbackRequestDto.getEmail())
                .build();
    }

    public static FeedbackResponseDTO feedbackToFeedbackResponseDto(Feedback feedback) {
        return FeedbackResponseDTO.builder()
                .name(feedback.getName())
                .message(feedback.getMessage())
                .email(feedback.getEmail())
                .build();
    }

}
