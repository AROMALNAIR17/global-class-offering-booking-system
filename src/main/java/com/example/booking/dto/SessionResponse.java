package com.example.booking.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionResponse {

    private Long sessionId;

    private String startTime;

    private String endTime;
}