package team.redrock.skills;

import team.redrock.base.DamageSkill;
import team.redrock.base.buff.Blooding;
import team.redrock.base.buff.Dizzy;

import java.util.Random;

/**
 * @Description
 * @Author 余歌
 * @Date 2018/10/31
 **/
public class ironHand extends DamageSkill implements Blooding {
    private static final String NAME = "无情铁手";
    private static final int DAMAGE = 300;
    private static final int BLOODING_TIME = 1;

    public ironHand() {
        super(NAME, DAMAGE);
    }

    @Override
    public int getTime() {
        return BLOODING_TIME;
    }

}