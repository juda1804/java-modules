package com.modules.api.impl;

import com.modules.api.Bank;
import com.modules.api.BankRepository;

public class CentralBank extends Bank {
    public CentralBank(BankRepository bankRepository) {
        super(bankRepository);
    }
}
