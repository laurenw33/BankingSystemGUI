import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;

class DrawPanel extends JPanel implements MouseListener, KeyListener {

    ArrayList<User> users;
    boolean drawRectangle = true;

    // rectangles
    private Rectangle login;
    private Rectangle admin;
    private Rectangle newUser;
    private Rectangle newAdmin;
    private Rectangle deposit;
    private Rectangle withdraw;
    private Rectangle usernameBox;
    private Rectangle passwordBox;

    // text
    private String username;
    private String password;

    // booleans
    private boolean showLogIn;
    private boolean showAdmin;
    private boolean highlight;
    private boolean usernameDone;
    private boolean passwordDone;
    private boolean doUser;
    private boolean doPass;
    private boolean userComplete;
    private boolean passComplete;

    public DrawPanel() {
        this.addMouseListener(this);
        login = new Rectangle(50, 370, 160, 50);
        admin= new Rectangle(265, 370, 160, 50);
        usernameBox = new Rectangle(90, 170, 290, 30);
        passwordBox = new Rectangle(90, 230, 290, 30);
        newAdmin = new Rectangle();
        newUser = new Rectangle();
        deposit = new Rectangle();
        withdraw = new Rectangle();

        username = "";
        password = "";

        users = new ArrayList<>();

        showLogIn = false;
        showAdmin = false;
        doUser = false;
        doPass = false;
        userComplete = false;
        passComplete = false;

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
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
            if (doUser) {
                g.drawString("Typing...", 322, 163);
                doPass = false;
            }
            if (doPass) {
                g.drawString("Typing...", 322, 222);
                doUser = false;
            }
            if (userComplete) {
                g.drawString("Entered!", 322, 163);
            }
            if (passComplete) {
                g.drawString("Entered!", 322, 222);
            }
            if (userComplete && passComplete) {

            }
        }
        if (showAdmin) {

        }

        if (highlight) {

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
            if (e.getKeyCode() == 8) {
                if (username.length() > 0) { // Add safety
                    username = username.substring(0, username.length() - 1);
                }
            } else {
                username += e.getKeyChar();
            }
        }
        if (doPass) {
            if (e.getKeyCode() == 8) {
                String temp = username.substring(0, username.length() - 1);
                password = temp;
            } else {
                password += e.getKeyChar();
            }
        }
        if (doUser) {
            if (e.getKeyCode() == 10) {
                doUser = false;
                userComplete = true;
            }
        }
        if (doPass) {
            if (e.getKeyCode() == 10) {
                doPass = false;
                passComplete = true;
            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}