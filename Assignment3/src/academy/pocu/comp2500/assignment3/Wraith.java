package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Wraith extends Unit implements IThinkable, IMovable {
    private IntVector2D initialPosition;

    public Wraith(IntVector2D position) {
        super(position, 'W', UnitType.SKY, 80, 4,
                0, 6, UnitType.ALL);
        this.initialPosition = new IntVector2D(position.getX(), position.getY());
        super.isShielded = true;
        super.protactable = true;
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

    @Override
    public void think(ArrayList<ArrayList<ArrayList<Unit>>> worldMap) {
        final int dx[] = {0, 0, 1, 0, -1};
        final int dy[] = {0, -1, 0, 1, 0};
        int thisRow = this.getPosition().getY();
        int thisCol = this.getPosition().getX();
        UnitType types[] = {UnitType.SKY, UnitType.GROUND};
        IntVector2D tempTarget = null;
        int minHp = Integer.MAX_VALUE;

        for (int t = 0; t < 2; ++t) {
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
                            && position.get(j).getUnitType() == types[t]
                            && position.get(j).getHp() > 0) {
                        tempTarget = position.get(j).getPosition();
                        minHp = position.get(j).getHp();
                    }
                }
            }
            if (tempTarget != null) {
                break;
            }
        }

        if (tempTarget != null) {
            super.setDecision(Decision.ATTACK);
            super.target = new IntVector2D(tempTarget.getX(), tempTarget.getY());
        } else {
            super.setDecision(Decision.MOVE);
            movePosition = searchEnemy(worldMap);
            super.target = null;
        }
    }

    @Override
    public void onAttacked(int damage) {
        if (protactable && damage > 0) {
            isShielded = false;
            return;
        }

        setHp(Math.max(0, getHp() - damage));
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

    private IntVector2D searchEnemy(ArrayList<ArrayList<ArrayList<Unit>>> worldMap) {
        final int dy[] = {1, 1, -1, -1, -1};
        final int dx[] = {1, -1, -1, 1, 0};
        UnitType types[] = {UnitType.SKY, UnitType.GROUND};

        for (int t = 0; t < 2; ++t) {
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
                            if (each.getUnitType() == types[t]
                                    && each.getHp() < minHp
                                    && each != this) {
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
        }

        return this.initialPosition;
    }
}
