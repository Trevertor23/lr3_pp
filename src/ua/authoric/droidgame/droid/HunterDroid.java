package ua.authoric.droidgame.droid;

import java.util.Random;

public class HunterDroid extends BaseDroid{
    private int classNum;
    private boolean dodgeStatus = false;

    private Random rand = new Random();

    public HunterDroid(String name) {
        super(name, 40, 20);
        this.classNum = 1;
    }

    @Override
    public int attacks(BaseDroid defender) {
        return super.attacks(defender);
    }

    @Override
    public void dealDamage(int damage) {
        if(this.dodgeStatus){
            System.out.println("Attack dodged:");
            this.dodgeStatus = false;
        }
        else {
            this.health -= damage;
            if (this.health < 0) {
                this.health = 0;
            }
        }
    }

    @Override
    public void tryAbillity(){
        if(!dodgeStatus) {
            if (rand.nextFloat(0, 1) < 0.3) {
                dodgeStatus = true;
                System.out.println("Hunter have used his abillity! He will dodge the next attack!");
            }
        }
    }
}
