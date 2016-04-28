package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        int number;



        Card card1 = new Card(1, "Scout", 1, 1, 1);
        Card card2 = new Card(2, "Space Marine", 1, 1, 1);
        Card card3 = new Card(3, "Inquisitor", 1, 1, 1);
        Card card4 = new Card(4, "Chaplain", 1, 1, 1);
        Card card5 = new Card(5, "Terminator", 1, 1, 1);
        Card card6 = new Card(6, "Dreadnought", 8, 8, 8);
        Card card7 = new Card(7, "Primarch", 10, 10, 10);
        Deck newDeck = new Deck("Player1");
        Field newField = new Field();
        newDeck.populateDeck(card1);
        newDeck.populateDeck(card2);
        newDeck.populateDeck(card3);
        newDeck.populateDeck(card4);
        newDeck.populateDeck(card5);
        newDeck.populateDeck(card6);
        newDeck.populateDeck(card7);
        newDeck.shuffle();   //Shuffle your hand
        Hand newHand = new Hand("myHand");
        newHand.populateHand(newDeck);

        Scanner newScanner = new Scanner(System.in);

        Card tempCard;

        startGame();
        printMenu();

        System.out.println("You have following cards in your hand : ");
        newHand.checkCards();
        boolean endGame = false;
        while (!endGame) {
            try {
                number = newScanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter correct number");
                continue;
            }



            switch (number) {

                case 1:
                    System.out.println("Enter card number");
                    newHand.checkCards();
                    number = newScanner.nextInt();
                    if (number < 0) {
                        System.out.println("Please enter positive number");
                    } else if (newHand.checkIfCardExist(number)) {
                        tempCard = newHand.getCard(number);
                        newField.putCardOnF(tempCard, 1);
                        System.out.println("Now on the field there is:");
                        newField.returnFieldCards();
                        printMenu();
                    }
                    break;

                case 2:
                    if (newHand.checkAmountOfCards() >= 3 && newDeck.amountCardsInDeck() > 0) {
                        Card burned = newDeck.fetch();
                        System.out.println("You have full hand.\n" + burned + '\n' + " have burned down");
                        System.out.println("You have " + newDeck.amountCardsInDeck() + " cards, left in your deck");
                        printMenu();
                        break;
                    } else if (newHand.checkAmountOfCards() < 3 && newDeck.amountCardsInDeck() > 0) {
                        Card pullFromDeck = newDeck.fetch();
                        newHand.endTurnCardDrow(pullFromDeck);
                        System.out.println("You have pulled " + pullFromDeck + " card");
                        printMenu();
                        break;
                    } else {
                        System.out.println("You have " + newDeck.amountCardsInDeck() + " cards, left in your deck");
                        System.out.println("Your current health is " + newHand.checkPlayerHealth());
                        newHand.removePlayerHealth(5);
                        System.out.println("You are suffering fatigue death and you have lost 5 Health");
                        System.out.println("Your new health number is " + newHand.checkPlayerHealth());
                        if (newHand.checkPlayerHealth() <= 0) {
                            System.out.println("You Dead! MuaHaHaHa!");
                            endGame = true;
                            break;
                        } else {
                            printMenu();
                            break;
                        }
                    }

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

    private static void printMenu() {
        System.out.println("Please press action\n" +
                "Press 1 - Play Card\n" +
                "Press 2 - End Turn\n" +
                "Press 4 - Surrender\n");
    }

}


