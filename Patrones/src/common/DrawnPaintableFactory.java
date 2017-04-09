package common;

import paintables.DrawnFace;

public class DrawnPaintableFactory implements PaintableFactory {

    @Override
    public Paintable create(int x1, int y1, int x2, int y2, int state) {
        return new DrawnFace(x1, y1, x2, y2, state);
    }
}
