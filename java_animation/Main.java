import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import framemanagement.FrameManager;
import framemanagement.tickables.AnimationPlayer;

public class Main {
    public static void main(String[] args) {
        FrameManager frameManager = new FrameManager();
        AnimationPlayer animationPlayer = new AnimationPlayer();

        frameManager.addTickable(animationPlayer);

        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Swing Drawing Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            frame.add(animationPlayer);

            frame.setVisible(true);
        });

        new Thread(frameManager).start();
    }
}
