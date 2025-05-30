import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;

class DrawPanel extends JPanel implements MouseListener, KeyListener {

    ArrayList<User> users;
    User user;
    Admin admin1;
    UserInterface UI;

    // rectangles
    Rectangle login;
    Rectangle admin;
    Rectangle newAdmin;
    Rectangle adminNameBox;
    Rectangle adminPassBox;


    // back buttons
    Rectangle backButton;
    Rectangle backAdminButton;
    Rectangle logOutButton;

    // admin function boxes
    Rectangle updateBalance;
    Rectangle updatePassword;
    Rectangle updateBalBox;
    Rectangle updatePassBox;

    // text
    String adminUser;
    String adminPass;
    String newAdminPass;
    String newTempAdminBal;
    String newBalanceFinal;
    String newPassFinal;

    // int
    int newAdminBal;

    // booleans

    boolean showAdmin;
    boolean doAdmin;
    boolean doAdminUser;
    boolean doAdminPass;
    boolean adminUserComplete;
    boolean adminPassComplete;

    // admin
    boolean updateNewPassword;
    boolean updateNewBalance;
    boolean updatePasswordTyping;
    boolean updatePassDone;
    boolean updateBalanceTyping;
    boolean updateBalanceDone;
    boolean updateBalanceOnce;
    boolean updatePasswordOnce;


    // back and logout functions
    boolean logOut;
    boolean goBack;
    boolean goAdminBack;
    boolean showIntroMessage;

