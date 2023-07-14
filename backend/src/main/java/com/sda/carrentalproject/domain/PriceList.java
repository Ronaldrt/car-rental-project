package com.sda.carrentalproject.domain;

import jakarta.persistence.Embeddable;
import lombok.Builder;

@Embeddable
@Builder

public class PriceList {


    long pricePerDayInEuroCents;

    public PriceList(long pricePerDayInEuroCents) {
        this.pricePerDayInEuroCents = pricePerDayInEuroCents;
    }

    public PriceList() {

    }


    public long getPricePerDayInEuroCents() {
        return pricePerDayInEuroCents;
    }

    public void setPricePerDayInEuroCents(long pricePerDayInEuroCents) {
        this.pricePerDayInEuroCents = pricePerDayInEuroCents;
    }
}
