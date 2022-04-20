package ru.ydubovitsky.engineerBlog.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ydubovitsky.engineerBlog.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {

    @Query("SELECT COUNT(id) FROM Post")
    Integer getPostsCount();

    Optional<List<Post>> findByTitleContainsIgnoreCase(String title);

    @Query(value = "SELECT DISTINCT p.category FROM Post p LIMIT 10", nativeQuery = true)
    Optional<List<String>> getCategories();

}
