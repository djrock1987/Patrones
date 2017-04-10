package plugins.happy;

import javax.swing.ImageIcon;

import common.ImageCache;
import common.SmileConstants;
import paintables.DrawnFace;
import plugins.Paintable;
import plugins.PaintableFactory;

// Factory
public class HappyPaintableFactory implements PaintableFactory {

	// private boolean useImage = true;

	public Paintable create(int x1, int y1, int x2, int y2, boolean isImage) {
		if (isImage) {
			 return new HappyPaintable(x1, y1, x2, y2);
			//return (Paintable) new DrawnFace(x1, y1, x2, y2, SmileConstants.SMILE_UP);
		} else {
			return new DrawnFace(x1, y1, x2, y2, SmileConstants.SMILE_UP);
		}

	}

	@Override
	public ImageIcon getToolIcon() {
		return new ImageIcon(ImageCache.getInstance().getImage("smile2_icon.png", getClass()));
		// return new
		// ImageIcon(ImageCache.getInstance().getSystemImage("smile2_icon.png"));
	}

	@Override
	public String getToolName() {
		return "Happy Face Tool";
	}
}
