package com.foro.ForoHub.repository;

import com.foro.ForoHub.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
