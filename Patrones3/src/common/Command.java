package common;

// Command
public interface Command {
    void executeCommand();

    void undoCommand();
}
