package com.example.opaque.GAME.Tool;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.opaque.GAME.SaveAndLoadActivity;

/**
 * Created by Opaque on 2018/6/3.
 */

public class showDialog {

    public void showOnlyPositiveDialog(Context context, String title, String content) {
        new AlertDialog.Builder(context).setTitle(title)
                .setMessage(content).setPositiveButton("确定", null)
                .show();
    }
    public void showTwoButtonDiaglog(Context context, String title, String content){
    AlertDialog dialog = new AlertDialog.Builder(context)
            .setTitle(title)//设置对话框的标题
            .setMessage(content)//设置对话框的内容
            //设置对话框的按钮
            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    setSureAboutSaveOrLoad(false);
                    dialog.dismiss();

                }
            })
            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    setSureAboutSaveOrLoad(true);
                    dialog.dismiss();

                }
            }).create();
        dialog.show();

    }
    public static void setSureAboutSaveOrLoad(boolean  s1){
        SaveAndLoadActivity.isSure =s1;
    }
}
