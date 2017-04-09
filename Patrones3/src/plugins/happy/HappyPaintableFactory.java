package plugins.happy;

import common.ImageCache;
import plugins.Paintable;
import plugins.PaintableFactory;
import plugins.happy.drawn.HappyDrawnFace;
import plugins.happy.image.HappyImageFace;

import javax.swing.*;


// Factory
public class HappyPaintableFactory implements PaintableFactory {

    // singleton
    private static HappyPaintableFactory instance;

    private HappyPaintableFactory() {
        // private constructor
    }

    // singleton
    public static HappyPaintableFactory getInstance() {
        if (HappyPaintableFactory.instance == null) {
            synchronized (HappyPaintableFactory.class) {
                if (HappyPaintableFactory.instance == null) {
                    HappyPaintableFactory.instance = new HappyPaintableFactory();
                }
            }
        }

        return HappyPaintableFactory.instance;
    }

    @Override
    public Paintable createImagePaintable(int x1, int y1, int x2, int y2) {
        return new HappyImageFace(x1, y1, x2, y2);
    }

    @Override
    public Paintable createDrawnPaintable(int x1, int y1, int x2, int y2) {
        return new HappyDrawnFace(x1, y1, x2, y2);
  }

  @Override
  public ImageIcon getToolIcon() {
    return new ImageIcon(ImageCache.getInstance().getImage("smile2_icon.png", getClass()));
  }

  @Override
  public String getToolName() {
    return "Happy Face Tool";
  }
}
