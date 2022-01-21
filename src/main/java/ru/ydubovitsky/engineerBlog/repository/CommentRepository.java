package ru.ydubovitsky.engineerBlog.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ydubovitsky.engineerBlog.entity.Comment;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
}
