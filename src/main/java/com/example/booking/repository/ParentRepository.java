package com.example.booking.repository;

import com.example.booking.entity.Parent;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface ParentRepository
        extends JpaRepository<Parent, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        SELECT p
        FROM Parent p
        WHERE p.id = :id
    """)
    Parent lockParent(@Param("id") Long id);
}