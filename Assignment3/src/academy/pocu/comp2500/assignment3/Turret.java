package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Turret extends Unit implements IThinkable {
    public Turret(IntVector2D position) {
        super(position, 'U', UnitType.GROUND, 99, 2,
                0, 7, UnitType.SKY);
    }

    @Override
    public AttackIntent attack() {
        return new AttackIntent(this.target, this);
    }

    @Override
    public boolean isMovable() {
        return false;
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
        final int dy[] = {-1, 0, 1, 0, -1, 0};
        final int dx[] = {0, 1, 0, -1, 0, 0};
        final int count[] = {1, 1, 2, 2, 2, 1};
        int row = getPosition().getY();
        int col = getPosition().getY();
        int minHp = Integer.MAX_VALUE;
        IntVector2D target = null;

        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < count[i]; ++j) {
                if (row < 0 || row >= NUM_ROWS || col < 0 || col >= NUM_COLUMNS) {
                    continue;
                }
                ArrayList<Unit> position = worldMap.get(row).get(col);
                for (int k = 0; k < position.size(); ++k) {
                    if (position.get(k) != this
                            && position.get(k).getUnitType() == UnitType.SKY
                            && position.get(k).getHp() < minHp) {
                        minHp = position.get(k).getHp();
                        target = position.get(k).getPosition();
                    }
                }

                row += dy[i];
                col += dx[i];
            }
        }
        if (target != null) {
            super.setDecision(Decision.ATTACK);
            super.target = new IntVector2D(target.getX(), target.getY());
        } else {
            super.setDecision(Decision.STAY);
            super.target = null;
        }
    }
}
