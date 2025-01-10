package com.foro.ForoHub.controller;

import com.foro.ForoHub.entity.Course;
import com.foro.ForoHub.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Crear un nuevo curso
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course createCourse(@RequestBody @Valid Course course) {
        return courseService.createCourse(course);
    }
}
