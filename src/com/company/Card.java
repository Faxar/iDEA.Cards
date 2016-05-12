package com.company;

/**
 * Created by vassili.holenev on 4.04.2016.
 */
public class Card {
    private int id;
    private String name;
    private int strenght;
    private int health;
    private int mana;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStrenght() {
        return strenght;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStrenght(int strenght) {
        this.strenght = strenght;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", strenght=" + strenght +
                ", health=" + health +
                ", mana=" + mana +
                '}';
    }
}
