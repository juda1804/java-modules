package com.modules.jmp.dto;

import java.time.LocalDate;

public class DebitBankCard extends BankCard {
    private final BankCardType cardType = BankCardType.DEBIT;
    public DebitBankCard(String number, User user) {
        super(number, user);
    }

    @Override
    public BankCardType getCardType() {
        return cardType;
    }
}
