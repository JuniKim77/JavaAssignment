package academy.pocu.comp2500.assignment4;

public class DecreasePixelCommand extends Command {
    private int x;
    private int y;

    public DecreasePixelCommand(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean execute(Canvas canvas) {
        if (!executeValidation(canvas)) {
            return false;
        }
        copy(canvas, prevStatus);
        if (!canvas.decreasePixel(x, y)) {
            executed = false;
            return false;
        }
        copy(canvas, currentStatus);
        return true;
    }
}
