public class Item {
    private String itemName;
    private int healingPoint;
    private int haveit;

    public Item(String itemName, int healingPoint, int haveit) {
        this.itemName = itemName;
        this.healingPoint = healingPoint;
        this.haveit = haveit;
    }

    public String getItemName() {
        return itemName;
    }

    public int getHealingPoint() {
        return healingPoint;
    }

    public int getHaveit() {
        return haveit;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setHealingPoint(int healingPoint) {
        this.healingPoint = healingPoint;
    }

    public void setHaveit(int haveit) {
        this.haveit = haveit;
    }
}
