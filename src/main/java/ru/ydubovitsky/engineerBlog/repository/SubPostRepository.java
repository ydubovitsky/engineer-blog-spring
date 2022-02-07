package ru.ydubovitsky.engineerBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ydubovitsky.engineerBlog.entity.SubPost;

public interface SubPostRepository extends JpaRepository<SubPost, Long> {
}
