package common;

// Command
public interface Command {

  public void redoCommand(boolean im);

  public void undoCommand();
}
