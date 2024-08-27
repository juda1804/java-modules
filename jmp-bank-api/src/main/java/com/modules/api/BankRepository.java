package com.modules.api;

import com.modules.jmp.dto.BankCard;
import com.modules.jmp.dto.Subscription;
import com.modules.jmp.dto.User;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface BankRepository {
    void saveBankCard(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    void storeSubscription(Subscription subscription);

    List<User> getAllUsers();

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate);
}
