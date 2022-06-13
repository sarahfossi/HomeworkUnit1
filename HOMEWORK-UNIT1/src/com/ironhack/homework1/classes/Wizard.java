package com.ironhack.homework1.classes;

public class Wizard extends Character{

    private int mana;
    private int intelligence;

    // CONSTRUCTOR FROM CSV
    public Wizard(String id,String name, int hp, boolean isAlive, int mana, int intelligence) {
        super(id, name, hp, isAlive);
        setMana(mana);
        setIntelligence(intelligence);
    }

    // CONSTRUCTOR FOR CREATING CHARACTER
    public Wizard(String name, int hp, int mana, int intelligence) {
        super(name, hp);
        setMana(mana);
        setIntelligence(intelligence);
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        if(mana > 50) {
            this.mana = 50;
        } else if(mana < 10) {
            this.mana = 10;
        } else {
            this.mana = mana;
        }
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        if(intelligence >50) {
            this.intelligence = 50;
        } else if(intelligence < 1) {
            this.intelligence = 1;
        } else {
            this.intelligence = intelligence;
        }
    }

    @Override
    public void setHp(int hp) {
        if(hp <=0) {
            super.setHp(0);
            super.setAlive(false);
        } else if(hp>100) {
            super.setHp(100);
        } else {
            super.setHp(hp);
        }
    }

    public void attack(Character character) throws InterruptedException {
        String opponent  = character instanceof Warrior ? "Warrior" : "Wizard";
        int damage;

        if(this.getMana() >=5) {
            System.out.println(this.getName() +"(wizard) is attacking "+ character.getName()+"("+opponent+") with a Fireball!");
            Thread.sleep(1000);
            damage = this.intelligence;
            setMana(getMana()-5);
        } else {
            System.out.println(this.getName() +"(wizard) is attacking "+ character.getName()+"("+opponent+") with a Staff hit!");
            Thread.sleep(1000);
            setMana(getMana()+1);
            damage=2;
        }

        character.setHp(character.getHp() - damage);
        System.out.println(character.getName() + " lost " +damage+ " hp!");
        if(!character.isAlive()) {
            System.out.println(character.getName() + " is dead [*]");
        }
    }


}
