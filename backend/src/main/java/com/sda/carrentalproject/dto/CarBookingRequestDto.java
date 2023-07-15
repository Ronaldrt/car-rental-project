package com.sda.carrentalproject.dto;

import java.time.LocalDate;

public record CarBookingRequestDto(
        long carToBookId,

        long clientId,

        LocalDate startDate,

        LocalDate endDate
) {
}
