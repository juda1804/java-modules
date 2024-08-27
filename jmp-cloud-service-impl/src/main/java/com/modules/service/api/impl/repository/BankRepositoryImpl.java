package com.modules.service.api.impl.repository;

import com.modules.api.BankRepository;
import com.modules.jmp.dto.BankCard;
import com.modules.jmp.dto.Subscription;
import com.modules.jmp.dto.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BankRepositoryImpl implements BankRepository {
    private final Vector<BankCard> bankCards;
    private final Vector<Subscription> subscriptions;

    public BankRepositoryImpl() {
        this.bankCards = new Vector<>();
        this.subscriptions = new Vector<>();

    }

    synchronized public void saveBankCard(BankCard bankCard) {
        bankCards.add(bankCard);
    }

    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        return subscriptions.stream()
                .filter(subscription -> subscription.getBankcardNumber().equals(cardNumber))
                .findFirst();
    }

    public void storeSubscription(Subscription subscription) {
        subscriptions.add(subscription);
    }

    public List<User> getAllUsers() {
        return bankCards
                .stream()
                .map(BankCard::getUser)
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate) {
        return subscriptions.stream().filter(predicate).collect(Collectors.toList());
    }
}
