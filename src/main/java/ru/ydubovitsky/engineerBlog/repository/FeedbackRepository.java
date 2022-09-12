package ru.ydubovitsky.engineerBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ydubovitsky.engineerBlog.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}
