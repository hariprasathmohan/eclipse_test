public class User {

private String name;
private String surname;
private BankAccount bankAccount;

private double[] transactionList;

public User(String n, String s, BankAccount bA, double[] tL) {
    this.name = n;
    this.surname = s;
    this.bankAccount = bA;
    this.transactionList = tL;
}

public void getName() {
    System.out.println("Name: " + this.name);
}

public double[] getTransactionList(){
    return transactionList;
}

public void getSurname() {
    System.out.println("Surname: " + this.surname);
}

public void getUserInfo() {
    System.out.println("Full name: " + this.name + " " + this.surname);
}

public BankAccount getBankAccount() {
    return bankAccount;
}   
}