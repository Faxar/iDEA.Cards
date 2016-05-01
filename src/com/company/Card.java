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


    public Card(int id, String name, int strenght, int health, int mana) {
        this.id = id;
        this.name = name;
        this.strenght = strenght;
        this.health = health;
        this.mana = mana;
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

    public int getId() {
        return id;
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
