package ru.ydubovitsky.engineerBlog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import ru.ydubovitsky.engineerBlog.entity.Post;

import java.util.List;
import java.util.Optional;

//!TODO Нужны ли тут эскейп символы в запросах?
public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {

    Optional<List<Post>> findByTitleContainsIgnoreCase(String title);

    @Query("SELECT COUNT(id) FROM Post")
    Integer getPostsCount();

    @Query(value = "SELECT DISTINCT p.category FROM Post p LIMIT 10", nativeQuery = true)
    Optional<List<String>> getCategories();

    @Query("select p from Post p where lower(p.title) like lower(concat('%', :title,'%')) and p.isDeleted = false")
    Optional<List<Post>> findAllNotDeletedPostsByTitleContainsIgnoreCase(@Param("title") String title);

    @Query("SELECT p from Post p where p.isDeleted = false")
    Optional<Page<Post>> findAllNotDeletedPosts(Pageable pageable);

    @Query("SELECT p from Post p where p.id = :id")
    Optional<Post> findNotDeletedPostById(@Param("id") Integer id);
}
