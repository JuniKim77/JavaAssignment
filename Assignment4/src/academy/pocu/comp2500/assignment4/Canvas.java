package academy.pocu.comp2500.assignment4;

public class Canvas {
    private int width;
    private int height;
    private char c = ' ';
    private char[][] board;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        board = new char[height + 2][width + 2];
        makeFrame();
        // initialization
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                board[i + 1][j + 1] = c;
            }
        }
    }

    public void drawPixel(final int x, final int y, final char c) {
        if (validateOutOfRange(x, y) || validatePixelRange(c)) {
            return;
        }

        board[y + 1][x + 1] = c;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char getPixel(int x, int y) {
        if (validateOutOfRange(x, y)) {
            return c;
        }

        return board[y + 1][x + 1];
    }

    public boolean increasePixel(int x, int y) {
        if (validateOutOfRange(x, y)) {
            return false;
        }

        char pixel = board[y + 1][x + 1];

        if (validatePixelRange(++pixel)) {
            return false;
        }

        drawPixel(x, y, pixel);

        return true;
    }

    public boolean decreasePixel(int x, int y) {
        if (validateOutOfRange(x, y)) {
            return false;
        }

        char pixel = board[y + 1][x + 1];

        if (validatePixelRange(--pixel)) {
            return false;
        }

        drawPixel(x, y, pixel);

        return true;
    }

    public void toUpper(int x, int y) {
        if (validateOutOfRange(x, y)) {
            return;
        }

        char pixel = board[y + 1][x + 1];

        if (pixel >= 'a' && pixel <= 'z') {
            drawPixel(x, y, (char) ((int) pixel - 32));
        }
    }

    public void toLower(int x, int y) {
        if (validateOutOfRange(x, y)) {
            return;
        }

        char pixel = board[y + 1][x + 1];

        if (pixel >= 'A' && pixel <= 'Z') {
            drawPixel(x, y, (char) ((int) pixel + 32));
        }
    }

    public void fillHorizontalLine(int y, char c) {
        if (validateOutOfRange(1, y)) {
            return;
        }
        if (validatePixelRange(c)) {
            return;
        }

        for (int i = 0; i < width; ++i) {
            drawPixel(i, y, c);
        }
    }

    public void fillVerticalLine(int x, char c) {
        if (validateOutOfRange(x, 1)) {
            return;
        }
        if (validatePixelRange(c)) {
            return;
        }

        for (int i = 0; i < height; ++i) {
            drawPixel(x, i, c);
        }
    }

    public void clear() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                drawPixel(j, i, c);
            }
        }
    }

    public String getDrawing() {
        StringBuilder builder = new StringBuilder((width + 3) * (height + 2) + 1);

        for (int i = 0; i <= height + 1; ++i) {
            for (int j = 0; j <= width + 1; ++j) {
                builder.append(board[i][j]);
            }
            builder.append(System.lineSeparator());
        }

        return builder.toString();
    }

    private boolean validateOutOfRange(int x, int y) {
        return x < 0 || x >= width || y < 0 || y >= height;
    }

    private boolean validatePixelRange(char c) {
        return c < 32 || c > 126;
    }

    private void makeFrame() {
        for (int i = 1; i < width + 1; ++i) {
            board[0][i] = '-';
            board[height + 1][i] = '-';
        }

        for (int i = 1; i < height + 1; ++i) {
            board[i][0] = '|';
            board[i][width + 1] = '|';
        }
        board[0][0] = '+';
        board[0][width + 1] = '+';
        board[height + 1][0] = '+';
        board[height + 1][width + 1] = '+';
    }
}
