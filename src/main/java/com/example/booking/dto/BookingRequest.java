package com.example.booking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookingRequest {

    @NotNull(message = "Parent ID is required")
    private Long parentId;

    @NotNull(message = "Offering ID is required")
    private Long offeringId;
}