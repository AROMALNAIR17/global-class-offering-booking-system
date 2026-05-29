package com.example.booking.service;

import com.example.booking.dto.BookingRequest;
import com.example.booking.dto.BookingResponse;
import com.example.booking.dto.OfferingResponse;
import com.example.booking.dto.SessionResponse;
import com.example.booking.entity.Booking;
import com.example.booking.entity.Offering;
import com.example.booking.entity.Parent;
import com.example.booking.entity.Session;
import com.example.booking.exception.BookingConflictException;
import com.example.booking.repository.BookingRepository;
import com.example.booking.repository.OfferingRepository;
import com.example.booking.repository.ParentRepository;
import com.example.booking.repository.SessionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentService {


    private final ParentRepository parentRepository;

    private final OfferingRepository offeringRepository;

    private final SessionRepository sessionRepository;

    private final BookingRepository bookingRepository;

    public List<OfferingResponse> getOfferingsForParent(
            Long parentId
    ) {

        Parent parent = parentRepository
                .findById(parentId)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Parent not found"
                        )
                );

        ZoneId parentZone =
                ZoneId.of(parent.getTimezone());

        List<Offering> offerings =
                offeringRepository.findAll();

        return offerings.stream()
                .map(offering -> {

                    List<SessionResponse> sessions =
                            sessionRepository
                                    .findByOfferingId(
                                            offering.getId()
                                    )
                                    .stream()
                                    .map(session -> {

                                        String startTime =
                                                session
                                                        .getStartTimeUtc()
                                                        .atZone(parentZone)
                                                        .toString();

                                        String endTime =
                                                session
                                                        .getEndTimeUtc()
                                                        .atZone(parentZone)
                                                        .toString();

                                        return SessionResponse
                                                .builder()
                                                .sessionId(
                                                        session.getId()
                                                )
                                                .startTime(
                                                        startTime
                                                )
                                                .endTime(
                                                        endTime
                                                )
                                                .build();

                                    })
                                    .toList();

                    return OfferingResponse
                            .builder()
                            .offeringId(
                                    offering.getId()
                            )
                            .title(
                                    offering.getTitle()
                            )
                            .sessions(
                                    sessions
                            )
                            .build();

                })
                .toList();
    }

    public List<BookingResponse> getBookings(
            Long parentId
    ) {

        return bookingRepository
                .findByParentId(parentId)
                .stream()
                .map(booking ->
                        BookingResponse.builder()
                                .bookingId(
                                        booking.getId()
                                )
                                .parentId(
                                        booking.getParent().getId()
                                )
                                .offeringId(
                                        booking.getOffering().getId()
                                )
                                .offeringTitle(
                                        booking.getOffering().getTitle()
                                )
                                .message(
                                        "Booking retrieved successfully"
                                )
                                .build()
                )
                .toList();
    }

    @Transactional
    public BookingResponse bookOffering(
            BookingRequest request
    ) {

        Parent parent =
                parentRepository.lockParent(
                        request.getParentId()
                );

        if (parent == null) {
            throw new RuntimeException(
                    "Parent not found"
            );
        }

        Offering offering =
                offeringRepository.findById(
                        request.getOfferingId()
                ).orElseThrow(
                        () -> new RuntimeException(
                                "Offering not found"
                        )
                );

        List<Session> targetSessions =
                sessionRepository
                        .findByOfferingId(
                                offering.getId()
                        );

        List<Session> bookedSessions =
                bookingRepository
                        .findBookedSessions(
                                parent.getId()
                        );

        for (Session target : targetSessions) {

            for (Session existing : bookedSessions) {

                boolean overlap =
                        target.getStartTimeUtc()
                                .isBefore(
                                        existing.getEndTimeUtc()
                                )
                                &&
                                target.getEndTimeUtc()
                                        .isAfter(
                                                existing.getStartTimeUtc()
                                        );

                if (overlap) {

                    throw new BookingConflictException(
                            "Booking conflict detected"
                    );
                }
            }
        }

        Booking booking =
                Booking.builder()
                        .parent(parent)
                        .offering(offering)
                        .build();

        Booking savedBooking =
                bookingRepository.save(
                        booking
                );

        return BookingResponse
                .builder()
                .bookingId(
                        savedBooking.getId()
                )
                .parentId(
                        parent.getId()
                )
                .offeringId(
                        offering.getId()
                )
                .offeringTitle(
                        offering.getTitle()
                )
                .message(
                        "Booking created successfully"
                )
                .build();
    }


}
