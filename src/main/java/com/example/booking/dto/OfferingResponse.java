package com.example.booking.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OfferingResponse {

    private Long offeringId;

    private String title;

    private List<SessionResponse> sessions;
}