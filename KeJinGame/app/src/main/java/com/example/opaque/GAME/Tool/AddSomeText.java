package com.example.opaque.GAME.Tool;

import java.util.ArrayList;

/**
 * Created by Opaque on 2018/5/29.
 */

public class AddSomeText {

    public static ArrayList forAddValue(ArrayList t1){
        String [] tempString = new String[5];
        /*
         "Create table Event(id integer primary key autoincrement,"+
            "name text,"+//事件名字【0】
            "char text"+//变更属性名字【1】
            "changeNum int"//变更属性【2】
            "reward int"+//事件奖励【3】
            "content text)";//事件内容【4】
         */
        tempString = changeStringArray(tempString,
                "搬到金砖","Luck","1","2000",
                "你在搬砖时看到砖堆里闪耀着金色光芒，" +
                        "于是捡起揣进兜里，转手卖出2000元；同时幸运+1");
        t1.add(tempString);//此时往t1 arrayList加入的是String[];
        tempString= null;
        //event id11
        tempString = changeStringArray(tempString,"额外加薪水","none","0","1000",
                "工头眼看工期完成得超预期，于是" +
                        "额外给你加薪1000元，你感动流泪收起了手上的菜刀");
        t1.add(tempString);
        tempString= null;
        //event id12
        tempString = changeStringArray(tempString,"被扣工资","none","0","-200",
                "你因为长得丑被扣了200块工资，就这样" );
        t1.add(tempString);
        tempString= null;
        //event id13
        tempString = changeStringArray(tempString,"被嘉奖","luck","3","2000",
                "你在打工时，发现了客人落下的钱包笔记本电脑手机，"+
                        "然后你根据钱包的联系方式联系客人，被客人感激并以2000元奖励"
        );
        t1.add(tempString);
        tempString= null;
        //event id21---4
        tempString = changeStringArray(tempString,"小发一财","none","0","1000",
        "你将客人留下的手机据为己有，然后拿去二手市场换成了钱，金钱+1000" );
        t1.add(tempString);
        tempString= null;
        //event id22---5
        tempString = changeStringArray(tempString,"不义则穷","life","1","-1000",
                "你将客人留下的手机据为己有，然后拿去二手市场，没认出老板就是客人，被修理一顿后"
        +"你在当地医院躺了1年");
        t1.add(tempString);
        tempString= null;
        //event id23---6
        tempString = changeStringArray(tempString,"领袖开智","speed","50","-1000",
                "你在偷车时遇到了那个男人，是他：窃·格瓦拉。领袖很热情，传授了你许多技巧，你花了"+
        "1000元给领袖买烟酒，你的速度提高了50点");
        t1.add(tempString);
        tempString= null;
        //event id31---7
        tempString = changeStringArray(tempString,"意外之财","none","0","1000",
                "你在偷车时.车内居然有现金！除去车的钱，你还意外得到1000元");
        t1.add(tempString);
        tempString= null;
        //event id32---8
        tempString = changeStringArray(tempString,"看守所人才","life","2","0",
                "你在偷车后，被卧底二手车市场警察捉获。进入看守所后，发现里面个个都是人才"+
        ",说话又好听，超喜欢在里面。刑满释放后， 你又故意犯事，在看守所里呆了两年");
        t1.add(tempString);
        tempString= null;
        //event id33---9
        tempString = changeStringArray(tempString,"大牛开智","wit","20","0",
                "入职后，大牛教导你:“哞哞，哞哞哞哞”你如梦初醒，给大牛道了谢，智力上升了20点");
        t1.add(tempString);
        tempString= null;
        //event id41---10
        tempString = changeStringArray(tempString,"暴打作者","power","20","0",
                "入职后，发现本游戏的作者居然和你一间公司，作者嘲讽你“又在写bug啊”，你回想"+
        "被作者拉到这个智障世界，于是暴打了一顿作者。作者许以好处，" +
                        "你才肯放过他。力量上升了20点，金钱+2000");
        t1.add(tempString);
        tempString= null;
        //event id42---11
        tempString = changeStringArray(tempString,"被作者报复","wit","-20","-1000",
                "遭遇作者后，你殴打了作者一顿，然后"+
                        "你发现你的智力下降20点，金钱-1000");
        t1.add(tempString);
        tempString= null;
        //event id43---12
        tempString = changeStringArray(tempString,"偶遇赌神","luck","2","2000",
                "赌场门口偶遇赌神的扮演者：发哥，运气+20。发哥邀请和你一起进去赌钱" +
                        "，最后发哥给了你他的签名照你当场转手卖出，又得到了赌本，金钱+2000");
        t1.add(tempString);
        tempString= null;
        //event id51---13
        tempString = changeStringArray(tempString,"贿赂荷官","none","0","1000",
                "你和贿赂了荷官，让她暗中和你通气" +
                        "除去贿赂的金钱，你赚的金钱+1000");
        t1.add(tempString);
        tempString= null;
        //event id51---14
        tempString = changeStringArray(tempString,"千王之王","life","2","-5000",
                "出千被发现，被老板和手下打手打了一顿，付出医药费5000后 "+
                        "还在医院住了2年，真的是还不如不出千，或者说不如不要赌博");
        t1.add(tempString);
        tempString= null;
        //event id51---15
        return t1;
    }
    public static String [] changeStringArray
            (String [] tempString,
             String t1, String t2,String t3,String t4,String t5){
        tempString = new String[5];
        tempString[0]= t1;
        tempString[1]= t2;
        tempString[2]= t3;
        tempString[3]= t4;
        tempString[4]= t5;
        return tempString;
    }

