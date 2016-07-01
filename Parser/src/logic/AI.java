package logic;

import com.company.Deck;
import com.company.Field;
import com.company.Hand;
import gameObjects.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Created by Alazerus on 4/29/2016.
 */
public class AI extends Player {

    public Hand hand;
    public Deck deck;

    public class CustomComparator implements Comparator<Card> {
        @Override
        public int compare(Card o1, Card o2) {
            return o2.getMana() - o1.getMana();
        }
    }

}