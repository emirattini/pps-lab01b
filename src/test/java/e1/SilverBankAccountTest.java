package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SilverBankAccountTest {

    public static final int DEPOSIT = 1000;
    public static final int WITHDRAW = 200;
    public static final int FEE = 1;
    private BankAccount account;

    @BeforeEach
    void init(){
        this.account = BankAccountFactory.createSilver(FEE);
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
    public void testCanWithdraw() {
        this.account.deposit(DEPOSIT);
        this.account.withdraw(WITHDRAW);
        assertEquals(DEPOSIT - WITHDRAW - FEE, this.account.getBalance());
    }

    @Test
    public void testCannotWithdrawMoreThanAvailable(){
        this.account.deposit(DEPOSIT);
        assertThrows(IllegalStateException.class, () -> this.account.withdraw(DEPOSIT + 200));
    }
}
