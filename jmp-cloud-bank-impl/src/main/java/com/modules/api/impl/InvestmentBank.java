package com.modules.api.impl;

import com.modules.api.Bank;
import com.modules.api.BankRepository;

public class InvestmentBank extends Bank {
    public InvestmentBank(BankRepository bankRepository) {
        super(bankRepository);
    }
}
