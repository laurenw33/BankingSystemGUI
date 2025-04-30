public class User {
    private String username;
    private String password;

    private int balance;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0;
    }

    public void setPin(String passNew) {
        password = passNew;
    }


    public boolean verifyPin(String enteredPass) {
        return password.equals(enteredPass);
    }

    public void deposit(int deposit)
    {
        if (deposit >= 0) {
            balance += deposit;
            System.out.println("Your balance after depositing " + deposit + " dollars is: $" + balance);
        }
        else {
            System.out.println("You must deposit a positive value!");
        }
    }

    public void withdraw(int withdraw)
    {
        if (withdraw <= balance) {
            if (withdraw >= 0) {
                balance -= withdraw;
                System.out.println("Your balance after withdrawing " + withdraw + " dollars is: $" + balance);
            }
            else {
                System.out.println("You must deposit a positive value!");
            }
        }
        else {
            System.out.println("Insufficient balance. Your current balance is: $" + balance);
        }
    }

    public void balance()
    {
        System.out.println("Your balance is: $" + balance);
    }

    public void setBalance(int balanceNew) {
        balance = balanceNew;
    }

    public boolean verifyUser(String user)
    {
        return username.equals(user);
    }
}