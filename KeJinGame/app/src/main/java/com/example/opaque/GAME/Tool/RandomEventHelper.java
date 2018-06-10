package com.example.opaque.GAME.Tool;

/**
 * Created by Opaque on 2018/5/31.
 */

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * 此类包含随机事件和数据库交互以及相关关于随机事件的帮助函数
 */
public class RandomEventHelper {
    public RandomEventHelper(){
        //空构造函数
    }
    /**
     * @param workClass
     * 工作类型  随机事件代号
     * 搬砖--1  11\12\13
     * 打工--2  21\22\23，
     * 偷车--3  31\32\33，
     * 码农--4  41\42\43，
     * 赌博--5  51\52\53
     * @param randomEventRate
     * 工作时随机生成的一个数字，当且仅当0、50、100时才能触发本方法
     * @return
     */
    public int isRandomEventActived(int workClass,int randomEventRate){
        if (randomEventRate== 0 ){
            //事件x1--good
            switch  (workClass){
                case 1:
                    return 11;
                case 2:
                    return 21;
                case 3:
                    return 31;
                case 4:
                    return 41;
                case 5:
                    return 51;
                default:
                    return -1;
            }

        }
        else if (randomEventRate == 50){
            //事件x2----normal
            switch  (workClass){
                case 1:
                    return 12;
                case 2:
                    return 22;
                case 3:
                    return 32;
                case 4:
                    return 42;
                case 5:
                    return 52;
                default:
                    return -1;
            }

        }
        else if (randomEventRate == 100 ){
            //事件x3--bad
            switch  (workClass){
                case 1:
                    return 13;
                case 2:
                    return 23;
                case 3:
                    return 33;
                case 4:
                    return 43;
                case 5:
                    return 53;
                default:
                    return -1;
            }
        }
        else{
            return 101;
            //error
        }

    }
    public ArrayList brickRandomEvent(ArrayList tempAttrArray, SQLiteDatabase db, int eventNum){
        Cursor cursor = db.query("Event", null, null, null, null, null, null);
        cursor.move(eventNum);
//        int count  =cursor.getCount();
//        int column = cursor.getColumnCount();
//        String S1 = cursor.getColumnName(1);
//        boolean s1 = cursor.isNull(cursor.getCount());
//        Log.d("cursor.co;unt",count+"");
//        Log.d("cursor.count.colunm",column+"");
//        Log.d("cursor.count.name",S1+"");
//        Log.d("cursor.isnull",s1+"");
        String name =cursor.getString(1);
        String attribute = cursor.getString(2);
        int changeNum = cursor.getInt(3);
        int  moneyReward =  cursor.getInt(4);
        String  content = cursor.getString(5);
        tempAttrArray.add(name);
        tempAttrArray.add(attribute);
        tempAttrArray.add(changeNum);
        tempAttrArray.add(moneyReward);
        tempAttrArray.add(content);
        return tempAttrArray;
    }

    public void workRanodmEvent(int eventNum){}
    public void codeRandomEvent(int eventNum){}
    public void gambleRandomEvent(int eventNum){}
    public  void stealRandomEvent(int eventNum){}


}
