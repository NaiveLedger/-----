package com.example.opaque.GAME;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opaque.GAME.Bean.EventBean;
import com.example.opaque.GAME.DB.DataCenter;
import com.example.opaque.GAME.DB.EventDao;
import com.example.opaque.GAME.Tool.AddSomeText;
import com.example.opaque.GAME.Tool.RandomEventHelper;
import com.example.opaque.GAME.Tool.progressDialog;

import java.util.ArrayList;

/**
 * Created by Opaque on 2018/5/24.
 */

public class WorkActivity extends AppCompatActivity {

    TextView initTemp;
    static int normalWorkNum;//总正常工作数
    static int abnormalWorkNum;//总非正常工作数
    static int getMoneyNum;//总获得钱数
    static int loseMoneyNum;//总失去钱数
    static int GoodRandomEventNum;//总共触发好或普通随机事件
    static int BadRandomEventNum;//总共触发坏随机事件

    Intent i;
    int randomMonth;//扣除月数
    int getMoney;//获得金钱量
    int randomRate;//随机概率
    int randomEventRate;
    AlertDialog.Builder builder;
    RandomEventHelper helperNo1;
    EventBean EventBean;
    int workClass;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_work);
        initThisView();
        DataCenter t1 = new DataCenter(this, "gameDataBase.db", null, 1);
        SQLiteDatabase db = t1.getReadableDatabase();
        Cursor cursor = db.query("Event", null, null, null, null, null, null);
        if(cursor.getCount() ==0) {
            db = t1.getWritableDatabase();
            ArrayList someTextArray = new ArrayList();
            someTextArray = AddSomeText.forAddValue(someTextArray);//加载事件数据,
            // 返回的someText里每一个元素为String[]
            // 准备放置数据于gameDataBase.db
            ContentValues values = new ContentValues();
            for (int i = 0; i < someTextArray.size(); i++) {
                String[] temp = (String[]) someTextArray.get(i);
                values.put("name", temp[0]);
                values.put("attribute", temp[1]);
                values.put("changenum", temp[2]);
                values.put("moneyreward", temp[3]);
                values.put("content", temp[4]);
                db.insert("Event", null, values);
                values.clear();
            }
            Log.d("database","Insert_success");
            Log.d("database",""+cursor.getCount());
            //插入数据完成
            db.close();
        }
        Log.d("database",""+cursor.getCount());
    }

    private void initThisView() {
        progressDialog p1 = new progressDialog(this);
        initTemp = (TextView) findViewById(R.id.DoBrick);
        initTemp.setBackgroundColor(Color.parseColor("#FF4500"));
        initTemp = (TextView) findViewById(R.id.DoCode);
        initTemp.setBackgroundColor(Color.parseColor("#E6E6FA"));
        initTemp = (TextView) findViewById(R.id.DoSteal);
        initTemp.setBackgroundColor(Color.parseColor("#CD00CD"));
        initTemp = (TextView) findViewById(R.id.DoGamble);
        initTemp.setBackgroundColor(Color.parseColor("#FFF68F"));
        initTemp = (TextView) findViewById(R.id.DoWork);
        initTemp.setBackgroundColor(Color.parseColor("#F0F8FF"));
        builder = new AlertDialog.Builder(WorkActivity.this);
    }

    public void backMain_work(View V) {
        progressDialog p1 = new progressDialog(this);
        DataCenter.refreshCenterPlayerData(MainGameActivity.currentPlayer);
        i = new Intent(this, MainGameActivity.class);
        startActivity(i);

    }

    public void BeginBrick(View v) {
        if (ifHaveTime() == true) {
            //每次点击随机生成一个随机数，作为是否触发随机事件的依据
            randomEventRate = (int) (Math.random() * (100 - 0 +1) + 0);
            Log.d("Brick",""+randomEventRate);
            workClass = 1;
            //工作
            int power = MainGameActivity.currentPlayer.getPower();
            randomMonth = 1;
            randomRate = (int) (Math.random() * ((power - power/2 +1) + power/2));
            int corporeity = MainGameActivity.currentPlayer.getCorporeity();//获得速度值，作为工作依据
            getMoney = (power / 5 + corporeity / 5) *
                    (int) (Math.random() * (MainGameActivity.currentPlayer.getLuck() / 5 - 1 + 1) + 1);
            getMoney = getMoney == 0 ? (int) (Math.random() * (50 + 1)) : getMoney;//设置低保，最低可以获得1--50元

            MainGameActivity.yearMonth -= randomMonth;
            MainGameActivity.currentPlayer
                    .setMoney(MainGameActivity.currentPlayer.getMoney() + getMoney);
            getMoneyNum += getMoney;
            String s = "打工是不可能打工的，这辈子是不可能打工的。因为就算是通过" + randomMonth + "个月" +
                    "的努力搬" + randomRate + "块砖，你才赚了" + getMoney + "元。。。";

            normalWorkNum++;

            //随机事件
            startRandomEventJudge(workClass, randomEventRate);
            showDialog("打工是不可能打工的", s);
        } else if (ifHaveTime() == false) {
            Toast.makeText(WorkActivity.this,
                    "你今年时间不足1个月或已超今年,请返回主界面刷新",
                    Toast.LENGTH_LONG).show();

        }
    }

    private void startRandomEventJudge(int workClass, int randomEventRate) {
            SQLiteDatabase db = new DataCenter
                (this, "gameDataBase.db", null, 1)
                .getReadableDatabase();
        //打开数据库
        ArrayList tempArr = new ArrayList();
        helperNo1 = new RandomEventHelper();
        int eventType = helperNo1.isRandomEventActived(workClass, randomEventRate);
        Log.d("eventType","===="+eventType);
        //eventType详细种类在RandomEventHelper注释中，
        switch (eventType) {
            case 11:
                tempArr = helperNo1.brickRandomEvent(tempArr, db, 1);
                GoodRandomEventNum++;
                //从数据库获取数据，存入tempArr中；传入的eventNum为数据库的第x行
                break;
            case 12:
                tempArr = helperNo1.brickRandomEvent(tempArr, db, 2);
                GoodRandomEventNum++;
                break;
            case 13:
                tempArr = helperNo1.brickRandomEvent(tempArr, db, 3);
                BadRandomEventNum++;
                break;
            case 21:
                tempArr = helperNo1.brickRandomEvent(tempArr, db, 4);
                GoodRandomEventNum++;
                break;
            case 22:
                tempArr = helperNo1.brickRandomEvent(tempArr, db, 5);
                GoodRandomEventNum++;
                break;
            case 23:
                tempArr = helperNo1.brickRandomEvent(tempArr, db, 6);
                BadRandomEventNum++;
                break;
            case 31:
                tempArr = helperNo1.brickRandomEvent(tempArr, db, 7);
                GoodRandomEventNum++;
                break;
            case 32:
                tempArr = helperNo1.brickRandomEvent(tempArr, db, 8);
                GoodRandomEventNum++;
                break;
            case 33:
                tempArr = helperNo1.brickRandomEvent(tempArr, db, 9);
                BadRandomEventNum++;
                break;
            case 41:
                tempArr = helperNo1.brickRandomEvent(tempArr, db, 10);
                GoodRandomEventNum++;
                break;
            case 42:
                tempArr = helperNo1.brickRandomEvent(tempArr, db, 11);
                GoodRandomEventNum++;
                break;
            case 43:
                tempArr = helperNo1.brickRandomEvent(tempArr, db, 12);
                BadRandomEventNum++;
                break;
            case 51:
                tempArr = helperNo1.brickRandomEvent(tempArr, db, 13);
                GoodRandomEventNum++;
                break;
            case 52:
                tempArr = helperNo1.brickRandomEvent(tempArr, db, 14);
                GoodRandomEventNum++;
                break;
            case 53:
                tempArr = helperNo1.brickRandomEvent(tempArr, db, 15);
                BadRandomEventNum++;
                break;
            case -1:
                showDialog("wow", "你是个有知识水平的py");
                break;
            case 101:
                break;
        }
        if (eventType!=101) {
            EventBean = InitEventBean(tempArr);
            randomEventRewardDao(EventBean);
        }
    }

    public void BeginSteal(View v) {
        String content = new String();
        String title = new String();
        if (ifHaveTime() == true) {
            randomEventRate = (int) (Math.random() * (100 - 0+1) + 0);
            workClass=3;
            int luck = MainGameActivity.currentPlayer.getLuck();//获得幸运值，作为工作依据
            randomRate = (int) (Math.random() * (200 - 0 + 1));//作为实际成功的基本几率，通过随机生成
            int speed = MainGameActivity.currentPlayer.getSpeed();//获得速度值，作为工作依据
            int successRate = randomRate - luck / 100 - speed / 100;
            //开始判断是否成功偷车
            if (successRate > 150) {
                randomMonth = 12;
                getMoney = -((int) ((Math.random() * (luck + 1)) * Math.random()));
                content = "你在本次偷车中被民警当场抓获，被关进看守所" +
                        randomMonth + "个月并处以罚金" + -getMoney + "元";
                loseMoneyNum += getMoney;
                title ="偷车失败";
            } else if (successRate > 100) {
                randomMonth = (int) (Math.random() * (3 - 1 + 1) + 1);
                getMoney = (int) ((Math.random() * (speed + 1)) * Math.random());
            } else if (successRate > 50) {
                randomMonth = (int) (Math.random() * (6 - 1 + 1) + 1);
                getMoney = (int) ((Math.random() * (speed + 1)) * Math.random());
            } else if (luck > 0) {
                randomMonth = (int) (Math.random() * (12 - 3 + 1) + 3);
            }
            MainGameActivity.yearMonth -= randomMonth;
            MainGameActivity.currentPlayer.setMoney(MainGameActivity.currentPlayer.getMoney() + getMoney);
            getMoneyNum += getMoney;
            if (successRate <= 150) {
                content = "你在蹲点追踪了车主 " +
                        randomMonth + " 个月，并于月黑风高的晚上成功偷走，转手卖出获益 " + getMoney + " 元";
                title = "靠偷才能维持生活";
            }
            abnormalWorkNum++;
            startRandomEventJudge(workClass, randomEventRate);
            showDialog(title,content);
        } else if (ifHaveTime() == false) {
            Toast.makeText(WorkActivity.this,
                    "你今年时间不足一个月,请返回主界面进入下一年",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void BeginWork(View v) {
        if (ifHaveTime() == true) {
            randomEventRate = (int) (Math.random() * (100 - 0+1) + 0);
                    workClass=2;
            int corporeity = MainGameActivity.currentPlayer.getCorporeity();
            randomMonth = 3;
            randomRate = (int) (Math.random() * (corporeity - corporeity / 2 + 1) + corporeity / 2);
            getMoney = corporeity / 100 *
                    (int) (Math.random() * (MainGameActivity.currentPlayer.getLuck() / 10 - 1 + 1) + 1);
            MainGameActivity.yearMonth -= randomMonth;
            MainGameActivity.currentPlayer
                    .setMoney(MainGameActivity.currentPlayer.getMoney() + getMoney);
            getMoneyNum += getMoney;
            String s = "打工是不可能打工的，这辈子是不可能打工的。因为就算是通过" + randomMonth +
                    "个月的努力工作了" + randomRate + "小时，你才赚了" + getMoney + "元。。。";

            startRandomEventJudge(workClass, randomEventRate);

            showDialog("打工是不可能打工的", s);
            normalWorkNum++;

        } else if (ifHaveTime() == false) {
            Toast.makeText(WorkActivity.this,
                    "你今年时间不足1个月,请返回主界面进入下一年",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void BeginCode(View v) {
        if (ifHaveTime() == true) {
            String title = new String();
            String content = new String();
            randomEventRate = (int) (Math.random() * (100 - 0+1) + 0);
            workClass=4;
            int wit = MainGameActivity.currentPlayer.getWit();
            randomMonth = 6;
            randomRate = (int) (Math.random() *
                    (1000 / wit - 500 / wit + 1) + 500 / wit);//bug量
            if (wit < 1000) {
                getMoney = (int) (Math.random() * (wit - 1 + 1) + 1);
                content = "你入职了多一点安全互联网有限公司，进行创业项目，一顿操作猛如虎,你写出了" +
                        randomRate + "个BUG，在拿到工资" + getMoney + "后，公司倒闭了。。。";
                title = "天有不测风云";
            } else if (wit > 1000) {
                randomRate = (int) (Math.random() *
                        (500 / wit - 250 / wit + 1) + 250 / wit);//bug量
                content = "你入职了亡义公司，进行项目一个主要功能编写，你写出了" +
                        randomRate + "个BUG。在项目上线后爆出还有好几个你写的重大bug，" +
                        "给项目组造成不小的麻烦后，拿到工资和违约金共" + getMoney + "元后" +
                        "，公司开了你";
                title = "人生坎坷";
            }
            else if (wit > 2000) {
                randomRate = (int) (Math.random() *
                        (40000/wit - 20000/wit + 1) + 20000/wit   );//bug量
                content = "你入职了败毒公司，进行项目多个主要功能编写，开玩笑大牛级别了，仅仅写了" +
                        randomRate + "个BUG。但忽然组长改了好几个需求" +
                        "你一怒之下，删库离职，拿到工资" + getMoney + "元";
                title = "睚眦必报";
            }
            MainGameActivity.yearMonth -= randomMonth;
            MainGameActivity.currentPlayer
                    .setMoney(MainGameActivity.currentPlayer.getMoney() + getMoney);
            getMoneyNum += getMoney;
            startRandomEventJudge(workClass, randomEventRate);
            showDialog(title, content);


        } else if (ifHaveTime() == false) {
            Toast.makeText(WorkActivity.this,
                    "你今年时间不足1个月,请返回主界面进入下一年",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void BeginGamble(View v) {
        String content = new String();
        String title = new String();
        if (ifHaveTime() == true) {
            randomEventRate = (int) (Math.random() * (100 - 0+1) + 0);
            workClass=5;
            int luck = MainGameActivity.currentPlayer.getLuck();//获得幸运值，作为工作依据
            randomRate = (int) (Math.random() * (100 - 0 + 1));//作为实际成功的基本几率，通过随机生成
            int successRate = randomRate - luck / 100;
            if (successRate > 75) {
                randomMonth = (int) (Math.random() * (3 - 1 + 1) + 1);
                getMoney = ((int) (Math.random() * (luck - luck * successRate / 100 + 1)
                        + luck * successRate / 100) * luck * successRate / 100);
                content = "你在赌场大获全胜，整整" +
                        randomMonth + "个月你都混迹在赌场，一共赚了 " + getMoney + " 元!";
                getMoneyNum += getMoney;
                title = "赌神附体";
            } else if (successRate > 50) {
                randomMonth = (int) (Math.random() * (6 - 3 + 1) + 3);
                getMoney = ((int) (Math.random() * (luck - luck * successRate / 100 + 1)
                        + luck * successRate / 100) * luck * successRate / 100);
                getMoneyNum += getMoney;
                content = "你在赌场大获全胜，整整" +
                        randomMonth + "个月你都混迹在赌场，一共赚了 " + getMoney + " 元!";
                title = "赌神附体";
            } else if (successRate > 25) {
                randomMonth = (int) (Math.random() * (9 - 3 + 1) + 3);
                getMoney = -((int) (Math.random() * (luck - luck * successRate / 100 + 1)
                        + luck * successRate / 100) * luck * successRate / 100);
                content = "混级赌场上头了 " +
                        randomMonth + " 个月，你输了 " + -getMoney + " 元，发现自己似乎不适合靠运气";
                getMoneyNum += getMoney;
                title = "运气总有起起落落";
            } else if (luck > 0) {
                randomMonth = (int) (Math.random() * (12 - 9 + 1) + 9);
                getMoney = -((int) (Math.random() * (luck - luck * successRate / 100 + 1)
                        + luck * successRate / 100) * luck * successRate / 100);
                getMoneyNum += getMoney;
                content = "混级赌场上头了 " +
                        randomMonth + " 个月，你输了 " + -getMoney + " 元，发现自己似乎不适合靠运气";
                title = "运气总有起起落落";
            }
            MainGameActivity.yearMonth -= randomMonth;
            MainGameActivity.currentPlayer.setMoney(
                    MainGameActivity.currentPlayer.getMoney() + getMoney);
            startRandomEventJudge(workClass, randomEventRate);
            showDialog(title, content);
            abnormalWorkNum++;
        } else if (ifHaveTime() == false) {
            Toast.makeText(WorkActivity.this,
                    "你今年时间不足1个月,请返回主界面进入下一年",
                    Toast.LENGTH_LONG).show();

        }
    }

    public boolean ifHaveTime() {
        if (MainGameActivity.yearMonth <= 1 || MainGameActivity.yearMonth > 12) {
            return false;
        } else {
            return true;
        }
    }

    public void showDialog(String title, String content) {
        new AlertDialog.Builder(this).setTitle(title)
                .setMessage(content).setPositiveButton("确定", null)
                .show();

    }

    private void randomEventRewardDao(EventBean Eventbean) {
        MainGameActivity.currentPlayer
                .setMoney(MainGameActivity.currentPlayer.getMoney()
                        + Eventbean.getMoneyReward());
        String attribute = Eventbean.getAttribute();
        int changeNum = Eventbean.getChangeNum();
        EventDao.StrengthAttributeByString(attribute, changeNum);
        showDialog("随机事件：" + Eventbean.getName(), "" + Eventbean.getContent());
    }

    public EventBean InitEventBean(ArrayList tempArr) {
        EventBean e1 = new EventBean((String) tempArr.get(0),
                (String) tempArr.get(1),
                (int) tempArr.get(2),
                (int) tempArr.get(3),
                (String) tempArr.get(4));
        return e1;
    }
}



