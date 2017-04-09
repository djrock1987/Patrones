package paintables;


import plugins.PaintableBase;
import plugins.PaintableFactory;
import plugins.PaintableType;

import java.awt.*;

public class Rect extends PaintableBase {

    public Rect(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    // --------------------------------------------------------------------------------

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x1, y1, x2 - x1, y2 - y1);
    }

    @Override
    public PaintableFactory getPaintableFactory() {
        return null;
    }

    @Override
    public PaintableType getPaintableType() {
        return null;
    }
}
