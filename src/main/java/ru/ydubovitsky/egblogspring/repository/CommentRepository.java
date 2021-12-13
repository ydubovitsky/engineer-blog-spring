package ru.ydubovitsky.egblogspring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ydubovitsky.egblogspring.entity.Comment;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
}