    public static ArrayList EndingListAdding(ArrayList end){
                String[] tempEnd = new String[9];
        tempEnd[0]="BadEnding";//0
        tempEnd[1]=("当你花光了身上最后一分钱的时候");
        tempEnd[2]=("系统不合时宜的宣判了你的结局：");
        tempEnd[3]=("\"已达成坏结局，根据设定，你将被抹杀\"");
        tempEnd[4]=("结局一：YOU DEAD");//4
                end.add(tempEnd);//No.1
        /*
        tempEnd.clear();
        tempEnd.add("TrueEnding");//0
        tempEnd.add("在最后一次强化速度后");
        tempEnd.add("你感觉自己的速度已经超越了时间");
        tempEnd.add("于是你以比香港记者还快的速度");
        tempEnd.add("跑到了正在制作的作者面前");
        tempEnd.add("打爆了他的狗头，并勒令他不得做出完全版");
        tempEnd.add("于是，你拥有了无限的寿命");
        tempEnd.add("你已解锁结局二");//7
                end.add(tempEnd);
        tempEnd.clear();
        tempEnd.add("TrueEnding");//0
        tempEnd.add("在最近一次强化力量后");
        tempEnd.add("你尝试挥了挥拳");
        tempEnd.add("但好像击破什么");
        tempEnd.add("你往破碎的地方定睛一看");
        tempEnd.add("----------------");
        tempEnd.add("你已解锁结局三");//6
                end.add(tempEnd);
        tempEnd.clear();
        tempEnd.add("TrueEnding");//0
        tempEnd.add("在最后一次强化运气后");
        tempEnd.add("好像什么都没发生");
        tempEnd.add("\"罢了，不氪这个属性就是了\"");
        tempEnd.add("而在系统的某个地方，悄然出现了bug");
        tempEnd.add("你已解锁结局三");//5
                end.add(tempEnd);
        tempEnd.clear();
        tempEnd.add("TrueEnding");//0
        tempEnd.add("体质变强后");
        tempEnd.add("如同小强一般的打不死");
        tempEnd.add("系统什么的已经不重要了");
        tempEnd.add("你已解锁结局四");//4
                end.add(tempEnd);
        tempEnd.clear();
        tempEnd.add("TrueEnding");
        tempEnd.add("在经过多次强化智力后");
        tempEnd.add("你发现了系统的严重bug");
        tempEnd.add("通过这个bug，你摆脱了这个系统");
        tempEnd.add("你已解锁结局五");//4
                end.add(tempEnd);
        tempEnd.clear();
        tempEnd.add("GoodEnding");
        tempEnd.add("\"这辣鸡玩意，也能算游戏？\"");
        tempEnd.add("\"还是上课有意思\"");
        tempEnd.add("你已解锁结局六");
                end.add(tempEnd);
                */
        return end;
    }
}
