package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BronzeBankAccountTest {

    public static final int MAX_AMOUNT_WITH_NO_FEE = 99;
    public static final int DEPOSIT = 1000;
    public static final int FEE = 1;
    private BankAccount account;

    @BeforeEach
    void init(){
        this.account = BankAccountFactory.createBronze(FEE, MAX_AMOUNT_WITH_NO_FEE);
    }

    @Test
    public void testInitiallyEmpty() {
        assertEquals(0, this.account.getBalance());
    }

    @Test
    public void testCanDeposit() {
        this.account.deposit(DEPOSIT);
        assertEquals(DEPOSIT, this.account.getBalance());
    }

    @Test
    public void testNoFeeUnderCertainAmount() {
        this.account.deposit(DEPOSIT);
        this.account.withdraw(MAX_AMOUNT_WITH_NO_FEE);
        assertEquals(DEPOSIT - MAX_AMOUNT_WITH_NO_FEE, this.account.getBalance());
    }

    @Test
    public void testFeeOverCertainAmount() {
        this.account.deposit(DEPOSIT);
        int amountToTax = MAX_AMOUNT_WITH_NO_FEE + 1;
        this.account.withdraw(amountToTax);
        int newBalance = DEPOSIT - (MAX_AMOUNT_WITH_NO_FEE + 1 + FEE);
        assertEquals(newBalance, account.getBalance());
    }
}
