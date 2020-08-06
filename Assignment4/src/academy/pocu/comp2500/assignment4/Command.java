package academy.pocu.comp2500.assignment4;

public abstract class Command implements ICommand {
    protected Canvas canvas;
    protected boolean executed = false;
    protected Canvas prevStatus;
    protected Canvas currentStatus;

    public boolean redo() {
        if (executed || canvas == null || !compare(canvas, prevStatus)) {
            return false;
        }

        executed = true;
        copy(currentStatus, canvas);
        return true;
    }

    public boolean undo() {
        if (!executed || !compare(canvas, currentStatus)) {
            return false;
        }

        executed = false;
        copy(prevStatus, canvas);
        return true;
    }

    protected boolean executeValidation(Canvas canvas) {
        if (executed) {
            return false;
        }

        if (prevStatus == null) {
            prevStatus = new Canvas(canvas.getWidth(), canvas.getHeight());
        }
        if (currentStatus == null) {
            currentStatus = new Canvas(canvas.getWidth(), canvas.getHeight());
        }

        executed = true;
        this.canvas = canvas;
        return true;
    }

    protected void copy(Canvas source, Canvas dest) {
        for (int i = 0; i < source.getHeight(); ++i) {
            for (int j = 0; j < source.getWidth(); ++j) {
                dest.drawPixel(j, i, source.getPixel(j, i));
            }
        }
    }

    private boolean compare(Canvas source, Canvas dest) {
        for (int i = 0; i < source.getHeight(); ++i) {
            for (int j = 0; j < source.getWidth(); ++j) {
                if (source.getPixel(j, i) != dest.getPixel(j, i)) {
                    return false;
                }
            }
        }
        return true;
    }
}
