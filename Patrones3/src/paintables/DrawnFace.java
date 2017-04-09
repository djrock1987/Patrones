package paintables;


import plugins.Paintable;
import plugins.PaintableBase;
import plugins.PaintableType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Composite
public abstract class DrawnFace extends PaintableBase {

    protected List<Paintable> paintableList = new ArrayList<>();

    // --------------------------------------------------------------------------------

    public DrawnFace(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);

        int x;
        int y;
        int w;
        int h;

        // ----------------------------------------

        y = (int) (y1 + (y2 - y1) * 0.25);

        w = (int) ((x2 - x1) * 0.25);
        h = (int) ((y2 - y1) * 0.25);

        // ----------------------------------------
        // Ojo Izq
        // ----------------------------------------

        x = (int) (x1 + (x2 - x1) * 0.20);
        paintableList.add(new Circle(x, y, x + w, y + h));

        // ----------------------------------------
        // Ojo Der
        // ----------------------------------------

        x = (int) (x1 + (x2 - x1) * 0.55);
        paintableList.add(new Circle(x, y, x + w, y + h));

        // ----------------------------------------
        // Sonrisa
        // ----------------------------------------

        x = (int) (x1 + (x2 - x1) * 0.25);
        y = (int) (y1 + (y2 - y1) * 0.625);
        w = (int) ((x2 - x1) * 0.5);
        h = (int) ((y2 - y1) * 0.5);
        drawSmile(x, y, w, h);
    }

    public abstract void drawSmile(int x, int y, int w, int h);

    // --------------------------------------------------------------------------------

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.drawOval(x1, y1, x2 - x1, y2 - y1);

        // ----------------------------------------
        // Draw children
        // ----------------------------------------

        for (Paintable paintable : paintableList) {
            paintable.draw(g2d);
        }
    }

    // --------------------------------------------------------------------------------

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);

        // ----------------------------------------
        // Move children
        // ----------------------------------------

        for (Paintable paintable : paintableList) {
            paintable.move(dx, dy);
        }
    }

    @Override
    public PaintableType getPaintableType() {
        return PaintableType.DRAWN;
    }
}
