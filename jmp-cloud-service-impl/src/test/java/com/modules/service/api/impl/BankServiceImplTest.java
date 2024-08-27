package com.modules.service.api.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.modules.api.Bank;
import com.modules.api.exceptions.CardNumberNotFound;
import com.modules.api.impl.CentralBank;
import com.modules.api.impl.InvestmentBank;
import com.modules.api.impl.RetailBank;
import com.modules.jmp.dto.BankCardType;
import com.modules.jmp.dto.Subscription;
import com.modules.jmp.dto.User;
import com.modules.service.api.BankService;
import com.modules.service.api.impl.repository.BankRepositoryImpl;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankServiceImplTest {
    private Bank retailBank;
    private Bank centralBank;
    private Bank investmentBank;

    BankServiceImpl bankService = new BankServiceImpl();

    @BeforeEach
    void beforeAll() {
        retailBank = new RetailBank(new BankRepositoryImpl());
        retailBank.subscribeNewBankCard(BankCardType.CREDIT, "123", new User("Juan", "Cadavid", LocalDate.of(2000, 12, 9)));
        retailBank.subscribeNewBankCard(BankCardType.DEBIT, "124", new User("Juan 2", "Cadavid", LocalDate.of(2000, 12, 19)));

        centralBank = new CentralBank(new BankRepositoryImpl());
        centralBank.subscribeNewBankCard(BankCardType.CREDIT, "456", new User("Ana", "Gomez", LocalDate.of(1985, 7, 22)));
        centralBank.subscribeNewBankCard(BankCardType.DEBIT, "457", new User("Ana", "Gomez", LocalDate.of(1985, 7, 12)));

        investmentBank = new InvestmentBank(new BankRepositoryImpl());
        investmentBank.subscribeNewBankCard(BankCardType.CREDIT, "789", new User("Carlos", "Martinez", LocalDate.of(1992, 3, 15)));
        investmentBank.subscribeNewBankCard(BankCardType.DEBIT, "790", new User("Carlos 2", "Martinez", LocalDate.of(1992, 3, 19)));

    }

    @Test
    void testDataWorks() throws CardNumberNotFound {
        Subscription subscriptionByBankCardNumber = investmentBank.getSubscriptionByBankCardNumber("789");
        assertNotNull(subscriptionByBankCardNumber);

        //assert 5 clients in the system
        assertEquals(5, bankService.getAllUsers(List.of(centralBank, retailBank, investmentBank)).size());
    }

    @Test
    void testErrorWhenCardNotFund() {
        Bank investmentBank = new InvestmentBank(new BankRepositoryImpl());
        assertThrows(CardNumberNotFound.class, () -> investmentBank.getSubscriptionByBankCardNumber("79"));
        assertThrows(CardNumberNotFound.class, () -> investmentBank.getSubscriptionByBankCardNumber("7999"));
    }

    @Test
    void testAverageAge() {
        double averageUsersAge = BankService.getAverageUsersAge(retailBank.getAllUsers());
        assertEquals(2024, averageUsersAge);
    }

    @Test
    void testIsPayableUser() {
        User user = retailBank.getAllUsers().stream().findAny().get();
        boolean payableUser = bankService.isPayableUser(user);
        assertTrue(payableUser);
    }

    @Test
    void filterByPredicate() {
        List<Subscription> allSubscriptionsByCondition = centralBank.getAllSubscriptionsByCondition(subscription -> subscription.getStartDate().isAfter(LocalDate.of(2024, 1, 1)));

        assertEquals(2,allSubscriptionsByCondition.size());
    }


}