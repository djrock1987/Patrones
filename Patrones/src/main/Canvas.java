package main;

import commands.Invoker;
import commands.MoveCommand;
import common.Command;
import common.Paintable;
import common.PaintableFactory;
import common.SmileConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JPanel {

  private List<Paintable> paintableList = new ArrayList<>();

  private Invoker invoker;

  private Paintable draggedPaintable;
  private Point/* */draggedBasePoint;

  private int dx;
  private int dy;

  // --------------------------------------------------------------------------------

  Canvas() {
    int x;
    int y;

    PaintableFactory paintableFactory = new PaintableFactory();

    // Face 1
    x = 100;
    y = 100;
    paintableList.add(paintableFactory.create(x, y, x + 100, y + 100, SmileConstants.SMILE_DW));

    // Face 2
    x = 200;
    y = 200;
    paintableList.add(paintableFactory.create(x, y, x + 100, y + 100, SmileConstants.SMILE_OK));

    // Face 3
    x = 300;
    y = 300;
    paintableList.add(paintableFactory.create(x, y, x + 100, y + 100, SmileConstants.SMILE_UP));

    // --------------------------------------------------------------------------------
    // Mouse Handling
    // --------------------------------------------------------------------------------

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent evt) {
        clientMouseDragged(evt);
      }
    });

    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent evt) {
        clientMousePressed(evt);
      }

      public void mouseReleased(MouseEvent evt) {
        clientMouseReleased(evt);
      }
    });

    this.invoker = new Invoker();
  }

  // --------------------------------------------------------------------------------

  private void clientMouseDragged(MouseEvent evt) {
    if (draggedPaintable == null) { // GTFO
      return;
    }

    dx = evt.getPoint().x - draggedBasePoint.x;
    dy = evt.getPoint().y - draggedBasePoint.y;

    repaint();
  }

  // --------------------------------------------------------------------------------

  private void clientMousePressed(MouseEvent evt) {
    draggedBasePoint = evt.getPoint();

    for (Paintable paintable : paintableList) {
      if (paintable.inside(draggedBasePoint)) {
        draggedPaintable = paintable;
        return;
      }
    }

    draggedBasePoint = null;
  }

  // --------------------------------------------------------------------------------

  private void clientMouseReleased(MouseEvent evt) {
    if (draggedPaintable == null) { // GTFO
      return;
    }

    if (dx == 0 && dy == 0) { // GTFO
      return;
    }

    Command moveCommand = new MoveCommand(draggedPaintable, dx, dy);
    invoker.setCommand(moveCommand);
    invoker.executeCommand();

    draggedBasePoint = null;
    draggedPaintable = null;

    dx = 0;
    dy = 0;

    repaint();
  }

  // --------------------------------------------------------------------------------

  @Override
  public void paint(Graphics g) {
    update(g);
  }

  // --------------------------------------------------------------------------------

  @Override
  public void update(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    g2d.setBackground(Color.WHITE);
    g2d.clearRect(0, 0, getWidth(), getHeight());

    // ----------------------------------------

    for (Paintable paintable : paintableList) {
      paintable.draw(g2d);
    }

    // ----------------------------------------

    if (draggedPaintable != null) {
      g2d.setColor(Color.BLACK);
      g2d.drawRect( //
          draggedPaintable.getX1() + dx, //
          draggedPaintable.getY1() + dy, //
          draggedPaintable.getX2() - draggedPaintable.getX1(), //
          draggedPaintable.getY2() - draggedPaintable.getY1());
    }
  }

  // --------------------------------------------------------------------------------

  void undo() {
    invoker.undo();
    repaint();
  }

  // --------------------------------------------------------------------------------

  void redo() {
    invoker.redo();
    repaint();
  }
}
