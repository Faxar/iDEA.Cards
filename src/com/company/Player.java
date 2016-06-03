package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by vassili.holenev on 04/05/16.
 */
public class Player {
    public Hand hand;
    public Deck deck;
    int turn = 1;

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

    public boolean menu(Hand player1, Field newField, Hand ai, Deck newDeck) {

        System.out.println("You have following cards in your hand : ");
        player1.checkCards();

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
                        if(player1.checkIfCardExist(cNumber)){
                            Card card = player1.returnCard(cNumber);
                            if(card.getMana() <= player1.getTempMana()){
                                Object o = player1.returnCard(cNumber);
                                if (o.getClass().equals(Minion.class)) {
                                    Minion minion = (Minion) player1.returnCard(cNumber);
                                    newField.putCardOnF(minion, 1);
                                    System.out.println("Now on the field there is:");
                                    newField.returnFieldCards();
                                    player1.modifyTempMana(minion.getMana());
                                    player1.removeCardHand(cNumber);
                                } else if (o.getClass().equals(DamageSpell.class)){
                                    DamageSpell dmg = (DamageSpell) player1.returnCard(cNumber);
                                    if (newField.checkAICards()) {
                                        System.out.println("1 - Attack AI\n" +
                                                "2 - Attack card on the field\n" +
                                                "3 - Cancel");
                                        int number = scanner();
                                        switch (number) {
                                            case 0:
                                                System.out.println("AI had " + ai.checkHealth());
                                                System.out.println("You have dealt " + dmg.getPower() + " to AI face.");
                                                player1.removeCardHand(cNumber);
                                                ai.removePlayerHealth(dmg.getPower());
                                                System.out.println("AI new health " + ai.checkHealth());
                                                player1.modifyTempMana(dmg.getMana());
                                                break;
                                            case 1:
                                                System.out.println("Select target");
                                                newField.giveEnemyCards();
                                                Minion tCard = (Minion) newField.returnCardElement(scanner(), 2);
                                                if (dmg.getPower() >= tCard.getHealth()) {
                                                    System.out.println("Zzzzaap! You have destroyed " + tCard.getName() + " by dealing " + dmg.getPower() + " damage with " + dmg.getName());
                                                    newField.removeCard(tCard, 1);
                                                    player1.removeCardHand(cNumber);
                                                    player1.modifyTempMana(dmg.getMana());
                                                } else if (dmg.getPower() < tCard.getHealth()) {
                                                    System.out.println("Zzap! You have dealt " + dmg.getPower() + " damage. But failed to destroy " + tCard.getName());
                                                    player1.removeCardHand(cNumber);
                                                    player1.modifyTempMana(dmg.getMana());
                                                }
                                            case 2:
                                                break;
                                        }
                                    } else if (!newField.checkAICards()){
                                        System.out.println("1 - Attack AI\n" +
                                                "2 - Cancel");
                                        int number = scanner();
                                        switch (number) {
                                            case 0:
                                                System.out.println("AI had " + ai.checkHealth());
                                                System.out.println("You have dealt " + dmg.getPower() + " to AI face.");
                                                player1.removeCardHand(cNumber);
                                                ai.removePlayerHealth(dmg.getPower());
                                                System.out.println("AI new health " + ai.checkHealth());
                                                player1.modifyTempMana(dmg.getMana());
                                                break;
                                            case 1:
                                                break;
                                        }
                                    }
                                } else if (o.getClass().equals(BuffSpell.class)){
                                    BuffSpell buff = (BuffSpell) player1.returnCard(cNumber);
                                    if (newField.checkIfPlayerCardsField()) {
                                        System.out.println("Select buff target");
                                        newField.returnFieldCards();
                                        int bTarget = scanner();
                                        if (newField.checkIfCardExistOnField(bTarget)) {
                                            Minion card2 = (Minion) newField.returnCardElement(bTarget, 1);
                                            System.out.println("You have buffed " + card2.getName());
                                            System.out.println("Strength was increased from " + card2.getStrenth());
                                            card2.setStrenth(card2.getStrenth() + buff.getModificator());
                                            System.out.println(" to " + card2.getStrenth());
                                            player1.removeCardHand(cNumber);
                                            player1.modifyTempMana(card2.getMana());
                                        }
                                    } else if (!newField.checkIfPlayerCardsField()){
                                        System.out.println("You don't have minions to buff");
                                    }
                                } else if (o.getClass().equals(HealSpell.class)){
                                    HealSpell heal = (HealSpell) player1.returnCard(cNumber);
                                    if (player1.checkHealth() == 30) {
                                        System.out.println("You can't heal. You have maximum health");
                                    } else if (player1.checkHealth() < 30) {
                                        if ((player1.checkHealth() + heal.getHeal()) >= 30) {
                                            System.out.println("You have been healed for " + (player1.checkHealth() - 30));
                                        } else if ((player1.checkHealth() + heal.getHeal()) < 30) {
                                            System.out.println("You have been healed for " + heal.getHeal());
                                        }
                                    }
                                }
                            } else if (card.getMana() > player1.getTempMana()) {
                                int missingM = card.getMana() - player1.getTempMana();
                                System.out.println("You are missing " + missingM + " mana, to play " + card.getName() );
                            }
                        }
                    } break;

