package gameObjects;

import com.company.Deck;
import com.company.Hand;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Created by vassili.holenev on 22.06.2016.
 */
public abstract class Player {

    private static final int maximumHandSize = 3;
    private static final int startingHealth = 30;

    private int mana;
    private int tempMana;
    private int health = startingHealth;
    public Hand hand;
    public Deck deck;


    public Player() {
        this.mana = 1;
        this.tempMana = mana;
    }
}
