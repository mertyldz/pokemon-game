package model;

public class Pokemon {
    private String name;
    private int health;
    private int damage;
    private ElementType elementType;
    private SpecialPower specialPower;
    private WeatherType weakWeather;


    public Pokemon(String name, int health, int damage, ElementType elementType, SpecialPower specialPower, WeatherType weakWeather) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.elementType = elementType;
        this.specialPower = specialPower;
        this.weakWeather = weakWeather;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public ElementType getElementType() {
        return elementType;
    }

    public void setElementType(ElementType elementType) {
        this.elementType = elementType;
    }

    public SpecialPower getSpecialPower() {
        return specialPower;
    }

    public void setSpecialPower(SpecialPower specialPower) {
        this.specialPower = specialPower;
    }

    public WeatherType getWeakWeather() {
        return weakWeather;
    }

    public void setWeakWeather(WeatherType weakWeather) {
        this.weakWeather = weakWeather;
    }

    public int specialAttack() {
        if (getSpecialPower().getRemainRight() > 0) {
            this.specialPower.setRemainRight(this.specialPower.getRemainRight() - 1);
            return this.damage + this.getSpecialPower().getDamage();
        } else {
            System.out.println("You can not use special power!");
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                ", elementType=" + elementType +
                ", specialPower=" + specialPower +
                ", weakWeather=" + weakWeather +
                '}';
    }
}
