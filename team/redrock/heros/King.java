package team.redrock.heros;

import team.redrock.base.DamageSkill;
import team.redrock.base.Hero;
import team.redrock.base.Skill;
import team.redrock.skills.*;

public class King extends Hero {
    private final static String NAME = "王元新";//你的名字
    private final static String STUID = "2017212029";//学号
    private  static int STR = 299;//力量
    private  static int INT = 100;//智力
    private  static int AGI = 101;//敏捷
    private  static int EXP = 0;//经验值
    private  static int LEVEL = 1;//等级

    public King() {
        //在构造方法里第一行写上调用父类的构造方法
        //第一个参数是你的名字，第二个是学号，后面依次是力量、敏捷、智力。
        super(NAME, STUID, STR, AGI, INT, EXP, LEVEL);
        //自己搞两个技能
        Skill ironhand = new ironHand();
        Skill Noxiasguillotine = new NoxiasGuillotine();
        super.addSkill(ironhand);
        super.addSkill(Noxiasguillotine);
    }
}