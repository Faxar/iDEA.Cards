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
        this.health = 10;
        this.mana = 3;
        this.tempMana = 3;
    }

    public void populateHand(Deck deck){
        for(int i=0; i<numberOfCards; i++){
            myHand.add(deck.fetch());
        }
    }

    public void checkCards(){
        for(int i=0; i<myHand.size(); i++)
            System.out.println(myHand.get(i));
    }


    public Card getCard(int number){
        return myHand.remove(number - 1);
    }

    public int getMana() {
        return mana;
    }

    public int getTempMana() {
        return tempMana;
    }

    public boolean checkIfCardExist(int number){
        return number - 1 < myHand.size();
    }

    public int checkAmountOfCards(){
        return myHand.size();
    }

    public void endTurnCardDrow(Card card){
        myHand.add(card);
    }

    public int checkPlayerHealth(){
        return health;
    }

    public void addPlayerHealth(int number){
        health += number;
    }

    public void removePlayerHealth(int number){
        health -= number;
    }

    public void modifyMana(){
        if(mana < 10) {
            mana++;
            tempMana = mana;
        }
    }

    public void modifyTempMana(int number){
        tempMana -=number;
    }

    public int getManaCard(int number){
        int mana;
        Card card = myHand.get(number - 1);
        return mana = card.getMana();
    }

}