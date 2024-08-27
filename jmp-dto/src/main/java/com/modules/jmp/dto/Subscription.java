package com.modules.jmp.dto;

import java.time.LocalDate;

public class Subscription {
    private String bankcardNumber;
    private LocalDate startDate;

    public Subscription() {
    }

    public String getBankcardNumber() {
        return bankcardNumber;
    }

    public void setBankcardNumber(String bankcardNumber) {
        this.bankcardNumber = bankcardNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
