package com.example.booking.controller;

import com.example.booking.dto.BookingRequest;
import com.example.booking.dto.BookingResponse;
import com.example.booking.dto.OfferingResponse;
import com.example.booking.service.ParentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parent")
@RequiredArgsConstructor
@Tag(
        name = "Parent APIs",
        description = "APIs for viewing and booking offerings"
)
public class ParentController {

    private final ParentService parentService;

    @Operation(
            summary = "Get offerings in parent's timezone"
    )
    @GetMapping("/{parentId}/offerings")
    public List<OfferingResponse> getOfferings(
            @PathVariable Long parentId
    ) {

        return parentService.getOfferingsForParent(
                parentId
        );
    }

    @Operation(
            summary = "Get parent bookings"
    )
    @GetMapping("/{parentId}/bookings")
    public List<BookingResponse> getBookings(
            @PathVariable Long parentId
    ) {

        return parentService.getBookings(
                parentId
        );
    }

    @Operation(
            summary = "Book an offering"
    )
    @PostMapping("/book")
    public BookingResponse bookOffering(
            @Valid @RequestBody BookingRequest request
    ) {

        return parentService.bookOffering(
                request
        );
    }
}