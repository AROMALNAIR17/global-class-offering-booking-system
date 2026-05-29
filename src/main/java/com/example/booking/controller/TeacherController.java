package com.example.booking.controller;

import com.example.booking.dto.CreateOfferingRequest;
import com.example.booking.dto.CreateSessionRequest;
import com.example.booking.dto.OfferingResponse;
import com.example.booking.entity.Offering;
import com.example.booking.entity.Session;
import com.example.booking.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
@Tag(name = "Teacher APIs", description = "APIs for managing offerings and sessions")
public class TeacherController {

    private final TeacherService teacherService;

    @Operation(summary = "Create a new offering")
    @PostMapping("/offerings")
    public Offering createOffering(
            @Valid @RequestBody CreateOfferingRequest request
    ) {
        return teacherService.createOffering(request);
    }

    @Operation(summary = "Add session to an offering")
    @PostMapping("/offerings/{id}/sessions")
    public Session addSession(
            @PathVariable Long id,
            @Valid @RequestBody CreateSessionRequest request
    ) {
        return teacherService.addSession(id, request);
    }

    @Operation(summary = "Get teacher offerings")
    @GetMapping("/{teacherId}/offerings")
    public List<OfferingResponse> getTeacherOfferings(
            @PathVariable Long teacherId
    ) {
        return teacherService.getTeacherOfferings(
                teacherId
        );
    }
}