package ua.authoric.droidgame.battle;

import ua.authoric.droidgame.droid.BaseDroid;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.nio.file.Files;
import java.nio.file.Path;

public class Arena {
    private BaseDroid teamRed[] = new BaseDroid[3];
    private BaseDroid teamBlue[] = new BaseDroid[3];

    private BaseDroid oppRed;
    private BaseDroid oppBlue;

    private BaseDroid temp;
    private BaseDroid tempTeam[];
    private BaseDroid tempTeam1[];

    private static final Random rand = new Random();

    TimeUnit time = TimeUnit.MILLISECONDS;

    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_BLUE = "\u001B[34m";
    private static final String TEXT_RESET = "\u001B[0m";

    public void setAttacker(BaseDroid opp){
        this.temp = opp;
        this.oppRed = this.temp;
    }

    public void setDefender(BaseDroid opp) {
        this.temp = opp;
        this.oppBlue = this.temp;
    }

    public void setTeams(BaseDroid[] tred, BaseDroid[] tblue){
        this.tempTeam = tred;
        this.teamRed = this.tempTeam;
        //System.out.println(this.teamRed[0].getBaseHealth());
        this.tempTeam1 = tblue;
        this.teamBlue = this.tempTeam1;
    }

    public boolean teamAlive(BaseDroid[] team){
        boolean count = false;
        for(BaseDroid droid : team){
            if(droid.isAlive() && droid != null){
                count = true;
                break;
            }
        }
        if(count){
            return true;
        }
        else{
            return false;
        }
    }

    public void startDuel() throws InterruptedException, IOException {
        Path fileName = Path.of(
                "/Users/bogda/Desktop/last_logs.docx");
        Files.writeString(fileName,"Duel Battle: ");

        if(this.oppRed == null || this.oppBlue == null){
            System.out.println("Error! No opponents to start a duel!");
            return;
        }

        System.out.println("\nAttacker: " + TEXT_RED + this.oppRed.getName() + TEXT_RESET + "[" + this.oppRed.getHealth() + "], Power - " + this.oppRed.getPower() +
                "\nDefender: " + TEXT_BLUE + this.oppBlue.getName() + TEXT_RESET + "[" + this.oppBlue.getHealth() + "], Power - " + this.oppBlue.getPower());
        time.sleep(1000L);

        int i = 0;
        do{
            System.out.println("---------------------------------------");
            /*int d1 = rand.nextInt(this.oppBlue.getPower());
            int d2 = rand.nextInt(this.oppRed.getPower());
            this.oppRed.dealDamage(d1);
            this.oppBlue.dealDamage(d2);*/

            System.out.println(TEXT_RED + this.oppRed.getName() + TEXT_RESET + " dealed " + this.oppRed.attacks(this.oppBlue) + " damage! ");
            this.oppRed.tryAbillity();
            time.sleep(1000L);


            System.out.println( TEXT_BLUE + this.oppBlue.getName() + TEXT_RESET + " responded with " +
                    this.oppBlue.attacks(this.oppRed) + " damage!");
            this.oppBlue.tryAbillity();

            time.sleep(1000L);

            System.out.println("\n" + TEXT_RED + this.oppRed.getName() + TEXT_RESET + "[" + this.oppRed.getHealth() + "]" +
                    "\n" + TEXT_BLUE + this.oppBlue.getName() + TEXT_RESET + "[" + this.oppBlue.getHealth() + "]");
            time.sleep(1000L);

            Files.writeString(fileName, Files.readString(fileName) + "\n" + TEXT_RED + this.oppRed.getName() + TEXT_RESET + "[" + this.oppRed.getHealth() + "]" +
                    "\n" + TEXT_BLUE + this.oppBlue.getName() + TEXT_RESET + "[" + this.oppBlue.getHealth() +
                    "]\n-------------------------");
        }
        while(this.oppRed.isAlive() && this.oppBlue.isAlive());

        if(!this.oppRed.isAlive() && !this.oppBlue.isAlive()) {
            System.out.println("\nNo winner... It`s a tie!!!");
            Files.writeString(fileName,Files.readString(fileName) + "\nNo winner... It`s a tie!!!");
        }
        else if(!this.oppRed.isAlive() && this.oppBlue.isAlive()){
            System.out.println("\nCongrats! " + TEXT_BLUE + this.oppBlue.getName() + TEXT_RESET + " is a winner today!");
            Files.writeString(fileName,Files.readString(fileName) + "\nCongrats! " + TEXT_BLUE + this.oppBlue.getName() + TEXT_RESET + " is a winner today!");
        }
        else if(this.oppRed.isAlive() && !this.oppBlue.isAlive()) {
            System.out.println("\nCongrats! " + TEXT_RED + this.oppRed.getName() + TEXT_RESET + " is a winner today!");
            Files.writeString(fileName, Files.readString(fileName) + "\nCongrats! " + TEXT_RED + this.oppRed.getName() + TEXT_RESET + " is a winner today!");
        }
        this.oppRed.resetHealth();
        this.oppBlue.resetHealth();
    }

