package gameObjects;

/**
 * Created by vassili.holenev on 1.07.2016.
 */
public enum Races {
    ORKS(1),
    SPACEMARINES(2),
    CHAOS(3),
    NECRONS(4);

    private int numVal;

    Races(int numVal){
        this.numVal = numVal;
    }

    public int getNumVal(){
        return numVal;
    }
}
