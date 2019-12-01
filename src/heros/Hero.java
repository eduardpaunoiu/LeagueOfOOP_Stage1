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

    /**
     * seteaza nivelul.
     * @param lvl
     */
    public void setLevel(final int lvl) {
        this.level = lvl;
    }

    /**
     * return level.
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return tipul de teren pe care se afla un erou.
     */
    public char getTerrain() {
        return terrain;
    }

    /**
     * @return hp-ul curent al eroului.
     */
    public int getCurrentHP() {
        return currentHP;
    }

    /**
     * setez hp-ul curent al eroului.
     */
    public void setCurrentHP(final int hp) {
        this.currentHP = hp;
    }

    /**
     * returnez hp-ul maxim al unui erou.
     */
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

    /**
     * @return experienta eroului.
     */
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
     * eroul primeste damage pasiv.
     */
    public void takePassiveDamage(final int dmg) {
        this.currentHP -= dmg;
    }

    public void battles(final Hero enemy) {

    }

    public void prepareHero() {

    }

    /**
     * va fi suprascrisa in clasele ce extind hero.
     */
    public int getUnmodifiedDamage() {
        return 0;
    }

    /**
     * imobilizez eroul pentru un numar de runde.
     * @param rounds rundele.
     */
    public void immobilizeEnemy(final int rounds) {
        this.immobilized = rounds;
    }

    /**
     *verific daca eroul este imobilizat.
     */
    public boolean isImmobilized() {
        return immobilized != 0;
    }

    /**
     * eroul primeste un damage pasiv pentru un numar de runde(passivecount).
     * count-ul scade de fiecare data cand se aplica damage ul.
     */
    public void getPassiveDamage() {
        if (this.passiveCount != 0) {
            this.takePassiveDamage(this.passiveDamage);
            this.passiveCount--;
        }

    }

    /**
     * @param count numarul de runde in care eroul primeste damage pasiv.
     * @param passDmg damage ul pasiv primit de erou.
     */
    public void setPassiveDamage(final int count, final int passDmg) {
        this.passiveCount = count;
        this.passiveDamage = passDmg;
    }

    /**
     * @return numarul de runde in care eroul e imobilizat.
     */
    public int getImmobilized() {
        return immobilized;
    }

    /**
     * face un erou imobilizat pentru un numar de runde.
     * @param immobilized
     */
    public void setImmobilized(final int immobilized) {
        this.immobilized = immobilized;
    }

    /**
     * metoda prin care dau experienta eroilor si level up.
     */
    public void experienceAndLevelUp(final Hero enemy) {
        this.xp = this.xp + Math.max(0, XP_POINTS
                - (this.getLevel() - enemy.getLevel()) * XP_POINTS_COEFFICIENT);
        int k = (this.getXp() - INITIAL_XP) / LEVEL_UP_XP;
        if (this.getXp() - INITIAL_XP < 0) {
            this.setLevel(0);
        } else {
            this.setLevel(k + 1);
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

    /**
     * @return outputul cerut de cerinta.
     */
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

    /**
     * seteaza hp-ul maxim in clasele care extind hero.
     * @param i
     */
    public void setMaxHP(final int i) {
        this.maxHP = i;
    }
}
