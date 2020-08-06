package academy.pocu.comp2500.assignment4;

import java.util.LinkedList;

public class OverdrawAnalyzer extends Canvas {
    private BoardLog[][] logs;
    private int[][] countingBoard;

    public OverdrawAnalyzer(int width, int height) {
        super(width, height);
        logs = new BoardLog[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                logs[i][j] = new BoardLog();
            }
        }
        countingBoard = new int[height][width];
    }

    public LinkedList<Character> getPixelHistory(int x, int y) {
        return logs[y][x];
    }

    public int getOverdrawCount(int x, int y) {
        return countingBoard[y][x];
    }

    public int getOverdrawCount() {
        int sum = 0;

        for (int i = 0; i < super.getHeight(); ++i) {
            for (int j = 0; j < super.getWidth(); ++j) {
                sum += countingBoard[i][j];
            }
        }

        return sum;
    }

    @Override
    public void drawPixel(int x, int y, char c) {
        char prevPixel = super.getPixel(x, y);

        if (prevPixel == c) {
            return;
        }

        logs[y][x].add(c);
        countingBoard[y][x]++;
        super.drawPixel(x, y, c);
    }
}
