package com.modules.api.impl;

import com.modules.api.Bank;
import com.modules.api.BankRepository;

public class RetailBank extends Bank {
    public RetailBank(BankRepository bankRepository) {
        super(bankRepository);
    }
}
