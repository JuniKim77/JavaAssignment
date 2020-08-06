package academy.pocu.comp2500.assignment3;

public abstract class Unit {
    protected final int NUM_COLUMNS = 16;
    protected final int NUM_ROWS = 8;

    private IntVector2D position;
    private char symbol;
    private UnitType unitType;
    private int maxHp;
    private int hp;
    private int vision;
    private int areaEffect;
    private int attackPower;
    private UnitType attackType;
    private Decision decision;
    protected IntVector2D target;
    protected IntVector2D movePosition;
    protected boolean isShielded = false;
    protected boolean protactable = false;

    public Unit(IntVector2D position, char symbol, UnitType unitType, int maxHp, int vision,
                int areaEffect, int attackPower, UnitType attackType) {
        this.position = position;
        this.symbol = symbol;
        this.unitType = unitType;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.vision = vision;
        this.areaEffect = areaEffect;
        this.attackPower = attackPower;
        this.attackType = attackType;
    }


    public IntVector2D getPosition() {
        return position;
    }

    public int getHp() {
        return hp;
    }

    protected void setHp(int hp) {
        this.hp = hp;
    }

    public abstract AttackIntent attack();

    public void onAttacked(int damage) {
        hp = Math.max(0, hp - damage);
    }

    public void onSpawn() {
    }

    public char getSymbol() {
        return symbol;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getAreaEffect() {
        return areaEffect;
    }

    public int getVision() {
        return vision;
    }

    public abstract boolean isMovable();

    public abstract boolean isThinkable();

    public abstract boolean isListenable();

    public UnitType getUnitType() {
        return unitType;
    }

    public UnitType getAttackType() {
        return attackType;
    }

    public void setMovePosition(IntVector2D movePosition) {
        this.movePosition = movePosition;
    }

    public void setTarget(IntVector2D target) {
        this.target = target;
    }

    public boolean isShielded() {
        return isShielded;
    }

    public void setProtactable(boolean protactable) {
        this.protactable = protactable;
    }

}