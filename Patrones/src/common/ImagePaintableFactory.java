package common;

import paintables.ImageFace;

public class ImagePaintableFactory implements PaintableFactory {

    @Override
    public Paintable create(int x1, int y1, int x2, int y2, int state) {
        return new ImageFace(x1, y1, x2, y2, state);
    }
}
