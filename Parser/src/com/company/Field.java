package com.company;

import gameObjects.Card;
import gameObjects.Minion;

import java.util.ArrayList;

/**
 * Created by vassili.holenev on 8.04.2016.
 */
public class Field {
    private ArrayList<Card> fieldCards1;
    private ArrayList<Card> fieldCards2;

    private ArrayList<Card> fatigueList1;
    private ArrayList<Card> fatigueList2;

    int maxLength = 3;

    public Field() {
        this.fieldCards1 = new ArrayList<>();
        this.fieldCards2 = new ArrayList<>();
        this.fatigueList1 = new ArrayList<>();
        this.fatigueList2 = new ArrayList<>();
    }

    public boolean putCardOnF(Card card, int sideNumber) {
        if (sideNumber == 1) {
            return put(card, fieldCards1, fatigueList1);
        } else {
            return put(card, fieldCards2, fatigueList2);
        }
    }

    private boolean put(Card card, ArrayList<Card> fieldCards, ArrayList<Card> fatigueList) {
        int fieldSize = fieldCards.size();
        if (fieldSize >= maxLength) {
            return false;
        }
        fieldCards.add(card);
        fatigueList.add(card);
        return true;
    }

    public void putToFatugue(int number, int side) {
        if (side == 1) {
            Card tempFatigue = fieldCards1.get(number);
            fatigueList1.add(tempFatigue);
        } else {
            Card tempFatigue = fieldCards2.get(number);
            fatigueList2.add(tempFatigue);
        }
    }

    public void putToFatigueCa(Card card, int side) {
        int id = card.getId();
        if (side == 1) {
            for (int i = 0; i < fieldCards1.size(); i++) {
                Card tempCard = fieldCards1.get(i);
                int idT = tempCard.getId();
                if (id == idT) {
                    fatigueList1.add(tempCard);
                }
            }
        } else {
            for (int j = 0; j < fieldCards2.size(); j++) {
                Card tempCard = fieldCards2.get(j);
                int idT = tempCard.getId();
                if (id == idT) {
                    fatigueList2.add(tempCard);
                }
            }
        }
    }

    public void returnFieldCards() {
        for (int i = 0; i < fieldCards1.size(); i++) {
            Minion card = (Minion) fieldCards1.get(i);
            System.out.println(card.toString());
        }
    }

    public void clearFatigue(int sideNumber) {
        if (sideNumber == 1) {
            fatigueList1.clear();
        } else {
            fatigueList2.clear();
        }
    }

    public boolean checkIfCardExistOnField(int number) {
        return number <= fieldCards1.size();
    }

    public boolean checkAICards() {
        return (fieldCards2.size() > 0);
    }

    public boolean checkIfPlayerCardsField() {
        return (fieldCards1.size() > 0);
    }

    public void giveEnemyCards() {
        for (int i = 0; i < fieldCards2.size(); i++)
            System.out.println(fieldCards2.get(i));
    }

    public boolean checkIfCardFatugued(int number, int side) {
        if (side == 1) {
            Card card = fieldCards1.get(number);
            int id = card.getId();
            for (int i = 0; i < fatigueList1.size(); i++) {
                Card cardF = fatigueList1.get(i);
                int idF = cardF.getId();
                if (id == idF) {
                    return true;
                }

            }
            return false;
        }
        Card card = fieldCards2.get(number);
        int id = card.getId();
        for (int i = 0; i < fatigueList2.size(); i++) {
            Card cardF = fatigueList2.get(i);
            int idF = cardF.getId();
            if (id == idF) {
                return true;
            }
        }
        return false;
    }

    public boolean isFieldEmpty() {
        return  (fieldCards1.size() <= 0);
    }

    public ArrayList<Card> returnAiFCards() {
        return fieldCards2;
    }

    public ArrayList<Card> returnPlayerFCards() {
        return fieldCards1;
    }

    public Card returnCardElement(int number, int side) {
        if (side == 1) {
            return fieldCards1.get(number);
        } else {
            return fieldCards2.get(number);
        }
    }

    public void removeCard(Card card, int number) {
        if (number == 1) {
            int cardId = card.getId();
            for (int j = 0; j < fieldCards2.size(); j++) {
                int idC = card.getId();
                if (idC == cardId) {
                    fieldCards2.remove(j);
                }
            }
        } else {
            int cardId = card.getId();
            for (int i = 0; i < fieldCards1.size(); i++) {
                int idC = card.getId();
                if (idC == cardId) {
                    fieldCards1.remove(i);
                }
            }
        }
    }

    public int verifyAttackWithMinion(Card attackCard, Card enemyCard) {
        Minion atCard = (Minion) attackCard;
        Minion enCard = (Minion) enemyCard;
        int willKillNotSurv = 1;
        int willKillSurv = 2;
        int willNKillNSurv = 3;
        int willNKillSurv = 4;
        int attackCardHealth = atCard.getHealth();
        int attackCardStrenght = atCard.getStrenth();
        int enemyCardHealth = enCard.getHealth();
        int enemyCardStrenght = enCard.getHealth();
        if (attackCardStrenght >= enemyCardHealth && enemyCardStrenght < attackCardHealth) {
            return willKillSurv; //2
        } else if (attackCardStrenght >= enemyCardHealth && enemyCardStrenght >= attackCardHealth) {
            return willKillNotSurv; //1
        } else if (attackCardStrenght < enemyCardHealth && enemyCardStrenght >= attackCardHealth) {
            return willNKillNSurv; //3
        } else if (attackCardStrenght < enemyCardHealth && enemyCardStrenght < attackCardHealth) {
            return willNKillSurv; //4
        } else return 5;
    }

    public void minionAttack(Card attack, Card enemy, int side) {
        Minion at = (Minion) attack;
        Minion en = (Minion) enemy;
        String attackCardName = at.getName();
        int attackCardHealth = at.getHealth();
        int attackCardStrenght = at.getStrenth();
        String enemyCardName = en.getName();
        int enemyCardHealth = en.getHealth();
        int enemyCardStrenght = en.getStrenth();
        if (attackCardStrenght >= enemyCardHealth && enemyCardStrenght < attackCardHealth) {
            System.out.println(attackCardName + " have destroyed " + enemyCardName);
            removeCard(enemy, side);
            putToFatigueCa(attack, side);
        } else if (attackCardStrenght >= enemyCardHealth && enemyCardStrenght >= attackCardHealth) {
            System.out.println(attackCardName + " have destroyed " + enemyCardName + " and was also destroyed");
            removeCard(attack, 1);
            removeCard(enemy, 2);
        } else if (attackCardStrenght < enemyCardHealth && enemyCardStrenght >= attackCardHealth) {
            System.out.println(attackCardName + " have attacked " + enemyCardName + " but failed to destroy it and\n"
                    + attackCardName + " destroyed in the process ");
            if (side == 1) {
                removeCard(attack, 2);
            } else {
                removeCard(attack, 1);
            }
        } else if (attackCardStrenght < enemyCardHealth && enemyCardStrenght < attackCardHealth) {
            System.out.println(attackCardName + " have attached " + enemyCardName + " but failed to destroy it.");
        }
    }
}
