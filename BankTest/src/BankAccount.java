public class BankAccount {

private long accountNo;
private double accountBalance;

public BankAccount() {
    this.accountNo = 9876543210L;
    this.accountBalance = 1980;
}

public void getAccountInfo() {
    System.out.println("Account Number: " + accountNo);
    System.out.println("Starting Account Balance: " + accountBalance);
}

public void getAccountBalance() {
    System.out.println("Account Balance: " + accountBalance);
}

public void deposit(double value) {
    accountBalance = (accountBalance + value);
     System.out.println("Account Balance After Deposit: " + accountBalance);
}

public void withdraw(double value) {
    accountBalance = (accountBalance + value);
    System.out.println("Account Balance After Withdrawal: " + accountBalance);
}
}