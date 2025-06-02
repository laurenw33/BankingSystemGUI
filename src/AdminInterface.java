import java.awt.*;
import java.util.ArrayList;

public class AdminInterface {
    // rectangles
    Rectangle newAdmin;
    Rectangle adminNameBox;
    Rectangle adminPassBox;

    // back buttons
    Rectangle backAdminButton;

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

    boolean goBack;

    // admin
    boolean updateNewPassword;
    boolean updateNewBalance;
    boolean updatePasswordTyping;
    boolean updatePassDone;
    boolean updateBalanceTyping;
    boolean updateBalanceDone;
    boolean updateBalanceOnce;
    boolean updatePasswordOnce;

    public AdminInterface() {
        newAdmin = new Rectangle();
        adminNameBox = new Rectangle(90, 170, 290, 30);
        adminPassBox = new Rectangle(90, 230, 290, 30);

        backAdminButton = new Rectangle(370, 330, 70, 30);

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

        doAdmin = false;
        doAdminUser = false;
        doAdminPass = false;
        adminUserComplete = false;
        adminPassComplete = false;

        showAdmin = false;
        goBack = false;

        updateNewPassword = false;
        updateNewBalance = false;
        updatePasswordTyping = false;
        updatePassDone = false;
        updateBalanceTyping = false;
        updateBalanceDone = false;
        updateBalanceOnce = true;
        updatePasswordOnce = true;

        newAdminBal = 0;
    }

