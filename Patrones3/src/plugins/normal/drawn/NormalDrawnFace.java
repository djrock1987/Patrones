package plugins.normal.drawn;

import common.SmileConstants;
import paintables.DrawnFace;
import paintables.Smile;
import plugins.PaintableFactory;
import plugins.normal.NormalPaintableFactory;

public class NormalDrawnFace extends DrawnFace {

    public NormalDrawnFace(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void drawSmile(int x, int y, int w, int h) {
        this.paintableList.add(new Smile(x, y, x + w, y + h, SmileConstants.SMILE_OK));
    }

    @Override
    public PaintableFactory getPaintableFactory() {
        return NormalPaintableFactory.getInstance();
    }
}
