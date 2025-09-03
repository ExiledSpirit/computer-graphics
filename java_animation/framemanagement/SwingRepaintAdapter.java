package framemanagement;

// SwingRepaintAdapter.java
import javax.swing.SwingUtilities;
import javax.swing.JComponent;

public class SwingRepaintAdapter implements FrameManager.RenderListener {
  private final JComponent target;

  public SwingRepaintAdapter(JComponent target) {
    this.target = target;
  }

  @Override
  public void onRender() {
    SwingUtilities.invokeLater(target::repaint);
  }
}
