package com.ironhack.homework1.classes;

public class Warrior extends Character{

    private int stamina;
    private int strength;

    // CONSTRUCTOR FROM CSV
    public Warrior(String id, String name, int hp, boolean isAlive, int stamina, int strength) {
        super(id, name, hp, isAlive);
        setStamina(stamina);
        setStrength(strength);
    }

    // CONSTRUCTOR FOR CREATING CHARACTER
    public Warrior(String name, int hp, int stamina, int strength) {
        super(name, hp);
        setStamina(stamina);
        setStrength(strength);
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        if(stamina > 50) {
            this.stamina = 50;
            System.out.println("You cannot set strength over 50!");
        } else if(stamina < 10) {
            this.stamina = 10;
        } else {
            this.stamina = stamina;
        }
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        if(strength >10) {
            this.strength = 10;
            System.out.println("You cannot set strength over 10!");
        } else if(strength < 1) {
            this.strength = 1;
        } else {
            this.strength = strength;
        }
    }

    @Override
    public void setHp(int hp) {
        if(hp <=0) {
            super.setHp(0);
            super.setAlive(false);
        } else if(hp>200) {
            super.setHp(200);
        } else {
            super.setHp(hp);
        }
    }

    @Override
    public void attack(Character character) throws InterruptedException {
            String opponent  = character instanceof Warrior ? "warrior" : "wizard";
            int damage;

            if(this.stamina >= 5) {
                System.out.println(this.getName() +"(warrior) is attacking "+character.getName()+"("+opponent+") with a Heavy Attack!");
                damage = this.strength;
                Thread.sleep(1000);
                this.stamina -=5;
            } else {
                System.out.println(this.getName() +"(warrior) is attacking : "+character.getName()+"("+opponent+") with a Weak Attack!");
                Thread.sleep(1000);
                damage = strength/2;
                this.stamina++;
            }

            character.setHp(character.getHp() - damage);
            System.out.println(character.getName()+" has lost " +damage+ " hp!");
            if(!character.isAlive()) {
                System.out.println(character.getName() + " is dead [*]");
            }
    }

}



