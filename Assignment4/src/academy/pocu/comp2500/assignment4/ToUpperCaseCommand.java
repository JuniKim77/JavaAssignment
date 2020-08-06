package academy.pocu.comp2500.assignment4;

public class ToUpperCaseCommand extends Command {
    private int x;
    private int y;

    public ToUpperCaseCommand(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean execute(Canvas canvas) {
        if (!executeValidation(canvas)) {
            return false;
        }

        copy(canvas, prevStatus);
        canvas.toUpper(x, y);
        copy(canvas, currentStatus);

        return true;
    }
}
