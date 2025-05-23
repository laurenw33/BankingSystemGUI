public class User {
    private String username;
    private String password;

    private int balance;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPin(String passNew) {
        password = passNew;
    }

    public boolean verifyUsername(String user)
    {
        return username.equals(user);
    }

    public boolean verifyPin(String enteredPass) {
        return password.equals(enteredPass);
    }

    public String deposit(int deposit)
    {
        if (deposit >= 0) {
            balance += deposit;
            return "Balance: $" + balance;
        }
        else {
            return "You must deposit a positive value!";
        }
    }

    public String withdraw(int withdraw)
    {
        if (withdraw <= balance) {
            if (withdraw >= 0) {
                balance -= withdraw;
                return "Withdrew: $" + withdraw + " Balance: $" + balance;
            }
            else {
                return "You must deposit a positive value!";
            }
        }
        return "Insufficient balance. Balance: $" + balance;
    }

    public String balance()
    {
        return "Your balance is: $" + balance;
    }

    public void setBalance(int balanceNew) {
        balance = balanceNew;
    }

}