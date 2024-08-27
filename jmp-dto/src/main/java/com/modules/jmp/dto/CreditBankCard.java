package com.modules.jmp.dto;

import java.time.LocalDate;

public class CreditBankCard extends BankCard {
    private final BankCardType cardType = BankCardType.CREDIT;
    public CreditBankCard(String number, User user) {
        super(number, user);
    }

    @Override
    public BankCardType getCardType() {
        return cardType;
    }
}
