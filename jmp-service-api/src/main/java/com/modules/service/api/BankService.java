package com.modules.service.api;

import com.modules.api.Bank;
import com.modules.jmp.dto.User;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public interface BankService {
    List<User> getAllUsers(List<Bank> banks);

    default boolean isPayableUser(User user) {
        Period age = Period.between(user.getBirthday(), LocalDate.now());
        return age.getYears() > 18;
    }

    static double getAverageUsersAge(List<User> users) {
        return users
                .stream()
                .map(User::getBirthday)
                .mapToLong(LocalDate::getYear)
                .average()
                .orElse(0d);
    }
}
