public class Admin extends User {
    private String username;
    private String password;

    public Admin(String username, String password) {
        super(username, password);
    }

    public boolean verifyAdmin(String admin)
    {
        return username.equals(admin);
    }

    public boolean verifyPin(String enteredPass) {
        return super.verifyPin(enteredPass);
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

    public void updatePin(User user, String newPass) {
        user.setPin(newPass);
        System.out.println("Pin updated successfully to: " + newPass);
    }

}