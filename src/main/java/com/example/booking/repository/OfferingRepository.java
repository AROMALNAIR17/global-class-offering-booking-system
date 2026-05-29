package com.example.booking.repository;

import com.example.booking.entity.Offering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferingRepository
        extends JpaRepository<Offering, Long> {

    List<Offering> findByTeacherId(Long teacherId);
}