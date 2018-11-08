package team.redrock.base;

import team.redrock.base.Hero;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Description
 * @Author 余歌
 * @Date 2018/10/31
 **/
public abstract class Skill {
    private static List<Hero> list;

    private static String packageName = "team.redrock.heros";

    private final String name;

    private final int consume;//蓝耗

    protected Skill(String name, int consume) {
        this.name = name;
        this.consume = consume;
    }

    public int getConsume() {
        return consume;
    }

    public String getName() {
        return this.name;
    }

    //返回值是对释放者的血量加减
    public abstract int cast(Hero hero);

    public static void levelUp(Hero hero){
        Random a =new Random();

        int b = a.nextInt(3);

        if(b == 0)  hero.addAgi();

        else if(b == 1) hero.addStr();

        else if (b == 2)  hero.addInt();
        }
}