import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;

class DrawPanel extends JPanel implements MouseListener, KeyListener {

    ArrayList<User> users;
    User u;

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

    Rectangle deposit;
    Rectangle withdraw;
    Rectangle balance;
    Rectangle logOutButton;

    Rectangle depositBox;

    Rectangle backButton;

    // text
    String username;
    String password;
    String adminUser;
    String adminPass;
    String depositTemp;
    String depositFinal;

    // int
    int depositAmt;

    // booleans
    boolean showLogIn;
    boolean showAdmin;
    boolean highlight;
    boolean usernameDone;
    boolean passwordDone;
    boolean doUser;
    boolean doPass;
    boolean doAdmin;
    boolean doAdminUser;
    boolean doAdminPass;
    boolean userComplete;
    boolean passComplete;
    boolean activateUser;

    boolean doBalance;

    boolean doWithdraw;

    boolean doDeposit;
    boolean depositDone;
    boolean depositTyping;
    boolean addDeposit;

    boolean logOut;
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

        backButton = new Rectangle(370, 330, 70, 30);

        username = "";
        password = "";
        depositTemp = "";

        depositAmt = 0;

        users = new ArrayList<>();

        doAdmin = false;
        doAdminUser = false;
        doAdminPass = false;

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
        doBalance = false;
        doWithdraw = false;
        doDeposit = false;
        depositTyping = false;
        depositDone = false;
        addDeposit = true;
        logOut = false;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // user chooses if they want to sign in to user or admin
        Graphics2D g2d = (Graphics2D) g;
        g.setFont(new Font("Courier New", Font.BOLD, 18));

        g2d.drawRect(login.x, login.y, login.width, login.height);
        g.drawString("Login", 100, 400);

        g2d.drawRect(admin.x, admin.y, admin.width, admin.height);
        g.drawString("Admin", 310, 400);

        if (showLogIn) {
            g2d.drawRect(usernameBox.x, usernameBox.y, usernameBox.width, usernameBox.height);
            g.drawString("username", 90, 163);
            g2d.drawRect(passwordBox.x, passwordBox.y, passwordBox.width, passwordBox.height);
            g.drawString("password", 90, 222);
            g.drawString(username, 95, 190);
            g.drawString(password, 95, 250);
            g.drawString("Click the boxes to log in.", 90, 300);
            g.drawString("Click one box at a time.", 90, 320);
            g.drawString("Hit enter when you're done.", 90, 340);
            if (doUser) { // user
                g.drawString("Typing...", 332, 163);
                doPass = false;
            }
            if (doPass) { // user
                g.drawString("Typing...", 325, 222);
                doUser = false;
            }
            if (userComplete) { // user
                g.drawString("Entered!", 322, 163);
            }
            if (passComplete) { // user
                g.drawString("Entered!", 322, 222);
            }
            if (usernameDone && passwordDone) { // user
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
            g.drawString("Logged in as: " + username, 5, 20);
            verifyCreateNewAcc = false;
            u = new User(username, password);
            users.add(u);
            activateUser = true;
        }
        if (activateUser) {
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
                    depositFinal = u.deposit(depositAmt);
                    addDeposit = false;
                }
                g.drawString(depositFinal, 70, 195);
            }
        }
        if (showAdmin) {
            activateUser = false;

        }
        if (logOut) {
            showLogIn = true;
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
            doDeposit = false;
            depositDone = false;
            username = "";
            password = "";
        }
        if (highlight) {
            activateUser = false;

        }
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
            if (withdraw.contains(clicked)) {
                doWithdraw = true;
            }
            if (balance.contains(clicked)) {
                doBalance = true;
            }
            if (activateUser) {
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
        }
    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (doUser) {
            if (e.getKeyCode() == 8) { // backspace
                String temp = username.substring(0, username.length() - 1);
                username = temp;
            } else {
                username += e.getKeyChar();
            }
        }
        if (doPass) {
            if (e.getKeyCode() == 8) { // backspace
                String temp = password.substring(0, password.length() - 1);
                password = temp;
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
        if (doDeposit) {
            if (e.getKeyCode() == 8) { // backspace
                String temp = depositTemp.substring(0, depositTemp.length() - 1);
                depositTemp = temp;
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

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}