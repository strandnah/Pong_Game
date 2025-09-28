import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class PongGame extends GraphicsApplication {
    private PongBall ball;
    private Paddle leftPaddle, rightPaddle;
    private Score score;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double elapsedTime;
    private final int netxLevelThreshold = 5; // value in seconds


    public PongGame() {
        ball = new PongBall(screenSize.width / 2, 50);
        leftPaddle = new Paddle(40, 50, screenSize.height * 0.25, 20);
        rightPaddle = new Paddle(screenSize.width - 40, 50, screenSize.height * 0.25, 20);
        score = new Score();
        elapsedTime = 0;
    }

    @Override
    public void update(double deltaTime) {
        elapsedTime += deltaTime;
        if (elapsedTime > netxLevelThreshold) {
            ball.increaseSpeed();
            elapsedTime = 0;
        }
        ball.move(deltaTime, leftPaddle, rightPaddle, score);
    }

    @Override
    public void paint(Graphics2D graphics) {
        ball.paint();
        leftPaddle.paint();
        rightPaddle.paint();
        score.paintScore();
    }

    @Override
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_W) {
            leftPaddle.moveUp();
        }
        if (keyCode == KeyEvent.VK_S) {
            leftPaddle.moveDown();
        }
        if (keyCode == KeyEvent.VK_UP) {
            rightPaddle.moveUp();
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            rightPaddle.moveDown();
        }
    }

    static void main() {
        PongGame app = new PongGame();
        app.run();
    }
}
