package com.example.booking.repository;

import com.example.booking.entity.Booking;
import com.example.booking.entity.Session;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository
        extends JpaRepository<Booking, Long> {

    @Query("""
        SELECT s
        FROM Booking b
        JOIN Session s
          ON b.offering.id = s.offering.id
        WHERE b.parent.id = :parentId
    """)
    List<Session> findBookedSessions(
            @Param("parentId") Long parentId
    );

    List<Booking> findByParentId(Long parentId);
}