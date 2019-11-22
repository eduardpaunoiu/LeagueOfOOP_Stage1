package com.project1.heros;

import static com.project1.helpers.Constants.INITIAL_XP;
import static com.project1.helpers.Constants.LEVEL_UP_XP;

public class Hero {
    /* Campuri legate de XP*/
    private int xp;
    private int level;
    /* Campuri legate de HP */
    private int currentHP;
    private int totalHP;
    private int levelUpHP;
    private boolean isKilled;
    /* Campuri legate de localizarea jucatorului */
    private int xPosition;
    private int yPosition;

    //TODO alte chestii de adaugat in HERO
    /**
     * Constructor fara parametrii.
     */
    public Hero() {

    }

    /**
     * Constructor care initializeaza eroul cu pozitia pe x si pe y.
     */
    public Hero(final int xPos, final int yPos) {
        this.xPosition = xPos;
        this.yPosition = yPos;
    }

    /**
     * @return Pozitia pe x a eroului.
     */
    public int getxPosition() {
        return xPosition;
    }

    /**
     * @return Pozitia pe y a eroului.
     */
    public int getyPosition() {
        return yPosition;
    }

    /**
     * @return XP-ul dupa ce a trecut un nivel.
     */
    public int canLevelUp() {
        return INITIAL_XP + LEVEL_UP_XP * this.level;
    }

    /**
     * Metoda ce reseteaza HP-ul si creste nivelul.
     */
    public void resetHP() {
        totalHP += levelUpHP;
        currentHP = totalHP;
        level++;
    }

    /**
     * Muta eroul sus.
     */
    public void moveUp() {
        xPosition--;
    }

    /**
     * Muta eroul jos.
     */
    public void moveDown() {
        xPosition++;
    }

    /**
     * Muta eroul la dreapta.
     */
    public void moveRight() {
        yPosition++;
    }

    /**
     * Muta eroul la stanga.
     */
    public void moveLeft() {
        yPosition--;
    }
}