package com.example.booking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long teacherId;

    private String title;

    private String teacherTimezone;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}