    public DrawPanel() {
        this.addMouseListener(this);

        UI = new UserInterface();

        login = new Rectangle(50, 370, 160, 50);
        admin = new Rectangle(265, 370, 160, 50);
        newAdmin = new Rectangle();
        adminNameBox = new Rectangle(90, 170, 290, 30);
        adminPassBox = new Rectangle(90, 230, 290, 30);

        backButton = new Rectangle(370, 330, 70, 30);
        backAdminButton = new Rectangle(370, 330, 70, 30);
        logOutButton = new Rectangle(150, 80, 200, 50);

        updateBalance = new Rectangle(150, 150, 200, 50);
        updatePassword = new Rectangle(150, 220, 200, 50);
        updateBalBox = new Rectangle(70, 146, 290, 30);
        updatePassBox = new Rectangle(70, 146, 290, 30);

        adminUser = "";
        newTempAdminBal = "";
        newAdminPass = "";
        adminPass = "";
        newBalanceFinal = "";
        newPassFinal = "";


        users = new ArrayList<>();

        doAdmin = false;
        doAdminUser = false;
        doAdminPass = false;
        adminUserComplete = false;
        adminPassComplete = false;

        showAdmin = false;

        updateNewPassword = false;
        updateNewBalance = false;
        updatePasswordTyping = false;
        updatePassDone = false;
        updateBalanceTyping = false;
        updateBalanceDone = false;
        updateBalanceOnce = true;
        updatePasswordOnce = true;

        newAdminBal = 0;

        logOut = false;
        goBack = false;
        goAdminBack = false;
        showIntroMessage = true;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // user chooses if they want to sign in to user or admin
        Graphics2D g2d = (Graphics2D) g;
        g.setFont(new Font("Courier New", Font.BOLD, 18));

        if (showIntroMessage) {
            g.drawString("Click the boxes to begin!", 90, 150);
        }

        g2d.drawRect(login.x, login.y, login.width, login.height);
        g.drawString("Login", 100, 400);

        g2d.drawRect(admin.x, admin.y, admin.width, admin.height);
        g.drawString("Admin", 310, 400);

        if (showLogIn) {
            doAdmin = false; // only show user login
            showAdmin = false;
            showIntroMessage = false;
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
        if (verifyCreateNewAcc) {
            g2d.drawRect(createNewAccount.x, createNewAccount.y, createNewAccount.width, createNewAccount.height);
            g.drawString("We couldn't find your account.", 80, 130);
            g.drawString("Create a new account?", 118, 190);
        }
        if (verifiedClick) {
            verifyClick();
        }
        if (activateUser) {
            verifiedClick = false;
            showLogIn = false;
            g.drawString("Logged in as: " + user.getUsername(), 5, 20);
            g2d.drawRect(logOutButton.x, logOutButton.y, logOutButton.width, logOutButton.height);
            g.drawString("Return to login", 168, 110);
            g2d.drawRect(deposit.x, deposit.y, deposit.width, deposit.height);
            g.drawString("Deposit", 210, 180);
            g2d.drawRect(withdraw.x, withdraw.y, withdraw.width, withdraw.height);
            g.drawString("Withdraw", 205, 250);
            g2d.drawRect(balance.x, balance.y, balance.width, balance.height);
            g.drawString("Balance", 210, 320);
        }
        if (doDeposit) {
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
        if (doBalance) {
            activateUser = false;
            verifiedClick = false;
            g2d.drawRect(backButton.x, backButton.y, backButton.width, backButton.height);
            g.drawString("Back", 380, 350);
            g.drawString(user.balance(), 70, 110);
        }
        if (doWithdraw) {
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
        if (showAdmin) {
            showLogIn = false;
            if (!users.isEmpty()) {
                activateUser = false;
                doDeposit = false;
                doBalance = false;
                doWithdraw = false;
                adminLogIn(g);
            }
            else {
                g.drawString("You must create a user account.", 70, 110);
            }
        }
        if (doAdmin) {
            doAdmin(g);
        }
        if (updateNewBalance) {
            doAdmin = false;
            g2d.drawRect(backAdminButton.x, backAdminButton.y, backAdminButton.width, backAdminButton.height);
            g.drawString("Back", 380, 350);
            g.drawString("Enter your updated balance.", 70, 110);
            g.drawString("Click the box to begin: ", 70, 130);
            g2d.drawRect(updateBalBox.x, updateBalBox.y, updateBalBox.width, updateBalBox.height);
            g.drawString(newTempAdminBal, 74, 170);
            if (updateBalanceTyping) {
                g.drawString("Typing...", 70, 195);
            }
            if (updateBalanceDone) {
                updateBalanceTyping = false;
                if (updateBalanceOnce) {
                    newBalanceFinal = admin1.updateBalance(user, newAdminBal);
                    System.out.println(newBalanceFinal);
                    updateBalanceOnce = false;
                }
                g.drawString(newBalanceFinal, 60, 195);
            }
        }
        if (updateNewPassword) {
            doAdmin = false;
            g2d.drawRect(backAdminButton.x, backAdminButton.y, backAdminButton.width, backAdminButton.height);
            g.drawString("Back", 380, 350);
            g.drawString("Enter your updated password:", 70, 110);
            g.drawString("Click the box to begin: ", 70, 130);
            g2d.drawRect(updatePassBox.x, updatePassBox.y, updatePassBox.width, updatePassBox.height);
            g.drawString(newAdminPass, 74, 170);
            if (updatePasswordTyping) {
                g.drawString("Typing...", 70, 195);
            }
            if (updatePassDone) {
                updatePasswordTyping = false;
                if (updatePasswordOnce) {
                    newPassFinal = admin1.updatePassword(user, newAdminPass);
                    updateBalanceOnce = false;
                }
                g.drawString(newPassFinal, 70, 195);
            }
        }
        if (goAdminBack) {
            doAdmin = true;
            updateNewPassword = false;
            updatePasswordTyping = false;
            updatePassDone = false;
            updateNewBalance = false;
            updateBalanceTyping = false;
            updateBalanceOnce = true;
            updateBalanceDone = false;
            newTempAdminBal = "";
            newAdminPass = "";
            newPassFinal = "";
            newBalanceFinal = "";
            goAdminBack = false;
        }
        if (goBack) {
            goBack();
        }
        if (logOut) {
            logOut();
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void doAdmin(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        admin1 = new Admin(user.getUsername(), user.getPassword());
        g.drawString("Logged in as admin for: " + user.getUsername(), 5, 20);
        g2d.drawRect(updateBalance.x, updateBalance.y, updateBalance.width, updateBalance.height);
        g2d.drawRect(updatePassword.x, updatePassword.y, updatePassword.width, updatePassword.height);
        g2d.drawRect(logOutButton.x, logOutButton.y, logOutButton.width, logOutButton.height);
        g.drawString("Update Balance", 175, 180);
        g.drawString("Update Password", 170, 250);
        g.drawString("Return to login", 168, 110);
    }

    public void adminLogIn(Graphics g) {
        doPass = false;
        doUser = false;
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRect(adminNameBox.x, adminNameBox.y, adminNameBox.width, adminNameBox.height);
        g.drawString("username", 90, 163);
        g2d.drawRect(adminPassBox.x, adminPassBox.y, adminPassBox.width, adminPassBox.height);
        g.drawString("password", 90, 222);
        g.drawString(adminUser, 95, 190);
        g.drawString(adminPass, 95, 250);
        g.drawString("Re-enter your login credentials.", 90, 300);
        if (doAdminUser) {
            g.drawString("Typing...", 332, 163);
            doAdminPass = false;
        }
        if (doAdminPass) {
            g.drawString("Typing...", 325, 222);
            doAdminUser = false;
        }
        if (adminUserComplete) {
            g.drawString("Entered!", 322, 163);
        }
        if (adminPassComplete) {
            g.drawString("Entered!", 322, 222);
        }
        if (adminUserComplete && adminPassComplete) {
            if (adminUser.equals(user.getUsername()) && adminPass.equals(user.getPassword())) {
                doAdmin = true;
                showAdmin = false;
            }
            if (adminUser.equals(user.getUsername()) && !adminPass.equals(user.getPassword())) {
                g.drawString("Incorrect password. Please retry.", 80, 120);
            }
            else {
                g.drawString("This user does not exist.", 80, 120);
            }
        }
    }

    public void verifyClick() {
        verifyCreateNewAcc = false;
        user = new User(username, password);
        users.add(user);
        System.out.println(users);
        activateUser = true;
        doUser = false;
        doPass = false;
    }

    public void goBack() {
        logOut = false;
        showAdmin = false;
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
        showAdmin = false;
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
        logOut = false;
        showLogIn = true;
        username = "";
        password = "";
        depositTemp = "";
        withdrawTemp = "";
        withdrawFinal = "";
        doAdmin = false;
        updateNewPassword = false;
        updatePasswordTyping = false;
        updatePassDone = false;
        updateNewBalance = false;
        updateBalanceTyping = false;
        updateBalanceOnce = true;
        updatePasswordOnce = true;
        updateBalanceDone = false;
        newTempAdminBal = "";
        newAdminPass = "";
        newPassFinal = "";
        newBalanceFinal = "";
        goAdminBack = false;
    }

    public void mousePressed(MouseEvent e) {
        Point clicked = e.getPoint();

        if (e.getButton() == 1) {
            if (login.contains(clicked)) {
                UI.setShowLogIn(!UI.isShowLogIn());
            }
            if (admin.contains(clicked)) {
                showAdmin = !showAdmin;
            }
            if (UI.getUsernameBox().contains(clicked)) {
                UI.setDoUser(!UI.isDoUser());
            }
            if (UI.getPasswordBox().contains(clicked)) {
                UI.setDoPass(!UI.isDoPass());
            }
            if (!UI.isShowLogIn() && !UI.isDoBalance() && !UI.isDoWithdraw() && !UI.isDoDeposit() && !showAdmin && doAdmin) {
                if (updateBalance.contains(clicked)) {
                    updateNewBalance = true;
                }
            }
            if (!UI.isShowLogIn() && !UI.isDoBalance() && !UI.isDoWithdraw() && !UI.isDoDeposit() && !showAdmin && doAdmin) {
                if (updatePassword.contains(clicked)) {
                    updateNewPassword = true;
                }
            }
            if (!UI.isShowLogIn() && !UI.isDoBalance() && !UI.isDoWithdraw() && showAdmin) {
                if (adminNameBox.contains(clicked)) {
                    doAdminUser = !doAdminUser;
                    adminUserComplete = false;
                }
            }
            if (!UI.isShowLogIn() && !UI.isDoBalance() && !UI.isDoWithdraw() && showAdmin) {
                if (adminPassBox.contains(clicked)) {
                    doAdminPass = !doAdminPass;
                    adminPassComplete = false;
                }
            }
            if (UI.isActivateUser()) {
                if (UI.getWithdraw().contains(clicked)) {
                    UI.setDoWithdraw(true);
                }
                if (UI.getBalance().contains(clicked)) {
                    UI.setDoBalance(true);
                }
                if (UI.getDeposit().contains(clicked)) {
                    UI.setDoDeposit(true);
                }
            }
            if (logOutButton.contains(clicked)) {
                logOut = true;
            }
            if (UI.isVerifyCreateNewAcc()) {
                if (UI.getCreateNewAccount().contains(clicked)) {
                    UI.setVerifiedClick(true);
                }
            }
            if (UI.isDoDeposit()) {
                if (UI.getDepositBox().contains(clicked)) {
                    UI.setDepositTyping(!UI.isDepositTyping());
                }
            }
            if (UI.isDoWithdraw()) {
                if (UI.getWithdrawBox().contains(clicked)) {
                    UI.setWithdrawTyping(!UI.isWithdrawTyping());
                }
            }
            if (UI.isDoDeposit() || UI.isDoBalance() || UI.isDoWithdraw()) {
                if (backButton.contains(clicked)) {
                    goBack = true;
                }
            }
            if (!showAdmin && (updateNewPassword || updateNewBalance)) {
                if (backAdminButton.contains(clicked)) {
                    goAdminBack = true;
                }
            }
            if (!UI.isShowLogIn() && !UI.isDoBalance() && !UI.isDoWithdraw() && !UI.isDoDeposit() && !showAdmin && !doAdmin && updateNewBalance) {
                if (updateBalBox.contains(clicked)) {
                    updateBalanceTyping = true;
                }
            }
            if (!UI.isShowLogIn() && !UI.isDoBalance() && !UI.isDoWithdraw() && !UI.isDoDeposit() && !showAdmin && !doAdmin && updateNewPassword) {
                if (updatePassBox.contains(clicked)) {
                    updatePasswordTyping = true;
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!UI.isShowLogIn() && !UI.isDoBalance() && !UI.isDoWithdraw() && !UI.isDoDeposit() && !showAdmin && !doAdmin && updateNewPassword){
            if (e.getKeyCode() == 8) { // backspace
                String userTemp = newAdminPass.substring(0, newAdminPass.length() - 1);
                newAdminPass = userTemp;
            } else {
                newAdminPass += e.getKeyChar();
            }
        }
        if (updateNewPassword && updatePasswordTyping) {
            if (e.getKeyCode() == 10) {
                updatePassDone = true;
            }
        }
        if (!UI.isShowLogIn() && !UI.isDoBalance() && !UI.isDoWithdraw() && !UI.isDoDeposit() && !showAdmin && !doAdmin && updateNewBalance) {
            if (e.getKeyCode() == 8) { // backspace
                String tempTwo = newTempAdminBal.substring(0, newTempAdminBal.length() - 1);
                newTempAdminBal = tempTwo;
            } else {
                if (Character.isDigit(e.getKeyChar())) { // can only type int
                    newTempAdminBal += e.getKeyChar();
                }
            }
            newAdminBal = Integer.parseInt(newTempAdminBal);
        }
        if (updateNewBalance && updateBalanceTyping) {
            if (e.getKeyCode() == 10) {
                updateBalanceDone = true;
            }
        }
        if (UI.isDoUser()) {
            if (e.getKeyCode() == 8) { // backspace
                String userTemp = UI.getUsername().substring(0, UI.getUsername().length() - 1);
                UI.setUsername(userTemp);
            } else {
                UI.setUsername(UI.getUsername() + e.getKeyChar());
            }
        }
        if (UI.isDoPass()) {
            if (e.getKeyCode() == 8) { // backspace
                String passTemp = UI.getPassword().substring(0, UI.getPassword().length() - 1);
                UI.setPassword(passTemp);
            } else {
                UI.setPassword(UI.getPassword() + e.getKeyChar());
            }
        }
        if (UI.isDoUser()) {
            if (e.getKeyCode() == 10) { // enter
                UI.setDoUser(false);
                UI.setUserComplete(true);
                UI.setUsernameDone(true);
            }
        }
        if (UI.isDoPass()) {
            if (e.getKeyCode() == 10) { // enter
                UI.setDoPass(false);
                UI.setUserComplete(true);
                UI.setPasswordDone(true);
            }
        }
        if (doAdminUser && !UI.isDoUser() && !UI.isDoPass()) {
            if (e.getKeyCode() == 8) { // backspace
                String userTemp = adminUser.substring(0, adminUser.length() - 1);
                adminUser = userTemp;
            } else {
                adminUser += e.getKeyChar();
            }
        }
        if (doAdminPass && !UI.isDoUser() && !UI.isDoPass()) {
            if (e.getKeyCode() == 8) { // backspace
                String passTemp = adminPass.substring(0, adminPass.length() - 1);
                adminPass = passTemp;
            } else {
                adminPass += e.getKeyChar();
            }
        }
        if (doAdminUser && !UI.isDoUser() && !UI.isDoPass()) {
            if (e.getKeyCode() == 10) { // Enter key
                doAdminUser = false;
                adminUserComplete = true;
            }
        }
        if (doAdminPass && !UI.isDoUser() && !UI.isDoPass()) {
            if (e.getKeyCode() == 10) { // Enter key
                doAdminPass = false;
                adminPassComplete = true;
            }
        }
        if (UI.isDoDeposit()) {
            if (e.getKeyCode() == 8) { // backspace
                String tempOne = UI.getDepositTemp().substring(0, UI.getDepositTemp().length() - 1);
                UI.setDepositTemp(tempOne);
            } else {
                if (Character.isDigit(e.getKeyChar())) { // can only type int
                    UI.setDepositTemp(UI.getDepositTemp() + e.getKeyChar());
                }
            }
            UI.setDepositAmt(Integer.parseInt(UI.getDepositTemp()));
        }
        if (UI.isDoDeposit()) {
            if (e.getKeyCode() == 10) { // enter
                UI.setDepositDone(true);
            }
        }
        if (UI.isDoWithdraw()) {
            if (e.getKeyCode() == 8) { // backspace
                String tempTwo = UI.getWithdrawTemp().substring(0, UI.getWithdrawTemp().length() - 1);
                UI.setWithdrawTemp(tempTwo);
            } else {
                if (Character.isDigit(e.getKeyChar())) { // can only type int
                    UI.setWithdrawTemp(UI.getWithdrawTemp() + e.getKeyChar());
                }
            }
            UI.setWithdrawAmt(Integer.parseInt(UI.getWithdrawTemp()));
        }
        if (UI.isDoWithdraw()) {
            if (e.getKeyCode() == 10) {
                UI.setWithdrawDone(true);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}