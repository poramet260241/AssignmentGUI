
public class Novice extends GameCharacter{
    private int manaPoint;
    private int maxManapoint;
    private int monsterKill;
    private int money;
    private int exp;

    public Novice(String name) {
        super(name);
        setHealthPoint(100);
        setMaxHealthPoint(100);
        manaPoint = 100;
        maxManapoint = 100;
        money = 0;
        monsterKill = 0;
        setDamage(30);

        Bag bag = new Bag();
    }

    public int getManaPoint() {
        return manaPoint;
    }

    public int getMaxManapoint() {
        return maxManapoint;
    }

    public int getMonsterKill() {
        return monsterKill;
    }

    public int getMoney() {
        return money;
    }

    public int getExp() {
        return exp;
    }

    public void setManaPoint(int manaPoint) {
        this.manaPoint = manaPoint;
    }

    public void setMonsterKill(int monsterKill) {
        this.monsterKill += monsterKill;
    }

    public void setMoney(int money) {
        this.money += money;
    }

    public void setExp(int exp) {
        this.exp += exp;
    }


}
