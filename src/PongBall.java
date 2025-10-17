import javax.swing.*;
import java.awt.*;

public class PongBall {
    private JFrame frame;
    private int xPosition;
    private int yPosition;
    private double directionX;
    private double directionY;
    private final int size;
    private final int defaultSpeed = 700;
    private double speed;

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    private GraphicsDevice gd = ge.getDefaultScreenDevice();
    private GraphicsConfiguration gc = gd.getDefaultConfiguration();
    private Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(gc);


    public PongBall(int startPositionX, int startPositionY) {
        this.size = 20;
        this.speed = defaultSpeed;
        this.directionX = -1;
        this.directionY = 1;
        this.xPosition = startPositionX;
        this.yPosition = startPositionY;
        this.frame = new JFrame();
        frame.setUndecorated(true);
        frame.setLocation(xPosition, yPosition);
        frame.setSize(size, size);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public int getScreenWidth() {
        return screenSize.width;
    }

    public int getScreenHeight() {
        return screenSize.height - insets.bottom;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public double getDirectionX() {
        return directionX;
    }

    public double getDirectionY() {
        return directionY;
    }

    public int getSize() {
        return size;
    }

    private boolean hitsLeft() {
        return xPosition <= 0;
    }

    private boolean hitsRight() {
        return xPosition + size >= screenSize.width;
    }


    private boolean hitsTopOrBottom() {
        return yPosition <= 0 || yPosition + size >= screenSize.height - insets.bottom;
    }

    private boolean hitsRightPaddle(Paddle rightPaddle) {
        return rightPaddle.getTopX() <= xPosition + size
                && rightPaddle.getTopX() + rightPaddle.getWidth() > xPosition + size
                && rightPaddle.getTopY() <= yPosition + size
                && rightPaddle.getTopY() + rightPaddle.getHeight() >= yPosition;
    }

    private boolean hitsLeftPaddle(Paddle leftPaddle) {
        return leftPaddle.getTopX() + leftPaddle.getWidth() >= xPosition
                && leftPaddle.getTopX() <= xPosition
                && leftPaddle.getTopY() <= yPosition + size
                && leftPaddle.getTopY() + leftPaddle.getHeight() >= yPosition;
    }

    private void bounceHorizontally() {
        directionX *= -1;
    }

    private void bounceVertically() {
        directionY *= -1;
    }

    private void respawn(Enemy rightPaddle) {
        xPosition = screenSize.width / 2;
        yPosition = 50;
        speed = defaultSpeed;
        rightPaddle.calculateCollisionPoint(this);
    }

    public void increaseSpeed() {
        speed += 25;
    }

    public void move(double deltaTime, Paddle leftPaddle, Enemy rightPaddle, Score score) {
        xPosition += (int) (directionX * speed * deltaTime);
        yPosition += (int) (directionY * speed * deltaTime);
        if (hitsLeft()) {
            respawn(rightPaddle);
            score.increaseRightScore();
        }
        if (hitsRight()) {
            respawn(rightPaddle);
            score.increaseLeftScore();
        }
        if (hitsTopOrBottom()) {
            bounceVertically();
        }
        if (hitsLeftPaddle(leftPaddle)) {
            increaseSpeed();
            bounceHorizontally();
            rightPaddle.calculateCollisionPoint(this);
        }
        if (hitsRightPaddle(rightPaddle)) {
            increaseSpeed();
            bounceHorizontally();
        }
    }


    public void paint() {
        frame.setLocation(xPosition, yPosition);
    }
}
