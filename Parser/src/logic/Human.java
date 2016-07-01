package logic;

import com.company.Deck;
import com.company.Field;
import com.company.Hand;
import gameObjects.*;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by vassili.holenev on 04/05/16.
 */
public class Human extends Player {

    private static void printMenu(boolean isHandEmpty, boolean isFieldEmpty) {
        if (isFieldEmpty && isHandEmpty) {
            System.out.println("Please select action\n" +
                    "Press 3 - End Turn\n" +
                    "Press 4 - Surrender\n");
        } else if (!isFieldEmpty && isHandEmpty) {
            System.out.println("Please select action\n" +
                    "Press 2 - Play Card on the field\n" +
                    "Press 3 - End Turn\n" +
                    "Press 4 - Surrender\n");
        } else if (isFieldEmpty && !isHandEmpty) {
            System.out.println("Please select action\n" +
                    "Press 1 - Play Card from the hand\n" +
                    "Press 3 - End Turn\n" +
                    "Press 4 - Surrender\n");
        } else {
            System.out.println("Please select action\n" +
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


