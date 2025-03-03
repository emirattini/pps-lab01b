package e1;

public class BankAccountFactory {

    public static BankAccount createBronze(int feeAmount, int maxNotTaxedAmount) {
        return new BankAccountDecorator(
                new FeeDecorator(new CoreBankAccount(), feeAmount, maxNotTaxedAmount));
    }

    public static BankAccount createSilver(int feeAmount) {
        return new BankAccountDecorator(new OverdraftDecorator(
                new FeeDecorator(new CoreBankAccount(), feeAmount, 0), 0));
    }

    public static BankAccount createGold(int overdraftAmount) {
        return new BankAccountDecorator(
                new OverdraftDecorator(new CoreBankAccount(), overdraftAmount));
    }
}
