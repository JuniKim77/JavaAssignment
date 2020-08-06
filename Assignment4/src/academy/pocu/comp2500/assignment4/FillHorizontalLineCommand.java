package academy.pocu.comp2500.assignment4;

public class FillHorizontalLineCommand extends Command {
    private int y;
    private char c;

    public FillHorizontalLineCommand(int y, final char c) {
        this.y = y;
        this.c = c;
    }

    @Override
    public boolean execute(Canvas canvas) {
        if (!executeValidation(canvas)) {
            return false;
        }

        copy(canvas, prevStatus);
        canvas.fillHorizontalLine(y, c);
        copy(canvas, currentStatus);
        return true;
    }
}
