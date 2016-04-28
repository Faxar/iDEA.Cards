package com.company;

import java.util.ArrayList;

/**
 * Created by vassili.holenev on 7.04.2016.
 */
public class Hand {

    public String hand;
    private int numberOfCards;
    private ArrayList<Card> myHand;
    private int playerHealth;

    public Hand(String hand) {
        this.hand = hand;
        this.numberOfCards = 3;
        this.myHand = new ArrayList<>();
        this.playerHealth = 10;
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
        return playerHealth;
    }

    public void addPlayerHealth(int number){
        playerHealth += number;
    }

    public void removePlayerHealth(int number){
        playerHealth -= number;
    }

}
