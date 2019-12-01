package heros;

import modifiers.Modifier;

import static helpers.Constants.INCREASE_DAMAGE_FIREBLAST;
import static helpers.Constants.INCREASE_DAMAGE_IGNITE;
import static helpers.Constants.INCREASE_PASSIVEDMG_IGNITE;
import static helpers.Constants.INITIAL_DAMAGE_FIREBLAST;
import static helpers.Constants.INITIAL_DAMAGE_IGNITE;
import static helpers.Constants.INITIAL_HP_PYROMANCER;
import static helpers.Constants.KNIGHT_MODIFIER_FIREBLAST;
import static helpers.Constants.LEVEL_UP_HP_PYROMANCER;
import static helpers.Constants.PASSIVE_DAMAGE_IGNITE;
import static helpers.Constants.PYROMANCER_MODIFIER_FIREBLAST;
import static helpers.Constants.ROGUE_MODIFIER_FIREBLAST;
import static helpers.Constants.ROUNDS_AFFECTED_IGNITE;
import static helpers.Constants.TERRAIN_MODIFIER_LAND;
import static helpers.Constants.TERRAIN_MODIFIER_VOLCANIC;
import static helpers.Constants.VOLCANIC_TERRAIN;
import static helpers.Constants.WIZARD_MODIFIER_FIREBLAST;

public class Pyromancer extends Hero {

    private Modifier fireblastModifier = new Modifier();
    private Modifier igniteModifier = new Modifier();

    private float fireblastDamage;
    private int fireblastDamagePerLevel;

    private float igniteActiveDamage;
    private float ignitePassiveDamage;
    private int igniteActiveDamagePerLevel;
    private int ignitePassiveDamagePerLevel;

    private int totalActiveDamage;
    private int totalPassiveDamage;

    public Pyromancer(final int xPos, final int yPos, final char land) {
        super(xPos, yPos, land);
        super.setCurrentHP(INITIAL_HP_PYROMANCER);
        super.setLevelUpHP(LEVEL_UP_HP_PYROMANCER);
        super.setMaxHP(INITIAL_HP_PYROMANCER);

    }

    @Override
    public void prepareHero() {
        fireblastDamage = INITIAL_DAMAGE_FIREBLAST;
        fireblastDamagePerLevel = INCREASE_DAMAGE_FIREBLAST;

        fireblastModifier.setPyromancerModifier(PYROMANCER_MODIFIER_FIREBLAST);
        fireblastModifier.setKnightModifier(KNIGHT_MODIFIER_FIREBLAST);
        fireblastModifier.setRogueModifier(ROGUE_MODIFIER_FIREBLAST);
        fireblastModifier.setWizardModifier(WIZARD_MODIFIER_FIREBLAST);
        fireblastModifier.setLandModifier(TERRAIN_MODIFIER_VOLCANIC);
        fireblastModifier.setPreferredTerrain(VOLCANIC_TERRAIN);

        igniteActiveDamage = INITIAL_DAMAGE_IGNITE;
        igniteActiveDamagePerLevel = INCREASE_DAMAGE_IGNITE;
        ignitePassiveDamage = PASSIVE_DAMAGE_IGNITE;
        ignitePassiveDamagePerLevel = INCREASE_PASSIVEDMG_IGNITE;

        igniteModifier.setPyromancerModifier(PYROMANCER_MODIFIER_FIREBLAST);
        igniteModifier.setKnightModifier(KNIGHT_MODIFIER_FIREBLAST);
        igniteModifier.setRogueModifier(ROGUE_MODIFIER_FIREBLAST);
        igniteModifier.setWizardModifier(WIZARD_MODIFIER_FIREBLAST);
        igniteModifier.setLandModifier(TERRAIN_MODIFIER_VOLCANIC);

    }

    /**
     * calculez damage ul dat de abilitatile pyromancer ului.
     */
    @Override
    public void battles(final Hero enemy) {
        this.prepareHero();

        fireblastDamage += fireblastDamagePerLevel * this.getLevel();
        igniteActiveDamage += igniteActiveDamagePerLevel * this.getLevel();
        ignitePassiveDamage += ignitePassiveDamagePerLevel * this.getLevel();

        if (this.getTerrain() == VOLCANIC_TERRAIN) {
            fireblastDamage += fireblastDamage * fireblastModifier.getLandModifier();
            igniteActiveDamage += igniteActiveDamage * igniteModifier.getLandModifier();
            ignitePassiveDamage += ignitePassiveDamage * igniteModifier.getLandModifier();
        }

        if (enemy instanceof Rogue) {
            fireblastDamage += fireblastDamage * fireblastModifier.getRogueModifier();
            igniteActiveDamage += igniteActiveDamage * igniteModifier.getRogueModifier();
            ignitePassiveDamage += ignitePassiveDamage * igniteModifier.getRogueModifier();
        }

        if (enemy instanceof Pyromancer) {
            fireblastDamage += fireblastDamage * fireblastModifier.getPyromancerModifier();
            igniteActiveDamage += igniteActiveDamage * igniteModifier.getPyromancerModifier();
            ignitePassiveDamage += ignitePassiveDamage * igniteModifier.getPyromancerModifier();
        }

        if (enemy instanceof Knight) {
            fireblastDamage += fireblastDamage * fireblastModifier.getKnightModifier();
            igniteActiveDamage += igniteActiveDamage * igniteModifier.getKnightModifier();
            ignitePassiveDamage += ignitePassiveDamage * igniteModifier.getKnightModifier();
        }


        if (enemy instanceof Wizard) {
            fireblastDamage += fireblastDamage * fireblastModifier.getWizardModifier();
            igniteActiveDamage += igniteActiveDamage * igniteModifier.getWizardModifier();
            ignitePassiveDamage += ignitePassiveDamage * igniteModifier.getWizardModifier();
        }
        int roundedDamageFireblast = Math.round(fireblastDamage);
        int roundedActiveDamageIgnite = Math.round(igniteActiveDamage);

        totalActiveDamage = roundedDamageFireblast + roundedActiveDamageIgnite;
        totalPassiveDamage = Math.round(ignitePassiveDamage);
        System.out.println(roundedDamageFireblast + " " + roundedActiveDamageIgnite + " " + totalPassiveDamage);
        enemy.takeDamage(totalActiveDamage);
        enemy.setPassiveDamage(ROUNDS_AFFECTED_IGNITE, totalPassiveDamage);
    }

    /**
     * returneaza damage ul activ total dat de pyromancer.
     * dupa ce se apeleaza metodata battles().
     */
    public int getTotalActiveDamage() {
        return totalActiveDamage;
    }

    /**
     * returneaza damage ul pasiv care va fi dat in mai multe runde.
     */
    public int getTotalPassiveDamage() {
        return totalPassiveDamage;
    }

    @Override
    public int getUnmodifiedDamage() {
        float fireblastDmg = INITIAL_DAMAGE_FIREBLAST + INCREASE_DAMAGE_FIREBLAST * this.getLevel();;
        float igniteActiveDmg = INITIAL_DAMAGE_IGNITE + INCREASE_PASSIVEDMG_IGNITE * this.getLevel();
        if (this.getTerrain() == VOLCANIC_TERRAIN) {
            fireblastDmg += fireblastDmg * TERRAIN_MODIFIER_VOLCANIC;
            igniteActiveDmg += igniteActiveDmg * TERRAIN_MODIFIER_VOLCANIC;
        }
        return Math.round(fireblastDmg) + Math.round(igniteActiveDmg);
    }
}
