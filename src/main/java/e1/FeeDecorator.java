package e1;

public class FeeDecorator extends BankAccountDecorator {

    private final int feeAmount;
    private final int maxNotTaxedAmount;

    public FeeDecorator(BankAccount base, int feeAmount, int maxNotTaxedAmount) {
        super(base);
        this.feeAmount = feeAmount;
        this.maxNotTaxedAmount = maxNotTaxedAmount;
    }

    @Override
    public void withdraw(int amount) {
        if (amount > maxNotTaxedAmount) {
            super.withdraw(amount + feeAmount);
        } else {
            super.withdraw(amount);
        }
    }
}
