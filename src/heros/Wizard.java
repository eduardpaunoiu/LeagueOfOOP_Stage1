package heros;

import modifiers.Modifier;

import static helpers.Constants.DEFLECT_PROCENT;
import static helpers.Constants.DESERT_TERRAIN;
import static helpers.Constants.DRAIN_PROCENT;
import static helpers.Constants.INCREASE_DEFLECT_PROCENT;
import static helpers.Constants.INCREASE_DRAIN_PROCENT;
import static helpers.Constants.INITIAL_HP_PYROMANCER;
import static helpers.Constants.INITIAL_HP_WIZARD;
import static helpers.Constants.KNIGHT_MODIFIER_DEFLECT;
import static helpers.Constants.KNIGHT_MODIFIER_DRAIN;
import static helpers.Constants.LEVEL_UP_HP_WIZARD;
import static helpers.Constants.MAX_DEFLECT_PROCENT;
import static helpers.Constants.MAX_HP_COEF_DRAIN;
import static helpers.Constants.PYROMANCER_MODIFIER_DEFLECT;
import static helpers.Constants.PYROMANCER_MODIFIER_DRAIN;
import static helpers.Constants.ROGUE_MODIFER_DEFLECT;
import static helpers.Constants.ROGUE_MODIFIER_DRAIN;
import static helpers.Constants.TERRAIN_MODIFIER_DESERT;
import static helpers.Constants.WIZARD_MODIFIER_DRAIN;

public class Wizard extends Hero {

    private Modifier drainModifier = new Modifier();
    private Modifier deflectModifier = new Modifier();

    public Wizard(final int xPos, final int yPos, final char land) {
        super(xPos, yPos, land);
        super.setCurrentHP(INITIAL_HP_WIZARD);
        super.setLevelUpHP(LEVEL_UP_HP_WIZARD);
        super.setMaxHP(INITIAL_HP_WIZARD);
    }

    @Override
    public void prepareHero() {
        drainModifier.setPyromancerModifier(PYROMANCER_MODIFIER_DRAIN);
        drainModifier.setKnightModifier(KNIGHT_MODIFIER_DRAIN);
        drainModifier.setWizardModifier(WIZARD_MODIFIER_DRAIN);
        drainModifier.setRogueModifier(ROGUE_MODIFIER_DRAIN);
        drainModifier.setPreferredTerrain(DESERT_TERRAIN);
        drainModifier.setLandModifier(TERRAIN_MODIFIER_DESERT);

        deflectModifier.setPyromancerModifier(PYROMANCER_MODIFIER_DEFLECT);
        deflectModifier.setKnightModifier(KNIGHT_MODIFIER_DEFLECT);
        deflectModifier.setRogueModifier(ROGUE_MODIFER_DEFLECT);

    }

    @Override
    public void battles(final Hero enemy) {
        this.prepareHero();
        float drainProcent = DRAIN_PROCENT;
        float deflectProcent = DEFLECT_PROCENT;
        float deflectDamage = 0f;
       // System.out.println("ceva1 " + drainProcent + " " + deflectProcent + " " + deflectDamage);
        drainProcent += INCREASE_DRAIN_PROCENT * this.getLevel();
        deflectProcent += INCREASE_DEFLECT_PROCENT * this.getLevel();
        if (deflectProcent >= MAX_DEFLECT_PROCENT) {
            deflectProcent = MAX_DEFLECT_PROCENT;
        }
        float hp_limit = Math.min(MAX_HP_COEF_DRAIN * enemy.getMaxHP(),
                enemy.getCurrentHP());
       System.out.println("hp_limit " + hp_limit);
        if (enemy instanceof Rogue) {
            drainProcent += drainProcent * drainModifier.getRogueModifier();
            deflectProcent += deflectProcent * deflectModifier.getRogueModifier();
            deflectDamage = deflectProcent * enemy.getUnmodifiedDamage();
        }
        if (enemy instanceof Wizard) {
            drainProcent += drainProcent * drainModifier.getWizardModifier();
            deflectDamage = 0;
        }
        if (enemy instanceof Pyromancer) {
            drainProcent += drainProcent * drainModifier.getPyromancerModifier();
            deflectProcent += deflectProcent * deflectModifier.getPyromancerModifier();
            deflectDamage = deflectProcent * enemy.getUnmodifiedDamage();
        }
        if (enemy instanceof Knight) {
            drainProcent += drainProcent * drainModifier.getKnightModifier();
            deflectProcent += deflectProcent * deflectModifier.getKnightModifier();
            deflectDamage = deflectProcent * enemy.getUnmodifiedDamage();
        }
        float drainDamage = drainProcent * hp_limit;
        System.out.println("drain damage" + drainDamage);

        if (this.getTerrain() == DESERT_TERRAIN) {
            drainDamage += drainDamage * drainModifier.getLandModifier();
            deflectDamage += deflectDamage * drainModifier.getLandModifier();
        }

        System.out.println("ceva eee" + drainProcent + " " + deflectProcent + " " + deflectDamage + " " + drainDamage);
        int roundedDrainDamage = Math.round(drainDamage);
        int roundedDeflectDamage = Math.round(deflectDamage);
        System.out.println("rotunjite" + roundedDrainDamage + " " + roundedDeflectDamage);
        int totalDamage = roundedDrainDamage + roundedDeflectDamage;
        System.out.println("damage total " + totalDamage);
        enemy.takeDamage(totalDamage);
        System.out.println(enemy.getCurrentHP());
    }
}