    private void printTeam(BaseDroid[] team){
        for(BaseDroid droid : team){
            if(droid != null && droid.isAlive())
                System.out.println(droid.getName() + " [H: " + droid.getHealth() + ", P: " + droid.getPower() + "]");
        }
        if(!teamAlive(team)){
            System.out.println("No one survived!");
        }
    }

    public short getTeamCount(BaseDroid[] team){
        short count = 0;
        for(BaseDroid droid : team){
            if(droid != null)
                count++;
        }

        return count;
    }

    public void startTeamBattle() throws InterruptedException, IOException {
        Path fileName = Path.of(
                "/Users/bogda/Desktop/last_logs.docx");
        Files.writeString(fileName,"Team Battle: ");

        if(getTeamCount(teamRed) == 0 || getTeamCount(teamBlue) == 0){
            System.out.println("Error! No opponents to start a duel!");
            return;
        }

        System.out.println(TEXT_RED + "Team Red:" + TEXT_RESET);
        printTeam(teamRed);

        time.sleep(1000L);

        System.out.println(TEXT_BLUE + "\n\nTeam Blue:" + TEXT_RESET);
        printTeam(teamBlue);

        time.sleep(2000L);

        short redCount = getTeamCount(teamRed);
        short blueCount = getTeamCount(teamBlue);


        //int i = 0;
        do{
            int ri;
            do{
                ri = rand.nextInt(0,redCount);
            }while(!teamRed[ri].isAlive());
            int bi;
            do{
                bi = rand.nextInt(0,blueCount);
            }while(!teamBlue[bi].isAlive());

            System.out.println("---------------------------------------");

            System.out.println(TEXT_RED + teamRed[ri].getName() + TEXT_RESET + " dealed " + teamRed[ri].attacks(teamBlue[bi]) + " damage! ");
            teamRed[ri].tryAbillity();
            time.sleep(1000L);


            System.out.println( TEXT_BLUE + teamBlue[bi].getName() + TEXT_RESET + " responded with " +
                    teamBlue[bi].attacks(teamRed[ri]) + " damage!");
            teamBlue[bi].tryAbillity();

            time.sleep(1000L);

            System.out.println("\n" + TEXT_RED + teamRed[ri].getName() + TEXT_RESET + "[" + teamRed[ri].getHealth() + "]" +
                    "\n" + TEXT_BLUE + teamBlue[bi].getName() + TEXT_RESET + "[" + teamBlue[bi].getHealth() + "]");
            time.sleep(1000L);

            Files.writeString(fileName, Files.readString(fileName) + "\n" + TEXT_RED + teamRed[ri].getName() + TEXT_RESET + "[" + teamRed[ri].getHealth() + "]" +
                    "\n" + TEXT_BLUE + teamBlue[bi].getName() + TEXT_RESET + "[" + teamBlue[bi].getHealth() +
                    "]\n-------------------------");
        }
        while(teamAlive(teamRed) && teamAlive(teamBlue));

        if(!teamAlive(teamRed) && !teamAlive(teamBlue)) {
            System.out.println("\nNo team wins this fight... It`s a tie!!!");
            Files.writeString(fileName,Files.readString(fileName) + "\nNo team wins this fight... It`s a tie!!!\n");
        }
        else if(!teamAlive(teamRed) && teamAlive(teamBlue)){
            System.out.println("\nCongrats! " + TEXT_BLUE + "Blue Team" + TEXT_RESET + " is a winner today!\n");
            Files.writeString(fileName,Files.readString(fileName) + "\nCongrats! " + TEXT_BLUE + "Blue Team" + TEXT_RESET + " is a winner today!");
        }
        else if(teamAlive(teamRed) && !teamAlive(teamBlue)) {
            System.out.println("\nCongrats! " + TEXT_RED + "Red Team" + TEXT_RESET + " is a winner today!\n");
            Files.writeString(fileName, Files.readString(fileName) + "\nCongrats! " + TEXT_RED + "Red Team" + TEXT_RESET + " is a winner today!");
        }

        System.out.println(TEXT_RED + "Alive in Team Red:" + TEXT_RESET);
        printTeam(teamRed);

        time.sleep(1000L);

        System.out.println(TEXT_BLUE + "\nAlive in Team Blue:" + TEXT_RESET);
        printTeam(teamBlue);

        time.sleep(2000L);

        for(int i = 0;i < teamRed.length;i++){ teamRed[i].resetHealth();}
        for(int i = 0;i < teamBlue.length;i++){ teamBlue[i].resetHealth();}
    }

}
