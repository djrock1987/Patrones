package plugins.sad;

import common.ImageCache;
import plugins.Paintable;
import plugins.PaintableFactory;
import plugins.sad.drawn.SadDrawnFace;
import plugins.sad.image.SadImageFace;

import javax.swing.*;

// Factory
public class SadPaintableFactory implements PaintableFactory {

    // singleton
    private static SadPaintableFactory instance;

    // singleton
    public static SadPaintableFactory getInstance() {
        if (SadPaintableFactory.instance == null) {
            synchronized (SadPaintableFactory.class) {
                if (SadPaintableFactory.instance == null) {
                    SadPaintableFactory.instance = new SadPaintableFactory();
                }
            }
        }

        return SadPaintableFactory.instance;
    }

    public Paintable createImagePaintable(int x1, int y1, int x2, int y2) {
        return new SadImageFace(x1, y1, x2, y2);
    }

    @Override
    public Paintable createDrawnPaintable(int x1, int y1, int x2, int y2) {
        return new SadDrawnFace(x1, y1, x2, y2);
  }

  @Override
  public ImageIcon getToolIcon() {
    return new ImageIcon(ImageCache.getInstance().getImage("smile0_icon.png", getClass()));
  }

  @Override
  public String getToolName() {
    return "Sad Face Tool";
  }
}
