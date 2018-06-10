package com.example.opaque.GAME.DB;

import android.util.Log;

import com.example.opaque.GAME.MainGameActivity;
import com.example.opaque.GAME.R;

/**
 * Created by Opaque on 2018/6/2.
 */

public class EventDao {

    public static boolean StrengthAttributeByString(String AttributeName, int changeNum) {
        switch (AttributeName) {
            case "luck":
                PlayerDao.strengthSomeAtt(R.id.luck, changeNum, 0);
                return true;
            case "power":
                PlayerDao.strengthSomeAtt(R.id.power, changeNum, 0);
                return true;
            case "wit":
                PlayerDao.strengthSomeAtt(R.id.wit, changeNum, 0);
                return true;
            case "corporeity":
                PlayerDao.strengthSomeAtt(R.id.corporeity, changeNum, 0);
                return true;
            case "speeed":
                PlayerDao.strengthSomeAtt(R.id.speed, changeNum, 0);
                return true;
            case "life":
                MainGameActivity.currentPlayer.setLife(
                        MainGameActivity.currentPlayer.getLife() + changeNum
                );
                return true;
            case "none":
                return true;
            default:
                Log.d("EventDao.java", "AttributeName ==" + AttributeName);
                return false;
        }



    }
}
