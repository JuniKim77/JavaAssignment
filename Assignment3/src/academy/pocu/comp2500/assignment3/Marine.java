package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Marine extends Unit implements IMovable, IThinkable {
    public Marine(IntVector2D position) {
        super(position, 'M', UnitType.GROUND, 35, 2,
                0, 6, UnitType.ALL);
    }

    @Override
    public AttackIntent attack() {
        return new AttackIntent(this.target, this);
    }

    @Override
    public boolean isMovable() {
        return true;
    }

    @Override
    public boolean isThinkable() {
        return true;
    }

    @Override
    public boolean isListenable() {
        return false;
    }

    @Override
    public void think(ArrayList<ArrayList<ArrayList<Unit>>> worldMap) {
        final int dx[] = {0, 0, 1, 0, -1};
        final int dy[] = {0, -1, 0, 1, 0};
        int thisRow = this.getPosition().getY();
        int thisCol = this.getPosition().getX();
        int minHp = Integer.MAX_VALUE;
        IntVector2D target = null;

        for (int i = 0; i < 5; ++i) {
            int newRow = thisRow + dy[i];
            int newCol = thisCol + dx[i];
            if (newRow < 0 || newRow >= NUM_ROWS || newCol < 0 || newCol >= NUM_COLUMNS) {
                continue;
            }
            ArrayList<Unit> position = worldMap.get(newRow).get(newCol);
            for (int j = 0; j < position.size(); ++j) {
                if (position.get(j) != this
                        && position.get(j).getHp() < minHp
                        && position.get(j).getUnitType() != UnitType.INVISIBLE
                        && position.get(j).getHp() > 0) {
                    target = position.get(j).getPosition();
                    minHp = position.get(j).getHp();
                }
            }
        }
        if (target != null) {
            super.setDecision(Decision.ATTACK);
            super.target = new IntVector2D(target.getX(), target.getY());
        } else {
            super.setDecision(Decision.MOVE);
            movePosition = searchEnemy(worldMap);
            super.target = null;
        }
    }

    @Override
    public void move() {
        int targetRow = movePosition.getY();
        int targetCol = movePosition.getX();
        int row = getPosition().getY();
        int col = getPosition().getX();

        if (targetRow > row) {
            super.getPosition().setY(row + 1);
        } else if (targetRow < row) {
            super.getPosition().setY(row - 1);
        } else if (targetCol > col) {
            super.getPosition().setX(col + 1);
        } else if (targetCol < col) {
            super.getPosition().setX(col - 1);
        }
    }

    private IntVector2D searchEnemy(ArrayList<ArrayList<ArrayList<Unit>>> worldMap) {
        final int dy[] = {1, 1, -1, -1, -1};
        final int dx[] = {1, -1, -1, 1, 0};
        int distance = 2;
        int row = this.getPosition().getY() - distance;
        int col = this.getPosition().getX();

        while (distance < 2 * getVision() + 1) {
            int minHp = Integer.MAX_VALUE;
            IntVector2D targetPosition = null;
            for (int i = 0; i < 4; ++i) {
                for (int k = 0; k < distance; ++k) {
                    if (row < 0 || row >= NUM_ROWS || col < 0 || col >= NUM_COLUMNS
                            || row < this.getPosition().getY() - getVision()
                            || row > this.getPosition().getY() + getVision()
                            || col < this.getPosition().getX() - getVision()
                            || col > this.getPosition().getX() + getVision()) {
                        row += dy[i];
                        col += dx[i];
                        continue;
                    }

                    ArrayList<Unit> units = worldMap.get(row).get(col);

                    for (Unit each : units) {
                        if (each.getUnitType() != UnitType.INVISIBLE && each.getHp() < minHp) {
                            minHp = each.getHp();
                            targetPosition = each.getPosition();
                        }
                    }
                    row += dy[i];
                    col += dx[i];
                }
            }
            if (targetPosition != null) {
                return new IntVector2D(targetPosition.getX(), targetPosition.getY());
            }
            row += dy[4];
            distance++;
        }
        return this.getPosition();
    }
}
