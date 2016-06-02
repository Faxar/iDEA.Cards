package com.company;

/**
 * Created by vassili.holenev on 1.06.2016.
 */
public class Minion extends Card {
    public int strenth;
    private int health;

    public int getStrenth() {
        return strenth;
    }

    public void setStrenth(int strenth) {
        this.strenth = strenth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
