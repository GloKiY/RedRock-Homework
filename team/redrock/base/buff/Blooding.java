package team.redrock.base.buff;

import team.redrock.base.Hero;
import team.redrock.base.buff.Buff;

/**
 * @Description 眩晕的buff
 * @Author 余歌
 * @Date 2018/10/31
 **/
public interface Blooding extends Buff {

    int aRoundConsume = 40;

    @Override
    default String getDescription(){
        return "并且让对手流血了"+getTime()+"回合！";
    }

    @Override
    default void action(Hero hero) {
        hero.setCanDamage(true);
        hero.setCanCast(true);
        hero.setContinousDamaging(true,8000);
    }

    @Override
    default boolean isDebuff() {
        return true;
    }

    @Override
    default int getConsume(){
        return getTime()*aRoundConsume;
    }
}
