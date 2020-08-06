package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;

public class Mine extends Unit implements ICollisionEventListener {
    protected int maxTouchedCount;
    protected int touchedCount = 0;
    protected boolean bomb;

    public Mine(IntVector2D position, int maxTouchedCount) {
        super(position, 'N', UnitType.INVISIBLE, 1, 0,
                0, 10, UnitType.GROUND);
        this.maxTouchedCount = maxTouchedCount;
        this.touchedCount = 0;
        this.bomb = false;
    }

    protected Mine(IntVector2D position, char symbol, UnitType unitType, int maxHp, int vision,
                   int areaEffect, int attackPower, UnitType attackType, int maxTouchedCount) {
        super(position, symbol, unitType, maxHp, vision,
                areaEffect, attackPower, attackType);
        this.maxTouchedCount = maxTouchedCount;
        this.touchedCount = 0;
    }

    @Override
    public void listen(ArrayList<ArrayList<ArrayList<Unit>>> worldMap) {
        ArrayList<Unit> units = worldMap.get(getPosition().getY()).get(getPosition().getX());

        for (Unit each : units) {
            if (each.getUnitType() == UnitType.GROUND) {
                ++touchedCount;
            }
        }

        if (touchedCount >= maxTouchedCount) {
            bomb = true;
        }
    }

    @Override
    public AttackIntent attack() {
        return new AttackIntent(getPosition(), this);
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public boolean isThinkable() {
        return false;
    }

    @Override
    public boolean isListenable() {
        return true;
    }

    @Override
    public boolean isBomb() {
        return bomb;
    }
}
