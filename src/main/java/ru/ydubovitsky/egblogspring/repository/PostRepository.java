package ru.ydubovitsky.egblogspring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ydubovitsky.egblogspring.entity.Post;

public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {
}
