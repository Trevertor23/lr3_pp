package ua.authoric.droidgame.droid;

import java.util.Random;

public class HealerDroid extends BaseDroid{
    private int classNum;

    private Random rand = new Random();
    public HealerDroid(String name) {
        super(name, 50, 20);
        this.classNum = 3;
    }

    @Override
    public void tryAbillity(){
        if(rand.nextFloat(0,1) < 0.4){
            this.health+=5;
            System.out.println("Healer has healed himself for 5 health points!");
        }
    }

}
