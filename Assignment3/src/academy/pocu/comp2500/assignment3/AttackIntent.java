package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;
import java.util.HashMap;

public class AttackIntent {
    private IntVector2D targetPosition;
    private Unit attacker;
    private HashMap<UnitType, Boolean> types = new HashMap<>();

    public AttackIntent(IntVector2D targetPosition, Unit attacker) {
        this.targetPosition = targetPosition;
        this.attacker = attacker;
        types.put(UnitType.GROUND, false);
        types.put(UnitType.SKY, false);
        types.put(UnitType.INVISIBLE, true);

        if (attacker.getAttackType() == UnitType.ALL) { // 마린, 망령
            types.replace(UnitType.GROUND, true);
            types.replace(UnitType.SKY, true);
        } else if (attacker.getUnitType() == UnitType.INVISIBLE) { // 지뢰류
            types.replace(UnitType.GROUND, true);
        } else if (attacker.getUnitType() == UnitType.DESTROY) { // 파괴자
            types.replace(UnitType.GROUND, true);
            types.replace(UnitType.SKY, true);
        } else if (attacker.getAttackType() == UnitType.SKY) { // 터렛
            types.replace(UnitType.SKY, true);
            types.replace(UnitType.INVISIBLE, false);
        } else { // 탱크
            types.replace(attacker.getAttackType(), true);
        }
    }

    public IntVector2D getTargetPosition() {
        return targetPosition;
    }

    public Unit getAttacker() {
        return attacker;
    }

    public void process(ArrayList<ArrayList<ArrayList<Unit>>> worldMap) {
        int power = attacker.getAttackPower();
        int effectArea = attacker.getAreaEffect();
        final int dy[] = {0, 1, 0, -1, -1};
        final int dx[] = {1, 0, -1, 0, -1};
        int distance = 1;
        int row = targetPosition.getY();
        int col = targetPosition.getX();

        // 타겟 위치 처리
        for (Unit each : worldMap.get(row).get(col)) {
            if (each != this.attacker && types.get(each.getUnitType())) {
                each.onAttacked(power);
                continue;
            }
            if (each.getUnitType() == UnitType.INVISIBLE) {
                each.onAttacked(power);
            }
        }

        row += dy[4];
        col += dx[4];
        while (distance <= effectArea) {
            double damage = power * (1.0 - distance / (double) (effectArea + 1));
            for (int i = 0; i < 4; ++i) {
                for (int k = 0; k < distance * 2; ++k) {
                    if (row < 0 || row >= worldMap.size() || col < 0 || col >= worldMap.get(0).size()) {
                        row += dy[i];
                        col += dx[i];
                        continue;
                    }

                    for (Unit each : worldMap.get(row).get(col)) {
                        if (types.get(each.getUnitType()) && each != attacker) {
                            each.onAttacked((int) damage);
                        }
                    }
                    row += dy[i];
                    col += dx[i];
                }
            }
            row += dy[4];
            col += dx[4];
            ++distance;
        }
    }
}

