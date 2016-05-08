package com.company;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Alazerus on 4/29/2016.
 */
public class AI {

    public Hand hand;
    public Deck deck;


    public void aiLogic(Hand player1, Field allField, Hand ai){
            Collections.sort(ai.getArray(), new CustomComparator());
            System.out.println("Ai have in the hand:");
            ai.checkCards();
            boolean breake = true;
            while (breake){
                for(int i=0;i<ai.getArray().size(); i++){
                    Card tempCard = ai.returnCard(i);
                    int tempCardMana = tempCard.getMana();
                    String cardName = tempCard.getName();
                    if(tempCardMana <= ai.getTempMana()){
                        ai.getCard(i);
                        ai.modifyTempMana(tempCardMana);
                        System.out.println("Ai played on the field following card: " + cardName);
                        allField.putCardOnF(tempCard, 2);
                    }
                }
                ai.modifyMana();
                breake = false;
            } if (allField.checkAICards()){
                aiAttack(player1, allField, ai);
            }
    }

    public class CustomComparator implements Comparator<Card> {
        @Override
        public int compare(Card o1, Card o2) {
            return o2.getMana() - o1.getMana();
        }
    }

    private void aiAttack(Hand player1, Field allField, Hand ai){
        Collections.sort(ai.getArray(), new CustomComparator());
        for(int i=0;i<allField.returnPlayerFCards().size();i++){
            Card playerMinion = allField.returnCardElement(i, 1);
            for(int j=0;j<allField.returnAiFCards().size(); j++){
                Card aiMinion = allField.returnCardElement(j, 2);
                if(!allField.checkIfCardFatugued(j, 2)){
                    int cardOutput = allField.verifyAttackWithMinion(aiMinion, playerMinion);
                    if(cardOutput == 2){
                        allField.minionAttack(aiMinion, playerMinion, 2);
                    } else if (cardOutput == 1){
                        allField.minionAttack(aiMinion, playerMinion, 2);
                    } else if(cardOutput == 3){
                        int damage = aiMinion.getStrenght();
                        player1.removePlayerHealth(damage);
                        System.out.println("AI inflicted " + damage + " to player");
                        System.out.println("Player health down to " + player1.checkHealth());
                        allField.putToFatugue(j, 2);
                    } else if (cardOutput == 4){
                        int damage = aiMinion.getStrenght();
                        player1.removePlayerHealth(damage);
                        System.out.println("AI inflicted " + damage + " to player");
                        System.out.println("Player health down to " + player1.checkHealth());
                        allField.putToFatugue(j, 2);
                    } else {
                        System.out.println("Strange behavior");
                    }
                }

            }
        }
    }

}