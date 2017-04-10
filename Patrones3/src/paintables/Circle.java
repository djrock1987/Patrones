package paintables;

import java.awt.Color;
import java.awt.Graphics2D;

import plugins.Paintable;
import plugins.PaintableBase;
import plugins.PaintableFactory;
import plugins.happy.HappyPaintableFactory;

public class Circle extends PaintableBase {

	public Circle(int x1, int y1, int x2, int y2) {
		super(x1, y1, x2, y2);
	}

	// --------------------------------------------------------------------------------

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.drawOval(x1, y1, x2 - x1, y2 - y1);
	}

	 public PaintableFactory getPaintableFactory() {
		 return new HappyPaintableFactory();
	 }
}
