package com.company;

import sun.net.www.content.text.Generic;

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
        for(int i=0;i<myHand.size();i++){
            Object o = myHand.get(i);
            if(o.getClass().equals(Minion.class)){
                Minion temp = (Minion) o;
                System.out.println(
                        (i + 1) + ". " + temp.getName() +
                                " | Strength: " + temp.getStrenth() +
                                " , Health: " + temp.getHealth() +
                                " , Mana: " + temp.getMana());
            } else if (o.getClass().equals(DamageSpell.class)) {
                DamageSpell temp = (DamageSpell) o;
                    System.out.println(
                            (i + 1) + ". " + temp.getName() +
                                    " | Power: " + temp.getPower() +
                                    " , Mana: " + temp.getMana() +
                                    " | Description : " + temp.getDesctiption());
            } else if (o.getClass().equals(BuffSpell.class)) {
                BuffSpell temp = (BuffSpell) o;
                    System.out.println(
                            (i + 1) + ". " + temp.getName() +
                                    " | Buff: " + temp.getModificator() +
                                    " , Mana: " + temp.getMana() +
                                    " | Description : " + temp.getDesctiption()
                    );
                } else if (o.getClass().equals(HealSpell.class)) {
                HealSpell temp = (HealSpell) o;
                    System.out.println(
                            (i + 1) + ". " + temp.getName() +
                                    " | Heal: " + temp.getHeal() +
                                    " , Mana: " + temp.getMana() +
                                    " | Description : " + temp.getDesctiption()
                    );
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