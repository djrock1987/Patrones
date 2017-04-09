package main;

import commands.CreateCommand;
import commands.DeleteCommand;
import commands.MoveCommand;
import common.Command;
import plugins.Paintable;
import plugins.PaintableFactory;
import plugins.PaintableType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JPanel {

  private List<Paintable> paintableList = new ArrayList<>();

  // --------------------------------------------------------------------------------
  private List<Command> undoList = new ArrayList<>();
  private List<Command> redoList = new ArrayList<>();
  private PaintableFactory paintableFactory = null;
  private Paintable draggedPaintable;
  private Point/* */draggedBasePoint;
  private Tool tool = Tool.SELECT;
  private int dx;
  private int dy;
  private PaintableType paintableType;

  Canvas() {
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

  private Paintable getPaintableAt(Point point) {
    synchronized (paintableList) {
      for (Paintable paintable : paintableList) {
        if (paintable.inside(point)) {
          return paintable;
        }
      }
    }

    return null;
  }

  // --------------------------------------------------------------------------------

  private void clientMousePressed(MouseEvent evt) {

    Paintable paintable;

    switch (tool) {
      case SELECT :
        paintable = getPaintableAt(evt.getPoint());

        if (paintable != null) {
          draggedBasePoint = evt.getPoint();
          draggedPaintable = paintable;
        }

        break;
      case DELETE :
        paintable = getPaintableAt(evt.getPoint());

        if (paintable != null) {
          DeleteCommand deleteCommand = new DeleteCommand( //
              paintable, //
              paintableList);

          deleteCommand.redoCommand();

          undoList.add(deleteCommand);
          redoList.clear();
        }

        break;
      case PLUGIN :
        CreateCommand createCommand = new CreateCommand( //
            paintableFactory, //
            evt.getPoint().x, evt.getPoint().y, //
                paintableList, this.paintableType);

        createCommand.redoCommand();

        undoList.add(createCommand);
        redoList.clear();
        break;
    }

    repaint();
  }

  private void clientMouseReleased(MouseEvent evt) {
    if (draggedPaintable == null) { // GTFO
      return;
    }

    if (dx == 0 && dy == 0) { // GTFO
      return;
    }

    MoveCommand moveCommand = new MoveCommand(draggedPaintable, dx, dy);
    moveCommand.redoCommand();

    undoList.add(moveCommand);
    redoList.clear();

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

    synchronized (paintableList) {
      for (Paintable paintable : paintableList) {
        paintable.draw(g2d);
      }
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
    if (undoList.isEmpty()) {
      return;
    }

    Command command = undoList.remove(undoList.size() - 1);
    command.undoCommand();
    redoList.add(command);
    repaint();
  }

  // --------------------------------------------------------------------------------

  void redo() {
    if (redoList.isEmpty()) {
      return;
    }

    Command command = redoList.remove(redoList.size() - 1);
    command.redoCommand();
    undoList.add(command);
    repaint();
  }

  // --------------------------------------------------------------------------------

  void setPaintableFactory(PaintableFactory paintableFactory) {
    this.paintableFactory = paintableFactory;
  }

  // --------------------------------------------------------------------------------
  // Misc
  // --------------------------------------------------------------------------------

  public List<Paintable> getPaintableList() {
    return paintableList;
  }

  public void setPaintableList(List<Paintable> paintableList) {
    this.paintableList = paintableList;
  }

  public Tool getTool() {
    return tool;
  }

  void setTool(Tool tool) {
    this.tool = tool;
  }

  public PaintableType getPaintableType() {
    return paintableType;
  }

  void setPaintableType(PaintableType paintableType) {
    this.paintableType = paintableType;
  }

  public enum Tool {
    SELECT, DELETE, PLUGIN
  }
}
