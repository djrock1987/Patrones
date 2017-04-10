package commands;

import java.util.List;

import common.Command;
import plugins.Paintable;
import plugins.PaintableFactory;

// Command
public class CreateCommand2 implements Command {

  private PaintableFactory paintableFactory;

  private Paintable paintable;

  private List<Paintable> paintableList;

  private int x;
  private int y;

  // --------------------------------------------------------------------------------

  public CreateCommand2(PaintableFactory paintableFactory, int x, int y, List<Paintable> paintableList) {
    this.paintableFactory = paintableFactory;
    this.x = x;
    this.y = y;
    this.paintableList = paintableList;
  }

  // --------------------------------------------------------------------------------

  @Override
  public void redoCommand() {
    if (paintable == null) {
      paintable = paintableFactory.create(x - 50, y - 50, x + 50, y + 50,false);
    }

    paintableList.add(paintable);
  }

  // --------------------------------------------------------------------------------

  @Override
  public void undoCommand() {
    paintableList.remove(paintable);
  }
}
