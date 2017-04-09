package commands;

import common.Command;
import plugins.Paintable;
import plugins.PaintableFactory;
import plugins.PaintableType;

import java.util.List;

// Command
public class CreateCommand implements Command {

  private PaintableFactory paintableFactory;

  private Paintable paintable;

  private List<Paintable> paintableList;

    private PaintableType paintableType;

  private int x;
  private int y;

  // --------------------------------------------------------------------------------

    public CreateCommand(PaintableFactory paintableFactory, int x, int y, List<Paintable> paintableList, PaintableType
            paintableType) {
    this.paintableFactory = paintableFactory;
    this.x = x;
    this.y = y;
    this.paintableList = paintableList;
        this.paintableType = paintableType;
  }

  // --------------------------------------------------------------------------------

  @Override
  public void redoCommand() {
    if (paintable == null) {
        switch (this.paintableType) {
            case DRAWN:
                paintable = paintableFactory.createDrawnPaintable(x - 50, y - 50, x + 50, y + 50);
                break;
            case IMAGE:
                paintable = paintableFactory.createImagePaintable(x - 50, y - 50, x + 50, y + 50);
                break;
        }
    }
      if (!paintableList.contains(paintable)) {
          paintableList.add(paintable);
      }
  }

  // --------------------------------------------------------------------------------

  @Override
  public void undoCommand() {
    paintableList.remove(paintable);
  }
}
