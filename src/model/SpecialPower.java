package model;

public class SpecialPower {
    private String name;
    private int damage;
    private int remainRight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpecialPower(String name, int damage, int remainRight) {
        this.name = name;
        this.damage = damage;
        this.remainRight = remainRight;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRemainRight() {
        return remainRight;
    }

    public void setRemainRight(int remainRight) {
        this.remainRight = remainRight;
    }

    @Override
    public String toString() {
        return "SpecialPower{" +
                "name='" + name + '\'' +
                ", damage=" + damage +
                ", remainRight=" + remainRight +
                '}';
    }
}
