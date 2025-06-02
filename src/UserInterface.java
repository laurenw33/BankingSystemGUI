import java.awt.*;
import java.util.ArrayList;

public class UserInterface {

    DrawPanel drawPanel;

    Rectangle usernameBox;
    Rectangle passwordBox;
    Rectangle createNewAccount;
    Rectangle backButton;

    // buttons on user interface
    Rectangle deposit;
    Rectangle withdraw;
    Rectangle balance;

    // typing boxes for deposit and withdraw
    Rectangle depositBox;
    Rectangle withdrawBox;

    // text
    String username;
    String password;
    String depositTemp;
    String depositFinal;
    String withdrawTemp;
    String withdrawFinal;

    // int
    int depositAmt;
    int withdrawAmt;

    // booleans
    boolean showLogIn;
    boolean usernameDone;
    boolean passwordDone;
    boolean doUser;
    boolean doPass;
    boolean userComplete;
    boolean passComplete;
    boolean activateUser;

    // balance
    boolean doBalance;

    // withdraw
    boolean doWithdraw;
    boolean withdrawTyping;
    boolean withdrawDone;
    boolean addWithdraw;

    // deposit
    boolean doDeposit;
    boolean depositDone;
    boolean depositTyping;
    boolean addDeposit;

    boolean verifyCreateNewAcc; // asks if want to make new account
    boolean verifiedClick; // checks if they clicked that they want to create
    boolean goBack;

    public UserInterface() {
        usernameBox = new Rectangle(90, 170, 290, 30);
        passwordBox = new Rectangle(90, 230, 290, 30);
        backButton = new Rectangle(370, 330, 70, 30);

        createNewAccount = new Rectangle(110, 150, 250, 70);
        deposit = new Rectangle(150, 150, 200, 50);
        withdraw = new Rectangle(150, 220, 200, 50);
        balance = new Rectangle(150, 290, 200, 50);

        depositBox = new Rectangle(70, 146, 290, 30);
        withdrawBox = new Rectangle(70, 146, 290, 30);

        username = "";
        password = "";
        depositTemp = "";
        depositFinal = "";
        withdrawTemp = "";
        withdrawFinal = "";

        showLogIn = false;
        doUser = false;
        doPass = false;
        usernameDone = false;
        passwordDone = false;
        userComplete = false;
        passComplete = false;
        activateUser = false;
        verifyCreateNewAcc = false;
        verifiedClick = false;
        goBack = false;

        doBalance = false;

        doWithdraw = false;
        withdrawTyping = false;
        withdrawDone = false;
        addWithdraw = true;

        doDeposit = false;
        depositTyping = false;
        depositDone = false;
        addDeposit = true;

        depositAmt = 0;
        withdrawAmt = 0;
    }


