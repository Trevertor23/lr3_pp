package ua.authoric.droidgame.droid;

public class TankDroid extends BaseDroid{
    private int classNum;

    public TankDroid(String name) {
        super(name, 80, 15);
        this.classNum = 2;
    }

    @Override
    public void tryAbillity(){
        //System.out.println("This droid has no special abillity!");
    }
}
