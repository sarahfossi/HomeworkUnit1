package com.ironhack.homework1.interfaces;

import com.ironhack.homework1.classes.Character;

public interface Attacker {
    void attack(Character character) throws InterruptedException;

}
