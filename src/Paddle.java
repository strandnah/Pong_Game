import javax.swing.*;
import java.awt.*;

public class Paddle {
    int topX, topY, height, width;
    JFrame frame;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    private GraphicsDevice gd = ge.getDefaultScreenDevice();
    private GraphicsConfiguration gc = gd.getDefaultConfiguration();
    private Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(gc);

    public Paddle(int topX, int topY, double height, double width) {
        this.topX = topX;
        this.topY = topY;
        this.height = (int) height;
        this.width = (int) width;
        frame = new JFrame();
        frame.setUndecorated(true);
        frame.setSize(this.width, this.height);
        frame.setLocation(topX, topY);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public int getTopX() {
        return topX;
    }

    public int getTopY() {
        return topY;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void moveUp() {
        topY -= height / 2;
        if (topY <= 0) {
            topY = 0;
        }
    }

    public void moveDown() {
        topY += height / 2;
        if (topY + height > screenSize.height - insets.bottom) {
            topY = screenSize.height - insets.bottom - height;
        }
    }

    public void paint() {
        frame.setLocation(topX, topY);
    }

}