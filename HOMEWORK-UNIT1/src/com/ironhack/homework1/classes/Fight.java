package com.ironhack.homework1.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fight {

    private static List<Object> graveyard = new ArrayList<>();

    public static List<Object> getGraveyard() {

        return graveyard;
    }


    public void fightToDeath(Character yourTeam, Character enemy) throws InterruptedException  {
        while(true) {
            if((checkHp(yourTeam) == 0) || checkHp(enemy) == 0) {
                break;
            } else {
                try {
                    if ((yourTeam instanceof Warrior)) {
                        ((Warrior) yourTeam).attack(enemy);
                    } else {
                        ((Wizard) yourTeam).attack(enemy);
                    }
                    if (checkHp(yourTeam) == 0 || checkHp(enemy) == 0) break;
                    if (enemy instanceof Warrior) {
                        ((Warrior) enemy).attack(yourTeam);
                    } else {
                        ((Wizard) enemy).attack(yourTeam);
                    }
                    System.out.println("Your team health: " + yourTeam.getHp());
                    System.out.println("Enemy health: " + enemy.getHp());
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("\nEnd of round!");
    }

    // CHECK IF DEATH OR ALIVE
    public void checkFighterHp(Object fighter, List<Object> party) {
        if(checkHp(fighter) == 0) {
            graveyard.add(fighter);
        } else {
            party.add(fighter);
        }
    }

         public static int checkHp(Object fighter) {
               int hp = ((Character) fighter).getHp();
               return hp;
          }

    // RANDOM PARTY USING THE RANDOM GENERATOR
    public List<Object> createRandomParty(int teamSize) {

        List<Object> randomParty = new ArrayList<Object>();

        Object randomObject;
        int typeSet;
        String randomID;
        String randomName;
        int randomHP;
        int randomStamina = 0;
        int randomStrength = 0;
        int randomMana = 0;
        int randomIntelligence = 0;

        for(int i = 0; i < teamSize; i++) {
            typeSet = RandomGenerator.getInstance().randomInt(1, 2);
            randomName = RandomGenerator.getInstance().generate();
            randomID = getRandomID();
            if(typeSet == 1) {
                randomHP = RandomGenerator.getInstance().randomInt(100, 200);
                randomStamina = RandomGenerator.getInstance().randomInt(10, 50);
                randomStrength = RandomGenerator.getInstance().randomInt(1, 10);
                randomObject = new Warrior(randomID, randomName, randomHP, true, randomStamina, randomStrength);
                randomParty.add(randomObject);
            }
            if(typeSet == 2) {
                randomHP = RandomGenerator.getInstance().randomInt(50, 100);
                randomIntelligence = RandomGenerator.getInstance().randomInt(1, 50);
                randomMana = RandomGenerator.getInstance().randomInt(10, 50);
                randomObject = new Wizard(randomID, randomName, randomHP, true, randomMana, randomIntelligence);
                randomParty.add(randomObject);
            }
        }
        return randomParty;
    }

    // RANDOM ID GENERATOR
    public static String getRandomID() {
        String id = "";
        String dictionary = "01234567890";
        for(int i = 0; i < 2; i++) {
            id += dictionary.charAt(getRandomNumber(0, dictionary.length()));
        }
        return id;
    }

    // SELECT RANDOM FIGHTER
    public Object randomFighters(List<Object> party) {
        Object fighter = party.get(getRandomNumber(0, party.size()));
        party.remove(fighter);
        return fighter;
    }

    // RANDOM NUMBER GENERATOR
    public static int getRandomNumber(int min, int max) {

        return (int) ((Math.random() * (max - min)) + min);
    }

    // SELECT UR FIGHTER MODE
    public Object selectFighter(List<Object> party) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please write the name of the fighter you would like to pick.\n" +
                "These are the fighters you can choose: " + toString(party));
        String fighter = scanner.nextLine();
        Object myFighter = null;
        for (Object f : party) {
            if (((Character) f).getName().equals(fighter)) {
                myFighter = f;
            }
        }
        party.remove(myFighter);
        return myFighter;
    }

    // UTILS METHOD TO STRING
    public String toString(List<Object> party) {
        List<String> names = new ArrayList<String>();
        String name;
        for (Object fighter : party) {
            name = ((Character) fighter).getName();
            names.add(name);
        }
        return names.toString().replace("[", "").replace("]", "");
    }
}
