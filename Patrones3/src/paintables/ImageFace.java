package paintables;

import plugins.PaintableBase;
import plugins.PaintableType;

public abstract class ImageFace extends PaintableBase {

    public ImageFace(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }


    @Override
    public PaintableType getPaintableType() {
        return PaintableType.IMAGE;
    }
}
