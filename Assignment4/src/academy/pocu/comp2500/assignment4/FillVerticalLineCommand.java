package academy.pocu.comp2500.assignment4;

public class FillVerticalLineCommand extends Command {
    private int x;
    private char c;

    public FillVerticalLineCommand(int x, final char c) {
        this.x = x;
        this.c = c;
    }

    @Override
    public boolean execute(Canvas canvas) {
        if (!executeValidation(canvas)) {
            return false;
        }

        copy(canvas, prevStatus);
        canvas.fillVerticalLine(x, c);
        copy(canvas, currentStatus);

        return true;
    }
}