    public void showLogin(Graphics g, ArrayList<User> users) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRect(usernameBox.x, usernameBox.y, usernameBox.width, usernameBox.height);
        g.drawString("username", 90, 163);
        g2d.drawRect(passwordBox.x, passwordBox.y, passwordBox.width, passwordBox.height);
        g.drawString("password", 90, 222);
        g.drawString(username, 95, 190);
        g.drawString(password, 95, 250);
        g.drawString("Click the boxes to log in.", 90, 300);
        g.drawString("Click one box at a time.", 90, 320);
        g.drawString("Hit enter when you're done.", 90, 340);
        if (doUser) {
            g.drawString("Typing...", 332, 163);
            doPass = false;
        }
        if (doPass) {
            g.drawString("Typing...", 325, 222);
            doUser = false;
        }
        if (userComplete) {
            g.drawString("Entered!", 322, 163);
        }
        if (passComplete) {
            g.drawString("Entered!", 322, 222);
        }
        if (usernameDone && passwordDone) {
            boolean hasUser = false;
            boolean hasPass = false;
            if (!users.isEmpty()) {
                for (User u : users) {
                    if (u.getUsername().equals(username)) {
                        hasUser = true;
                        if (u.getPassword().equals(password)) {
                            showLogIn = false;
                            activateUser = true;
                            hasPass = true;
                        }
                    }
                    if (hasUser && hasPass) {
                        break;
                    }
                    if (hasUser && !hasPass) {
                        g.drawString("Incorrect password. Please retry.", 80, 120);
                    }
                    else {
                        showLogIn = false;
                        verifyCreateNewAcc = true;
                    }
                }
            }
            else {
                showLogIn = false;
                verifyCreateNewAcc = true;
            }
        }
    }

    public void verifyCreateNewAcc(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawRect(createNewAccount.x, createNewAccount.y, createNewAccount.width, createNewAccount.height);
        g.drawString("We couldn't find your account.", 80, 130);
        g.drawString("Create a new account?", 118, 190);
    }

    public void activateUser(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        verifiedClick = false;
        showLogIn = false;
        g.drawString("Return to login", 168, 110);
        g2d.drawRect(deposit.x, deposit.y, deposit.width, deposit.height);
        g.drawString("Deposit", 210, 180);
        g2d.drawRect(withdraw.x, withdraw.y, withdraw.width, withdraw.height);
        g.drawString("Withdraw", 205, 250);
        g2d.drawRect(balance.x, balance.y, balance.width, balance.height);
        g.drawString("Balance", 210, 320);
    }

    public void doDeposit(Graphics g, User user){
        Graphics2D g2d = (Graphics2D) g;

        activateUser = false;
        verifiedClick = false;
        doUser = false;
        g.drawString("How much would you like to deposit?", 70, 110);
        g.drawString("Click the box to begin: ", 70, 130);
        g2d.drawRect(backButton.x, backButton.y, backButton.width, backButton.height);
        g.drawString("Back", 380, 350);
        g2d.drawRect(depositBox.x, depositBox.y, depositBox.width, depositBox.height);
        g.drawString(depositTemp, 74, 170);
        if (depositTyping) {
            g.drawString("Typing...", 70, 195);
        }
        if (depositDone) {
            depositTyping = false;
            if (addDeposit) {
                depositFinal = user.deposit(depositAmt);
                addDeposit = false;
            }
            g.drawString(depositFinal, 70, 195);
            user.balance();
        }
    }

    public void doBalance(Graphics g, User user) {
        Graphics2D g2d = (Graphics2D) g;
        activateUser = false;
        verifiedClick = false;
        g2d.drawRect(backButton.x, backButton.y, backButton.width, backButton.height);
        g.drawString("Back", 380, 350);
        g.drawString(user.balance(), 70, 110);
    }

    public void doWithdraw(Graphics g, User user) {
        Graphics2D g2d = (Graphics2D) g;
        activateUser = false;
        verifiedClick = false;
        doUser = false;
        g2d.drawRect(backButton.x, backButton.y, backButton.width, backButton.height);
        g.drawString("Back", 380, 350);
        g.drawString("How much would you like to withdraw?", 70, 110);
        g.drawString("Click the box to begin: ", 70, 130);
        g2d.drawRect(withdrawBox.x, withdrawBox.y, withdrawBox.width, withdrawBox.height);
        g.drawString(withdrawTemp, 74, 170);
        if (withdrawTyping) {
            g.drawString("Typing...", 70, 195);
        }
        if (withdrawDone) {
            withdrawTyping = false;
            if (addWithdraw) {
                withdrawFinal = user.withdraw(withdrawAmt);
                addWithdraw = false;
            }
            g.drawString(withdrawFinal, 60, 195);
            user.balance();
            withdrawTemp = "";
        }
    }

    public void goBack() { // keep

        doDeposit = false;
        doWithdraw = false;
        doBalance = false;
        depositDone = false;
        verifiedClick = false;
        activateUser = true;
        depositTyping = false;
        withdrawTyping = false;
        addDeposit = true;
        addWithdraw = true;
        withdrawDone = false;
        goBack = false;
        depositTemp = "";
        withdrawTemp = "";
        withdrawFinal = "";
    }

    public void logOut() {
        doUser = false;
        doPass = false;
        usernameDone = false;
        passwordDone = false;
        userComplete = false;
        passComplete = false;
        activateUser = false;
        verifyCreateNewAcc = false;
        verifiedClick = false;
        doBalance = false;
        doWithdraw = false;
        withdrawTyping = false;
        withdrawDone = false;
        addWithdraw = true;
        doDeposit = false;
        depositTyping = false;
        depositDone = false;
        addDeposit = true;
        showLogIn = true;
        username = "";
        password = "";
        depositTemp = "";
        withdrawTemp = "";
        withdrawFinal = "";
    }

    public Rectangle getBackButton() {
        return backButton;
    }

    public void setBackButton(Rectangle backButton) {
        this.backButton = backButton;
    }

    public boolean isGoBack() {
        return goBack;
    }

    public void setGoBack(boolean goBack) {
        this.goBack = goBack;
    }

    public Rectangle getUsernameBox() {
        return usernameBox;
    }

    public void setUsernameBox(Rectangle usernameBox) {
        this.usernameBox = usernameBox;
    }

    public Rectangle getPasswordBox() {
        return passwordBox;
    }

    public void setPasswordBox(Rectangle passwordBox) {
        this.passwordBox = passwordBox;
    }

    public Rectangle getCreateNewAccount() {
        return createNewAccount;
    }

    public void setCreateNewAccount(Rectangle createNewAccount) {
        this.createNewAccount = createNewAccount;
    }

    public Rectangle getDeposit() {
        return deposit;
    }

    public void setDeposit(Rectangle deposit) {
        this.deposit = deposit;
    }

    public Rectangle getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Rectangle withdraw) {
        this.withdraw = withdraw;
    }

    public Rectangle getBalance() {
        return balance;
    }

    public void setBalance(Rectangle balance) {
        this.balance = balance;
    }

    public Rectangle getDepositBox() {
        return depositBox;
    }

    public void setDepositBox(Rectangle depositBox) {
        this.depositBox = depositBox;
    }

    public Rectangle getWithdrawBox() {
        return withdrawBox;
    }

    public void setWithdrawBox(Rectangle withdrawBox) {
        this.withdrawBox = withdrawBox;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepositTemp() {
        return depositTemp;
    }

    public void setDepositTemp(String depositTemp) {
        this.depositTemp = depositTemp;
    }

    public String getDepositFinal() {
        return depositFinal;
    }

    public void setDepositFinal(String depositFinal) {
        this.depositFinal = depositFinal;
    }

    public int getDepositAmt() {
        return depositAmt;
    }

    public void setDepositAmt(int depositAmt) {
        this.depositAmt = depositAmt;
    }

    public int getWithdrawAmt() {
        return withdrawAmt;
    }

    public void setWithdrawAmt(int withdrawAmt) {
        this.withdrawAmt = withdrawAmt;
    }

    public String getWithdrawTemp() {
        return withdrawTemp;
    }

    public void setWithdrawTemp(String withdrawTemp) {
        this.withdrawTemp = withdrawTemp;
    }

    public String getWithdrawFinal() {
        return withdrawFinal;
    }

    public void setWithdrawFinal(String withdrawFinal) {
        this.withdrawFinal = withdrawFinal;
    }

    public boolean isShowLogIn() {
        return showLogIn;
    }

    public void setShowLogIn(boolean showLogIn) {
        this.showLogIn = showLogIn;
    }

    public boolean isUsernameDone() {
        return usernameDone;
    }

    public void setUsernameDone(boolean usernameDone) {
        this.usernameDone = usernameDone;
    }

    public boolean isPasswordDone() {
        return passwordDone;
    }

    public void setPasswordDone(boolean passwordDone) {
        this.passwordDone = passwordDone;
    }

    public boolean isDoUser() {
        return doUser;
    }

    public void setDoUser(boolean doUser) {
        this.doUser = doUser;
    }

    public boolean isDoPass() {
        return doPass;
    }

    public void setDoPass(boolean doPass) {
        this.doPass = doPass;
    }

    public boolean isUserComplete() {
        return userComplete;
    }

    public void setUserComplete(boolean userComplete) {
        this.userComplete = userComplete;
    }

    public boolean isPassComplete() {
        return passComplete;
    }

    public void setPassComplete(boolean passComplete) {
        this.passComplete = passComplete;
    }

    public boolean isActivateUser() {
        return activateUser;
    }

    public void setActivateUser(boolean activateUser) {
        this.activateUser = activateUser;
    }

    public boolean isDoBalance() {
        return doBalance;
    }

    public void setDoBalance(boolean doBalance) {
        this.doBalance = doBalance;
    }

    public boolean isDoWithdraw() {
        return doWithdraw;
    }

    public void setDoWithdraw(boolean doWithdraw) {
        this.doWithdraw = doWithdraw;
    }

    public boolean isWithdrawTyping() {
        return withdrawTyping;
    }

    public void setWithdrawTyping(boolean withdrawTyping) {
        this.withdrawTyping = withdrawTyping;
    }

    public boolean isWithdrawDone() {
        return withdrawDone;
    }

    public void setWithdrawDone(boolean withdrawDone) {
        this.withdrawDone = withdrawDone;
    }

    public boolean isAddWithdraw() {
        return addWithdraw;
    }

    public void setAddWithdraw(boolean addWithdraw) {
        this.addWithdraw = addWithdraw;
    }

    public boolean isDoDeposit() {
        return doDeposit;
    }

    public void setDoDeposit(boolean doDeposit) {
        this.doDeposit = doDeposit;
    }

    public boolean isDepositDone() {
        return depositDone;
    }

    public void setDepositDone(boolean depositDone) {
        this.depositDone = depositDone;
    }

    public boolean isDepositTyping() {
        return depositTyping;
    }

    public void setDepositTyping(boolean depositTyping) {
        this.depositTyping = depositTyping;
    }

    public boolean isAddDeposit() {
        return addDeposit;
    }

    public void setAddDeposit(boolean addDeposit) {
        this.addDeposit = addDeposit;
    }

    public boolean isVerifyCreateNewAcc() {
        return verifyCreateNewAcc;
    }

    public void setVerifyCreateNewAcc(boolean verifyCreateNewAcc) {
        this.verifyCreateNewAcc = verifyCreateNewAcc;
    }

    public boolean isVerifiedClick() {
        return verifiedClick;
    }

    public void setVerifiedClick(boolean verifiedClick) {
        this.verifiedClick = verifiedClick;
    }
}
