package plugins;

import javax.swing.ImageIcon;

// Factory
public interface PaintableFactory {

  public Paintable create(int x1, int y1, int x2, int y2,boolean useIm);

  public ImageIcon getToolIcon();

  public String/**/getToolName();
}
