public class Admin extends User {
    private String username;
    private String password;

    public Admin(String username, String password) {
        super(username, password);
    }

    public boolean verifyUsername(String admin)
    {
        return super.verifyUsername(admin);
    }

    public boolean verifyPin(String enteredPass) {
        return super.verifyPin(enteredPass);
    }

    public String updateBalance(User user, int newBalance) {
        if (newBalance >= 0) {
            user.setBalance(newBalance);
            return "Your new balance is: $" + newBalance;
        }
        return "You must enter a positive value!";
    }

    public String updatePassword(User user, String newPass) {
        user.setPin(newPass);
        return "Pin updated successfully to: " + newPass;
    }

}