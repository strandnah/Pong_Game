import java.awt.*;

public class Enemy extends Paddle {
    private int yPointToMove;
    private int enemySpeed = 250;
    private int direction = 1;

    public Enemy(int topX, int topY, double height, double width) {
        super(topX, topY, height, width);
        yPointToMove = topY;
    }

    public void calculateCollisionPoint(PongBall pongBall) {
        int yHit = Physics.predictYAtRightWall(
                pongBall.getxPosition(),
                pongBall.getyPosition(),
                pongBall.getDirectionX(),
                pongBall.getDirectionY(),
                pongBall.getSize(),
                pongBall.getScreenWidth(),
                pongBall.getScreenHeight()
        );

        if (yHit >= 0) {
            this.direction = 1;
            this.yPointToMove = yHit;
//            this.setTopY(yPointToMove);
        }
    }

    public void move(double deltaTime) {
        int currentPosition = getTopY() + getHeight() / 2;
        int topOffset = currentPosition - 10;
        int bottomOffset = currentPosition + 10;
        if (topOffset < yPointToMove && yPointToMove < bottomOffset) {
            direction = 0;
        } else if (currentPosition < yPointToMove) {
            direction = 1;
        } else if (currentPosition > yPointToMove) {
            direction = -1;
        }
        int newY = (int) (getTopY() + enemySpeed * deltaTime * direction);
        this.setTopY(newY);
    }


    @Override
    public void paint() {
        frame.setLocation(this.getTopX(), this.getTopY());
    }
}
