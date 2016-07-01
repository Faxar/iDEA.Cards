package gameObjects;

/**
 * Created by vassili.holenev on 1.06.2016.
 */
public abstract class Spell extends Card {
    private int spellType;

    public int getSpellType() {
        return spellType;
    }

    public void setSpellType(int spellType) {
        this.spellType = spellType;
    }
}
