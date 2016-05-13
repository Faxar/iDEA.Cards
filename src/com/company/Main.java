package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Deck newDeck = new Deck();
        Deck aiDeck = new Deck();
        Player player1 = new Player();
        player1.hand = new Hand("my Hand");
        player1.deck = newDeck;
        AI ai = new AI();
        ai.hand = new Hand("ai Hand");
        ai.deck = aiDeck;
        Field newField = new Field();

        System.out.println("Choose your fate");
        System.out.println("1 - Space Marines\n" +
                            "2 - Orks\n" +
                            "3 - Chaos\n" +
                            "4 - Necrons");

        int number = scanner();
        cardBuilder.builder(player1.deck, number);
        Random randomGenerator = new Random();
        cardBuilder.builder(ai.deck, randomGenerator.nextInt(3) +1);
        player1.deck.shuffle();
        ai.deck.shuffle();
        player1.hand.populateHand(newDeck);
        ai.hand.populateHand(aiDeck);


        startGame();

        while (true) {
           if (player1.menu(player1.hand, newField, ai.hand, player1.deck)) {
               break;
           }
           if (ai.aiLogic(player1.hand, newField, ai.hand, ai.deck)) {
               break;
           }
      }

    }

    private static void startGame() {
        System.out.println("Let's the Games begin!");
    }

    private static int scanner() {
        int number;
        try {
            Scanner newScanner = new Scanner(System.in);
            number = newScanner.nextInt();
            return number;
        } catch (InputMismatchException e) {
            return 0;
        }
    }
}





