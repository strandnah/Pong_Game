import java.awt.*;
import java.awt.event.KeyEvent;

public class PongGame extends GraphicsApplication {
    private PongBall ball;
    private Paddle leftPaddle;
    private Enemy rightPaddle;
    private Score score;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    public PongGame() {
        ball = new PongBall(screenSize.width / 2, 50);
        leftPaddle = new Paddle(40, 50, screenSize.height * 0.25, 20);
        rightPaddle = new Enemy(screenSize.width - 40, 50, screenSize.height * 0.25, 20);
        score = new Score();
    }

    @Override
    public void update(double deltaTime) {
        ball.move(deltaTime, leftPaddle, rightPaddle, score);
        rightPaddle.move(deltaTime);
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
