package com.sda.carrentalproject.dto;

import com.sda.carrentalproject.domain.PriceList;
import com.sda.carrentalproject.domain.enumeration.Color;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

@Builder
public record CarDto(
                    Long id,

                     String brand,

                     String model,

                     YearMonth productionYear,

                     Color color,

                     boolean available,

                     long pricePerDayInEuroCents
) {
}