                case 1:
                    System.out.println("Ai have following cards on the field");
                    newField.giveEnemyCards();
                    System.out.println("---------------------------");
                    System.out.println("Select card to attack with");
                    newField.returnFieldCards();
                    int cardNumber = scanner();
                    Minion card = (Minion) newField.returnCardElement(cardNumber, 1);
                    if (newField.checkIfCardExistOnField(cardNumber)) {
                        if (!newField.checkIfCardFatugued(cardNumber, 1)) {
                            if (newField.checkAICards()) {
                                System.out.println("Please select target");
                                System.out.println("1 - Attack AI\n" +
                                        "2 - Attack card on the field\n" +
                                        "3 - Cancel");
                                int number = scanner();
                                switch (number) {
                                    case 0:
                                        ai.removePlayerHealth(card.getStrenth());
                                        System.out.println("AI received " + card.getStrenth() + " points of damage");
                                        System.out.println("New AI health = " + ai.checkHealth());
                                        newField.putToFatugue(cardNumber, 1);
                                        if (ai.checkHealth() <= 0) {
                                            System.out.println("People! Rejoice! We finally vanquished this evil AI!");
                                            System.out.println("====================================================");
                                            return true;
                                        }
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
                            } else if (!newField.checkAICards()) {
                                System.out.println("Please select target");
                                System.out.println("1 - Attack AI\n" +
                                        "2 - Cancel");
                                int number = scanner();
                                switch (number) {
                                    case 0:
                                        ai.removePlayerHealth(card.getStrenth());
                                        System.out.println("AI received " + card.getStrenth() + " points of damage");
                                        System.out.println("New AI health = " + ai.checkHealth());
                                        newField.putToFatugue(cardNumber, 1);
                                        if (ai.checkHealth() <= 0) {
                                            System.out.println("People! Rejoice! We finally vanquished this evil AI!");
                                            System.out.println("====================================================");
                                            return true;
                                        }
                                        break;
                                    case 1:
                                        break;
                                }
                            }

                        } else {
                            System.out.println("You cannot play " + card.getName() + ". It's fatigued");
                        }
                    } else {
                        System.out.println("Please select correct card");
                    }
                    break;


                case 2:
                    if (player1.checkAmountOfCards() >= 3 && newDeck.amountCardsInDeck() > 0) {
                        Card burned = newDeck.fetch();
                        System.out.println("You have full hand.\n" + burned.getName() + '\n' + " card, have burned down.");
                        System.out.println("You have " + newDeck.amountCardsInDeck() + " cards, left in your deck");
                    } else if (player1.checkAmountOfCards() < 3 && newDeck.amountCardsInDeck() > 0) {
                        Card pullFromDeck = newDeck.fetch();
                        player1.endTurnCardDrow(pullFromDeck);
                        System.out.println("You have pulled " + pullFromDeck.getName() + " card");
                    } else {
                        System.out.println("You have " + newDeck.amountCardsInDeck() + " cards, left in your deck");
                        System.out.println("Your current health is " + player1.checkHealth());
                        player1.removePlayerHealth(5);
                        System.out.println("You are suffering fatigue death and you have lost 5 Health");
                        System.out.println("Your new health number is " + player1.checkHealth());
                        if (player1.checkHealth() <= 0) {
                            System.out.println("You Dead! MuaHaHaHa!");
                            return true;
                        }
                    }
                    turn++;
                    newField.clearFatigue(1);
                    player1.modifyMana();
                    endTurn = true;
                    break;

                case 3:
                    System.out.println("Weakling!");
                    return true;
            }
        }
        return false;
    }

}


