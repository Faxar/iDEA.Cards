package com.company;

import gameObjects.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vassili.holenev on 7.04.2016.
 */
public class Hand {

    public String hand;
    private int numberOfCards;
    private List<Card> myHand;
    private int health;
    private int mana;
    private int tempMana;

    public Hand(String hand) {
        this.hand = hand;
        this.numberOfCards = 3;
        this.myHand = new ArrayList<>();
        this.health = 30;
        this.mana = 3;
        this.tempMana = 3;
    }

    public void populateHand(Deck deck) {
        for (int i = 0; i < numberOfCards; i++) {
            myHand.add(deck.fetch());
        }
    }

    public Card removeCardHand(int number) {
        return myHand.remove(number);
    }

    public int getTempMana() {
        return tempMana;
    }

    public boolean checkIfCardExist(int number) {
        return number < myHand.size();
    }

    public int checkAmountOfCards() {
        return myHand.size();
    }

    public void endTurnCardDrow(Card card) {
        myHand.add(card);
    }

    public int checkHealth() {
        return health;
    }

    public void removePlayerHealth(int number) {
        health -= number;
    }

    public void modifyMana() {
        if (mana < 10) {
            mana++;
            tempMana = mana;
        }
    }

    public void modifyTempMana(int number) {
        tempMana -= number;
    }

    public boolean isHandEmpty() {
        return (myHand.size() <= 0);
    }

    public List<Card> getArray() {
        return myHand;
    }

    public Card returnCard(int number) {
        return myHand.get(number);
    }

    public void addPlayerHealth(int number){
        health +=number;
    }

}