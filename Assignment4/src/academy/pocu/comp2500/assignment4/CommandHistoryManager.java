package academy.pocu.comp2500.assignment4;

import java.util.ArrayList;
import java.util.Stack;

public class CommandHistoryManager {
    private Canvas canvas;
    private final Stack<ICommand> undos = new Stack<>();
    private final Stack<ICommand> redos = new Stack<>();

    public CommandHistoryManager(Canvas canvas) {
        this.canvas = canvas;
    }

    public boolean execute(ICommand command) {
        if (command.execute(canvas)) {
            undos.add(command);
            redos.clear();
            return true;
        }

        return false;
    }

    public boolean canUndo() {
        return !undos.empty();
    }

    public boolean canRedo() {
        return !redos.empty();
    }

    public boolean undo() {
        if (!canUndo()) {
            return false;
        }

        ICommand current = undos.pop();
        redos.add(current);
        current.undo();
        return true;
    }

    public boolean redo() {
        if (!canRedo()) {
            return false;
        }

        ICommand current = redos.pop();
        undos.add(current);
        current.redo();
        return true;
    }
}
