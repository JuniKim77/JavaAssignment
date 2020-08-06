package academy.pocu.comp2500.assignment4;

public class ClearCommand extends Command {
    public ClearCommand() {
    }

    @Override
    public boolean execute(Canvas canvas) {
        if (!executeValidation(canvas)) {
            return false;
        }

        copy(canvas, prevStatus);
        canvas.clear();
        copy(canvas, currentStatus);
        return true;
    }
}
