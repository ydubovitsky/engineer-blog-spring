package ru.ydubovitsky.engineerBlog.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ydubovitsky.engineerBlog.entity.Comment;

import java.util.List;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId);

}
