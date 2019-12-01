package heros;

import modifiers.Modifier;

import static helpers.Constants.HP_LIMIT;
import static helpers.Constants.HP_LIMIT_BOOSTER;
import static helpers.Constants.HP_LIMIT_MAXLEVEL;
import static helpers.Constants.INCREASE_DAMAGE_EXECUTE;
import static helpers.Constants.INCREASE_DAMAGE_SLAM;
import static helpers.Constants.INITIAL_DAMAGE_EXECUTE;
import static helpers.Constants.INITIAL_DAMAGE_SLAM;
import static helpers.Constants.INITIAL_HP_KNIGHT;
import static helpers.Constants.INITIAL_HP_PYROMANCER;
import static helpers.Constants.KNIGHT_MODIFIER_EXECUTE;
import static helpers.Constants.KNIGHT_MODIFIER_SLAM;
import static helpers.Constants.LAND_TERRAIN;
import static helpers.Constants.LEVEL_UP_HP_KNIGHT;
import static helpers.Constants.PYROMANCER_MODIFIER_EXECUTE;
import static helpers.Constants.PYROMANCER_MODIFIER_SLAM;
import static helpers.Constants.ROGUE_MODIFIER_EXECUTE;
import static helpers.Constants.ROGUE_MODIFIER_SLAM;
import static helpers.Constants.TERRAIN_MODIFIER_LAND;
import static helpers.Constants.WIZARD_MODIFIER_EXECUTE;
import static helpers.Constants.WIZARD_MODIFIER_SLAM;

public class Knight extends Hero {

    public Knight(final int xPos, final int yPos, final char land) {
        super(xPos, yPos, land);
        super.setCurrentHP(INITIAL_HP_KNIGHT);
        super.setLevelUpHP(LEVEL_UP_HP_KNIGHT);
        super.setMaxHP(INITIAL_HP_KNIGHT);

    }

    private Modifier executeModifier = new Modifier();
    private Modifier slamModifier = new Modifier();

    private float executeDamage;
    private int executeDamagePerLevel;

    private float slamDamage;
    private int slamDamagePerLevel;
    private int totalDamage;

    /**
     * pregateste Knight-ul de joc, setand modificatorii, hp-ul etc.
     */
    public void prepareHero() {

        executeDamage = INITIAL_DAMAGE_EXECUTE;
        executeDamagePerLevel = INCREASE_DAMAGE_EXECUTE;

        executeModifier.setPyromancerModifier(PYROMANCER_MODIFIER_EXECUTE);
        executeModifier.setKnightModifier(KNIGHT_MODIFIER_EXECUTE);
        executeModifier.setRogueModifier(ROGUE_MODIFIER_EXECUTE);
        executeModifier.setWizardModifier(WIZARD_MODIFIER_EXECUTE);
        executeModifier.setLandModifier(TERRAIN_MODIFIER_LAND);

        slamDamage = INITIAL_DAMAGE_SLAM;
        slamDamagePerLevel = INCREASE_DAMAGE_SLAM;

        slamModifier.setPyromancerModifier(PYROMANCER_MODIFIER_SLAM);
        slamModifier.setWizardModifier(WIZARD_MODIFIER_SLAM);
        slamModifier.setKnightModifier(KNIGHT_MODIFIER_SLAM);
        slamModifier.setRogueModifier(ROGUE_MODIFIER_SLAM);
        slamModifier.setLandModifier(TERRAIN_MODIFIER_LAND);
    }

    /**
     * Knight-ul ataca.
     */
    @Override
    public void battles(final Hero enemy) {
        final int rounds = 1;
        this.prepareHero();
            executeDamage += executeDamagePerLevel * this.getLevel();
            slamDamage += slamDamagePerLevel * this.getLevel();

        if (this.getTerrain() == LAND_TERRAIN) {
            executeDamage += executeDamage * executeModifier.getLandModifier();
            slamDamage += slamDamage * slamModifier.getLandModifier();
        }


        if (enemy instanceof Rogue) {
            executeDamage += executeDamage * executeModifier.getRogueModifier();
            slamDamage += slamDamage * slamModifier.getRogueModifier();
        }

        if (enemy instanceof Knight) {
            executeDamage += executeDamage * executeModifier.getKnightModifier();
            slamDamage += slamDamage * slamModifier.getKnightModifier();
        }

        if (enemy instanceof Pyromancer) {
            executeDamage += executeDamage * executeModifier.getPyromancerModifier();
            slamDamage += slamDamage * slamModifier.getPyromancerModifier();
        }

        if (enemy instanceof Wizard) {
            executeDamage += executeDamage * executeModifier.getWizardModifier();
            slamDamage += slamDamage * slamModifier.getWizardModifier();
        }

        float limit;
        if (this.getLevel() <= HP_LIMIT_MAXLEVEL) {
            limit = (HP_LIMIT + HP_LIMIT_BOOSTER * this.getLevel()) * enemy.getMaxHP();
        } else {
            limit = (HP_LIMIT + HP_LIMIT_BOOSTER * HP_LIMIT_MAXLEVEL) * enemy.getMaxHP();
        }
        if (enemy.getCurrentHP() < Math.round(limit)) {
            enemy.setCurrentHP(0);
        }
        int roundedExecuteDamage = Math.round(executeDamage);
        int roundedSlamDamage = Math.round(slamDamage);
        totalDamage = roundedExecuteDamage + roundedSlamDamage;
        enemy.takeDamage(totalDamage);
        enemy.immobilizeEnemy(rounds);
    }

    /**
     * @return damage-ul total dat de Knight.
     */
    public int getTotalDamage() {
        return totalDamage;
    }

    public int getUnmodifiedDamage() {
       float executeDmg = INITIAL_DAMAGE_EXECUTE;
       float slamDmg = INITIAL_DAMAGE_SLAM;
       executeDmg += INCREASE_DAMAGE_EXECUTE * this.getLevel();
       slamDmg += INCREASE_DAMAGE_SLAM * this.getLevel();
        if (this.getTerrain() == LAND_TERRAIN) {
            executeDmg += executeDmg * TERRAIN_MODIFIER_LAND;
            slamDmg += slamDmg * TERRAIN_MODIFIER_LAND;
        }
        float knightDamage = executeDmg + slamDmg;
        return Math.round(knightDamage);
    }





}




