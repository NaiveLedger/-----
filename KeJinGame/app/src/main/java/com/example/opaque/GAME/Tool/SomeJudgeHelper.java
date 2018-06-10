package com.example.opaque.GAME.Tool;

import com.example.opaque.GAME.MainGameActivity;

/**
 * Created by Opaque on 2018/5/31.
 */

public class SomeJudgeHelper {
    public void judgeAttrNameToMutiPlayer(String name,int Degree) throws Exception {
        switch (name){
            case "wit":
                MainGameActivity.currentPlayer.setWit(
                        MainGameActivity.currentPlayer.getWit()+Degree
                );
                break;
            case "corporeity":
                MainGameActivity.currentPlayer.setCorporeity(
                        MainGameActivity.currentPlayer.getCorporeity()+Degree
                );
                break;
            case "speed":
                MainGameActivity.currentPlayer.setCorporeity(
                        MainGameActivity.currentPlayer.getCorporeity()+Degree
                );
                break;
            case "luck":
                MainGameActivity.currentPlayer.setCorporeity(
                        MainGameActivity.currentPlayer.getCorporeity()+Degree
                );
                break;
            case "power":
                MainGameActivity.currentPlayer.setCorporeity(
                        MainGameActivity.currentPlayer.getCorporeity()+Degree
                );
                break;

            case "life":
                MainGameActivity.currentPlayer.setCorporeity(
                        MainGameActivity.currentPlayer.getCorporeity()+Degree
                );
                break;
            default:
                throw new Exception("Unknowed Attribute");
        }

    }
}
