import java.math.BigDecimal;

public class Account
{
    public boolean isUserCustomer = false;
    private Account customerAccount;
    private String customerFirstName;
    private String customerLastName;
    private String customerAddress;
    private String customerPhoneNumber;
    private String customerEmailAddress;
    private String stringAccountBalance;
    private BigDecimal bigDecimalAccountBalance = BigDecimal.ZERO;

    public Account()
    {

    }

    public Account(String customerFirstName, String customerLastName, String customerAddress, String customerPhoneNumber, String customerEmailAddress, String stringAccountBalance)
    {
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerAddress = customerAddress;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerEmailAddress = customerEmailAddress;
        this.stringAccountBalance = stringAccountBalance;
    }

    public String getCustomerFirstName()
    {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName)
    {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName()
    {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName)
    {
        this.customerLastName = customerLastName;
    }

    public String getCustomerAddress()
    {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress)
    {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhoneNumber()
    {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber)
    {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerEmailAddress()
    {
        return customerEmailAddress;
    }

    public void setCustomerEmailAddress(String customerEmailAddress)
    {
        this.customerEmailAddress = customerEmailAddress;
    }

    public String getAccountBalance()
    {
        return bigDecimalAccountBalance.toString();
    }

    public void setAccountBalance(String stringAccountBalance)
    {
        this.stringAccountBalance = stringAccountBalance;
        bigDecimalAccountBalance = new BigDecimal(stringAccountBalance);
    }

    public void openAccount()
    {
        customerAccount = new Account(customerFirstName, customerLastName, customerAddress, customerPhoneNumber, customerEmailAddress, stringAccountBalance);
        isUserCustomer = true;
    }

    public void depositFunds(String stringDepositAmount)
    {
        BigDecimal bigDecimalDepositAmount = new BigDecimal(stringDepositAmount);
        bigDecimalAccountBalance = bigDecimalAccountBalance.add(bigDecimalDepositAmount);
    }

    public void withdrawFunds(String stringWithdrawAmount)
    {
        BigDecimal bigDecimalWithdrawAmount = new BigDecimal(stringWithdrawAmount);
        bigDecimalAccountBalance = bigDecimalAccountBalance.subtract(bigDecimalWithdrawAmount);
    }

    public boolean checkAccountForAvailableFunds(String stringWithdrawAmount)
    {
        boolean isAvailable;
        BigDecimal bigDecimalWithdrawAmount = new BigDecimal(stringWithdrawAmount);

        // Checks to make sure there are enough funds in the account to make the withdraw.
        if (bigDecimalAccountBalance.subtract(bigDecimalWithdrawAmount).compareTo(BigDecimal.ZERO) < 0)
        {
            isAvailable = true;
        }
        else
        {
            isAvailable = false;
        }
        return isAvailable;
    }
}