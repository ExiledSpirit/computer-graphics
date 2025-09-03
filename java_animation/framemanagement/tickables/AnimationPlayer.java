package framemanagement.tickables;

import framemanagement.Tickable;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class AnimationPlayer extends JPanel implements Tickable {
    private int tickCounter = 0;

    public AnimationPlayer() {
        this.setBounds(0, 0, 100, 200);
    }

    @Override
    public void tick() {
        tickCounter++;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Always call super.paintComponent()

        // Example drawing:
        g.setColor(Color.BLUE);
        g.fillRect(50, 50, 100, 80); // Draw a filled rectangle
        g.setColor(Color.RED);
        g.drawOval(180, 50, 70, 70); // Draw an oval outline
        g.drawString(Integer.toString(this.tickCounter), 100, 200); // Draw text
    }
}
