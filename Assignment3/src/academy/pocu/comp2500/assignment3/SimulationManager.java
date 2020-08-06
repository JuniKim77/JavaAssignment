package academy.pocu.comp2500.assignment3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public final class SimulationManager {
    private final int NUM_COLUMNS = 16;
    private final int NUM_ROWS = 8;

    private static SimulationManager instance;
    private ArrayList<ArrayList<ArrayList<Unit>>> unitPositions = new ArrayList<>();
    private ArrayList<Unit> units = new ArrayList<>();
    private ArrayList<IThinkable> thinkableUnits = new ArrayList<>();
    private ArrayList<IMovable> movableUnits = new ArrayList<>();
    private ArrayList<ICollisionEventListener> collisionListeners = new ArrayList<>();
    private Queue<AttackIntent> attackIntents = new LinkedList<>();

    public static SimulationManager getInstance() {
        if (instance == null) {
            instance = new SimulationManager();
        }

        return instance;
    }

    public void clear() {
        unitPositions.clear();
        units.clear();
        thinkableUnits.clear();
        movableUnits.clear();
        collisionListeners.clear();
        attackIntents.clear();
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void spawn(Unit unit) {
        units.add(unit);
        unitPositions.get(unit.getPosition().getY()).get(unit.getPosition().getX()).add(unit);
        if (unit.isThinkable()) {
            registerThinkable((IThinkable) unit);
        }
        if (unit.isMovable()) {
            registerMovable((IMovable) unit);
        }
        if (unit.isListenable()) {
            registerCollisionEventListener((ICollisionEventListener) unit);
        }

    }

    public void registerThinkable(IThinkable thinkable) {
        thinkableUnits.add(thinkable);
    }

    public void registerMovable(IMovable movable) {
        movableUnits.add(movable);
    }

    public void registerCollisionEventListener(ICollisionEventListener listener) {
        collisionListeners.add(listener);
    }

    public void update() {
        // 초기화
        for (Unit each : units) {
            each.setDecision(Decision.STAY);
            each.setTarget(null);
            each.setMovePosition(null);
        }
        // 생각
        for (IThinkable each : thinkableUnits) {
            each.think(unitPositions);
        }
        // 공격 준비
        for (Unit each : units) {
            if (each.getDecision() == Decision.ATTACK) {
                AttackIntent intent = each.attack();
                if (intent == null) {
                    continue;
                }
                attackIntents.offer(intent);
            }
        }
        // 이동부
        for (IMovable each : movableUnits) {
            if (((Unit) each).getDecision() == Decision.MOVE) {
                each.move();
            }
        }
        // 충돌 해결부
        for (ICollisionEventListener each : collisionListeners) {
            each.listen(unitPositions);
        }
        for (ICollisionEventListener each : collisionListeners) {
            if (each.isBomb()) {
                AttackIntent attackIntent = ((Unit) each).attack();
                attackIntent.process(unitPositions);
            }
        }
        // 공격 실행
        while (!attackIntents.isEmpty()) {
            AttackIntent each = attackIntents.poll();
            if (each.getAttacker().getHp() > 0) {
                each.process(unitPositions);
            }
        }
        for (Unit each : units) {
            if (!each.isShielded()) {
                each.setProtactable(false);
            }
        }
        // 사후 처리
        units.removeIf(unit -> unit.getHp() == 0);
        thinkableUnits.removeIf(unit -> ((Unit) unit).getHp() == 0);
        movableUnits.removeIf(unit -> ((Unit) unit).getHp() == 0);
        collisionListeners.removeIf(unit -> ((Unit) unit).getHp() == 0);
        for (int i = 0; i < NUM_ROWS; ++i) {
            for (int j = 0; j < NUM_COLUMNS; ++j) {
                ArrayList<Unit> position = unitPositions.get(i).get(j);
                position.clear();
            }
        }
        // 맵 업데이트
        for (Unit unit : units) {
            unitPositions.get(unit.getPosition().getY()).get(unit.getPosition().getX()).add(unit);
        }
    }

    private SimulationManager() {
        for (int i = 0; i < NUM_ROWS; ++i) {
            unitPositions.add(new ArrayList<>());

            for (int j = 0; j < NUM_COLUMNS; ++j) {
                unitPositions.get(i).add(new ArrayList<>());
            }
        }
    }
}