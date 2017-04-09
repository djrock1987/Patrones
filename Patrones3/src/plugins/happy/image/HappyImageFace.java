package plugins.happy.image;

import common.ImageCache;
import paintables.ImageFace;
import plugins.PaintableFactory;
import plugins.happy.HappyPaintableFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HappyImageFace extends ImageFace {

  private BufferedImage bufferedImage;

  // --------------------------------------------------------------------------------

    public HappyImageFace(int x1, int y1, int x2, int y2) {
    super(x1, y1, x2, y2);

    bufferedImage = ImageCache.getInstance().getImage("smile2.png", getClass());
  }

  // --------------------------------------------------------------------------------

  @Override
  public void draw(Graphics2D g2d) {
    g2d.drawImage(bufferedImage, x1, y1, x2 - x1, y2 - y1, null);
  }

  // --------------------------------------------------------------------------------

  @Override
  public PaintableFactory getPaintableFactory() {
      return HappyPaintableFactory.getInstance();
  }
}
