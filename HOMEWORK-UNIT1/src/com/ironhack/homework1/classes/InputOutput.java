package com.ironhack.homework1.classes;

import com.ironhack.homework1.interfaces.Attacker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputOutput {

    public List<Object> createParty() {
        List<Object> tempList = new ArrayList<>();

        int typeSet = 0;
        String tempID = "";
        String tempName;
        int tempHP;
        int tempStamina = 0;
        int tempStrength = 0;
        int tempMana = 0;
        int tempIntelligence = 0;
        boolean done = false;

        Attacker tempObject;
        String choice;

        Scanner aScanner = new Scanner(System.in);
        while(!done) {
            System.out.println("Will your fighter be a warrior or a wizard? Type '1' for warrior or '2' for wizard.");
            choice = aScanner.next();
            if (choice.equals("1")) {
                typeSet = 1;
            } else if (choice.equals("2")){
                typeSet = 2;
            }
            else {
                System.out.println("This is not a valid option. Try again.");
                continue;
            }
            while(tempID.length() < 2) {
                System.out.println("Enter an ID two numbers.");
                tempID = aScanner.next();
            }
            System.out.println("What is their name?");
            tempName = aScanner.next();
            if (typeSet == 1) {
                System.out.println("What is their HP? It should be between 100 and 200.");
                tempHP = aScanner.nextInt();
                System.out.println("What is their Stamina? It should be between 10 and 50.");
                tempStamina = aScanner.nextInt();
                System.out.println("What is their Strength? It should be between 1 and 10");
                tempStrength = aScanner.nextInt();
                tempObject = new Warrior(tempID,tempName,tempHP, true, tempStamina,tempStrength);
                tempList.add(tempObject);
                tempID = "";
            } else if (typeSet == 2) {
                System.out.println("What is their HP? It should be between 50 and 100.");
                tempHP = aScanner.nextInt();
                System.out.println("What is their Mana? It should be between 10 and 50.");
                tempMana = aScanner.nextInt();
                System.out.println("What is their Intelligence? It should be between 1 and 50");
                tempIntelligence = aScanner.nextInt();
                tempObject = new Wizard(tempID,tempName,tempHP, true, tempMana,tempIntelligence);
                tempList.add(tempObject);
                tempID = "";
            }
            System.out.println("Done! Would you like to create another character? Press 1 for YES and 2 for NO");
            if (aScanner.next().equals("2")) {
                done = true;
            }
        }
        return tempList;
    }
}
