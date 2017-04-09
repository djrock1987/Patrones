package paintables;


import plugins.Paintable;
import plugins.PaintableType;

import java.awt.*;

// Composite
public abstract class DrawnFace extends PaintableComposite {

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
        this.add(new Circle(x, y, x + w, y + h));

        // ----------------------------------------
        // Ojo Der
        // ----------------------------------------

        x = (int) (x1 + (x2 - x1) * 0.55);
        this.add(new Circle(x, y, x + w, y + h));

        // ----------------------------------------
        // Sonrisa
        // ----------------------------------------

        x = (int) (x1 + (x2 - x1) * 0.25);
        y = (int) (y1 + (y2 - y1) * 0.625);
        w = (int) ((x2 - x1) * 0.5);
        h = (int) ((y2 - y1) * 0.5);
        this.drawSmile(x, y, w, h);
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

    @Override
    public PaintableType getPaintableType() {
        return PaintableType.DRAWN;
    }
}
