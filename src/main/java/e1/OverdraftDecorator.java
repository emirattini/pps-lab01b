package e1;

public class OverdraftDecorator extends BankAccountDecorator {

    private final int overdraftAmount;

    public OverdraftDecorator(BankAccount base, int overdraftAmount) {
        super(base);
        this.overdraftAmount = overdraftAmount;
    }

    @Override
    public void withdraw(int amount) {
        if (base.getBalance() + overdraftAmount < amount) {
            throw new IllegalStateException("Maximum overdraft permitted: " + overdraftAmount);
        }
        super.withdraw(amount);
    }
}
