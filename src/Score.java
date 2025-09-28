import javax.swing.*;
import java.awt.*;

public class Score {
    int leftScore;
    int rightScore;
    private JFrame scoreFrame;
    private ScorePanel scorePanel;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Score() {
        leftScore = 0;
        rightScore = 0;
        scoreFrame = new JFrame();
        scoreFrame.setUndecorated(true);
        scoreFrame.setBackground(new Color(0, 0, 0, 0));
        scoreFrame.setOpacity(1f);
        scoreFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        scorePanel = new ScorePanel();
        scorePanel.setOpaque(false);
        scorePanel.setBackground(new Color(0, 0, 0, 0));
        scoreFrame.add(scorePanel);
        scoreFrame.setSize(300, 100);
//        scoreFrame.setLocationRelativeTo(null);
        scoreFrame.setLocation(screenSize.width / 2 - 300 / 2, 20);
        scoreFrame.setAlwaysOnTop(true);
        scoreFrame.setVisible(true);
    }

    public void increaseLeftScore() {
        leftScore++;
        scorePanel.repaint();
    }

    public void increaseRightScore() {
        rightScore++;
        scorePanel.repaint();
    }

    private String generateScore() {
        return leftScore + " : " + rightScore;
    }

    public void paintScore() {
        scorePanel.repaint();
    }

    private class ScorePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics = (Graphics2D) g;
            graphics.setComposite(AlphaComposite.Src);
            graphics.setColor(Color.red);
            graphics.setFont(new Font("Arial", Font.BOLD, 50));
            graphics.drawString(generateScore(), 80, 60);
        }
    }
}
