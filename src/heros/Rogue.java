package heros;

import modifiers.Modifier;

import static helpers.Constants.BACKSTAB_COMBO;
import static helpers.Constants.EVERY_THREE_ROUNDS;
import static helpers.Constants.INCREASE_DAMAGE_BACKSTAB;
import static helpers.Constants.INCREASE_DAMAGE_PARALYSIS;
import static helpers.Constants.INITIAL_DAMAGE_BACKSTAB;
import static helpers.Constants.INITIAL_HP_PYROMANCER;
import static helpers.Constants.INITIAL_HP_ROGUE;
import static helpers.Constants.INITIAL_PARALYSIS_DAMAGE;
import static helpers.Constants.KNIGHT_MODIFIER_BACKSTAB;
import static helpers.Constants.KNIGHT_MODIFIER_PARALYSIS;
import static helpers.Constants.LEVEL_UP_HP_ROGUE;
import static helpers.Constants.OVERTIME_ROUNDS;
import static helpers.Constants.PYROMANCER_MODIFIER_BACKSTAB;
import static helpers.Constants.PYROMANCER_MODIFIER_PARALYSIS;
import static helpers.Constants.ROGUE_MODIFIER_BACKSTAB;
import static helpers.Constants.ROGUE_MODIFIER_PARALYSIS;
import static helpers.Constants.TERRAIN_MODIFIER_WOODS;
import static helpers.Constants.WIZARD_MODIFIER_BACKSTAB;
import static helpers.Constants.WIZARD_MODIFIER_PARALYSIS;
import static helpers.Constants.WOODS_OVERTIME_ROUNDS;
import static helpers.Constants.WOODS_TERRAIN;

public class Rogue extends Hero {

    private int count = 3;
    private boolean firstStrike = false;
    private Modifier backstabModifier = new Modifier();
    private Modifier paralysisModifier = new Modifier();
    public Rogue(final int xPos, final int yPos, final char land) {
        super(xPos, yPos, land);
        super.setCurrentHP(INITIAL_HP_ROGUE);
        super.setLevelUpHP(LEVEL_UP_HP_ROGUE);
        super.setMaxHP(INITIAL_HP_ROGUE);
    }

    @Override
    public void prepareHero() {

        backstabModifier.setRogueModifier(ROGUE_MODIFIER_BACKSTAB);
        backstabModifier.setKnightModifier(KNIGHT_MODIFIER_BACKSTAB);
        backstabModifier.setPyromancerModifier(PYROMANCER_MODIFIER_BACKSTAB);
        backstabModifier.setWizardModifier(WIZARD_MODIFIER_BACKSTAB);
        backstabModifier.setPreferredTerrain(WOODS_TERRAIN);
        backstabModifier.setLandModifier(TERRAIN_MODIFIER_WOODS);

        paralysisModifier.setWizardModifier(WIZARD_MODIFIER_PARALYSIS);
        paralysisModifier.setPyromancerModifier(PYROMANCER_MODIFIER_PARALYSIS);
        paralysisModifier.setKnightModifier(KNIGHT_MODIFIER_PARALYSIS);
        paralysisModifier.setRogueModifier(ROGUE_MODIFIER_PARALYSIS);
        paralysisModifier.setLandModifier(TERRAIN_MODIFIER_WOODS);
    }

