package ru.ydubovitsky.engineerBlog.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ydubovitsky.engineerBlog.dto.request.FeedbackRequestDto;
import ru.ydubovitsky.engineerBlog.entity.Feedback;
import ru.ydubovitsky.engineerBlog.facade.FeedbackFacade;
import ru.ydubovitsky.engineerBlog.service.FeedbackService;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping("/feedback/message/add")
    public ResponseEntity<?> addFeedbackMessage(@RequestBody FeedbackRequestDto feedbackRequestDto) {
        Feedback feedback = feedbackService.saveFeedback(feedbackRequestDto);
        if(Objects.isNull(feedback)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info(String.format("Feedback with id - %s saved", feedback.getId()));
        return ResponseEntity.ok(FeedbackFacade.feedbackToFeedbackResponseDto(feedback));
    }

}
