package com.company;

import java.util.ArrayList;

/**
 * Created by vassili.holenev on 8.04.2016.
 */
public class Field {
    private ArrayList<Card> fieldCards1;
    private ArrayList<Card> fieldCards2;

    private ArrayList<Card> fatigueList1;
    private ArrayList<Card> fatigueList2;

    private int maxLength = 3;

    public Field() {
        this.fieldCards1 = new ArrayList<>();
        this.fieldCards2 = new ArrayList<>();
        this.fatigueList1 = new ArrayList<>();
        this.fatigueList2 = new ArrayList<>();
    }

    public boolean putCardOnF(Card card, int sideNumber){
        if(sideNumber == 1){
            return put(card, fieldCards1, fatigueList1);
        } else {
            return put(card, fieldCards2, fatigueList2);
        }
    }

    private boolean put(Card card, ArrayList<Card> fieldCards, ArrayList<Card> fatigueList){
        int fieldSize = fieldCards.size();
        if(fieldSize >= maxLength){
            return false;
        }
        fieldCards.add(card);
        fatigueList.add(card);
        return true;
    }

    public void putToFatugue(int number){
        Card tempFatigue = fieldCards1.get(number);
        fatigueList1.add(tempFatigue);
    }

    public void returnFieldCards(){
        for(int i=0; i<fieldCards1.size(); i++){
            System.out.println(fieldCards1.get(i));
        }
    }

    public void clearFatigue(int sideNumber){
        if(sideNumber == 1){
            fatigueList1.clear();
        } else {
            fatigueList2.clear();
        }
    }

    public boolean checkIfCardExistOnField(int number){
        return  number - 1 <= fieldCards1.size();
    }

    public boolean checkEnemyCards(){
        if(fieldCards2.size() > 0){
            return true;
        } return false;
    }

    public boolean checkIfPlayerCardsField(){
        if(fieldCards1.size() > 0){
            return true;
        } return false;
    }

    public int getCardStrengh(int number){
        Card card = fieldCards1.get(number -1);
        return card.getStrenght();
    }

    public void giveEnemyCards(){
        for(int i=0; i<fieldCards2.size(); i++)
            System.out.println(fieldCards2.get(i));
    }

    public int getCardHealth(int number){
        Card card = fieldCards1.get(number - 1);
        return card.getHealth();
    }

    public String getCardName(int number){
        Card card = fieldCards1.get(number - 1);
        return card.getName();
    }

    public boolean checkIfCardFatugued(int number){
        Card card = fieldCards1.get(number -1);
        int id = card.getId();
        for(int i=0;i< fatigueList1.size(); i++){
            Card cardF = fatigueList1.get(i);
            int idF = cardF.getId();
            if(id == idF){
                return true;
            }

        }return false;
    }

    public boolean isFieldEmpty(){
        if(fieldCards1.size() <= 0){
            return true;
        } return false;
    }


}