    @Override
    public void battles(Hero enemy) {
        this.prepareHero();
        int paralysedRoundsCount = 0;
        float backstabDamage = INITIAL_DAMAGE_BACKSTAB
                + INCREASE_DAMAGE_BACKSTAB * this.getLevel();
        float paralysisActiveDamage = INITIAL_PARALYSIS_DAMAGE
                + INCREASE_DAMAGE_PARALYSIS * this.getLevel();
        float paralysisPassiveDamage = INITIAL_PARALYSIS_DAMAGE
                + INCREASE_DAMAGE_PARALYSIS * this.getLevel();
        System.out.println(backstabDamage + " " + paralysisActiveDamage);
        firstStrike = true;
        if (count % EVERY_THREE_ROUNDS == 0) {
            if (this.getTerrain() == WOODS_TERRAIN) {
                backstabDamage = backstabDamage * BACKSTAB_COMBO;
                count = 0;
            }
        } else {
            if (count == EVERY_THREE_ROUNDS) {
                count = 0;
            }
        }
        System.out.println(backstabDamage + " " + paralysisActiveDamage);
        this.count++;
        if (this.getTerrain() == 'W') {
            paralysedRoundsCount = WOODS_OVERTIME_ROUNDS;
            backstabDamage += backstabDamage * backstabModifier.getLandModifier();
            paralysisActiveDamage += paralysisActiveDamage * paralysisModifier.getLandModifier();
            paralysisPassiveDamage += paralysisPassiveDamage * paralysisModifier.getLandModifier();

        } else {
            paralysedRoundsCount = OVERTIME_ROUNDS;
        }
        if (enemy instanceof Pyromancer) {
            backstabDamage += backstabDamage * backstabModifier.getPyromancerModifier();
            paralysisActiveDamage += paralysisActiveDamage * paralysisModifier.getPyromancerModifier();
            paralysisPassiveDamage += paralysisPassiveDamage * paralysisModifier.getPyromancerModifier();
        }
        if (enemy instanceof Wizard) {
            backstabDamage += backstabDamage * backstabModifier.getWizardModifier();
            paralysisActiveDamage += paralysisActiveDamage * paralysisModifier.getWizardModifier();
            paralysisPassiveDamage += paralysisPassiveDamage * paralysisModifier.getWizardModifier();
        }
        if (enemy instanceof  Knight) {
            backstabDamage += backstabDamage * backstabModifier.getKnightModifier();
            paralysisActiveDamage += paralysisActiveDamage * paralysisModifier.getKnightModifier();
            paralysisPassiveDamage += paralysisPassiveDamage * paralysisModifier.getKnightModifier();
        }
        if (enemy instanceof Rogue) {
            backstabDamage += backstabDamage * backstabModifier.getRogueModifier();
            paralysisActiveDamage += paralysisActiveDamage * paralysisModifier.getRogueModifier();
            paralysisPassiveDamage += paralysisPassiveDamage * paralysisModifier.getRogueModifier();
        }
        int totalActiveDamage = Math.round(backstabDamage) + Math.round(paralysisActiveDamage);
        int totalPassiveDamage = Math.round(paralysisPassiveDamage);
        enemy.takeDamage(totalActiveDamage);
        enemy.setPassiveDamage(paralysedRoundsCount, totalPassiveDamage);
        enemy.immobilizeEnemy(paralysedRoundsCount);

    }

    public int getUnmodifiedDamage() {
        float backstabDmg = INITIAL_DAMAGE_BACKSTAB
                + INCREASE_DAMAGE_BACKSTAB * this.getLevel();
        float paralysisActiveDmg = INITIAL_PARALYSIS_DAMAGE
                + INCREASE_DAMAGE_PARALYSIS * this.getLevel();
        if (firstStrike ) {
            if (count == 1 && this.getTerrain() == 'W') {
                backstabDmg = backstabDmg * BACKSTAB_COMBO; }

        } else {
            if (count == EVERY_THREE_ROUNDS && this.getTerrain() == 'W') {
                backstabDmg += backstabDmg * BACKSTAB_COMBO;
            }
        }
        System.out.println("EDDD1 " + backstabDmg + " " + paralysisActiveDmg);
        if (this.getTerrain() == 'W') {
            backstabDmg += backstabDmg * backstabModifier.getLandModifier();
            paralysisActiveDmg += paralysisActiveDmg * paralysisModifier.getLandModifier();
        }
        System.out.println("EDDD " + backstabDmg + " " + paralysisActiveDmg);
        return Math.round(backstabDmg) + Math.round(paralysisActiveDmg);
    }
}
