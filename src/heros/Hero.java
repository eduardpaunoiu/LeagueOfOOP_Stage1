package heros;

import static helpers.Constants.INITIAL_HP_KNIGHT;
import static helpers.Constants.INITIAL_HP_PYROMANCER;
import static helpers.Constants.INITIAL_HP_ROGUE;
import static helpers.Constants.INITIAL_HP_WIZARD;
import static helpers.Constants.INITIAL_XP;
import static helpers.Constants.LEVEL_UP_HP_KNIGHT;
import static helpers.Constants.LEVEL_UP_HP_PYROMANCER;
import static helpers.Constants.LEVEL_UP_HP_ROGUE;
import static helpers.Constants.LEVEL_UP_HP_WIZARD;
import static helpers.Constants.LEVEL_UP_XP;
import static helpers.Constants.XP_POINTS;
import static helpers.Constants.XP_POINTS_COEFFICIENT;

public class Hero {
    /* XP */
    private int xp;
    private int level;
    /* HP */
    private int levelUpHP;
    private int currentHP;
    private int maxHP;
    /* Localizarea jucatorului */
    private int xPosition;
    private int yPosition;
    private char terrain;
    /* Abilitatile jucatorilor */
    protected int passiveDamage;
    protected int passiveCount;
    protected int immobilized;

    /**
     * Constructor fara parametrii.
     */
    public Hero() {

    }

    /**
     * Constructor care initializeaza eroul cu pozitia pe x si pe y si terenul.
     */
    public Hero(final int xPos, final int yPos, final char land) {
        this.xPosition = xPos;
        this.yPosition = yPos;
        this.terrain = land;
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
     * getter levelup hp.
     */
    public int getLevelUpHP() {
        return levelUpHP;
    }

    /**
     * setter level up hp.
     */
    public void setLevelUpHP(final int levelUpHP) {
        this.levelUpHP = levelUpHP;
    }

    public void setLevel(final int lvl) {
        this.level = lvl;
    }

    /**
     * return level.
     */
    public int getLevel() {
        return level;
    }

    public char getTerrain() {
        return terrain;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(final int hp) { this.currentHP = hp; }

    public int getMaxHP() {
        return maxHP;
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

    /**
     * Eroul primeste damage.
     */
    public void takeDamage(final int damage) {
        currentHP = currentHP - damage;
    }
    /**
     * Metoda ce reseteaza HP-ul si creste nivelul.
     */
    public void resetHP() {
        maxHP += levelUpHP;
        currentHP = maxHP;
        level++;
    }

    public int getXp() {
        return xp;
    }

    /**
     * Verifica daca eroul a murit.
     */
    public boolean isKilled() {
        return this.currentHP <= 0;
    }


    /**
     * eroul primeste damage pasiv
     */
    public void takePassiveDamage(final int passiveDamage) {
        this.currentHP -= passiveDamage;
    }

    public void battles(Hero enemy) {

    }

    public void prepareHero() {

    }

    public int getUnmodifiedDamage() {
        return 0;
    }

    public void moveHero (final char land, final int x, final int y) {
        if (! isKilled()) {
            if (this.immobilized == 0) {
                this.terrain = land;
                this.xPosition = x;
                this.yPosition = y;
            } else {
                --this.immobilized;
            }
        }
    }

    public void immobilizeEnemy(final int rounds) {
        this.immobilized = rounds;
    }

    public boolean isImmobilized(){
        return immobilized != 0;
    }

    public void getPassiveDamage() {
        if (this.passiveCount != 0) {
            this.takePassiveDamage(this.passiveDamage);
            this.passiveCount--;
        }

    }

    public void setPassiveDamage(final int count, final int passDmg) {
        this.passiveCount = count;
        this.passiveDamage = passDmg;
    }

    public int getImmobilized() {
        return immobilized;
    }

    public void setImmobilized(int immobilized) {
        this.immobilized = immobilized;
    }

    public void experienceAndLevelUp(Hero enemy) {
        System.out.println("bla bla " + this.xp);

        this.xp = this.xp + Math.max(0, XP_POINTS
                - (this.getLevel() - enemy.getLevel()) * XP_POINTS_COEFFICIENT);
        System.out.println("bla bla " + this.xp);
        int k = (this.getXp() - INITIAL_XP) / LEVEL_UP_XP;
        if (this.getXp() - INITIAL_XP < 0) {
            this.setLevel(0);
        }
        else {
            this.setLevel(k+1);
            if (this instanceof Pyromancer) {
                this.currentHP = INITIAL_HP_PYROMANCER + LEVEL_UP_HP_PYROMANCER * this.getLevel();
                this.maxHP = currentHP;
            }
            if (this instanceof Knight) {
                this.currentHP = INITIAL_HP_KNIGHT + LEVEL_UP_HP_KNIGHT * this.getLevel();
                this.maxHP = currentHP;
            }
            if (this instanceof Wizard) {
                this.currentHP = INITIAL_HP_WIZARD + LEVEL_UP_HP_WIZARD * this.getLevel();
                this.maxHP = currentHP;
            }
            if (this instanceof Rogue) {
                this.currentHP = INITIAL_HP_ROGUE + LEVEL_UP_HP_ROGUE * this.getLevel();
                this.maxHP = currentHP;
            }
        }


    }

    public String output() {
        if (this instanceof Wizard) {
            if (!this.isKilled()) {
                return "W " + level
                        + " " + xp
                        + " " + currentHP
                        + " " + xPosition
                        + " " + yPosition;
            } else {
                return "W dead";
            }
        } else if (this instanceof Rogue) {
            if (!this.isKilled()) {
                return "R " + level
                        + " " + xp
                        + " " + currentHP
                        + " " + xPosition
                        + " " + yPosition;
            } else {
                return "R dead";
            }
        } else if (this instanceof Pyromancer) {
            if (!this.isKilled()) {
                return "P " + level
                        + " " + xp
                        + " " + currentHP
                        + " " + xPosition
                        + " " + yPosition;
            } else {
                return "P dead";
            }
        } else {
            if (!this.isKilled()) {
                return "K " + level
                        + " " + xp
                        + " " + currentHP
                        + " " + xPosition
                        + " " + yPosition;
            } else {
                return "K dead";
            }
        }
    }

    protected void setMaxHP(int i) {
        this.maxHP = i;
    }
}