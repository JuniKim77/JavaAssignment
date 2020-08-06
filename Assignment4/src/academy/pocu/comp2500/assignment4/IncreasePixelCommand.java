package academy.pocu.comp2500.assignment4;

public class IncreasePixelCommand extends Command {
    private int x;
    private int y;

    public IncreasePixelCommand(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean execute(Canvas canvas) {
        if (!executeValidation(canvas)) {
            return false;
        }

        copy(canvas, prevStatus);
        if (!canvas.increasePixel(x, y)) {
            executed = false;
            return false;
        }
        copy(canvas, currentStatus);

        return true;
    }
}
