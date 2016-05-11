package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {


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

        startGame();
        boolean endGame = false;

        while (!endGame) {
            if (player1.menu(player1.hand, newField, ai.hand, player1.deck)) {
                endGame = true;
            }
            ai.aiLogic(player1.hand, newField, ai.hand, ai.deck);
        }

    }


    private static void startGame() {
        System.out.println("Let's the Games begin!");
    }


}


