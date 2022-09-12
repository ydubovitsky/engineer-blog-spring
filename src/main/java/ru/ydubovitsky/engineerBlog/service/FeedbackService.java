package ru.ydubovitsky.engineerBlog.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.engineerBlog.dto.request.FeedbackRequestDto;
import ru.ydubovitsky.engineerBlog.entity.Feedback;
import ru.ydubovitsky.engineerBlog.facade.FeedbackFacade;
import ru.ydubovitsky.engineerBlog.repository.FeedbackRepository;

@Service
@AllArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public Feedback saveFeedback(FeedbackRequestDto feedbackRequestDto) {
        Feedback feedback = FeedbackFacade.feedbackRequestDtoToFeedback(feedbackRequestDto);

        return feedbackRepository.save(feedback);
    }

}
