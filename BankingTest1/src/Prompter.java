import java.util.Scanner;

public class Prompter
{
    protected boolean isFinishedBanking;
    protected boolean areCustomerDetailsCorrect;
    private String option;

    private Scanner scanner = new Scanner(System.in);
    private Account account = new Account();

    public void prompt()
    {
        isFinishedBanking = false;
        System.out.print("> ");
        option = scanner.nextLine();
    }

    public void processPrompt()
    {
        if (option.toLowerCase().equals("exit"))
        {
            System.out.println("\nThank you for banking with JBank, goodbye!");
            isFinishedBanking = true;
        }

        else if (option.toLowerCase().equals("home"))
        {
            System.out.println();
            greetUser();
        }

        else if (option.toLowerCase().equals("options"))
        {
            showOptions();
        }

        else if (option.toLowerCase().equals("open"))
        {
            if(account.isUserCustomer)
            {
                System.out.println("Error! you have already opened an account.");
            }

            else
            {
                do 
                {
                    openAccountPrompt();
                    System.out.println("\nAre these details correct? Y/n");
                    printCustomerDetails();
                    prompt();

                    if (option.toLowerCase().equals("n"))
                    {
                        areCustomerDetailsCorrect = false;
                    }

                    else
                    {
                        areCustomerDetailsCorrect = true;
                        account.openAccount();
                        System.out.println("\nCongratulations! You have successfully opened a new account.");
                    }
                } while(!areCustomerDetailsCorrect);
            }
        }

        else if (option.toLowerCase().equals("deposit"))
        {
            if (!account.isUserCustomer)
            {
                System.out.println("\nError! you must open an account before you can use this option.\n");
            }

            else
            {
                System.out.println("\nEnter amount you with to deposit.");
                prompt();
                account.depositFunds(option);
                System.out.println("\nSuccess! your new balance is: " + account.getAccountBalance());
            }
        }

        else if (option.toLowerCase().equals("check"))
        {
            if (!account.isUserCustomer)
            {
                System.out.println("\nError! you must open an account before you can use this option.\n");
            }
            System.out.println();
            System.out.println("Your account balance is: " + account.getAccountBalance());
        }

        else if (option.toLowerCase().equals("withdraw"))
        {
            if (!account.isUserCustomer)
            {
                System.out.println("\nError! you must open an account before you can use this option.\n");
            }

            else
            {
                System.out.println("\nEnter amount you with to withdraw.");
                prompt();

                if (account.checkAccountForAvailableFunds(option))
                {
                    System.out.println("\nError! you don't have the available funds in your account to complete this transaction. Your available balance is: " + account.getAccountBalance());
                }
                else
                {
                    account.withdrawFunds(option);
                    System.out.println("\nSuccess! your new balance is: " + account.getAccountBalance());
                }
            }
        }

        else
        {
            System.out.println("\nError! I didn't recognize your response, please try again.\n");
        }
    }

    public void openAccountPrompt()
    {
        System.out.println("\n\nEnter your first name.");
        prompt();
        account.setCustomerFirstName(option);

        System.out.println("\nEnter your last name.");
        prompt();
        account.setCustomerLastName(option);

        System.out.println("\nEnter your address.");
        prompt();
        account.setCustomerAddress(option);

        System.out.println("\nEnter your phone number.");
        prompt();
        account.setCustomerPhoneNumber(option);

        System.out.println("\nEnter your email address.");
        prompt();
        account.setCustomerEmailAddress(option);

        System.out.println("\nEnter amount to fund your new account.");
        prompt();

        // If left blank defaults to zero.
        if (option.equals(""))
        {
            option = "0";
        }
        account.setAccountBalance(option);
    }

    public void printCustomerDetails()
    {
        System.out.printf("Name: %s %s\nAddress: %s\nTelephone number: %s\nEmail address: %s\nBeginning balance: %s\n\n",
                          account.getCustomerFirstName(), account.getCustomerLastName(),
                          account.getCustomerAddress(),
                          account.getCustomerPhoneNumber(),
                          account.getCustomerEmailAddress(),
                          account.getAccountBalance());
    }

    public void greetUser()
    {
        System.out.println("Welcome to JBank! Type \"options\" for a list of options, \"home\" to get back here, or \"exit\" to exit.\n");
    }

    public void showOptions()
    {
        System.out.println("\nOptions: \"open\" an account, \"deposit\" funds, \"check\" balance, or make a \"withdraw\"\n");
    }
}