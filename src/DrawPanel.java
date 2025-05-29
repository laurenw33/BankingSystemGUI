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

    // rectangles
    Rectangle login;
    Rectangle admin;
    Rectangle newUser;
    Rectangle newAdmin;
    Rectangle usernameBox;
    Rectangle passwordBox;
    Rectangle adminNameBox;
    Rectangle adminPassBox;
    Rectangle createNewAccount;

    // user login
    Rectangle deposit;
    Rectangle withdraw;
    Rectangle balance;
    Rectangle logOutButton;

    // typing boxes for deposit and withdraw
    Rectangle depositBox;
    Rectangle withdrawBox;

    // back buttons
    Rectangle backButton;
    Rectangle backAdminButton;

    // admin function boxes
    Rectangle updateBalance;
    Rectangle updatePassword;
    Rectangle updateBalBox;
    Rectangle updatePassBox;

    // text
    String username;
    String password;
    String adminUser;
    String adminPass;
    String newAdminPass;
    String newTempAdminBal;
    String depositTemp;
    String depositFinal;
    String withdrawTemp;
    String withdrawFinal;
    String newBalanceFinal;
    String newPassFinal;

    // int
    int depositAmt;
    int withdrawAmt;
    int newAdminBal;

    // booleans
    boolean showLogIn;
    boolean showAdmin;
    boolean usernameDone;
    boolean passwordDone;
    boolean doUser;
    boolean doPass;
    boolean doAdmin;
    boolean doAdminUser;
    boolean doAdminPass;
    boolean adminUserComplete;
    boolean adminPassComplete;
    boolean userComplete;
    boolean passComplete;
    boolean activateUser;

    // admin
    boolean updateNewPassword;
    boolean updateNewBalance;
    boolean updatePasswordTyping;
    boolean updatePassDone;
    boolean updateBalanceTyping;
    boolean updateBalanceDone;
    boolean updateBalanceOnce;
    boolean updatePasswordOnce;

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

    // back and logout functions
    boolean logOut;
    boolean goBack;
    boolean goAdminBack;
    boolean showIntroMessage;

    boolean verifyCreateNewAcc;
    boolean verifiedClick;

    public DrawPanel() {
        this.addMouseListener(this);
        login = new Rectangle(50, 370, 160, 50);
        admin = new Rectangle(265, 370, 160, 50);
        usernameBox = new Rectangle(90, 170, 290, 30);
        passwordBox = new Rectangle(90, 230, 290, 30);
        newAdmin = new Rectangle();
        newUser = new Rectangle();
        createNewAccount = new Rectangle(110, 150, 250, 70);
        adminNameBox = new Rectangle(90, 170, 290, 30);
        adminPassBox = new Rectangle(90, 230, 290, 30);

        logOutButton = new Rectangle(150, 80, 200, 50);
        deposit = new Rectangle(150, 150, 200, 50);
        withdraw = new Rectangle(150, 220, 200, 50);
        balance = new Rectangle(150, 290, 200, 50);

        depositBox = new Rectangle(70, 146, 290, 30);
        withdrawBox = new Rectangle(70, 146, 290, 30);

        backButton = new Rectangle(370, 330, 70, 30);
        backAdminButton = new Rectangle(370, 330, 70, 30);

        updateBalance = new Rectangle(150, 150, 200, 50);
        updatePassword = new Rectangle(150, 220, 200, 50);
        updateBalBox = new Rectangle(70, 146, 290, 30);
        updatePassBox = new Rectangle(70, 146, 290, 30);

        username = "";
        password = "";
        adminUser = "";
        newTempAdminBal = "";
        newAdminPass = "";
        depositTemp = "";
        withdrawTemp = "";
        withdrawFinal = "";
        adminPass = "";
        newBalanceFinal = "";
        newPassFinal = "";

        depositAmt = 0;
        withdrawAmt = 0;
        newAdminBal = 0;

        users = new ArrayList<>();

        doAdmin = false;
        doAdminUser = false;
        doAdminPass = false;
        adminUserComplete = false;
        adminPassComplete = false;

        showLogIn = false;
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

        updateNewPassword = false;
        updateNewBalance = false;
        updatePasswordTyping = false;
        updatePassDone = false;
        updateBalanceTyping = false;
        updateBalanceDone = false;
        updateBalanceOnce = true;
        updatePasswordOnce = true;

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
                showLogIn = !showLogIn;
            }
            if (admin.contains(clicked)) {
                showAdmin = !showAdmin;
            }
            if (usernameBox.contains(clicked)) {
                doUser = !doUser;
            }
            if (passwordBox.contains(clicked)) {
                doPass = !doPass;
            }
            if (!showLogIn && !doBalance && !doWithdraw && !doDeposit && !showAdmin && doAdmin) {
                if (updateBalance.contains(clicked)) {
                    updateNewBalance = true;
                }
            }
            if (!showLogIn && !doBalance && !doWithdraw && !doDeposit && !showAdmin && doAdmin) {
                if (updatePassword.contains(clicked)) {
                    updateNewPassword = true;
                }
            }
            if (!showLogIn && !doBalance && !doWithdraw && showAdmin) {
                if (adminNameBox.contains(clicked)) {
                    doAdminUser = !doAdminUser;
                    adminUserComplete = false;
                }
            }
            if (!showLogIn && !doBalance && !doWithdraw && showAdmin) {
                if (adminPassBox.contains(clicked)) {
                    doAdminPass = !doAdminPass;
                    adminPassComplete = false;
                }
            }
            if (activateUser) {
                if (withdraw.contains(clicked)) {
                    doWithdraw = true;
                }
                if (balance.contains(clicked)) {
                    doBalance = true;
                }
                if (deposit.contains(clicked)) {
                    doDeposit = true;
                }
            }
            if (logOutButton.contains(clicked)) {
                logOut = true;
            }
            if (verifyCreateNewAcc) {
                if (createNewAccount.contains(clicked)) {
                    verifiedClick = true;
                }
            }
            if (doDeposit) {
                if (depositBox.contains(clicked)) {
                    depositTyping = !depositTyping;
                }
            }
            if (doWithdraw) {
                if (withdrawBox.contains(clicked)) {
                    withdrawTyping = !withdrawTyping;
                }
            }
            if (doDeposit || doBalance || doWithdraw) {
                if (backButton.contains(clicked)) {
                    goBack = true;
                }
            }
            if (!showAdmin && (updateNewPassword || updateNewBalance)) {
                if (backAdminButton.contains(clicked)) {
                    goAdminBack = true;
                }
            }
            if (!showLogIn && !doBalance && !doWithdraw && !doDeposit && !showAdmin && !doAdmin && updateNewBalance) {
                if (updateBalBox.contains(clicked)) {
                    updateBalanceTyping = true;
                }
            }
            if (!showLogIn && !doBalance && !doWithdraw && !doDeposit && !showAdmin && !doAdmin && updateNewPassword) {
                if (updatePassBox.contains(clicked)) {
                    updatePasswordTyping = true;
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!showLogIn && !doBalance && !doWithdraw && !doDeposit && !showAdmin && !doAdmin && updateNewPassword){
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
        if (!showLogIn && !doBalance && !doWithdraw && !doDeposit && !showAdmin && !doAdmin && updateNewBalance) {
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
        if (doUser) {
            if (e.getKeyCode() == 8) { // backspace
                String userTemp = username.substring(0, username.length() - 1);
                username = userTemp;
            } else {
                username += e.getKeyChar();
            }
        }
        if (doPass) {
            if (e.getKeyCode() == 8) { // backspace
                String passTemp = password.substring(0, password.length() - 1);
                password = passTemp;
            } else {
                password += e.getKeyChar();
            }
        }
        if (doUser) {
            if (e.getKeyCode() == 10) { // enter
                doUser = false;
                userComplete = true;
                usernameDone = true;
            }
        }
        if (doPass) {
            if (e.getKeyCode() == 10) { // enter
                doPass = false;
                userComplete = true;
                passwordDone = true;
            }
        }
        if (doAdminUser && !doUser && !doPass) {
            if (e.getKeyCode() == 8) { // backspace
                String userTemp = adminUser.substring(0, adminUser.length() - 1);
                adminUser = userTemp;
            } else {
                adminUser += e.getKeyChar();
            }
        }
        if (doAdminPass && !doUser && !doPass) {
            if (e.getKeyCode() == 8) { // backspace
                String passTemp = adminPass.substring(0, adminPass.length() - 1);
                adminPass = passTemp;
            } else {
                adminPass += e.getKeyChar();
            }
        }
        if (doAdminUser && !doUser && !doPass) {
            if (e.getKeyCode() == 10) { // Enter key
                doAdminUser = false;
                adminUserComplete = true;
            }
        }
        if (doAdminPass && !doUser && !doPass) {
            if (e.getKeyCode() == 10) { // Enter key
                doAdminPass = false;
                adminPassComplete = true;
            }
        }
        if (doDeposit) {
            if (e.getKeyCode() == 8) { // backspace
                String tempOne = depositTemp.substring(0, depositTemp.length() - 1);
                depositTemp = tempOne;
            } else {
                if (Character.isDigit(e.getKeyChar())) { // can only type int
                    depositTemp += e.getKeyChar();
                }
            }
            depositAmt = Integer.parseInt(depositTemp);
        }
        if (doDeposit) {
            if (e.getKeyCode() == 10) { // enter
                depositDone = true;
            }
        }
        if (doWithdraw) {
            if (e.getKeyCode() == 8) { // backspace
                String tempTwo = withdrawTemp.substring(0, withdrawTemp.length() - 1);
                withdrawTemp = tempTwo;
            } else {
                if (Character.isDigit(e.getKeyChar())) { // can only type int
                    withdrawTemp += e.getKeyChar();
                }
            }
            withdrawAmt = Integer.parseInt(withdrawTemp);
        }
        if (doWithdraw) {
            if (e.getKeyCode() == 10) {
                withdrawDone = true;
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