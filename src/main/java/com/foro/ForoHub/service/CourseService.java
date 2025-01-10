package com.foro.ForoHub.service;

import com.foro.ForoHub.entity.Course;
import com.foro.ForoHub.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Crear un nuevo curso
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }
}
