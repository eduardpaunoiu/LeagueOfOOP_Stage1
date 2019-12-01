package modifiers;

public class Modifier {
    private float rogueModifier;
    private float knightModifier;
    private float pyromancerModifier;
    private float wizardModifier;
    private float landModifier;
    private char preferredTerrain;

    /**
     * @return modiferul pentru rogue in functie de abilitatea implementata.
     */
    public float getRogueModifier() {
        return rogueModifier;
    }

    /**
     * setez modifierul in functie de abilitatea implementata.
     */
    public void setRogueModifier(final float rogueModifier) {
        this.rogueModifier = rogueModifier;
    }
    /**
     * @return modiferul pentru knight in functie de abilitatea implementata.
     */
    public float getKnightModifier() {
        return knightModifier;
    }
    /**
     * setez modifierul in functie de abilitatea implementata.
     */
    public void setKnightModifier(final float knightModifier) {
        this.knightModifier = knightModifier;
    }
    /**
     * @return modiferul pentru pyromancer in functie de abilitatea implementata.
     */
    public float getPyromancerModifier() {
        return pyromancerModifier;
    }
    /**
     * setez modifierul in functie de abilitatea implementata.
     */
    public void setPyromancerModifier(final float pyromancerModifier) {
        this.pyromancerModifier = pyromancerModifier;
    }
    /**
     * @return modiferul pentru wizard in functie de abilitatea implementata.
     */
    public float getWizardModifier() {
        return wizardModifier;
    }
    /**
     * setez modifierul in functie de abilitatea implementata.
     */
    public void setWizardModifier(final float wizardModifier) {
        this.wizardModifier = wizardModifier;
    }
    /**
     * @return modiferul pentru land in functie de tipul de teren pe care se afla eroul.
     */
    public float getLandModifier() {
        return landModifier;
    }
    /**
     * setez modifierul in functie de abilitatea implementata.
     */
    public void setLandModifier(final float landModifier) {
        this.landModifier = landModifier;
    }

    /**
     * setez modifierul in functie de abilitatea implementata.
     */
    public void setPreferredTerrain(final char preferredTerrain) {
        this.preferredTerrain = preferredTerrain;
    }
}
