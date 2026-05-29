package com.example.booking.repository;

import com.example.booking.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository
        extends JpaRepository<Course, Long> {
}