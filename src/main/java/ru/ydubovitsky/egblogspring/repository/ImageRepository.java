package ru.ydubovitsky.egblogspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ydubovitsky.egblogspring.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