    public void adminLogIn(Graphics g, ArrayList<User> users, User user) {
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

    public void doAdmin(Graphics g, User user) {
        Graphics2D g2d = (Graphics2D) g;
        g.drawString("Logged in as admin for: " + user.getUsername(), 5, 20);
        g2d.drawRect(updateBalance.x, updateBalance.y, updateBalance.width, updateBalance.height);
        g2d.drawRect(updatePassword.x, updatePassword.y, updatePassword.width, updatePassword.height);
        g.drawString("Update Balance", 175, 180);
        g.drawString("Update Password", 170, 250);
        g.drawString("Return to login", 168, 110);
    }

    public void updateNewBalance(Graphics g, User user, Admin admin) {
        Graphics2D g2d = (Graphics2D) g;
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
                newBalanceFinal = admin.updateBalance(user, newAdminBal);
                System.out.println(newBalanceFinal);
                updateBalanceOnce = false;
            }
            g.drawString(newBalanceFinal, 60, 195);
        }
    }

    public void updateNewPassword(Graphics g, User user, Admin admin) {
        Graphics2D g2d = (Graphics2D) g;
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
                newPassFinal = admin.updatePassword(user, newAdminPass);
                updateBalanceOnce = false;
            }
            g.drawString(newPassFinal, 70, 195);
        }
    }

    public void goBack() {
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
        goBack = false;
    }

    public void logOut() {
        showAdmin = false;
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
    }

    public boolean isGoBack() {
        return goBack;
    }

    public void setGoBack(boolean goBack) {
        this.goBack = goBack;
    }

    public Rectangle getNewAdmin() {
        return newAdmin;
    }

    public void setNewAdmin(Rectangle newAdmin) {
        this.newAdmin = newAdmin;
    }

    public Rectangle getAdminNameBox() {
        return adminNameBox;
    }

    public void setAdminNameBox(Rectangle adminNameBox) {
        this.adminNameBox = adminNameBox;
    }

    public Rectangle getAdminPassBox() {
        return adminPassBox;
    }

    public void setAdminPassBox(Rectangle adminPassBox) {
        this.adminPassBox = adminPassBox;
    }

    public Rectangle getBackAdminButton() {
        return backAdminButton;
    }

    public void setBackAdminButton(Rectangle backAdminButton) {
        this.backAdminButton = backAdminButton;
    }

    public Rectangle getUpdateBalance() {
        return updateBalance;
    }

    public void setUpdateBalance(Rectangle updateBalance) {
        this.updateBalance = updateBalance;
    }

    public Rectangle getUpdatePassword() {
        return updatePassword;
    }

    public void setUpdatePassword(Rectangle updatePassword) {
        this.updatePassword = updatePassword;
    }

    public Rectangle getUpdateBalBox() {
        return updateBalBox;
    }

    public void setUpdateBalBox(Rectangle updateBalBox) {
        this.updateBalBox = updateBalBox;
    }

    public Rectangle getUpdatePassBox() {
        return updatePassBox;
    }

    public void setUpdatePassBox(Rectangle updatePassBox) {
        this.updatePassBox = updatePassBox;
    }

    public String getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(String adminUser) {
        this.adminUser = adminUser;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }

    public String getNewAdminPass() {
        return newAdminPass;
    }

    public void setNewAdminPass(String newAdminPass) {
        this.newAdminPass = newAdminPass;
    }

    public String getNewTempAdminBal() {
        return newTempAdminBal;
    }

    public void setNewTempAdminBal(String newTempAdminBal) {
        this.newTempAdminBal = newTempAdminBal;
    }

    public String getNewBalanceFinal() {
        return newBalanceFinal;
    }

    public void setNewBalanceFinal(String newBalanceFinal) {
        this.newBalanceFinal = newBalanceFinal;
    }

    public String getNewPassFinal() {
        return newPassFinal;
    }

    public void setNewPassFinal(String newPassFinal) {
        this.newPassFinal = newPassFinal;
    }

    public int getNewAdminBal() {
        return newAdminBal;
    }

    public void setNewAdminBal(int newAdminBal) {
        this.newAdminBal = newAdminBal;
    }

    public boolean isShowAdmin() {
        return showAdmin;
    }

    public void setShowAdmin(boolean showAdmin) {
        this.showAdmin = showAdmin;
    }

    public boolean isDoAdmin() {
        return doAdmin;
    }

    public void setDoAdmin(boolean doAdmin) {
        this.doAdmin = doAdmin;
    }

    public boolean isDoAdminUser() {
        return doAdminUser;
    }

    public void setDoAdminUser(boolean doAdminUser) {
        this.doAdminUser = doAdminUser;
    }

    public boolean isDoAdminPass() {
        return doAdminPass;
    }

    public void setDoAdminPass(boolean doAdminPass) {
        this.doAdminPass = doAdminPass;
    }

    public boolean isAdminUserComplete() {
        return adminUserComplete;
    }

    public void setAdminUserComplete(boolean adminUserComplete) {
        this.adminUserComplete = adminUserComplete;
    }

    public boolean isAdminPassComplete() {
        return adminPassComplete;
    }

    public void setAdminPassComplete(boolean adminPassComplete) {
        this.adminPassComplete = adminPassComplete;
    }

    public boolean isUpdateNewPassword() {
        return updateNewPassword;
    }

    public void setUpdateNewPassword(boolean updateNewPassword) {
        this.updateNewPassword = updateNewPassword;
    }

    public boolean isUpdateNewBalance() {
        return updateNewBalance;
    }

    public void setUpdateNewBalance(boolean updateNewBalance) {
        this.updateNewBalance = updateNewBalance;
    }

    public boolean isUpdatePasswordTyping() {
        return updatePasswordTyping;
    }

    public void setUpdatePasswordTyping(boolean updatePasswordTyping) {
        this.updatePasswordTyping = updatePasswordTyping;
    }

    public boolean isUpdatePassDone() {
        return updatePassDone;
    }

    public void setUpdatePassDone(boolean updatePassDone) {
        this.updatePassDone = updatePassDone;
    }

    public boolean isUpdateBalanceTyping() {
        return updateBalanceTyping;
    }

    public void setUpdateBalanceTyping(boolean updateBalanceTyping) {
        this.updateBalanceTyping = updateBalanceTyping;
    }

    public boolean isUpdateBalanceDone() {
        return updateBalanceDone;
    }

    public void setUpdateBalanceDone(boolean updateBalanceDone) {
        this.updateBalanceDone = updateBalanceDone;
    }

    public boolean isUpdateBalanceOnce() {
        return updateBalanceOnce;
    }

    public void setUpdateBalanceOnce(boolean updateBalanceOnce) {
        this.updateBalanceOnce = updateBalanceOnce;
    }

    public boolean isUpdatePasswordOnce() {
        return updatePasswordOnce;
    }

    public void setUpdatePasswordOnce(boolean updatePasswordOnce) {
        this.updatePasswordOnce = updatePasswordOnce;
    }
}
