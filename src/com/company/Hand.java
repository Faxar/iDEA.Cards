package com.company;

import java.util.ArrayList;

/**
 * Created by vassili.holenev on 7.04.2016.
 */
public class Hand {

    public String hand;
    private int numberOfCards;
    private ArrayList<Card> myHand;
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

    public void checkCards() {
        for(int i=0;i < myHand.size(); i++){
            Card tmpCard = myHand.get(i);
            if(tmpCard.getIsMinion()== 1){

                System.out.println(
                        (i+1) + ". " + tmpCard.getName() +
                                " | Strength: " + tmpCard. +
                                " , Health: " + tmpCard.getHealth() +
                                " , Mana: " + tmpCard.getMana());
            } else if (tmpCard.getIsMinion() == 0) {
                if (tmpCard.getSpellType() == 1) {
                    System.out.println(
                            (i + 1) + ". " + tmpCard.getName() +
                                    " | Power: " + tmpCard.getPower() +
                                    " , Mana: " + tmpCard.getMana() +
                                    " | Description : " + tmpCard.getDesctiption()
                    );
                } else if (tmpCard.getSpellType() == 2) {
                    System.out.println(
                            (i + 1) + ". " + tmpCard.getName() +
                                    " | Buff: " + tmpCard.getModificator() +
                                    " , Mana: " + tmpCard.getMana() +
                                    " | Description : " + tmpCard.getDesctiption()
                    );
                } else if (tmpCard.getSpellType() == 3){
                    System.out.println(
                            (i + 1) + ". " + tmpCard.getName() +
                                    " | Heal: " + tmpCard.getHeal() +
                                    " , Mana: " + tmpCard.getMana() +
                                    " | Description : " + tmpCard.getDesctiption()
                    );
                }
            }
        }
    }


    public Card removeCardHand(int number) {
        return myHand.remove(number);
    }

    public int getMana() {
        return mana;
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

    public ArrayList<Card> getArray() {
        return myHand;
    }

    public Card returnCard(int number) {
        return myHand.get(number);
    }

    public void addPlayerHealth(int number){
        health +=number;
    }

}