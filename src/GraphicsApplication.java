import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

/**
 * A class for writing a simple graphical applications in Java using Swing.
 * The rendering can be specified by overriding the {@link #paint} method. The logic can be specified
 * by overriding the {@link #update} method. To start the application, call the {@link #run} method.
 */
public class GraphicsApplication {
    private JFrame frame;
    private Canvas canvas;
    private int canvasSize = 400;
    private int numRefreshPerSecond = 120;
    private Color backgroundColor = new Color(0,0,0,0);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    /**
     * Initializes the application.
     */
    public GraphicsApplication() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(new Color(0,0,0,0));
        canvas = new Canvas();
        canvas.setSize(canvasSize, canvasSize);
        canvas.setBackground(new Color(0,0,0,1));
        frame.add(canvas);
        frame.pack();
        frame.setLocation(screenSize.width / 2 - canvasSize / 2, 20);
        frame.setOpacity(1f);
        canvas.createBufferStrategy(2);
    }


    /**
     * Starts the application. Runs until the {@link #stop} method is called.
     */
    public final void run() {
        Timer refreshTimer = new Timer(1000 / numRefreshPerSecond, event -> refresh());
        refreshTimer.start();
        KeyListener keyListener = new KeyListener();
        canvas.setFocusable(true);
        canvas.addKeyListener(keyListener);
        frame.addKeyListener(keyListener);
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    /**
     * Terminates the application.
     */
    public final void stop() {
        frame.setVisible(false);
    }

    /**
     * This method is called on every frame refresh to update the graphics.
     *
     * @param graphics the Graphics context in which to paint
     *
     */
    public void paint(Graphics2D graphics) {
    }

    /**
     * This method is called on every tick to update the application logic.
     *
     * @param deltaTime Time in seconds since the last update
     */
    public void update(double deltaTime) {

    }

    /**
     * This method is called whenever a key is pressed in the application window.
     *
     * @param keyCode Key code of the pressed key
     */
    public void keyPressed(int keyCode) {
    }

    /**
     * This method is called whenever a key is released in the application window.
     *
     * @param keyCode Key code of the released key
     */
    public void keyReleased(int keyCode) {
    }

    /**
     * Changes the title of the application window.
     *
     * @param title New title
     */
    public void setTitle(String title) {
        frame.setTitle(title);
    }

    /**
     * Returns the size of the rendering canvas.
     *
     * @return Size of the rendering canvas
     */
    public int getCanvasSize() {
        return canvasSize;
    }

    private void refresh() {
        update(1.0 / numRefreshPerSecond);
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        do {
            do {
                Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                graphics.setBackground(GraphicsApplication.this.backgroundColor);
                graphics.clearRect(0, 0, canvasSize, canvasSize);
                paint(graphics);
                graphics.dispose();
            } while (bufferStrategy.contentsRestored());
            bufferStrategy.show();
        } while (bufferStrategy.contentsLost());
    }

    private class KeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent event) {
            GraphicsApplication.this.keyPressed(event.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent event) {
            GraphicsApplication.this.keyReleased(event.getKeyCode());
        }
    }
}
