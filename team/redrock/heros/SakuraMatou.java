package team.redrock.heros;

import team.redrock.base.DamageSkill;
import team.redrock.base.Hero;
import team.redrock.base.Skill;
import team.redrock.skills.NoxiasGuillotine;

/**
 * @Description
 * @Author 余歌
 * @Date 2018/11/1
 **/
public class SakuraMatou extends Hero {
    private final static String NAME = "小怪一号";//你的名字
    private final static String STUID = "123456";//学号
    private static int STR = 100;//力量
    private static int INT = 100;//智力
    private static int AGI = 100;//敏捷
    private  static int EXP = 0;//经验值
    private  static int LEVEL = 1;//等级

    public SakuraMatou() {
        //在构造方法里第一行写上调用父类的构造方法
        //第一个参数是你的名字，第二个是学号，后面依次是力量、敏捷、智力。
        super(NAME, STUID, STR, AGI, INT, EXP, LEVEL);
        //自己搞两个技能
        Skill shadowRaze = new DamageSkill("嘤嘤嘤", 50);
        super.addSkill(shadowRaze);
        Skill Noxiasguillotine = new NoxiasGuillotine();
        super.addSkill(Noxiasguillotine);
    }
}
