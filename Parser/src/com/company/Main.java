package com.company;

import logic.AI;
import gameObjects.cardBuilder;
import logic.Human;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        startGame();
        gameController gc = new gameController();
        gc.gameStart(mainMenu());

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

    public static int mainMenu(){
        System.out.println("Select race to play:\n"+
                            "1.Heartstonish game\n"+
                            "2.MTG type");
        return scanner();
    }
}





