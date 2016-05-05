package com.company;

import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

/**
 * Created by Alazerus on 4/29/2016.
 */
public class AI {

    public Hand hand;
    public Deck deck;


    public void aiLogic(Hand player1, Field allField, Hand ai){
        if(!allField.checkIfPlayerCardsField()){
            Collections.sort(ai.getArray(), new CustomComparator());
            System.out.println("Ai have in the hand:");
            Card card = ai.lastItem();
            int lowestCardMana = card.getMana();
            while (ai.getTempMana() >= lowestCardMana){
                for(int i=0;i<ai.getArray().size(); i++){
                    Card tempCard = ai.getCard(i);
                    int tempCardMana = tempCard.getMana();
                    if(tempCardMana <= ai.getTempMana()){
                        ai.getCard(i+1);
                        allField.putCardOnF(tempCard, 2);
                    }
                }
            }
        }
    }

    public class CustomComparator implements Comparator<Card> {
        @Override
        public int compare(Card o1, Card o2) {
            return o2.getMana() - o1.getMana();
        }
    }

}