import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;

class DrawPanel extends JPanel implements MouseListener {

    ArrayList<User> users = new ArrayList<>();
    boolean drawRectangle = true;

    // rectangles
    Rectangle createAccount;
    Rectangle newUser;
    Rectangle newAdmin;
    Rectangle deposit;
    Rectangle withdraw;

    public DrawPanel() {
        this.addMouseListener(this);
        createAccount = new Rectangle(147, 250, 160, 26);
        newUser = new Rectangle();
        newAdmin = new Rectangle();
        deposit = new Rectangle();
        withdraw = new Rectangle();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRect(createAccount.x, createAccount.y, createAccount.width, createAccount.height);
    }
    

    public void mousePressed(MouseEvent e) {

    }

    public void hideRectangle() {
        drawRectangle = false;
        repaint(); // Ensure the component is redrawn without the rectangle
    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}