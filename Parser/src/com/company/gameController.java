package com.company;

import gameObjects.Player;
import logic.AI;
import logic.Human;
import gameObjects.Races;

import java.util.*;

/**
 * Created by vassili.holenev on 7.06.2016.
 */
public final class gameController {

    Random random = new Random(1);

    private Player currentPlayer;
    private Player waitingPlayer;

    public void gameStart(int race){
         createPlayers(race);
    }

    private void gameEngine(List<Object> players){

    }

    private List<Object> createPlayers(int race){

        AI ai = new AI();
        Human human = new Human();
        List<Object> collection = new ArrayList<>();
        ai.deck.fetch();

        collection.add(ai);
        collection.add(human);



        return collection;
    }




}
