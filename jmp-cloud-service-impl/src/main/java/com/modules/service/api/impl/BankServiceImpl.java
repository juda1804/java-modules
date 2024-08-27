package com.modules.service.api.impl;

import com.modules.api.Bank;
import com.modules.jmp.dto.User;
import com.modules.service.api.BankService;
import java.util.List;
import java.util.stream.Collectors;

public class BankServiceImpl implements BankService {
    @Override
    public List<User> getAllUsers(List<Bank> banks) {
        return banks.stream()
                .flatMap(bank -> bank.getAllUsers().stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
