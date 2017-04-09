package commands;

import common.Command;
import common.Paintable;
import paintables.BorderDecorator;

import java.util.List;

public class AddBorderCommand implements Command {

    private Paintable undecoratedPaintable;
    private Paintable decoratedPaintable;
    private List<Paintable> paintableList;

    public AddBorderCommand(Paintable undecoratedPaintable, List<Paintable> paintableList) {
        this.undecoratedPaintable = undecoratedPaintable;
        this.paintableList = paintableList;
    }

    @Override
    public void executeCommand() {
        this.paintableList.remove(undecoratedPaintable);
        if (this.decoratedPaintable == null) {
            this.decoratedPaintable = new BorderDecorator(undecoratedPaintable);
        }
        paintableList.add(decoratedPaintable);
    }

    @Override
    public void undoCommand() {
        this.paintableList.remove(decoratedPaintable);
        paintableList.add(undecoratedPaintable);
    }
}
