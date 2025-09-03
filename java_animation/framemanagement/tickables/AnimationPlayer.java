package framemanagement.tickables;

import javax.swing.*;
import java.awt.*;
import framemanagement.FrameManager;

public class AnimationPlayer extends JPanel implements FrameManager.UpdateListener {
    private volatile double x, y; // position
    private volatile double vx, vy; // velocity
    private final int size = 50; // square side

    public AnimationPlayer(double startX, double startY, double velX, double velY) {
        this.x = startX;
        this.y = startY;
        this.vx = velX;
        this.vy = velY;

        setPreferredSize(new Dimension(400, 300));
        setDoubleBuffered(true);
        setBackground(Color.WHITE);
    }

    @Override
    public void onUpdate(double dt) {
        x += vx * dt;
        y += vy * dt;

        int w = getWidth(), h = getHeight();

        // Bounce horizontally
        if (x < 0) {
            x = 0;
            vx = -vx;
        } else if (x + size > w) {
            x = w - size;
            vx = -vx;
        }

        // Bounce vertically
        if (y < 0) {
            y = 0;
            vy = -vy;
        } else if (y + size > h) {
            y = h - size;
            vy = -vy;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect((int) x, (int) y, size, size);
    }
}