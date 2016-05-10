package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        int numberS;
        int turn = 1;
        Scanner newScanner = new Scanner(System.in);

        Card card1 = new Card(1, "Scout", 1, 1, 1);
        Card card2 = new Card(2, "Space Marine", 2, 2, 2);
        Card card3 = new Card(3, "Inquisitor", 3, 3, 3);
        Card card4 = new Card(4, "Chaplain", 4, 4, 4);
        Card card5 = new Card(5, "Terminator", 5, 5, 5);
        Card card6 = new Card(6, "Dreadnought", 8, 8, 8);
        Card card7 = new Card(7, "Primarch", 10, 10, 10);


        Card aiCard1 = new Card(1, "Cultist", 1, 1, 1);
        Card aiCard2 = new Card(2, "Chaos Space Marine", 2, 2, 2);
        Card aiCard3 = new Card(3, "Defiler", 3, 3, 3);
        Card aiCard4 = new Card(4, "Sorcerer", 3, 3, 4);
        Card aiCard5 = new Card(5, "Corrupted Terminator", 3, 3, 5);
        Card aiCard6 = new Card(6, "Fell Dreadnought", 8, 8, 6);


        Deck aiDeck = new Deck("ai Deck");

        aiDeck.populateDeck(aiCard1);
        aiDeck.populateDeck(aiCard2);
        aiDeck.populateDeck(aiCard3);
        aiDeck.populateDeck(aiCard4);
        aiDeck.populateDeck(aiCard5);
        aiDeck.populateDeck(aiCard6);

        aiDeck.shuffle();

        Deck newDeck = new Deck("Player1");
        Field newField = new Field();
        newDeck.populateDeck(card1);
        newDeck.populateDeck(card2);
        newDeck.populateDeck(card3);
        newDeck.populateDeck(card4);
        newDeck.populateDeck(card5);
        newDeck.populateDeck(card6);
        newDeck.populateDeck(card7);
        newDeck.shuffle();

        Player player1 = new Player();
        player1.hand = new Hand("my Hand");
        player1.deck = newDeck;
        player1.hand.populateHand(newDeck);
        AI ai = new AI();
        ai.hand = new Hand("ai Hand");
        ai.deck = aiDeck;
        ai.hand.populateHand(aiDeck);

        Card tempCard;

        startGame();

        System.out.println("You have following cards in your hand : ");
        player1.hand.checkCards();
        boolean endGame = false;
        while (!endGame) {

            System.out.println("==============================================");
            System.out.println("Turn " + turn);
            System.out.println("You have currently: " + player1.hand.getTempMana() + " mana in mana pool.");
            printMenu(player1.hand.isHandEmpty(), newField.isFieldEmpty());



            try {
                numberS = scanner();
            } catch (InputMismatchException e) {
                System.out.println("Please enter correct number");
                continue;
            }


            switch (numberS) {

                case 0:
                    System.out.println("Enter card number");
                    player1.hand.checkCards();
                    int cNumber = scanner();
                    if (cNumber < 0) {
                        System.out.println("Please enter positive number");
                    } else {
                        if (player1.hand.checkIfCardExist(cNumber)) {
                            if (player1.hand.getManaCard(cNumber) <= player1.hand.getMana()) {
                                int cardManaCost = player1.hand.getManaCard(cNumber);
                                tempCard = player1.hand.getCard(cNumber);
                                newField.putCardOnF(tempCard, 1);
                                System.out.println("Now on the field there is:");
                                player1.hand.modifyTempMana(cardManaCost);
                                newField.returnFieldCards();
                            } else {
                                System.out.println("You don't have enough mana. Your current mana pool is " + player1.hand.getMana());
                            }
                        }
                    }
                    break;

                case 1:
                    System.out.println("Ai have following cards on the field");
                    newField.giveEnemyCards();
                    System.out.println("Select card to attack with");
                    newField.returnFieldCards();
                    int cardNumber = scanner();
                    if(newField.checkIfCardExistOnField(cardNumber)){
                        if(!newField.checkIfCardFatugued(cardNumber, 1)){
                                int damage = newField.getCardStrengh(cardNumber);
                                if(newField.checkAICards()){
                                    System.out.println("Please select target");
                                    System.out.println("1 - Attack AI\n" +
                                            "2 - Attack card on the filed\n" +
                                            "3 - Cancel");
                                    int number = scanner();
                                    switch (number){
                                        case 0:
                                            ai.hand.removePlayerHealth(damage);
                                            System.out.println("AI received " + damage + " points of damage");
                                            System.out.println("New AI health = " + ai.hand.checkHealth());
                                            newField.putToFatugue(cardNumber, 1);
                                            break;
                                        case 1:
                                            System.out.println("Select card that you want to attack: ");
                                            newField.giveEnemyCards();
                                            int target = scanner();
                                            Card attacker = newField.returnCardElement(cardNumber, 1);
                                            Card targetCard = newField.returnCardElement(target, 2);
                                            newField.minionAttack(attacker, targetCard, 1);
                                            break;
                                        case 2:
                                            break;
                                    }
                                }

                        } else {
                            System.out.println("You cannot play " + newField.getCardName(cardNumber) + ". It's fatigued");
                        }
                    } else {
                        System.out.println("Please select correct card");
                    } break;


                case 2:
                    if (player1.hand.checkAmountOfCards() >= 3 && newDeck.amountCardsInDeck() > 0) {
                        Card burned = newDeck.fetch();
                        System.out.println("You have full hand.\n" + burned + '\n' + " have burned down");
                        System.out.println("You have " + newDeck.amountCardsInDeck() + " cards, left in your deck");
                    } else if (player1.hand.checkAmountOfCards() < 3 && newDeck.amountCardsInDeck() > 0) {
                        Card pullFromDeck = newDeck.fetch();
                        player1.hand.endTurnCardDrow(pullFromDeck);
                        System.out.println("You have pulled " + pullFromDeck + " card");
                    } else {
                        System.out.println("You have " + newDeck.amountCardsInDeck() + " cards, left in your deck");
                        System.out.println("Your current health is " + player1.hand.checkHealth());
                        player1.hand.removePlayerHealth(5);
                        System.out.println("You are suffering fatigue death and you have lost 5 Health");
                        System.out.println("Your new health number is " + player1.hand.checkHealth());
                        if (player1.hand.checkHealth() <= 0) {
                            System.out.println("You Dead! MuaHaHaHa!");
                            endGame = true;
                            break;
                        }
                    }
                    turn++;
                    newField.clearFatigue(1);
                    player1.hand.modifyMana();
                    ai.aiLogic(player1.hand, newField, ai.hand, ai.deck);
                    break;

                case 3:
                    System.out.println("Weakling!");
                    endGame = true;
                    break;
            }
        }
    }

    private static void startGame() {
        System.out.println("Let's the Games begin!");
    }

    private static void printMenu(boolean isHandEmpty, boolean isFieldEmpty) {
        if(isFieldEmpty && isHandEmpty){
            System.out.println("Please press action\n" +
                    "Press 3 - End Turn\n" +
                    "Press 4 - Surrender\n");
        } else if (!isFieldEmpty && isHandEmpty){
            System.out.println("Please press action\n" +
                    "Press 2 - Play Card on the field\n" +
                    "Press 3 - End Turn\n" +
                    "Press 4 - Surrender\n");
        } else if (isFieldEmpty && !isHandEmpty){
            System.out.println("Please press action\n" +
                    "Press 1 - Play Card from the hand\n" +
                    "Press 3 - End Turn\n" +
                    "Press 4 - Surrender\n");
        } else {
            System.out.println("Please press action\n" +
                    "Press 1 - Play Card from the hand\n" +
                    "Press 2 - Play Card on the field\n" +
                    "Press 3 - End Turn\n" +
                    "Press 4 - Surrender\n");
        }


    }

    private static int scanner() {
        int number;
        try {
            Scanner newScanner = new Scanner(System.in);
             number = newScanner.nextInt();
            return number - 1;
        } catch (InputMismatchException e) {
            return 0;
        }
    }

}

