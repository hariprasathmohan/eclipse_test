import java.util.Scanner;

public class BankTest {

private static double[][] transactionList = {
    {50, 10, -20, 10, -20, 20, 10, 50, -10, 10, -10, 50},
    {20, 20, -20, 50, -20, 10, 50, 50, -20, 10, 10},
    {50, 10, 10, -10, -10, 50, 20, -10, -20},
    {50, 10, -20, 20, 10, -20}
};

private static BankAccount myAccount;

private static UserThread myUser1;
private static UserThread myUser2;
private static UserThread myUser3;
private static UserThread myUser4;

public static void main(String[] args) {

    for (;;) {
        System.out.println("<--------- Banking Menu ----------->");
        System.out.println("1. Create Bank Account");
        System.out.println("2. Create User");
        System.out.println("3. Run Simulation");
        System.out.println("4. Exit");
        System.out.println("Enter choice: ");

        Scanner in = new Scanner(System.in);

        switch (in.nextInt()) {
            case 1:
                System.out.println("<--------- Create Bank Account ----------->");
                myAccount = new BankAccount();

                System.out.println("Bank Account Created!");
                break;

            case 2:
                System.out.println("<--------- Create User ----------->");
                System.out.println("1. User 1");
                System.out.println("2. User 2");
                System.out.println("3. User 3");
                System.out.println("4. User 4");
                userMenu();
                break;

            case 3:
                myUser1.start();
                myUser2.start();
                myUser3.start();
                myUser4.start();
                break;

            case 4:
                System.out.println("Goodbye");
                System.exit(0);
                break;

            default:
                System.err.println("Unrecognized option");
                break;
        }
    }
}

public static void userMenu() {
    Scanner in = new Scanner(System.in);

    switch (in.nextInt()) {
        case 1:
            System.out.println("<----- User 1 ------>");
            User user1 = new User("Saul", "Goodman", myAccount, transactionList[0]);
            myUser1 = new UserThread(user1, "User 1 Thread");
            user1.getUserInfo();
            break;

        case 2:
            System.out.println("<-------- User 2 ------->");
            User user2 = new User("Walter", "White", myAccount, transactionList[1]);
            myUser2 = new UserThread(user2, "User 2 Thread");
            user2.getUserInfo();
            break;

        case 3:
            System.out.println("<-------- User 3 ------->");
            User user3 = new User("Jessie", "Pinkman", myAccount, transactionList[2]);
            myUser3 = new UserThread(user3, "User 3 Thread");
            user3.getUserInfo();
            break;

        case 4:
            System.out.println("<-------- User 4 ------->");
            User user4 = new User("Hank", "Schrader", myAccount, transactionList[3]);
            myUser4 = new UserThread(user4, "User 4 Thread");
            user4.getUserInfo();
            break;

        default:
            System.err.println("Unrecognized option");
            break;
    }
}

public static void runSimulation(User u) {
    double[] tList = u.getTransactionList();
    BankAccount ba = u.getBankAccount();

    for (int i = 0; i < tList.length; i++){
        if(tList[i] < 0) {
            ba.withdraw(tList[i]);
        } else{
            ba.deposit(tList[i]);
        }
        u.getName();
        ba.getAccountBalance();
    }
}
}
