import javax.swing.*;
import java.awt.*;

public class PongBall {
    private JFrame frame;
    private int xPosition;
    private int yPosition;
    private int directionX;
    private int directionY;
    private final int size;
    private final int defaultSpeed = 500;
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

    private boolean hitsLeft() {
        return xPosition <= 0;
    }

    private boolean hitsRight() {
        return xPosition + size >= screenSize.width;
    }


    private boolean hitsTopOrBottom() {
        return yPosition <= 0 || yPosition + size >= screenSize.height - insets.bottom;
    }

//    private boolean hitsPaddle(Paddle paddle) {
//        return !(paddle.getTopX() + paddle.getWidth() < xPosition ||
//                xPosition + size < paddle.getTopX() ||
//                paddle.getTopY() + paddle.getHeight() < yPosition ||
//                paddle.getTopY() + paddle.getHeight() < paddle.getTopY());
//
//    }

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

    private void respawn() {
        xPosition = screenSize.width / 2;
        yPosition = 50;
        speed = defaultSpeed;
    }

    public void increaseSpeed() {
        speed *= 1.5;
    }

    public void move(double deltaTime, Paddle leftPaddle, Paddle rightPaddle, Score score) {
        xPosition += (int) (directionX * speed * deltaTime);
        yPosition += (int) (directionY * speed * deltaTime);
        if (hitsLeft()) {
            respawn();
            score.increaseRightScore();
        }
        if (hitsRight()) {
            respawn();
            score.increaseLeftScore();
        }
        if (hitsTopOrBottom()) {
            bounceVertically();
        }
        if (hitsLeftPaddle(leftPaddle)) {
            bounceHorizontally();
        }
        if (hitsRightPaddle(rightPaddle)) {
            bounceHorizontally();
        }
    }


    public void paint() {
        frame.setLocation(xPosition, yPosition);
    }
}
