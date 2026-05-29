package com.example.booking.controller;

import com.example.booking.entity.Course;
import com.example.booking.repository.CourseRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@Tag(name = "Course APIs", description = "APIs for managing courses")
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}