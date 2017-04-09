package plugins.normal;

import common.ImageCache;
import plugins.Paintable;
import plugins.PaintableFactory;
import plugins.normal.drawn.NormalDrawnFace;
import plugins.normal.image.NormalImageFace;

import javax.swing.*;

// Factory
public class NormalPaintableFactory implements PaintableFactory {

    // singleton
    private static NormalPaintableFactory instance;

    // singleton
    public static NormalPaintableFactory getInstance() {
        if (NormalPaintableFactory.instance == null) {
            synchronized (NormalPaintableFactory.class) {
                if (NormalPaintableFactory.instance == null) {
                    NormalPaintableFactory.instance = new NormalPaintableFactory();
                }
            }
        }

        return NormalPaintableFactory.instance;
    }

    public Paintable createImagePaintable(int x1, int y1, int x2, int y2) {
        return new NormalImageFace(x1, y1, x2, y2);
    }

    @Override
    public Paintable createDrawnPaintable(int x1, int y1, int x2, int y2) {
        return new NormalDrawnFace(x1, y1, x2, y2);
  }

  @Override
  public ImageIcon getToolIcon() {
    return new ImageIcon(ImageCache.getInstance().getImage("smile1_icon.png", getClass()));
  }

  @Override
  public String getToolName() {
    return "Normal Face Tool";
  }
}
