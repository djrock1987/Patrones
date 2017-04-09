package plugins;

import javax.swing.*;

// Factory
public interface PaintableFactory {

    Paintable createImagePaintable(int x1, int y1, int x2, int y2);

    Paintable createDrawnPaintable(int x1, int y1, int x2, int y2);

    ImageIcon getToolIcon();

    String/**/getToolName();
}
