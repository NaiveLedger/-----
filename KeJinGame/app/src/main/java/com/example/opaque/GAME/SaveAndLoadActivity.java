package com.example.opaque.GAME;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.opaque.GAME.Bean.PlayerBean;
import com.example.opaque.GAME.DB.DataCenter;
import com.example.opaque.GAME.Tool.progressDialog;
import com.example.opaque.GAME.Tool.showDialog;

import java.text.SimpleDateFormat;

/**
 * Created by Opaque on 2018/6/2.
 */

public class SaveAndLoadActivity extends AppCompatActivity {
    TextView pageTitle;
    public static boolean isSure = false;
    String s1;
    ContentValues values;
    PlayerBean saveOrLoadPlayer;
    showDialog showDialog;
    TextView pageName;
    TextView pageLife;
    TextView pageMoney;
    TextView pageTime;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_save_and_load);
        if (MainGameActivity.currentPlayer == null) {
            MainGameActivity.currentPlayer = DataCenter.DataCenterPlayer;
        }
        Intent i = getIntent();
        s1 = i.getStringExtra("type");//type =save or load
        pageTitle = (TextView) findViewById(R.id.titleOfSL);
        s1 = s1.equals("save") ? "保存存档" : "读取存档";
        pageTitle.setText(s1);
        if (s1.equals("读取存档")) {
            DataCenter d1 = new DataCenter(
                    this, "gameDataBase.db", null, 1);
            SQLiteDatabase db2 = d1.getReadableDatabase();
            Cursor read = db2.query
                    ("player", null, null, null, null, null, null);
            Cursor readTime = db2.query
                    ("playertime", null, null, null, null, null, null);
            if (read.moveToFirst() || readTime.moveToFirst()) {

            } else {
                refreshLoadPage(read, readTime);
            }
        }
    }

    private void refreshLoadPage(Cursor readPlayer, Cursor readTime) {
        int temp = 1;
        readPlayer.moveToFirst();
        readTime.moveToFirst();
        do {
            Log.v("temp == ", "" + temp);
            initView(temp++);
            //临时变量columnIndex----记录当行各列名的索引
            int columnIndex = readPlayer.getColumnIndex("name");
            pageName.setText(readPlayer.getString(columnIndex));
            columnIndex = readPlayer.getColumnIndex("life");
            pageLife.setText(readPlayer.getInt(columnIndex) + "年");
            columnIndex = readPlayer.getColumnIndex("money");
            pageMoney.setText("" + readPlayer.getDouble(columnIndex));
            columnIndex = readTime.getColumnIndex("time");
            pageTime.setText(readTime.getString(columnIndex));

        }
        while (readPlayer.moveToNext() || readTime.moveToNext());
    }

    public void firstSaveOrLoad(View v) {

        int id = 1;//设置为表的第一条
        if (s1.equals("保存存档")) {
            saveFunction(id);
        } else {
            loadFunction(id);
        }

    }

    public void secondSaveOrLoad(View v) {
        int id = 2;//设置为表的第二条
        if (s1.equals("保存存档")) {
            saveFunction(id);
        } else {
            loadFunction(id);
        }

    }

    public void thirdSaveOrLoad(View v) {
        int id = 3;//设置为表的第三条
        if (s1.equals("保存存档")) {
            saveFunction(id);
        } else {
            loadFunction(id);
        }

    }

    private synchronized void saveFunction(int id) {
        new showDialog().showTwoButtonDiaglog(
                SaveAndLoadActivity.this, "提示", "是否存档在此位置？");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.v("确定吗?", "isSure" + isSure);
        if (isSure) {
            DataCenter d1 = new DataCenter(
                    this, "gameDataBase.db", null, 1);
            SQLiteDatabase db = d1.getWritableDatabase();
            SQLiteDatabase db2 = d1.getReadableDatabase();
            //打开数据库
            values = new ContentValues();
            SimpleDateFormat DateFormat =
                    new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String date = DateFormat.format(new java.util.Date());
            date = date.substring(5);//分割时间为月日分秒  MM-dd hh:mm:ss
            boolean isSuccess = DataCenter.savePlayerToDB(db, db2, date, id);
            Log.v("是否成功", "" + isSuccess);
            if (isSuccess) {
                showDialog = new showDialog();
                showDialog.showOnlyPositiveDialog(
                        this, "提示", "保存成功");
                refreshThisPage(db2);
            } else {
                showDialog.showOnlyPositiveDialog(
                        this, "失败", "保存失败");

            }
        }
    }

    private synchronized void loadFunction(int id) {
        new showDialog().showTwoButtonDiaglog(
                SaveAndLoadActivity.this, "提示", "是否读取该存档");
        if (isSure) {
            DataCenter d1 = new DataCenter(
                    this, "gameDataBase.db", null, 1);
            SQLiteDatabase dbAgain = d1.getReadableDatabase();
            Cursor readPlayer = dbAgain.query(
                    "player", null, null, null, null, null, null);
            if (readPlayer.moveToPosition(id)) {//疑似bug处---
                String name = readPlayer.getString(readPlayer.getColumnIndex("name"));
                int wit = readPlayer.getInt(readPlayer.getColumnIndex("wit"));
                int life = readPlayer.getInt(readPlayer.getColumnIndex("life"));
                int power = readPlayer.getInt(readPlayer.getColumnIndex("power"));
                int luck = readPlayer.getInt(readPlayer.getColumnIndex("luck"));
                int speed = readPlayer.getInt(readPlayer.getColumnIndex("speed"));
                int corporeity = readPlayer.getInt(readPlayer.getColumnIndex("corporeity"));
                double money = readPlayer.getDouble(readPlayer.getColumnIndex("money"));
                saveOrLoadPlayer =
                        new PlayerBean(id, name, wit, life, power, luck, speed, corporeity, money);
                MainGameActivity.currentPlayer = saveOrLoadPlayer;
                showDialog.showOnlyPositiveDialog(this, "提示", "读档成功");
                progressDialog p1 = new progressDialog(this);
            }
        }
    }

    private void refreshThisPage(SQLiteDatabase read) {
        Cursor cursor = read.query("player", null, null, null, null, null, null);
        Cursor cursor1 = read.query("playertime", null, null, null, null, null, null);
        if (!cursor.moveToFirst() || !cursor1.moveToFirst()) {//为空表时
        } else {
            int x = 1;
            while (cursor.moveToNext() && cursor1.moveToNext()) {
                initView(x++);
                pageTime.setText(cursor1.getString(cursor.getColumnIndex("time")) + "");
                pageName.setText(cursor.getString(cursor.getColumnIndex("name")));
                pageMoney.setText("" + cursor.getDouble(cursor.getColumnIndex("money")));
                pageLife.setText(cursor.getString(cursor1.getColumnIndex("life")) + "年");
                if (x>3){
                    break;

                }
            }
        }
    }

    private void initView(int num) {
        switch (num) {
            case 1:
                pageLife = (TextView) findViewById(R.id.saveLife1);
                pageMoney = (TextView) findViewById(R.id.saveMoney1);
                pageName = (TextView) findViewById(R.id.saveName1);
                pageTime = (TextView) findViewById(R.id.saveTime1);
                break;
            case 2:
                pageLife = (TextView) findViewById(R.id.saveLife2);
                pageMoney = (TextView) findViewById(R.id.saveMoney2);
                pageName = (TextView) findViewById(R.id.saveName2);
                pageTime = (TextView) findViewById(R.id.saveTime2);
                break;
            case 3:
                pageLife = (TextView) findViewById(R.id.saveLife3);
                pageMoney = (TextView) findViewById(R.id.saveMoney3);
                pageName = (TextView) findViewById(R.id.saveName3);
                pageTime = (TextView) findViewById(R.id.saveTime3);
                break;
            default:
                Log.d("initViewError", "num===" + num);
                break;

        }

    }

    public void backMain(View view) {
        Intent i = new Intent(this, MainGameActivity.class);
        startActivity(i);
    }
}
