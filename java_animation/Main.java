import javax.swing.*;

import framemanagement.FrameManager;
import framemanagement.tickables.AnimationPlayer;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AnimationPlayer panel = new AnimationPlayer(
                    100, 100, // start position
                    180, 120 // initial velocity px/sec (force vector equivalent)
            );

            JFrame frame = new JFrame("Bouncing Square");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            FrameManager fm = new FrameManager(60, 60); // 60 updates, 60 fps
            fm.addUpdateListener(panel);
            fm.addRenderListener(() -> SwingUtilities.invokeLater(panel::repaint));
            fm.start();

            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    fm.stop();
                }
            });
        });
    }
}
