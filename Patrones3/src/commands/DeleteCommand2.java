package commands;

import java.util.List;

import common.Command;
import plugins.Paintable;

// Command
public class DeleteCommand2 implements Command {

  private Paintable paintable;

  private List<Paintable> paintableList;

  private int index = -1;

  // --------------------------------------------------------------------------------

  public DeleteCommand2(Paintable paintable, List<Paintable> paintableList) {
    this.paintable = paintable;
    this.paintableList = paintableList;

    this.index = paintableList.indexOf(paintable);
  }

  // --------------------------------------------------------------------------------

  @Override
  public void redoCommand() {
    paintableList.remove(paintable);
  }

  // --------------------------------------------------------------------------------

  @Override
  public void undoCommand() {
    paintableList.add(index, paintable);
  }
}
