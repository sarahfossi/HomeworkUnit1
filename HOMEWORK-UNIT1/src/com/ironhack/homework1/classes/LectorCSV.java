package com.ironhack.homework1.classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static src.com.ironhack.homework1.main.Main.importCsv;


public class LectorCSV {

    public List<Object> readFile(String aFile) {
        List<Object> tempList = new ArrayList<>();

        String row;
        String typeSetter;
        Object tempObject;
        String anID;
        String aName;
        int hp;
        boolean isAlive;
        int stamina;
        int strength;
        int mana;
        int intelligence;

        BufferedReader csvReader = null;
        try {
            csvReader = new BufferedReader(new FileReader(aFile));
        } catch (FileNotFoundException e) {
            System.out.println("This is not a valid option. Try again.");
            importCsv();        }
        try {
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                typeSetter = data[0];
                if (typeSetter.equals("1")) {
                    anID = data[1];
                    aName = data[2];
                    hp = Integer.parseInt(data[3]);
                    isAlive = Boolean.parseBoolean(data[4]);
                    stamina = Integer.parseInt(data[5]);
                    strength = Integer.parseInt(data[6]);
                    tempObject = new Warrior(anID, aName, hp, isAlive, stamina, strength);
                    tempList.add(tempObject);
                }
                if (typeSetter.equals("2")) {
                    anID = data[1];
                    aName = data[2];
                    hp = Integer.parseInt(data[3]);
                    isAlive = Boolean.parseBoolean(data[4]);
                    mana = Integer.parseInt(data[5]);
                    intelligence = Integer.parseInt(data[6]);
                    tempObject = new Wizard(anID, aName, hp, isAlive, mana, intelligence);
                    tempList.add(tempObject);
                }
            }
            csvReader.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return tempList;
    }
}


