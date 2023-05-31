package ru.ydubovitsky.engineerBlog.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ydubovitsky.engineerBlog.dto.FeedbackRequestDTO;
import ru.ydubovitsky.engineerBlog.entity.Feedback;
import ru.ydubovitsky.engineerBlog.facade.FeedbackFacade;
import ru.ydubovitsky.engineerBlog.repository.FeedbackRepository;

@Transactional
@Slf4j
@Service
@AllArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public Feedback saveFeedback(FeedbackRequestDTO feedbackRequestDto) {
        Feedback feedback = FeedbackFacade.feedbackRequestDtoToFeedback(feedbackRequestDto);
        Feedback savedFeedback = feedbackRepository.save(feedback);
        log.info(String.format("Feedback with id %s saved", feedback.getId()));
        return savedFeedback;
    }

}
