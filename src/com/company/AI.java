package com.company;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Alazerus on 4/29/2016.
 */
public class AI {

    public Hand hand;
    public Deck deck;


    public boolean aiLogic(Hand player1, Field allField, Hand ai, Deck aiD) {
        Card pulledFromD;
        if (aiD.amountCardsInDeck() > 0) {
            pulledFromD = aiD.fetch();
            ai.endTurnCardDrow(pulledFromD);
            String cName = pulledFromD.getName();
            System.out.println("Ai pulled from deck " + cName);
        }
        Collections.sort(ai.getArray(), new CustomComparator());
        //System.out.println("Ai have in the hand:");
        //ai.checkCards();
        if(allField.checkIfPlayerCardsField()){ // attack spells
            for (int i=0; i<ai.getArray().size();i++) {
                Card card = ai.returnCard(i);
                if(card.getIsMinion() == 0 && card.getSpellType() == 1 && card.getMana() <= ai.getTempMana()){
                    for (int x=0;x<allField.returnPlayerFCards().size();x++) {
                        Card card4 = allField.returnCardElement(x, 1);
                        if(card.getPower() >= card4.getHealth()){
                            ai.removeCardHand(i);
                            allField.removeCard(card4, 2);
                            System.out.println("Zzzzap!!! AI have destroyed " + card4.getName() + " with " + card.getName() + " by dealing to it " + card.getPower() + " points of damage" );
                            ai.modifyTempMana(card.getMana());
                        }
                    }
                } else if (card.getIsMinion() == 0 && card.getSpellType() == 2 && card.getMana() <= ai.getTempMana() && allField.checkAICards()){ // buff spells
                    for(int j=0;j<allField.returnAiFCards().size(); j++){
                        Card card2 = allField.returnCardElement(j, 2); // AI field card
                        for(int n=0;n<allField.returnPlayerFCards().size();n++){
                            Card card3 = allField.returnCardElement(n, 1); // Player field card
                            if(!allField.checkIfCardFatugued(j, 2) && (card2.getStrenght() + card.getModificator()) >= card3.getHealth() && card2.getHealth() > card3.getStrenght()){
                                System.out.println("AI enhanced " + card2.getName() + ". Strength was increased on " + card.getModificator());
                                card2.setStrenght(card2.getStrenght() + card.getModificator());
                                System.out.println("New card have " + card2.getStrenght() + " - strength and " + card2.getHealth() + " - health.");
                                allField.minionAttack(card2, card3, 2);
                                allField.putToFatugue(j, 2);
                                ai.modifyTempMana(card.getMana());
                                ai.removeCardHand(i);
                            }
                        }
                    }
                } else if (card.getIsMinion() == 0 && card.getSpellType() == 3 && card.getMana() <= ai.getTempMana()){ // ai heal spells
                    if(ai.checkHealth() <= 10){
                        System.out.println("AI cast heal spell on himself. Healing for " + card.getHeal());
                        System.out.println("AI health was " + ai.checkHealth());
                        ai.addPlayerHealth(card.getHeal());
                        System.out.println("AI health now " + ai.checkHealth());
                        ai.removeCardHand(i);
                        ai.modifyTempMana(card.getMana());
                    }
                }
            }
        }
        for (int i = 0; i < ai.getArray().size(); i++) {
            Card tempCard = ai.returnCard(i);
            if(tempCard.getIsMinion() == 1){
                int tempCardMana = tempCard.getMana();
                String tempName = tempCard.getName();
                int tempStrength = tempCard.getStrenght();
                int tempHealth = tempCard.getHealth();
                if (tempCardMana <= ai.getTempMana()) {
                    ai.removeCardHand(i);
                    ai.modifyTempMana(tempCardMana);
                    allField.putCardOnF(tempCard, 2);
                    System.out.println("AI played on the field - " + tempName + " | Strength:" + tempStrength + " Health:" + tempHealth);
                }
            }
        }
        aiAttackMinion(player1, allField);
        aiAttackFace(player1, allField);
        ai.modifyMana();
        allField.clearFatigue(2);
        if (player1.checkHealth() <= 0) {
            System.out.println("And again AI reigns supreme! MuaHaHaHa!");
            System.out.println("========================================");
            return true;
        }
        return false;
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
                if (!allField.checkIfCardFatugued(j, 2)) {
                    int damage = aiMinion.getStrenght();
                    String mName = aiMinion.getName();
                    player1.removePlayerHealth(damage);
                    System.out.println("AI minion " + mName + " inflicted " + damage + " damage to player");
                    System.out.println("Player health down to " + player1.checkHealth());
                    allField.putToFatugue(j, 2);
                }

            }
        }
    }

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

    public class CustomComparator implements Comparator<Card> {
        @Override
        public int compare(Card o1, Card o2) {
            return o2.getMana() - o1.getMana();
        }
    }

}