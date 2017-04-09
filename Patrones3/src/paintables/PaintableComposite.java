package paintables;

import plugins.Paintable;
import plugins.PaintableBase;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class PaintableComposite extends PaintableBase {
    protected List<Paintable> paintableList;


    PaintableComposite(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
        paintableList = new ArrayList<>();
    }

    @Override
    public void draw(Graphics2D g2d) {
        for (Paintable paintable : paintableList) {
            paintable.draw(g2d);
        }
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);

        for (Paintable paintable : paintableList) {
            paintable.move(dx, dy);
        }
    }

    @Override
    public void add(Paintable paintable) {
        this.paintableList.add(paintable);
    }

    @Override
    public void remove(Paintable paintable) {
        this.paintableList.remove(paintable);
    }

    @Override
    public Paintable getChild(int index) {
        return this.paintableList.get(index);
    }
}
