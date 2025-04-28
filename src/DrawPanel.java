import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;

class DrawPanel extends JPanel implements MouseListener, KeyListener {

    ArrayList<User> users = new ArrayList<>();
    boolean drawRectangle = true;

    // rectangles
    Rectangle createAccount;
    Rectangle createAdmin;
    Rectangle newUser;
    Rectangle newAdmin;
    Rectangle deposit;
    Rectangle withdraw;

    // text
    String userName;
    String password;

    // booleans
    boolean showLogIn;
    boolean showCreateAdmin;
    boolean highlight;

    public DrawPanel() {
        this.addMouseListener(this);
        createAccount = new Rectangle(50, 370, 160, 50);
        createAdmin= new Rectangle(265, 370, 160, 50);
        newAdmin = new Rectangle();
        newUser = new Rectangle();
        deposit = new Rectangle();
        withdraw = new Rectangle();

        userName = "";

        showLogIn = true;
        showCreateAdmin = true;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g.setFont(new Font("Courier New", Font.BOLD, 20));

//        if (showLogIn) {
//            g2d.drawRect(createAccount.x, createAccount.y, createAccount.width, createAccount.height);
//            g.drawString("Login", 100, 400);
//        }
//        if (showCreateAdmin) {
//            g2d.drawRect(createAdmin.x, createAdmin.y, createAdmin.width, createAdmin.height);
//            g.drawString("Admin", 310, 400);
//        }

        if (highlight) {

        }
        g.drawString(userName, 10, 15);
    }
    

    public void mousePressed(MouseEvent e) {
        Point clicked = e.getPoint();

        if (e.getButton() == 1) {
            if (createAccount.contains(clicked)) {
                showLogIn = !showLogIn;
                showCreateAdmin = !showCreateAdmin;
            }
            if (createAdmin.contains(clicked)) {
                showLogIn = !showLogIn;
                showCreateAdmin = !showCreateAdmin;
            }
        }
    }

    public void hideRectangle() {
        drawRectangle = false;
        repaint(); // Ensure the component is redrawn without the rectangle
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
        if (e.getKeyCode() == 8) {
            String temp = userName.substring(0, userName.length() - 1);
            userName = temp;
        }
        else {
            userName += e.getKeyChar();
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}