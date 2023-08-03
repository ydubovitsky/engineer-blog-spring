package ru.ydubovitsky.engineerBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ydubovitsky.engineerBlog.entity.Project;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Short> {

    Optional<Project> findById(Short id);

}
