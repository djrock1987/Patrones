package plugins.sad.drawn;

import common.SmileConstants;
import paintables.DrawnFace;
import paintables.Smile;
import plugins.PaintableFactory;
import plugins.sad.SadPaintableFactory;

public class SadDrawnFace extends DrawnFace {

    public SadDrawnFace(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void drawSmile(int x, int y, int w, int h) {
        this.paintableList.add(new Smile(x, y, x + w, y + h, SmileConstants.SMILE_DW));
    }

    @Override
    public PaintableFactory getPaintableFactory() {
        return SadPaintableFactory.getInstance();
    }
}
