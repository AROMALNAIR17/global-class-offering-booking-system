package com.example.booking.service;
import com.example.booking.dto.OfferingResponse;
import com.example.booking.dto.SessionResponse;

import java.util.List;
import com.example.booking.dto.CreateOfferingRequest;
import com.example.booking.dto.CreateSessionRequest;
import com.example.booking.dto.OfferingResponse;
import com.example.booking.entity.Course;
import com.example.booking.entity.Offering;
import com.example.booking.entity.Session;
import com.example.booking.repository.CourseRepository;
import com.example.booking.repository.OfferingRepository;
import com.example.booking.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final CourseRepository courseRepository;
    private final OfferingRepository offeringRepository;
    private final SessionRepository sessionRepository;

    public Offering createOffering(
            CreateOfferingRequest request
    ) {

        Course course = courseRepository.findById(
                request.getCourseId()
        ).orElseThrow();

        Offering offering = Offering.builder()
                .title(request.getTitle())
                .teacherId(request.getTeacherId())
                .teacherTimezone(request.getTeacherTimezone())
                .course(course)
                .build();

        return offeringRepository.save(offering);
    }

    public Session addSession(
            Long offeringId,
            CreateSessionRequest request
    ) {

        Offering offering = offeringRepository
                .findById(offeringId)
                .orElseThrow();

        ZoneId zoneId =
                ZoneId.of(offering.getTeacherTimezone());

        LocalDateTime start =
                LocalDateTime.parse(request.getStartTime());

        LocalDateTime end =
                LocalDateTime.parse(request.getEndTime());

        Instant startUtc =
                start.atZone(zoneId).toInstant();

        Instant endUtc =
                end.atZone(zoneId).toInstant();

        Session session = Session.builder()
                .offering(offering)
                .startTimeUtc(startUtc)
                .endTimeUtc(endUtc)
                .build();

        return sessionRepository.save(session);
    }

    public List<OfferingResponse> getTeacherOfferings(
            Long teacherId
    ) {

        return offeringRepository
                .findByTeacherId(teacherId)
                .stream()
                .map(offering -> {

                    List<SessionResponse> sessions =
                            sessionRepository
                                    .findByOfferingId(
                                            offering.getId()
                                    )
                                    .stream()
                                    .map(session ->
                                            SessionResponse.builder()
                                                    .sessionId(
                                                            session.getId()
                                                    )
                                                    .startTime(
                                                            session.getStartTimeUtc()
                                                                    .toString()
                                                    )
                                                    .endTime(
                                                            session.getEndTimeUtc()
                                                                    .toString()
                                                    )
                                                    .build()
                                    )
                                    .toList();

                    return OfferingResponse.builder()
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
}