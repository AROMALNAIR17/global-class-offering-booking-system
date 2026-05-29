package com.example.booking.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponse {

    private Long bookingId;

    private Long parentId;

    private Long offeringId;

    private String offeringTitle;

    private String message;
}