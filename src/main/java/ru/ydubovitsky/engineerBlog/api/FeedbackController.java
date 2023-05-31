package ru.ydubovitsky.engineerBlog.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ydubovitsky.engineerBlog.dto.FeedbackRequestDTO;
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
    @ResponseStatus(HttpStatus.CREATED)
    public void addFeedbackMessage(@RequestBody FeedbackRequestDTO feedbackRequestDto) {
        feedbackService.saveFeedback(feedbackRequestDto);
    }
}
