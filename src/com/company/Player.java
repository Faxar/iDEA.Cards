package com.company;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by kengos on 04/05/16.
 */
public class Player {
    public Hand hand;
    public Deck deck;
    int turn = 1;

    public boolean menu(Hand player1, Field newField, Hand ai, Deck newDeck) {


        Card tempCard;


        System.out.println("You have following cards in your hand : ");
        player1.checkCards();

        boolean endGame;
        boolean endTurn = false;
        while (!endTurn) {

            System.out.println("==============================================");
            System.out.println("Turn " + turn);
            System.out.println("You have currently: " + player1.getTempMana() + " mana in mana pool.");
            printMenu(player1.isHandEmpty(), newField.isFieldEmpty());

            int numberS = scanner();

            switch (numberS) {

                case 0:
                    System.out.println("Enter card number");
                    player1.checkCards();
                    int cNumber = scanner();
                    if (cNumber < 0) {
                        System.out.println("Please enter positive number");
                    } else {
                        if (player1.checkIfCardExist(cNumber)) {
                            if (player1.getManaCard(cNumber) <= player1.getMana()) {
                                int cardManaCost = player1.getManaCard(cNumber);
                                tempCard = player1.getCard(cNumber);
                                newField.putCardOnF(tempCard, 1);
                                System.out.println("Now on the field there is:");
                                player1.modifyTempMana(cardManaCost);
                                newField.returnFieldCards();
                            } else {
                                System.out.println("You don't have enough mana. Your current mana pool is " + player1.getMana());
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
                    if (newField.checkIfCardExistOnField(cardNumber)) {
                        if (!newField.checkIfCardFatugued(cardNumber, 1)) {
                            int damage = newField.getCardStrengh(cardNumber);
                            if (newField.checkAICards()) {
                                System.out.println("Please select target");
                                System.out.println("1 - Attack AI\n" +
                                        "2 - Attack card on the filed\n" +
                                        "3 - Cancel");
                                int number = scanner();
                                switch (number) {
                                    case 0:
                                        ai.removePlayerHealth(damage);
                                        System.out.println("AI received " + damage + " points of damage");
                                        System.out.println("New AI health = " + ai.checkHealth());
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
                    }
                    break;


                case 2:
                    if (player1.checkAmountOfCards() >= 3 && newDeck.amountCardsInDeck() > 0) {
                        Card burned = newDeck.fetch();
                        System.out.println("You have full hand.\n" + burned + '\n' + " have burned down");
                        System.out.println("You have " + newDeck.amountCardsInDeck() + " cards, left in your deck");
                    } else if (player1.checkAmountOfCards() < 3 && newDeck.amountCardsInDeck() > 0) {
                        Card pullFromDeck = newDeck.fetch();
                        player1.endTurnCardDrow(pullFromDeck);
                        System.out.println("You have pulled " + pullFromDeck + " card");
                    } else {
                        System.out.println("You have " + newDeck.amountCardsInDeck() + " cards, left in your deck");
                        System.out.println("Your current health is " + player1.checkHealth());
                        player1.removePlayerHealth(5);
                        System.out.println("You are suffering fatigue death and you have lost 5 Health");
                        System.out.println("Your new health number is " + player1.checkHealth());
                        if (player1.checkHealth() <= 0) {
                            System.out.println("You Dead! MuaHaHaHa!");
                            return endGame = true;
                        }
                    }
                    turn++;
                    newField.clearFatigue(1);
                    player1.modifyMana();
                    endTurn = true;
                    break;

                case 3:
                    System.out.println("Weakling!");
                    return endGame = true;
            }
        }
        return endGame = false;
    }


    private static void printMenu(boolean isHandEmpty, boolean isFieldEmpty) {
        if (isFieldEmpty && isHandEmpty) {
            System.out.println("Please press action\n" +
                    "Press 3 - End Turn\n" +
                    "Press 4 - Surrender\n");
        } else if (!isFieldEmpty && isHandEmpty) {
            System.out.println("Please press action\n" +
                    "Press 2 - Play Card on the field\n" +
                    "Press 3 - End Turn\n" +
                    "Press 4 - Surrender\n");
        } else if (isFieldEmpty && !isHandEmpty) {
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


