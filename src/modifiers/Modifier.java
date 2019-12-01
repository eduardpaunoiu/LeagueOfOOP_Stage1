package modifiers;

public class Modifier {
    private float rogueModifier;
    private float knightModifier;
    private float pyromancerModifier;
    private float wizardModifier;
    private float landModifier;
    private char preferredTerrain;

    public float getRogueModifier() {
        return rogueModifier;
    }

    public void setRogueModifier(float rogueModifier) {
        this.rogueModifier = rogueModifier;
    }

    public float getKnightModifier() {
        return knightModifier;
    }

    public void setKnightModifier(float knightModifier) {
        this.knightModifier = knightModifier;
    }

    public float getPyromancerModifier() {
        return pyromancerModifier;
    }

    public void setPyromancerModifier(float pyromancerModifier) {
        this.pyromancerModifier = pyromancerModifier;
    }

    public float getWizardModifier() {
        return wizardModifier;
    }

    public void setWizardModifier(float wizardModifier) {
        this.wizardModifier = wizardModifier;
    }

    public float getLandModifier() {
        return landModifier;
    }

    public void setLandModifier(float landModifier) {
        this.landModifier = landModifier;
    }

    public char getPreferredTerrain() {
        return preferredTerrain;
    }

    public void setPreferredTerrain(char preferredTerrain) {
        this.preferredTerrain = preferredTerrain;
    }
}