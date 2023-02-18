package ua.authoric.droidgame;

import ua.authoric.droidgame.droid.BaseDroid;
import ua.authoric.droidgame.battle.Arena;
import ua.authoric.droidgame.droid.HealerDroid;
import ua.authoric.droidgame.droid.HunterDroid;
import ua.authoric.droidgame.droid.TankDroid;

import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;


public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        int menu;
        Arena arena = new Arena();
        Scanner inp = new Scanner(System.in);
        BaseDroid redDroid = null, blueDroid = null;
        BaseDroid teamRed[] = new BaseDroid[3];
        BaseDroid teamBlue[] = new BaseDroid[3];

        do{
            System.out.println("\nChoose your action: ");
            System.out.println("1. Team Battle");
            System.out.println("2. Duel");
            System.out.println("3. Get last battle logs");
            System.out.println("4. Exit the game");


            menu = inp.nextInt();

            if(menu == 1){
                do{
                    System.out.println("\n1. Add droids to Team Red");
                    System.out.println("2. Add droids to Team Blue");
                    System.out.println("3. Battle!");
                    System.out.println("4. Back to menu");
                    //int i = 0;
                    menu = inp.nextInt();
                    switch(menu){
                        case 1:
                            for(int i = 0;i < teamRed.length;i++){
                                System.out.println("\nChoose class for "+ (i+1) + " droid: ");
                                System.out.println("1. Hunter ");
                                System.out.println("2. Tank ");
                                System.out.println("3. Healer ");

                                String side = "Red";
                                menu = inp.nextInt();

                                if (menu == 1) {
                                    teamRed[i] = new HunterDroid(side + " Hunter " + (i+1));
                                    //System.out.println("herer");
                                } else if (menu == 2) {
                                    teamRed[i] = new TankDroid(side + " Tank " + (i+1));
                                } else if (menu == 3) {
                                    teamRed[i] = new HealerDroid(side + " Healer " + (i+1));
                                }
                                //i++;
                            }
                            break;
                        case 2:
                            for(int i = 0;i < teamBlue.length;i++){
                                System.out.println("\nChoose class for "+ (i+1) + " droid: ");
                                System.out.println("1. Hunter ");
                                System.out.println("2. Tank ");
                                System.out.println("3. Healer ");

                                String side = "Blue";
                                menu = inp.nextInt();

                                if (menu == 1) {
                                    teamBlue[i] = new HunterDroid(side + " Hunter " + (i+1));
                                } else if (menu == 2) {
                                    teamBlue[i] = new TankDroid(side + " Tank " + (i+1));
                                } else if (menu == 3) {
                                    teamBlue[i] = new HealerDroid(side + " Healer " + (i + 1));
                                }
                            }
                            break;
                        case 3:
                            arena.setTeams(teamRed,teamBlue);
                            arena.startTeamBattle();
                            break;
                    }
                }while(menu!=4);
                menu = 0;
            }

            else if(menu == 2){
                do{
                    System.out.println("\n1. Set Red droid");
                    System.out.println("2. Set Blue droid");
                    System.out.println("3. Battle!");
                    System.out.println("4. Back to menu");

                    menu = inp.nextInt();

                    if(menu == 1 || menu == 2){
                        System.out.println("\nChoose class: ");
                        System.out.println("1. Hunter ");
                        System.out.println("2. Tank ");
                        System.out.println("3. Healer ");

                        String side = (menu==1 ? "Red" : "Blue");
                        menu = inp.nextInt();
                        if(side == "Red") {
                            if (menu == 1) {
                                redDroid = new HunterDroid(side + " Hunter");
                            } else if (menu == 2) {
                                redDroid = new TankDroid(side + " Tank");
                            } else if (menu == 3) {
                                redDroid = new HealerDroid(side + " Healer");
                            }
                        }
                        else if(side == "Blue") {
                            if (menu == 1) {
                                blueDroid = new HunterDroid(side + " Hunter");
                            } else if (menu == 2) {
                                blueDroid = new TankDroid(side + " Tank");
                            } else if (menu == 3) {
                                blueDroid = new HealerDroid(side + " Healer");
                            }
                        }
                    }
                    else if(menu==3){
                        if(redDroid != null && blueDroid != null){
                            arena.setAttacker(redDroid);
                            arena.setDefender(blueDroid);
                            arena.startDuel();
                        }
                    }
                }while(menu!=4);
                redDroid = null;
                blueDroid = null;
                menu = 0;
            }

            else if(menu == 3){
                Path fileName = Path.of(
                        "/Users/bogda/Desktop/last_logs.docx");
                System.out.println(Files.readString(fileName));
            }

        }while(menu!=4);

        System.out.println("\n\n Thank you for playing this game!");
    }
}