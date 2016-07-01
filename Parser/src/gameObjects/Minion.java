package gameObjects;

/**
 * Created by vassili.holenev on 1.06.2016.
 */
public class Minion extends Card {
    public int strenth;
    private int health;
    private boolean dead;
    private boolean fatigue;

    public Minion() {
        this.dead = false;
        this.fatigue = false;
    }

    public int getStrenth() {
        return strenth;
    }

    public void setStrenth(int strenth) {
        this.strenth = strenth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        if(health < 0){
            dead = true;
        }
    }

    public boolean isDead() {
        return dead;
    }

    public void attack(int damage){
        setHealth(damage);
    }

    @Override
    public String toString() {
        return super.toString() + " Strength: " + strenth + " Health: " + health;
    }
}
