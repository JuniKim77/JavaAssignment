package academy.pocu.comp2500.assignment3;

public class Destroy extends Unit {
    public Destroy(IntVector2D position) {
        super(position, 'D', UnitType.DESTROY, 1, 16, 16, Integer.MAX_VALUE / 2, UnitType.ALL);
        super.setDecision(Decision.ATTACK);
    }

    @Override
    public void setDecision(Decision decision) {

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
        return false;
    }
}
