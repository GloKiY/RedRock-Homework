package team.redrock.skills;


import team.redrock.base.DamageSkill;
import team.redrock.base.buff.Blooding;



public class NoxiasGuillotine extends DamageSkill implements Blooding {
    private static final String NAME = "诺克萨斯断头台";
    private static final int DAMAGE = 500;
    private static final int BLOODING_TIME = 1;

    public NoxiasGuillotine() {
        super(NAME, DAMAGE);
    }

    @Override
    public int getTime() {
        return BLOODING_TIME;
    }
}
