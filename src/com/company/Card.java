package com.company;

/**
 * Created by vassili.holenev on 4.04.2016.
 */
public class Card {
    private int isMinion;
    private int id;
    private String name;
    private int strenght;
    private int health;
    private int power;
    private int heal;
    private int mana;
    private int spellType;
    private int modificator;
    private String desctiption;

    private int tempHealth;
    private int tempStrength;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getStrenght() {
        return strenght;
    }

    public void setStrenght(int strenght) {
        this.tempStrength = strenght;
        this.strenght = strenght;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.tempHealth = health;
        this.health = health;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public int getModificator() {
        return modificator;
    }

    public void setModificator(int modificator) {
        this.modificator = modificator;
    }

    public int getIsMinion() {
        return isMinion;
    }

    public void setIsMinion(int isMinion) {
        this.isMinion = isMinion;
    }

    public int getSpellType() {
        return spellType;
    }

    public void setSpellType(int spellType) {
        this.spellType = spellType;
    }

    public String getDesctiption() {
        return desctiption;
    }

    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
    }

    public int getTempHealth() {
        return tempHealth;
    }

    public void setTempHealth(int tempHealth) {
        this.tempHealth = tempHealth;
    }

    public int getTempStrength() {
        return tempStrength;
    }

    public void setTempStrength(int tempStrength) {
        this.tempStrength = tempStrength;
    }

    @Override
    public String toString() {
        if(isMinion == 0){
            if(spellType == 1){
                return "Spell{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", power=" + power +
                        ", mana=" + mana +
                        '}';
            } else if (spellType == 2){
                return "Spell{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", buff=" + modificator +
                        ", mana=" + mana +
                        '}';
            }return "Spell{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", heal=" + heal +
                    ", mana=" + mana +
                    '}';

        }
        return name +
                " | Strength: " + strenght +
                " , Health: " + health +
                " , Mana: " + mana;
    }
}
