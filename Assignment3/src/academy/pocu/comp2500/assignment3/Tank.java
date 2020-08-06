package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Tank extends Unit implements IThinkable, IMovable {
    private boolean mode;
    private boolean isRightDirection = true;

    public Tank(IntVector2D position) {
        super(position, 'T', UnitType.GROUND, 85, 3,
                1, 8, UnitType.GROUND);
        this.mode = false;
    }

    @Override
    public void onAttacked(int damage) {
        setHp(Math.max(0, getHp() - (mode ? 2 * damage : damage)));
    }

    @Override
    public AttackIntent attack() {
        if (!mode) {
            mode = true;
            return null;
        }
        if (this.target == null) {
            return null;
        }

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

    public void move() {
        if (mode) {
            mode = false;
            return;
        }

        int nextCol = super.getPosition().getX() + (isRightDirection ? 1 : -1);

        if (nextCol < 0 || nextCol >= NUM_COLUMNS) {
            isRightDirection = !isRightDirection;
            nextCol = super.getPosition().getX() + (isRightDirection ? 1 : -1);
        }

        super.getPosition().setX(nextCol);
    }

    @Override
    public void think(ArrayList<ArrayList<ArrayList<Unit>>> worldMap) {
        final int dy[] = {0, 1, 0, -1, 0};
        final int dx[] = {1, 0, -1, 0, 0};
        final int beginY[] = {-2, -1, 2, 1, -2};
        final int beginX[] = {0, 2, 1, -2, -1};
        final int count[] = {2, 3, 3, 3, 1};
        int minHp = Integer.MAX_VALUE;
        IntVector2D target = null;
        boolean isFound = false;

        // 시야에 공격할 대상이 있는지 확인
        for (int i = 0; i < 5; ++i) {
            int row = this.getPosition().getY() + beginY[i];
            int col = this.getPosition().getX() + beginX[i];
            for (int j = 0; j < count[i]; ++j) {
                if (row < 0 || row >= NUM_ROWS || col < 0 || col >= NUM_COLUMNS) {
                    row += dy[i];
                    col += dx[i];
                    continue;
                }
                ArrayList<Unit> position = worldMap.get(row).get(col);
                for (int k = 0; k < position.size(); ++k) {
                    if (position.get(k).getHp() < minHp
                            && position.get(k).getUnitType() == UnitType.GROUND
                            && position.get(k).getHp() > 0
                            && position.get(k) != this) {
                        target = position.get(k).getPosition();
                        minHp = position.get(k).getHp();
                    }
                }
                row += dy[i];
                col += dx[i];
            }
        }

        if (target == null) {
            int row = this.getPosition().getY();
            int col = this.getPosition().getX();

            Loops:
            for (int i = row - getVision(); i <= row + getVision(); ++i) {
                for (int j = col - getVision(); j <= col + getVision(); ++j) {
                    if (i < 0 || i >= NUM_ROWS || j < 0 || j >= NUM_COLUMNS) {
                        continue;
                    }
                    ArrayList<Unit> position = worldMap.get(i).get(j);

                    for (Unit each : position) {
                        if (each.getUnitType() == UnitType.GROUND && each != this) {
                            isFound = true;
                            break Loops;
                        }
                    }
                }
            }
        }

        if (target != null) {
            super.setDecision(Decision.ATTACK);
            super.target = new IntVector2D(target.getX(), target.getY());
        } else if (isFound) {
            super.setDecision(Decision.ATTACK);
            super.target = null;
        } else {
            super.setDecision(Decision.MOVE);
            super.target = null;
        }

    }
}
