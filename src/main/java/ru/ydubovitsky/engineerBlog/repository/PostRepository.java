package ru.ydubovitsky.engineerBlog.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ydubovitsky.engineerBlog.entity.Post;

public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {

    @Query("SELECT COUNT(id) FROM Post")
    Integer getPostsCount();
}
