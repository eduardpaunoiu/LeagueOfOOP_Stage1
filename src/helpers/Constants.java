package helpers;

public final class Constants {
    private Constants() {
    }
    public static final int INITIAL_XP = 250;
    public static final int LEVEL_UP_XP = 50;
    public static final int XP_POINTS = 200;
    public static final int XP_POINTS_COEFFICIENT = 40;

    public static final int INITIAL_HP_PYROMANCER = 500;
    public static final int LEVEL_UP_HP_PYROMANCER = 50;
    public static final int INITIAL_DAMAGE_FIREBLAST = 350;
    public static final int INCREASE_DAMAGE_FIREBLAST = 50;
    public static final int INITIAL_DAMAGE_IGNITE = 150;
    public static final int INCREASE_DAMAGE_IGNITE = 20;
    public static final int PASSIVE_DAMAGE_IGNITE = 50;
    public static final int INCREASE_PASSIVEDMG_IGNITE = 30;
    public static final float ROGUE_MODIFIER_FIREBLAST = -0.20f;
    public static final float KNIGHT_MODIFIER_FIREBLAST = 0.20f;
    public static final float PYROMANCER_MODIFIER_FIREBLAST = -0.10f;
    public static final float WIZARD_MODIFIER_FIREBLAST = 0.05f;
    public static final float TERRAIN_MODIFIER_VOLCANIC = 0.25f;
    public static final char VOLCANIC_TERRAIN = 'V';
    public static final int ROUNDS_AFFECTED_IGNITE = 2;

    public static final int INITIAL_HP_KNIGHT = 900;
    public static final int LEVEL_UP_HP_KNIGHT = 80;
    public static final int INITIAL_DAMAGE_EXECUTE = 200;
    public static final int INCREASE_DAMAGE_EXECUTE = 30;
    public static final float ROGUE_MODIFIER_EXECUTE = 0.15f;
    public static final float KNIGHT_MODIFIER_EXECUTE = 0f;
    public static final float PYROMANCER_MODIFIER_EXECUTE = 0.10f;
    public static final float WIZARD_MODIFIER_EXECUTE = -0.20f;
    public static final int INITIAL_DAMAGE_SLAM = 100;
    public static final int INCREASE_DAMAGE_SLAM = 40;
    public static final float ROGUE_MODIFIER_SLAM = -0.20f;
    public static final float KNIGHT_MODIFIER_SLAM = 0.20f;
    public static final float PYROMANCER_MODIFIER_SLAM = -0.10f;
    public static final float WIZARD_MODIFIER_SLAM = 0.05f;
    public static final float TERRAIN_MODIFIER_LAND = 0.15f;
    public static final char LAND_TERRAIN = 'L';
    public static final float HP_LIMIT = 0.20f;
    public static final float HP_LIMIT_BOOSTER = 0.01f;
    public static final int HP_LIMIT_MAXLEVEL = 20;

    public static final int INITIAL_HP_WIZARD = 400;
    public static final int LEVEL_UP_HP_WIZARD = 30;
    public static final float DRAIN_PROCENT = 0.20f;
    public static final float INCREASE_DRAIN_PROCENT = 0.05f;
    public static final float ROGUE_MODIFIER_DRAIN = -0.20f;
    public static final float KNIGHT_MODIFIER_DRAIN = 0.20f;
    public static final float PYROMANCER_MODIFIER_DRAIN = -0.10f;
    public static final float WIZARD_MODIFIER_DRAIN = 0.05f;
    public static final float DEFLECT_PROCENT = 0.35f;
    public static final float MAX_DEFLECT_PROCENT = 0.70f;
    public static final float INCREASE_DEFLECT_PROCENT = 0.02f;
    public static final float ROGUE_MODIFER_DEFLECT = 0.20f;
    public static final float KNIGHT_MODIFIER_DEFLECT = 0.40f;
    public static final float PYROMANCER_MODIFIER_DEFLECT = 0.30f;
    public static final float TERRAIN_MODIFIER_DESERT = 0.10f;
    public static final char DESERT_TERRAIN = 'D';
    public static final float MAX_HP_COEF_DRAIN = 0.30f;



    public static final int INITIAL_HP_ROGUE = 600;
    public static final int LEVEL_UP_HP_ROGUE = 40;
    public static final int INITIAL_DAMAGE_BACKSTAB = 200;
    public static final int INCREASE_DAMAGE_BACKSTAB = 20;
    public static final float BACKSTAB_COMBO = 1.5f;
    public static final int EVERY_THREE_ROUNDS = 3;
    public static final float ROGUE_MODIFIER_BACKSTAB = 0.20f;
    public static final float KNIGHT_MODIFIER_BACKSTAB = -0.10f;
    public static final float PYROMANCER_MODIFIER_BACKSTAB = 0.25f;
    public static final float WIZARD_MODIFIER_BACKSTAB = 0.25f;
    public static final int INITIAL_PARALYSIS_DAMAGE = 40;
    public static final int INCREASE_DAMAGE_PARALYSIS = 10;
    public static final int OVERTIME_ROUNDS = 3;
    public static final int WOODS_OVERTIME_ROUNDS = 6;
    public static final float ROGUE_MODIFIER_PARALYSIS = -0.10f;
    public static final float KNIGHT_MODIFIER_PARALYSIS = -0.20f;
    public static final float PYROMANCER_MODIFIER_PARALYSIS = 0.20f;
    public static final float WIZARD_MODIFIER_PARALYSIS = 0.25f;
    public static final char WOODS_TERRAIN = 'W';
    public static final float TERRAIN_MODIFIER_WOODS = 0.15f;

}
