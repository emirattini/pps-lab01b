package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GoldBankAccountTest {

    public static final int MAX_OVERDRAFT = 500;
    public static final int DEPOSIT = 1000;
    private BankAccount account;

    @BeforeEach
    void init(){
        this.account = BankAccountFactory.createGold(MAX_OVERDRAFT);
    }

    @Test
    public void testInitiallyEmpty() {
        assertEquals(0, this.account.getBalance());
    }

    @Test
    public void testCanDeposit() {
        this.account.deposit(DEPOSIT);
        assertEquals(1000, this.account.getBalance());
    }

    @Test
    public void testCanGoOverdraft() {
        this.account.withdraw(MAX_OVERDRAFT);
        assertEquals(-MAX_OVERDRAFT, this.account.getBalance());
    }

    @Test
    public void testMaximumOverdraft() {
        assertThrows(IllegalStateException.class, () -> this.account.withdraw(MAX_OVERDRAFT + 1));
    }
}
