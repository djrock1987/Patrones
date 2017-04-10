package paintables;

import java.awt.Color;
import java.awt.Graphics2D;

import plugins.PaintableBase;
import plugins.PaintableFactory;

public class Smile extends PaintableBase {

  private int state;

  // --------------------------------------------------------------------------------

  public Smile(int x1, int y1, int x2, int y2, int state) {
    super(x1, y1, x2, y2);

    this.state = state;
  }

  // --------------------------------------------------------------------------------

  @Override
  public void draw(Graphics2D g2d) {
    g2d.setColor(Color.BLACK);

    int w = x2 - x1;
    int h = y2 - y1;
    switch (state) {
      case 2 :
        g2d.drawArc(x1, y1 - w / 2, w, h, 0, -180);
        break;
      case 0 :
        g2d.drawArc(x1, y1/*    */, w, h, 0, +180);
        break;
      case 1 :
        g2d.drawLine(x1, y1 + h / 3, x1 + w, y1 + h / 3);
        break;
    }
  }

@Override
public PaintableFactory getPaintableFactory() {
	// TODO Auto-generated method stub
	return null;
}
}
