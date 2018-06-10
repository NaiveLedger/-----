package com.example.opaque.GAME.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.opaque.GAME.Bean.PlayerBean;
import com.example.opaque.GAME.MainGameActivity;

/**
 * Created by Opaque on 2018/5/7.
 */

public class DataCenter extends SQLiteOpenHelper{


    public static PlayerBean DataCenterPlayer =new PlayerBean(null,5, 20, 5, 5, 5,
            5, 1000);
    private static String SqlForPlayerParam =
            "Create table Player(id integer primary key," +
            "name text,"+
            "wit integer,"+
            "life integer,"+
            "power integer,"+
            "luck integer,"+
            "speed integer,"+
            "corporeity integer,"+
            "money double)";
    private static String SqlForMissionParam =
            "Create table Mission(id integer primary key autoincrement,"+
            "name text,"+
            "require text,"+
            "reward text,"+
            "time integer,"+
            "content text)";
    private static  String SqlForRandomEventParam =
            "Create table Event(id integer primary key autoincrement,"+
            "name text,"+
            "attribute text,"+
            "changenum integer,"+
            "moneyreward integer,"+
            "content text)";
    private static  String SqlForPlayerTimeParam =
            "Create table playertime(id integer primary key,"+
                    "time text)";
    private Context mContext;
    public DataCenter(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    public static void refreshCenterPlayerData(PlayerBean p1){
        DataCenterPlayer = p1;

    }
    public static ContentValues getInsertSqlPlayerParam(PlayerBean p1,int id){
        ContentValues values = new ContentValues();
        values.put("id",id);
        values.put("name",p1.getName());
        values.put("wit",p1.getWit());
        values.put("life",p1.getLife());
        values.put("power",p1.getPower());
        values.put("luck",p1.getLuck());
        values.put("speed",p1.getSpeed());
        values.put("corporeity",p1.getCorporeity());
        values.put("money",p1.getMoney());
        return  values;
    }
    public static boolean savePlayerToDB
            (SQLiteDatabase write,SQLiteDatabase read,String time,int DatabaseID){
            ContentValues values = getInsertSqlPlayerParam(MainGameActivity.currentPlayer,DatabaseID);
        if(DatabaseID <=3) {
            long a =write.replace("Player", null, values);
            Log.v("Replace---long","=="+a);
            Cursor cursor = read.query(
                    "player", null, null, null, null, null, null);
            if (cursor.moveToFirst() ==false){
                return false;
            }
            else {
                cursor.moveToPosition(DatabaseID-1);
                int Timeid = cursor.getInt(cursor.getColumnIndex("id"));
                values.clear();
                values.clear();
                values.put("id", Timeid);
                values.put("time", time);
                write.replace("playertime", null, values);
            }
            return true;
        }
        else{
           return false;
        }


    }
    /**vent
     * 第一次使用时会调用onCreate创建数据库
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SqlForPlayerParam);
        Toast.makeText(mContext, "Create table player succeeded", Toast.LENGTH_SHORT).show();
        db.execSQL(SqlForMissionParam);
        Toast.makeText(mContext, "Create table mission succeeded", Toast.LENGTH_SHORT).show();
        db.execSQL(SqlForRandomEventParam);
        db.execSQL(SqlForPlayerTimeParam );
        //test:will delete later
        Toast.makeText(mContext, "Create table E succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
