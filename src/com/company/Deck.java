package com.company;

import java.util.Collections;
import java.util.Stack;

/**
 * Created by vassili.holenev on 4.04.2016.
 */
public class Deck {


    private Stack<Card> myCollection;


    public Deck() {
        this.myCollection = new Stack<>();
    }

    public int amountCardsInDeck() {
        return myCollection.size();
    }


    public void populateDeck(Card card) {
        myCollection.push(card);
    }

    public void shuffle() {
        Collections.shuffle(myCollection);
    }

    public Card fetch() {
        if (!myCollection.empty()) {
            return myCollection.pop();
        }
        return null;
    }

    public void showCards() {
        for (int i = 0; i < myCollection.size(); i++) {
            System.out.println(myCollection.get(i));
        }
    }


}