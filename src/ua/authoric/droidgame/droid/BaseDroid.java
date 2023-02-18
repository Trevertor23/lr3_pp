package ua.authoric.droidgame.droid;

import java.util.Random;

public class BaseDroid {
    protected String name;
    protected int health;
    protected int power;
    protected int baseHealth;

    private Random rand = new Random();

    public BaseDroid(String name, int health, int power) {
        this.name = name;
        this.health = health;
        this.baseHealth = health;
        this.power = power;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getName() {
        return this.name;
    }

    public int getPower() {
        return this.power;
    }

    public int getHealth(){
        return this.health;
    }

    public void dealDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public void resetHealth(){
        this.health = this.baseHealth;
    }

    public int attacks(BaseDroid defender){
        int minDamage = Math.round(this.power * 0.3f);
        int damage = rand.nextInt(minDamage,this.power);
        defender.dealDamage(damage);
        return damage;
    }

    public boolean isAlive() {
        return health > 0;
    }
    public void tryAbillity(){
        System.out.println("This droid has no special abillity!");
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", health=" + health;
    }


}
