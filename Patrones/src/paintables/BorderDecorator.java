package paintables;

import common.Paintable;
import common.PaintableDecorator;

import java.awt.*;
import java.util.Random;

public class BorderDecorator extends PaintableDecorator {

    private static final int THICKNESS = 10;
    private Color color;

    public BorderDecorator(Paintable decoratedPaintable) {
        super(decoratedPaintable);
        this.x1 = decoratedPaintable.getX1() - THICKNESS;
        this.x2 = decoratedPaintable.getX2() + THICKNESS;
        this.y1 = decoratedPaintable.getY1() - THICKNESS;
        this.y2 = decoratedPaintable.getY2() + THICKNESS;

        ensureColorIsSet();
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);

        Stroke originalStroke = g2d.getStroke();

        g2d.setColor(getColor());
        BasicStroke bs = new BasicStroke(THICKNESS);
        g2d.setStroke(bs);
        g2d.drawRect(getX1(), getY1(), getX2() - getX1(), getY2() - getY1());

        g2d.setStroke(originalStroke);
    }

    private void ensureColorIsSet() {
        if (getColor() == null) {
            Random random = new Random();
            setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256), random.nextInt(256)));
        }
    }

    private Color getColor() {
        return color;
    }

    private void setColor(Color color) {
        this.color = color;
    }
}
