package com.company;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Alazerus on 4/29/2016.
 */
public class AI {

    public Hand hand;
    public Deck deck;


    public void aiLogic(Hand player1, Field allField, Hand ai, Deck aiD) {
        Card pulledFromD = aiD.fetch();
        ai.endTurnCardDrow(pulledFromD);
        Collections.sort(ai.getArray(), new CustomComparator());
        System.out.println("Ai have in the hand:");
        ai.checkCards();
        for (int i = 0; i < ai.getArray().size(); i++) {
            Card tempCard = ai.returnCard(i);
            int tempCardMana = tempCard.getMana();
            String tempName = tempCard.getName();
            int tempStrength = tempCard.getStrenght();
            int tempHealth = tempCard.getHealth();
            if (tempCardMana <= ai.getTempMana()) {
                ai.getCard(i);
                ai.modifyTempMana(tempCardMana);
                allField.putCardOnF(tempCard, 2);
                System.out.println("AI played on the field - " + tempName + " | Strength:" + tempStrength + " Health:" + tempHealth);
            }
        }
        if (allField.checkAICards()) {
            aiAttackMinion(player1, allField);
            aiAttackFace(player1, allField);
        }
        ai.modifyMana();
        allField.clearFatigue(2);
    }

    public class CustomComparator implements Comparator<Card> {
        @Override
        public int compare(Card o1, Card o2) {
            return o2.getMana() - o1.getMana();
        }
    }

    private void aiAttackMinion(Hand player1, Field allField) {
            for (int j = 0; j < allField.returnAiFCards().size(); j++) {
                if (!allField.checkIfCardFatugued(j, 2)) {
                    Card aiMinion = allField.returnCardElement(j, 2);
                    for (int i = 0; i < allField.returnPlayerFCards().size(); i++) {
                        Card playerMinion = allField.returnCardElement(i, 1);
                        int cardOutput = allField.verifyAttackWithMinion(aiMinion, playerMinion);
                        if (cardOutput == 2) {
                            allField.minionAttack(aiMinion, playerMinion, 2);
                        } else if (cardOutput == 1) {
                            allField.minionAttack(aiMinion, playerMinion, 2);
                        }
                    }

                    int damage = aiMinion.getStrenght();
                    player1.removePlayerHealth(damage);
                    System.out.println("AI inflicted " + damage + " damage to player");
                    System.out.println("Player health down to " + player1.checkHealth());
                    allField.putToFatugue(j, 2);
                }
            }
    }












//                 else if (cardOutput == 3) {
//
//                    } else if (cardOutput == 4) {
//                        int damage = aiMinion.getStrenght();
//                        player1.removePlayerHealth(damage);
//                        System.out.println("AI inflicted " + damage + " damage to player");
//                        System.out.println("Player health down to " + player1.checkHealth());
//                        allField.putToFatugue(j, 2);
//                    } else {
//                        System.out.println("Strange behavior");
//
//
//
//
//
//
//                    }
//                }
//
//            }
//        }


    private void aiAttackFace(Hand player1, Field allField) {
        for (int i = 0; i < allField.returnAiFCards().size(); i++) {
            if (!allField.checkIfCardFatugued(i, 2)) {
                Card aiCard = allField.returnCardElement(i, 2);
                int damage = aiCard.getStrenght();
                player1.removePlayerHealth(damage);
                System.out.println("AI inflicted " + damage + " to player");
                System.out.println("Player health down to " + player1.checkHealth());
                allField.putToFatugue(i, 2);
            }
        }
    }

}