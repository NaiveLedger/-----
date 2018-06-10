package com.example.opaque.GAME;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opaque.GAME.Bean.PlayerBean;
import com.example.opaque.GAME.DB.DataCenter;
import com.example.opaque.GAME.DB.PlayerDao;
import com.example.opaque.GAME.Tool.progressDialog;

/**
 * Created by Opaque on 2018/4/21.
 */

public class MainGameActivity extends AppCompatActivity {
    private String playerName;
    private ProgressBar progesss;
    private TextView progesssValue;
    private LinearLayout full;
    public static PlayerBean currentPlayer;
    TextView pageWit;
    TextView pageSpeed;
    TextView pageCorporeity;
    TextView pagePower;
    TextView pageLuck;
    TextView pageMoney;
    TextView pageRemainMonth;
    TextView pageSave;
    TextView pageLoad;
    static int yearMonth = 12;

    /**
     * 在进入本Activity时进行刷新页面数据
     */
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.new_game_main);
        progressDialog p1 = new progressDialog(this);
        Intent i = getIntent();
        if (i != null && currentPlayer == null) {
            playerName = i.getStringExtra("playerName");
            currentPlayer = DataCenter.DataCenterPlayer;
            //将初始化数据插入数据库
            //玩家第一次访问本页面时访问DataCenter对象，其中初始化人物角色
            refreshEveryChar(currentPlayer);
            TextView pageName = (TextView) findViewById(R.id.PlayerName);
            pageName.setText(playerName);

        }
        pageSave = (TextView)findViewById(R.id.main_save);
        pageLoad = (TextView)findViewById(R.id.main_load);
        pageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(MainGameActivity.this,SaveAndLoadActivity.class);
                    intent.putExtra("type","save");
                    startActivity(intent);
                }

        });
        pageLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainGameActivity.this,SaveAndLoadActivity.class);
                intent.putExtra("type","load");
                startActivity(intent);
            }

        });
        progesss = (ProgressBar) findViewById(R.id.progesss1);
        progesssValue = (TextView) findViewById(R.id.progesss_value1);
        full = (LinearLayout) findViewById(R.id.full);
        initview();
    }

    //返回时刷新所有属性；
    protected void onResume() {
        super.onResume();
        if(EndingActivity.isEnding==true){
            Intent i = getIntent();
            playerName = i.getStringExtra("playerName");
            PlayerBean p1 = new PlayerBean(playerName,5,20,5,5,5,5,1000);
            currentPlayer = p1;
            refreshEveryChar(currentPlayer);
            TextView pageName = (TextView) findViewById(R.id.PlayerName);
            pageName.setText(playerName);
            EndingActivity.isEnding=false;
        }
        if(currentPlayer.getMoney()<0
                ||currentPlayer.getLife()>=100
                ||currentPlayer.getWit()>65535
                ||currentPlayer.getLuck()>65535
                ||currentPlayer.getCorporeity()>65535
                ||currentPlayer.getPower()>65535
                ||currentPlayer.getSpeed()>3*10E8
                ){
            Intent i = new Intent(this,EndingActivity.class);
            startActivity(i);


        }
        if (yearMonth <= 0) {
            int temp = yearMonth;
            yearMonth = 12;
            yearMonth += temp;//将多余的负数减掉
            PlayerDao.SetPlayerToNextYear(currentPlayer);
            Toast.makeText(MainGameActivity.this, "当前岁数可支配月数小于1个月，已自动进入下一年", Toast.LENGTH_SHORT).show();
        }
        currentPlayer = DataCenter.DataCenterPlayer;
        refreshEveryChar(currentPlayer);
        initview();
    }

    private void initview() {
        progesss.setProgress(currentPlayer.getLife());
        progesssValue.setText(new StringBuffer().append(progesss.getProgress()).append("岁/100岁"));
        setPosWay1();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            setPos();
        }
    }

    private void setPosWay1() {
        progesssValue.post(new Runnable() {
            @Override
            public void run() {
                setPos();
            }
        });
    }

    public void setPos() {
        int w = getWindowManager().getDefaultDisplay().getWidth();
        Log.e("w=====", "" + w);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) progesssValue.getLayoutParams();
        int pro = progesss.getProgress();
        int tW = progesssValue.getWidth();
        if (w * pro / 100 + tW * 0.3 > w) {
            params.leftMargin = (int) (w - tW * 1.1);
        } else if (w * pro / 100 < tW * 0.7) {
            params.leftMargin = 0;
        } else {
            params.leftMargin = (int) (w * pro / 100 - tW * 0.7);
        }
        progesssValue.setLayoutParams(params);

    }

    public void toKeJin(View v) {
        Intent i = new Intent(this, KejingActivity.class);
        startActivity(i);
    }

    public void nextYear(View v) {
        boolean b1 = PlayerDao.SetPlayerToNextYear(currentPlayer);
        if (b1 == true) {
            yearMonth = 12;
            refreshEveryChar(currentPlayer);
            initview();
        } else {
            new AlertDialog.Builder(this).setTitle("失败")
                    .setMessage("已结局").setPositiveButton("确定", null)
                    .show();
            //跳转结局画面！！！！
        }
    }

    public void ToWork(View v) {
        if (yearMonth < 2) {
            Toast.makeText(MainGameActivity.this,
                    "你今年时间不足,请选择参加任务或进入下一年",
                    Toast.LENGTH_LONG).show();

        } else {
            Intent i = new Intent(this, WorkActivity.class);
            startActivity(i);
        }

    }

    /**
     * 进行刷新本页面的人物属性，若需要更改人物属性需 人物.set再调用刷新方法
     */
    public void refreshEveryChar(PlayerBean p1) {
        pageWit = (TextView) findViewById(R.id.main_wit);
        pageSpeed = (TextView) findViewById(R.id.main_speed);
        pageCorporeity = (TextView) findViewById(R.id.main_corporeity);
        pagePower = (TextView) findViewById(R.id.main_power);
        pageLuck = (TextView) findViewById(R.id.main_luck);
        pageMoney = (TextView) findViewById(R.id.money);
        pageRemainMonth = (TextView) findViewById(R.id.MonthRemain);
        pageCorporeity.setText("体质:" + p1.getCorporeity());
        pageLuck.setText("运气:" + p1.getLuck());
        pagePower.setText("力量:" + p1.getPower());
        pageSpeed.setText("敏捷:" + p1.getSpeed());
        pageWit.setText("智力:" + p1.getWit());
        pageMoney.setText("金钱" + p1.getMoney());
        pageRemainMonth.setText("你今年所剩时间：" + yearMonth + "个月");
    }
    public void toSave(View v){
        Intent i = new Intent(this,SaveAndLoadActivity.class);
        i.putExtra("type","save");
        startActivity(i);

    }
    public void toLoad(View v){
        Intent i = new Intent(this,SaveAndLoadActivity.class);
        i.putExtra("type","load");
        startActivity(i);
    }
}

