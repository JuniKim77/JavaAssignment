package academy.pocu.comp2500.assignment4;

public class ToLowerCaseCommand extends Command {
    private int x;
    private int y;

    public ToLowerCaseCommand(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean execute(Canvas canvas) {
        if (!executeValidation(canvas)) {
            return false;
        }

        copy(canvas, prevStatus);
        canvas.toLower(x, y);
        copy(canvas, currentStatus);

        return true;
    }
}
