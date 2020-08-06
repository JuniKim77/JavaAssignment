package academy.pocu.comp2500.assignment3;

public class IntVector2D {
    private int x;
    private int y;

    public IntVector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        if (x < 0 || x >= 16) {
            return;
        }

        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        if (y < 0 || y >= 16) {
            return;
        }

        this.y = y;
    }
}