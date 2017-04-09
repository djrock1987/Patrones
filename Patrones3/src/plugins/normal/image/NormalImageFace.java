package plugins.normal.image;

import common.ImageCache;
import paintables.ImageFace;
import plugins.PaintableFactory;
import plugins.normal.NormalPaintableFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NormalImageFace extends ImageFace {

  private BufferedImage bufferedImage;

  // --------------------------------------------------------------------------------

    public NormalImageFace(int x1, int y1, int x2, int y2) {
    super(x1, y1, x2, y2);

    bufferedImage = ImageCache.getInstance().getImage("smile1.png", getClass());
  }

  // --------------------------------------------------------------------------------

  @Override
  public void draw(Graphics2D g2d) {
    g2d.drawImage(bufferedImage, x1, y1, x2 - x1, y2 - y1, null);
  }

  // --------------------------------------------------------------------------------

  @Override
  public PaintableFactory getPaintableFactory() {
      return NormalPaintableFactory.getInstance();
  }
}
