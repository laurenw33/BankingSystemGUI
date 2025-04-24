public class Admin extends User {
    private String username;
    private int pin;

    public Admin(String username, int pin) {
        super(username, pin);
    }

    public boolean verifyAdmin(String admin)
    {
        return username.equals(admin);
    }

    public boolean verifyPin(int enteredPin) {
        return super.verifyPin(enteredPin);
    }

    public void updateBalance(User user, int newBalance) {
        if (newBalance >= 0) {
            user.setBalance(newBalance);
            System.out.println("Your new balance is: $" + newBalance);
        }
        else {
            System.out.println("You must enter a positive value!");
        }
    }

    public void updatePin(User user, int newPin) {
        if (String.valueOf(newPin).length() == 4) {
            user.setPin(newPin);
            System.out.println("Pin updated successfully to: " + newPin);
        }
        else {
            System.out.println("Error: Pin must be exactly four digits.");
        }
    }

}