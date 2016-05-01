package com.company;

/**
 * Created by Alazerus on 4/29/2016.
 */
public class AI {
    int aiHealth;

    public AI() {
        this.aiHealth = 20;
    }

    public int getAiHealth() {
        return aiHealth;
    }

    public void removeHealth(int number){
        aiHealth -= number;
    }

    public void addHealth(int number){
        aiHealth += number;
    }

}
