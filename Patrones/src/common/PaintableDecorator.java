package common;

import java.awt.*;

public class PaintableDecorator extends PaintableBase {

    private Paintable component;

    public PaintableDecorator(Paintable decoratedPaintable) {
        super(decoratedPaintable.getX1(), decoratedPaintable.getY1(), decoratedPaintable.getX2(), decoratedPaintable
                .getY2());
        this.component = decoratedPaintable;
    }

    @Override
    public void draw(Graphics2D g2d) {
        component.draw(g2d);
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        component.move(dx, dy);
    }
}
