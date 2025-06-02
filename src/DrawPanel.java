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
    Admin admin;
    UserInterface UI;
    AdminInterface AI;

    Rectangle login;
    Rectangle adminBox;

    // back and logout functions
    Rectangle logOutButton;

    boolean logOut;
    boolean showIntroMessage;

    public DrawPanel() {
        this.addMouseListener(this);

        UI = new UserInterface();
        AI = new AdminInterface();

        users = new ArrayList<>();

        login = new Rectangle(50, 370, 160, 50);
        adminBox = new Rectangle(265, 370, 160, 50);
        logOutButton = new Rectangle(150, 80, 200, 50);


        logOut = false;
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

        g2d.drawRect(adminBox.x, adminBox.y, adminBox.width, adminBox.height);
        g.drawString("Admin", 310, 400);

        if (UI.isShowLogIn()) {
            AI.setDoAdmin(false);
            AI.setShowAdmin(false);

            showIntroMessage = false;
            UI.showLogin(g, users);
        }
        if (UI.isVerifyCreateNewAcc()) {
            UI.verifyCreateNewAcc(g);
        }
        if (UI.isVerifiedClick()) {
            UI.setVerifyCreateNewAcc(false);
            user = new User(UI.getUsername(), UI.getPassword());
            users.add(user);
            System.out.println(users);
            UI.setActivateUser(true);
            UI.setDoUser(false);
            UI.setDoPass(false);
        }
        if (UI.isActivateUser()) {
            g.drawString("Logged in as: " + user.getUsername(), 5, 20);
            g2d.drawRect(logOutButton.x, logOutButton.y, logOutButton.width, logOutButton.height);
            UI.activateUser(g);
        }
        if (UI.isDoDeposit()) {
            UI.doDeposit(g, user);
        }
        if (UI.isDoBalance()) {
            UI.doBalance(g, user);
        }
        if (UI.isDoWithdraw()) {
            UI.doWithdraw(g, user);
        }
        if (AI.isShowAdmin()) {
            UI.setShowLogIn(false);
            if (!users.isEmpty()) {
                UI.setActivateUser(false);
                UI.setDoDeposit(false);
                UI.setDoBalance(false);
                UI.setDoWithdraw(false);
                UI.setDoPass(false);
                UI.setDoUser(false);
                AI.adminLogIn(g, users, user);
            }
            else {
                g.drawString("You must create a user account.", 70, 110);
            }
        }
        if (AI.isDoAdmin()) {
            admin = new Admin(user.getUsername(), user.getPassword());
            g2d.drawRect(logOutButton.x, logOutButton.y, logOutButton.width, logOutButton.height);
            AI.doAdmin(g, user);
        }
        if (AI.isUpdateNewBalance()) {
            AI.updateNewBalance(g, user, admin);
        }
        if (AI.isUpdateNewPassword()) {
            AI.updateNewPassword(g, user, admin);
        }
        if (AI.isGoBack()) {
            AI.goBack();
        }
        if (UI.isGoBack()) {
            UI.goBack();
            logOut = false;
            AI.setShowAdmin(false);
        }
        if (logOut) {
            logOut();
        }
    }

    public void logOut() { // keep
        logOut = false;
        UI.logOut();
        AI.logOut();
    }

    public void mousePressed(MouseEvent e) {
        Point clicked = e.getPoint();

        if (e.getButton() == 1) {
            if (login.contains(clicked)) {
                UI.setShowLogIn(!UI.isShowLogIn());
            }
            if (adminBox.contains(clicked)) {
                AI.setShowAdmin(!AI.isShowAdmin());
            }
            if (UI.getUsernameBox().contains(clicked)) {
                UI.setDoUser(!UI.isDoUser());
            }
            if (UI.getPasswordBox().contains(clicked)) {
                UI.setDoPass(!UI.isDoPass());
            }
            if (!UI.isShowLogIn() && !UI.isDoBalance() && !UI.isDoWithdraw() && !UI.isDoDeposit() && !AI.isShowAdmin() && AI.isDoAdmin()) {
                if (AI.getUpdateBalance().contains(clicked)) {
                    AI.setUpdateNewBalance(true);
                }
            }
            if (!UI.isShowLogIn() && !UI.isDoBalance() && !UI.isDoWithdraw() && !UI.isDoDeposit() && !AI.isShowAdmin() && AI.isDoAdmin()) {
                if (AI.getUpdatePassword().contains(clicked)) {
                    AI.setUpdateNewPassword(true);
                }
            }
            if (!UI.isShowLogIn() && !UI.isDoBalance() && !UI.isDoWithdraw() && AI.isShowAdmin()) {
                if (AI.getAdminNameBox().contains(clicked)) {
                    AI.setDoAdminUser(!AI.isDoAdminUser());
                    AI.setAdminUserComplete(false);
                }
            }
            if (!UI.isShowLogIn() && !UI.isDoBalance() && !UI.isDoWithdraw() && AI.isShowAdmin()) {
                if (AI.getAdminPassBox().contains(clicked)) {
                    AI.setDoAdminPass(!AI.isDoAdminPass());
                    AI.setAdminPassComplete(false);
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
                if (UI.getBackButton().contains(clicked)) {
                    UI.setGoBack(true);
                }
            }
            if (!AI.isShowAdmin() && (AI.isUpdateNewPassword() || AI.isUpdateNewBalance())) {
                if (AI.getBackAdminButton().contains(clicked)) {
                    AI.setGoBack(true);
                }
            }
            if (!UI.isShowLogIn() && !UI.isDoBalance() && !UI.isDoWithdraw() && !UI.isDoDeposit() && !AI.isShowAdmin() && !AI.isDoAdmin() && AI.isUpdateNewBalance()) {
                if (AI.getUpdateBalBox().contains(clicked)) {
                    AI.setUpdateBalanceTyping(true);
                }
            }
            if (!UI.isShowLogIn() && !UI.isDoBalance() && !UI.isDoWithdraw() && !UI.isDoDeposit() && !AI.isShowAdmin() && !AI.isDoAdmin() && AI.isUpdateNewPassword()) {
                if (AI.getUpdatePassBox().contains(clicked)) {
                    AI.setUpdatePasswordTyping(true);
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!UI.isShowLogIn() && !UI.isDoBalance() && !UI.isDoWithdraw() && !UI.isDoDeposit() && !AI.isShowAdmin() && !AI.isDoAdmin() && AI.isUpdateNewPassword()){
            if (e.getKeyCode() == 8) { // backspace
                String userTemp = AI.getNewAdminPass().substring(0, AI.getNewAdminPass().length() - 1);
                AI.setNewAdminPass(userTemp);
            } else {
                AI.setNewAdminPass(AI.getNewAdminPass() + e.getKeyChar());
            }
        }
        if (AI.isUpdateNewPassword() && AI.isUpdatePasswordTyping()) {
            if (e.getKeyCode() == 10) {
                AI.setUpdatePassDone(true);
            }
        }
        if (!UI.isShowLogIn() && !UI.isDoBalance() && !UI.isDoWithdraw() && !UI.isDoDeposit() && !AI.isShowAdmin() && !AI.isDoAdmin() && AI.isUpdateNewBalance()) {
            if (e.getKeyCode() == 8) { // backspace
                String tempTwo = AI.getNewTempAdminBal().substring(0, AI.getNewTempAdminBal().length() - 1);
                AI.setNewTempAdminBal(tempTwo);
            } else {
                if (Character.isDigit(e.getKeyChar())) { // can only type int
                    AI.setNewTempAdminBal(AI.getNewTempAdminBal() + e.getKeyChar());
                }
            }
            AI.setNewAdminBal( Integer.parseInt(AI.getNewTempAdminBal()));
        }
        if (AI.isUpdateNewBalance() && AI.isUpdateBalanceTyping()) {
            if (e.getKeyCode() == 10) {
                AI.setUpdateBalanceDone(true);
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
        if (AI.isDoAdminUser() && !UI.isDoUser() && !UI.isDoPass()) {
            if (e.getKeyCode() == 8) { // backspace
                String userTemp = AI.getAdminUser().substring(0, AI.getAdminUser().length() - 1);
                AI.setAdminUser(userTemp);
            } else {
                AI.setAdminUser(AI.getAdminUser() + e.getKeyChar());
            }
        }
        if (AI.isDoAdminPass() && !UI.isDoUser() && !UI.isDoPass()) {
            if (e.getKeyCode() == 8) { // backspace
                String passTemp = AI.getAdminPass().substring(0, AI.getAdminPass().length() - 1);
                AI.setAdminPass(passTemp);
            } else {
                AI.setAdminPass(AI.getAdminPass() + e.getKeyChar());
            }
        }
        if (AI.isDoAdminUser() && !UI.isDoUser() && !UI.isDoPass()) {
            if (e.getKeyCode() == 10) { // Enter key
                AI.setDoAdminUser(false);
                AI.setAdminUserComplete(true);
            }
        }
        if (AI.isDoAdminPass() && !UI.isDoUser() && !UI.isDoPass()) {
            if (e.getKeyCode() == 10) { // Enter key
                AI.setDoAdminPass(false);
                AI.setAdminPassComplete(true);
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