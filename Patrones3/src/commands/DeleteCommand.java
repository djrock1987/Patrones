package commands;

import common.Command;
import plugins.Paintable;

import java.util.List;

// Command
public class DeleteCommand implements Command {

  private Paintable paintable;

  private List<Paintable> paintableList;

  private int index = -1;

  // --------------------------------------------------------------------------------

  public DeleteCommand(Paintable paintable, List<Paintable> paintableList) {
    this.paintable = paintable;
    this.paintableList = paintableList;

    this.index = paintableList.indexOf(paintable);
  }

  // --------------------------------------------------------------------------------

  @Override
  public void executeCommand() {
    paintableList.remove(paintable);
  }

  // --------------------------------------------------------------------------------

  @Override
  public void undoCommand() {
    paintableList.add(index, paintable);
  }
}
