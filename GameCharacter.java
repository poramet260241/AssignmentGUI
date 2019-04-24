public class GameCharacter {

    private String name;
    private int healthPoint;
    private int maxHealthPoint;
    private int level;
    private int damage;

    public GameCharacter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public int getLevel() {
        return level;
    }

    public int getDamage() {
        return damage;
    }

    public int getMaxHealthPoint() {
        return maxHealthPoint;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public void setMaxHealthPoint(int maxHealthPoint) {
        this.maxHealthPoint = maxHealthPoint;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isDead(){
        if(healthPoint == 0)
            return true;
        return false;
    }

    public void attack(int damage){
        this.healthPoint -= damage;
        if(healthPoint < 0)
            healthPoint = 0;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void damage(int healthPoint){
        this.healthPoint -= healthPoint;
        if(this.healthPoint < 0)
            this.healthPoint = 0;
    }

    public void attack(GameCharacter enemy){
        enemy.damage(damage);
    }
}
