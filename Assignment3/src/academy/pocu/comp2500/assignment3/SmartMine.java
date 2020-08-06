package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class SmartMine extends Mine {
    private int minDetectedCount;

    public SmartMine(IntVector2D position, int maxTouchedCount, int minDetectedCount) {
        super(position, 'A', UnitType.INVISIBLE, 1, 1, 1,
                15, UnitType.GROUND, maxTouchedCount);
        this.minDetectedCount = minDetectedCount;
        this.touchedCount = 0;
    }

    @Override
    public void listen(ArrayList<ArrayList<ArrayList<Unit>>> worldMap) {
        ArrayList<Unit> units = worldMap.get(getPosition().getY()).get(getPosition().getX());
        int row = getPosition().getY();
        int col = getPosition().getX();
        int count = 0;

        for (int i = row - getVision(); i <= row + getVision(); ++i) {
            for (int j = col - getVision(); j <= col + getVision(); ++j) {
                if (i < 0 || i >= NUM_ROWS || j < 0 || j >= NUM_COLUMNS) {
                    continue;
                }
                ArrayList<Unit> position = worldMap.get(i).get(j);
                for (int k = 0; k < position.size(); ++k) {
                    if (position.get(k) == this) {
                        continue;
                    }
                    if (position.get(k).getUnitType() == UnitType.GROUND
                            || position.get(k).getUnitType() == UnitType.INVISIBLE) {
                        count++;
                    }
                }
            }
        }

        if (count >= minDetectedCount) {
            bomb = true;
            return;
        }

        for (Unit each : units) {
            if (each.getUnitType() == UnitType.GROUND) {
                ++touchedCount;
            }
        }

        if (touchedCount >= maxTouchedCount) {
            bomb = true;
        }
    }
}
