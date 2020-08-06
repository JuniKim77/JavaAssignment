package academy.pocu.comp2500.assignment4;

public class DrawPixelCommand extends Command {
    private int x;
    private int y;
    private char c;

    public DrawPixelCommand(int x, int y, final char c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }

    @Override
    public boolean execute(Canvas canvas) {
        if (!executeValidation(canvas)) {
            return false;
        }
        copy(canvas, prevStatus);
        canvas.drawPixel(x, y, c);
        copy(canvas, currentStatus);
        return true;
    }
}
