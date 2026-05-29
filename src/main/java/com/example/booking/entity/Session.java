package com.example.booking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant startTimeUtc;

    private Instant endTimeUtc;

    @ManyToOne
    @JoinColumn(name = "offering_id")
    private Offering offering;
}