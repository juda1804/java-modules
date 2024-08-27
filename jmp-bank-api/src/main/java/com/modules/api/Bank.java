package com.modules.api;

import com.modules.api.exceptions.CardNumberNotFound;
import com.modules.jmp.dto.BankCard;
import com.modules.jmp.dto.BankCardType;
import com.modules.jmp.dto.CreditBankCard;
import com.modules.jmp.dto.DebitBankCard;
import com.modules.jmp.dto.Subscription;
import com.modules.jmp.dto.User;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public abstract class Bank {
    private final BankRepository bankRepository;

    private static final BiFunction<String, User, BankCard> createCreditBankCard = CreditBankCard::new;
    private static final BiFunction<String, User, BankCard> createDebitBankCard = DebitBankCard::new;

    protected Bank(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public void subscribeNewBankCard(BankCardType type, String number, User user) {
        BankCard bankCard = createBankCard(type, number, user);
        subscribe(bankCard);
    }

    protected BankCard createBankCard(BankCardType type, String number, User user) {
        switch (type) {
            case DEBIT: return createDebitBankCard.apply(number, user);
            case CREDIT: return createCreditBankCard.apply(number, user);
            default: throw new IllegalArgumentException("Illegal Bank card type");
        }
    }

    protected void subscribe(BankCard bankCard) {
        Subscription subscription = new Subscription();
        subscription.setBankcardNumber(bankCard.getNumber());
        subscription.setStartDate(LocalDate.now());

        //TODO missing validate if the car number is already registered

        bankRepository.storeSubscription(subscription);
        bankRepository.saveBankCard(bankCard);
    }

    public List<User> getAllUsers() {
        return bankRepository.getAllUsers();
    }

    public Subscription getSubscriptionByBankCardNumber(String cardNumber) throws CardNumberNotFound {
        return bankRepository.getSubscriptionByBankCardNumber(cardNumber).orElseThrow(CardNumberNotFound::new);
    }

    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate) {
        return bankRepository.getAllSubscriptionsByCondition(predicate);
    }

}
