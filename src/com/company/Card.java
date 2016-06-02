package com.company;

/**
 * Created by vassili.holenev on 4.04.2016.
 */
public abstract class Card {
    private int isMinion;
    private int id;
    private String name;
    private int mana;
    private String desctiption;

    public int getIsMinion() {
        return isMinion;
    }

    public void setIsMinion(int isMinion) {
        this.isMinion = isMinion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getDesctiption() {
        return desctiption;
    }

    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
    }



    @Override
    public String toString() {
        return "Card{" +
                "isMinion=" + isMinion +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", mana=" + mana +
                ", desctiption='" + desctiption + '\'' +
                '}';
    }
}
