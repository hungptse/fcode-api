package dev.fcodeapi.repositories;

import dev.fcodeapi.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
    public CourseEntity findByCourseName(String courseName);
    
}
