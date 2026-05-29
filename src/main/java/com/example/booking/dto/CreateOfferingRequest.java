package com.example.booking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOfferingRequest {

    @NotNull(message = "Course ID is required")
    private Long courseId;

    @NotNull(message = "Teacher ID is required")
    private Long teacherId;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Teacher timezone is required")
    private String teacherTimezone;
}