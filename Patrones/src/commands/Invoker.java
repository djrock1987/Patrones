package commands;

import common.Command;

import java.util.ArrayList;
import java.util.List;

public class Invoker {
    private Command command;
    private List<Command> redoCommands;
    private List<Command> undoCommands;

    public Invoker() {
        this.redoCommands = new ArrayList<>();
        this.undoCommands = new ArrayList<>();
    }

    public void executeCommand() {
        if (this.command != null) {
            this.command.executeCommand();
            addUndoCommand(this.command);
            this.redoCommands.clear();
        }
    }

    public void undo() {
        if (!this.undoCommands.isEmpty()) {
            Command command = this.undoCommands.remove(this.undoCommands.size() - 1);
            command.undoCommand();
            redoCommands.add(command);
        }
    }

    public void redo() {
        if (!this.redoCommands.isEmpty()) {
            Command command = this.redoCommands.remove(this.redoCommands.size() - 1);
            command.executeCommand();
            undoCommands.add(command);
        }
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    private void addUndoCommand(Command command) {
        this.undoCommands.add(command);
    }
}
