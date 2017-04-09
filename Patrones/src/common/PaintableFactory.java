package common;

// Factory
public interface PaintableFactory {
    Paintable create(int x1, int y1, int x2, int y2, int state);
}
