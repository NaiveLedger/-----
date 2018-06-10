package com.example.opaque.GAME.DB;

import android.util.Log;

import com.example.opaque.GAME.Bean.PlayerBean;
import com.example.opaque.GAME.MainGameActivity;
import com.example.opaque.GAME.R;

/**
 * Created by Opaque on 2018/5/6.
 */

public class PlayerDao {
    /**
     * 本类进行玩家相关数据的操作
     */
    static PlayerBean p1;

    /**
     * 本方法被氪金类调用，进行相关玩家数据的增强
     *
     * @param ID     选中的按钮
     * @param degree 需要氪多少量
     * @param money  氪了多少钱
     * @return 是否成功
     */
    public static boolean strengthSomeAtt(int ID, int degree, int money) {
        //p1 = DataCenter.DataCenterPlayer;
        p1 = MainGameActivity.currentPlayer;
        switch (ID) {
            case R.id.random: {
                boolean isSuccess = RandomStrength(degree, money);
                if (isSuccess == true) {
                    break;
                } else {
                    Log.v("PlayerDao.java", "SomeThingCrab---");
                    break;
                }
            }
            case R.id.corporeity: {
                p1.setMoney(p1.getMoney() - money);
                p1.setCorporeity(p1.getCorporeity() + degree);
                break;
            }
            case R.id.speed: {
                p1.setMoney(p1.getMoney() - money);
                p1.setSpeed(p1.getSpeed() + degree);
                break;
            }
            case R.id.wit: {
                p1.setMoney(p1.getMoney() - money);
                p1.setWit(p1.getWit() + degree);
                break;
            }
            case R.id.power: {
                p1.setMoney(p1.getMoney() - money);
                p1.setPower(p1.getPower() + degree);
                break;
            }
            case R.id.luck: {
                p1.setMoney(p1.getMoney() - money);
                p1.setLuck(p1.getLuck() + degree);
                break;
            }
            default: {
                Log.v("PlayerDao.java", "SomeThingCrab---" + ID);
                return false;
            }

        }
        DataCenter.refreshCenterPlayerData(p1);
        return true;
    }

    public static int getRandomId() {
        return (int) (Math.random() * (5 - 1 + 1) + 1);

    }

    public static boolean RandomStrength(int degree, int money) {
        switch (getRandomId()) {
            case 1: {
                p1.setMoney(p1.getMoney() - money);
                p1.setCorporeity(p1.getCorporeity() + degree);
                return true;
            }
            case 2: {
                p1.setMoney(p1.getMoney() - money);
                p1.setWit(p1.getWit() + degree);
                return true;
            }
            case 3: {
                p1.setMoney(p1.getMoney() - money);
                p1.setSpeed(p1.getSpeed() + degree);
                return true;
            }
            case 4: {
                p1.setMoney(p1.getMoney() - money);
                p1.setPower(p1.getPower() + degree);
                return true;
            }
            case 5: {
                p1.setMoney(p1.getMoney() - money);
                p1.setLuck(p1.getCorporeity() + degree);
                return true;
            }
            default: {
                Log.v("PlayerDao.java", "SomeThingCrab---" + getRandomId());
                return false;
            }
        }

    }
    public static boolean SetPlayerToNextYear(PlayerBean p1){
        if (p1.getLife() >= 100){
            //游戏结束
            return false;
        }
        else{
            p1.setLife(p1.getLife()+1);
            DataCenter.refreshCenterPlayerData(p1);
            return true;
        }

    }

}
