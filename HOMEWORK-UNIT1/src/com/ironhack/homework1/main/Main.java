package src.com.ironhack.homework1.main;

import com.ironhack.homework1.classes.Character;
import com.ironhack.homework1.classes.Fight;
import com.ironhack.homework1.classes.InputOutput;
import com.ironhack.homework1.classes.LectorCSV;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static List<Object> myTeam = new ArrayList<>();
    private static List<Object> enemyTeam = new ArrayList<>();
    private static List<Object> graveyard = new ArrayList<>();


    public static void main(String[] args) throws InterruptedException {
        playGame();
    }

    public static void playGame() throws InterruptedException {
        greetUser();
        System.out.println("\n" + "It's time to create your team.");
        createTeam();

        System.out.println("Great. Let's start!");
        chooseGameMode();
    }

    public static void greetUser() {
        System.out.println("\n" +
                "#                                                      #\n" +
                "#            - WARRIORS VS WIZARDS -                   #\n" +
                "#                 Created by Debuggirls                #\n" +
                "#                                                      #\n");

        Scanner scanner = new Scanner(System.in);
        System.out.println( "Greetings and welcome to the greatest of tournaments! \n"+
                            "Before we start, what's your name?");
        String name = scanner.nextLine();
        System.out.println("What a lovely name! We are happy to have you here, " + name + ".");
    }

    public static void createTeam() throws InterruptedException {
        System.out.println( "Select an option: \n" +
                "  1) Create your own team\n" +
                "  2) Randomly create team\n" +
                "  3) Import team from CVS \n" +
                "  4) Exit\n ");
        Scanner scanner = new Scanner(System.in);

        switch (scanner.nextLine()) {
            case "1":
                createYourTeam();
                break;
            case "2":
                randomTeam();
                break;
            case "3":
                importCsv();
                break;
            case "q":
                quitGame();
                break;
            default:
                System.out.println("This is not a valid option. Try again.");
                createTeam();
        }
    }

    public static int getTeamsize(List<Object> myList) {

        return myList.size();
    }

    // CREATE UR TEAM
    public static void createYourTeam() throws InterruptedException {
        Fight fight = new Fight();
        InputOutput io = new InputOutput();
        // OUR TEAM
        myTeam = io.createParty();
        int teamsize = getTeamsize(myTeam);
        // ENEMY TEAM ALLWAYS RANDOM
        enemyTeam = fight.createRandomParty(teamsize);
        System.out.println("The teams are set and ready to fight! Each team has " + teamsize + " team member(s).");
    }

    // RANDOM TEAM
    public static void randomTeam() {
        Fight fight = new Fight();
        Scanner scanner = new Scanner(System.in);
        System.out.println(" How many team members should each team have? ");
        while (true) {
            int teamsize = scanner.nextInt();
            if (teamsize > 0) {
                myTeam = fight.createRandomParty(teamsize);
                enemyTeam = fight.createRandomParty(teamsize);
                System.out.println("The teams are set and ready to fight! Each team has " + teamsize + " team member(s).");
                break;
            } else {
                System.out.println("Wrong size. Please try again.");
            }
        }
    }

    // IMPORT TEAM FROM CSV
    public static void importCsv() {
        Fight tempFight = new Fight();
        LectorCSV io = new LectorCSV();
        Scanner aScanner = new Scanner(System.in);
        System.out.println("Good choice! What is the name of the file ?\n"+
                "We recomend you to use .csv extension.\n" +
                "If you don't have a file:\n partyof6.csv,\n partyof3.csv,\n partyof10.csv\n are our recommendations.");
        String aFile = aScanner.next();
        // OUR TEAM
        myTeam = io.readFile(aFile);
        int teamsize = getTeamsize(myTeam);
        // OTHER TEAM ALWAYS RANDOM
        enemyTeam = tempFight.createRandomParty(teamsize);
        System.out.println("The teams are set and ready to fight! Each team has " + teamsize + " team member(s).");
    }


    // EXIT GAME
    public static void quitGame() throws InterruptedException {
        myTeam.clear();
        enemyTeam.clear();
        playGame();
    }

    // CHOOSE GAME MODE MENU
    public static void chooseGameMode() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("One last question. How would you like play? \n" +
                "  1) Selecting your player\n" +
                "  2) Watch the Battle unfold\n" +
                "  3) Exit \n" );
        switch (scanner.nextLine()) {
            case "1":
                System.out.println("Great. Let's start!");
                playNormalBattle();
                break;
            case "2":
                System.out.println("Great. Let's start!");
                playWithOneClick();
                break;
            case "q":
                quitGame();
                break;
            default:
                System.out.println("This is not a valid option. Try again.");
                chooseGameMode();
        }
    }

    // NORMAL BATTLE AUTOMATIC
    public static void playNormalBattle() throws InterruptedException {
        Fight fight = new Fight();
        while(hasPartyFighters()) {
            Character champion = (Character) fight.selectFighter(myTeam);
            Character opponent = (Character) fight.randomFighters(enemyTeam);
            fight.fightToDeath(champion, opponent);
            fight.checkFighterHp(champion, myTeam);
            fight.checkFighterHp(opponent, enemyTeam);
        }
        graveyard = Fight.getGraveyard();
        evaluateGame();
    }

    // CHOOSE PLAYER BY PLAYER
    public static void playWithOneClick() throws InterruptedException {
        Fight fight = new Fight();
        while(hasPartyFighters()) {
            Character champion = (Character) fight.randomFighters(myTeam);
            Character opponent = (Character) fight.randomFighters(enemyTeam);
            fight.fightToDeath(champion, opponent);
            fight.checkFighterHp(champion, myTeam);
            fight.checkFighterHp(opponent, enemyTeam);
        }
        graveyard = Fight.getGraveyard();
        evaluateGame();
    }

    // CHECK IF PLAYERS IN PARTY
    public static boolean hasPartyFighters() {
        if (myTeam.size() == 0 || enemyTeam.size() == 0) {
            return false;
        }
        return true;
    }

    // EVALUATE GAME AND GRAVEYARD SHOW
    public static void evaluateGame() throws InterruptedException {
        Fight fight = new Fight();

        if(myTeam.size() == 0) {
            System.out.println("Your team has lost the tournament. \n" +
                    "These are the fighters that survived until the end: " + fight.toString(enemyTeam));
        } else {
            System.out.println("Congratulations! Your team has won! \n" +
                    "These are the fighters that survived until the end: " + fight.toString(myTeam));
        }

        System.out.println("These are the fighters that succumbed their wounds: " + fight.toString(graveyard));

        Scanner scanner = new Scanner(System.in);
        System.out.println("If you want to start a new game, please press q. Press Enter to end your adventure.");
        String newGame = scanner.nextLine();

        if(newGame.equals("q")) {
            quitGame();
        }
        scanner.close();
    }

}
