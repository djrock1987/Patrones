package plugins;

import java.awt.*;

public interface Paintable {

    boolean inside(Point point);

  // --------------------------------------------------------------------------------

    void draw(Graphics2D g2d);

    void move(int dx, int dy);

  // --------------------------------------------------------------------------------

    int getX1();

    int getY1();

    int getX2();

    int getY2();

  // --------------------------------------------------------------------------------

    PaintableFactory getPaintableFactory();

    PaintableType getPaintableType();
}
