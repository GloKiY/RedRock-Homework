package team.redrock.base;

import team.redrock.heros.King;
import team.redrock.heros.SakuraMatou;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * @Description
 * @Author 余歌
 * @Date 2018/10/31
 **/
public class Start {

    //这一轮参战的英雄
    private static List<Hero> list;

    //赢家的排名
    private Stack<Hero> rank = new Stack<>();

    //舞台
    private Stage stage;

    public static boolean win;

    //英雄的包名
    private static String packageName = "team.redrock.heros";

    public Start() {
        load();
        stage = new Stage();
    }

    //类加载 现在不用搞懂
    private void load() {
        list = new LinkedList<>();
        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packageName.replaceAll("\\.", "/"));
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                String protocol = resource.getProtocol();
                if (protocol.equalsIgnoreCase("file")) {
                    String packagePath = resource.getPath();
                    loadClass(packageName, packagePath);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //类加载 现在不用搞懂
    private void loadClass(String packageName, String packagePath) {
        File[] files = new File(packagePath).listFiles(file -> file.isDirectory() || file.getName().endsWith(".class"));
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                if (file.isDirectory()) {
                    String subPackageName = packageName + "." + fileName;
                    String subPackagePath = packagePath + "/" + fileName;
                    loadClass(subPackageName, subPackagePath);
                } else {
                    fileName = packageName + "." + fileName.substring(0, fileName.lastIndexOf("."));
                    Class<?> clazz = null;
                    try {
                        clazz = Class.forName(fileName);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (clazz != null) {
                        if (!clazz.equals(Hero.class) && Hero.class.isAssignableFrom(clazz)) {
                            Hero hero = null;
                            try {
                                hero = (Hero) clazz.newInstance();
                                list.add(hero);
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
        }
    }

    //开始战斗
    public void begin() {

        King king =new King();

        int expNeed = 100;

        SakuraMatou sakuraMatou =new SakuraMatou();

        Hero hero1 = king;

        Hero hero2 = sakuraMatou;



        while ( hero1.getLevel() <= 5 && hero2.getLevel() <= 5) {

            boolean winner = stage.battle(hero1, hero2);

            if (winner == true)    {
                hero1.addExp(5);

                System.out.println(hero1.getName()+"的经验提升了5点");
            }

            else    {

                hero2.addExp(5);

                System.out.println(hero1.getName()+"的经验提升了5点");
            }

            if (hero1.getExp() >= expNeed) {

                hero1.addLevel();

                System.out.println(hero2.getName()+"等级提升一级！！！");

                Skill.levelUp(hero1);

                hero1.resetExp();

            }
            if (hero2.getExp() >= expNeed) {

                hero2.addLevel();

                System.out.println(hero2.getName()+"等级提升一级！！！");

                Skill.levelUp(hero2);

                hero2.resetExp();

            }
            System.out.println(hero1.getName()+"现在的等级为"+hero1.getLevel()+"经验值为"+hero1.getExp());
            System.out.println(hero2.getName()+"现在的等级为"+hero2.getLevel()+"经验值为"+hero2.getExp());
        }
        System.out.println("任务顺利完成！你提升了五级，但是你一共输给了小怪"+((hero2.getLevel()-1)*20+hero2.getExp()/5)+"次，再接再厉!");
        System.out.println("目前你的三维是 力量："+hero1.getStr()+"敏捷："+hero1.getAgi()+"智力："+hero1.getInt());
    }

    public static void main(String[] args) {
        Start start = new Start();

        start.begin();

    }
}
