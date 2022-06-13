package com.ironhack.homework1.classes;

import java.util.ArrayList;
import java.util.Random;

public class RandomGenerator {

    private static RandomGenerator randomGenerator;

    private ArrayList<String> alreadyUsedNames;

    private final String [] PlayersNames = {
            "Xhaiden","Luther","Inga","Manfrid","Kalva","Ecgtheow","Adelie","Finian","Aryo","Lita",
            "Ingaberg","Kondo","Batal","Siraj","Abner","Halle","Cuchulainn","Bathilde","Aysel","Edrei",
            "Batal","Kango","Aardburzin","Danasur","Isolde","Tehmina","Dhrishit","Rajveer","Harbin","Eleanor",
            "Citlalmina","Kaunteya","Jasbir","Abhiveer","Radames","Sigurd","Barbod","Sheridan","Ebba","Harb",
            "Audra","Siraj","Mandek","Bijann","Laertes","Wyetta","Thibaut","Lesedi","Onella","Hyldeihera"
    };


    private RandomGenerator(ArrayList<String> alreadyUsedNames) {

        this.alreadyUsedNames = alreadyUsedNames;
    }

    // INSTANCE OF RANDOM GENERATOR
    public static RandomGenerator getInstance() {
        if(randomGenerator == null) {
            randomGenerator = new RandomGenerator(new ArrayList<>());
        }
        return randomGenerator;
    }


    // GENERATE NAMES AND ALREADY USE NAMES
    public String generate() {
        String name = getPlayersNames()[randomInt(0, PlayersNames.length-1)];

        for (String characterName: getAlreadyUsedNames()) {
            if (characterName.equals(name)) {
                name+=" Jr";
            }
        }

        alreadyUsedNames.add(name);
        return name;
    }

    // UTIL RANDOM NUMBER FOR PLAYER NAME
    public int randomInt(int min, int max) {

        if(min >= max) {
            return max;
        }
        else {
            Random random = new Random();
            return random.nextInt(max - min +1) + min;
        }
    }


    // GETTER OF NAMES
    public String[] getPlayersNames() {
        return PlayersNames;
    }


    // GETTER OF ALREADY USED NAMES

    public ArrayList<String> getAlreadyUsedNames() {

        return this.alreadyUsedNames;
    }
}
