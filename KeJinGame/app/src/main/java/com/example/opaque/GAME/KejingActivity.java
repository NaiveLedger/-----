package com.example.opaque.GAME;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.opaque.GAME.DB.PlayerDao;

/**
 * Created by Opaque on 2018/4/21.
 */

public class KejingActivity extends AppCompatActivity {
    Button confirm;
    Button preview;
    private int RandomID = R.id.random;
    private int LuckID = R.id.luck;
    private int PowerID = R.id.power;
    private int WitID = R.id.wit;
    private int CorporeityID = R.id.corporeity;
    private int SpeedID = R.id.speed;
    private int GameCoinID = R.id.GameCoin;
    private int CashID = R.id.RMB;
    private boolean isChargeSuccess = false;
    RadioGroup R1;
    RadioGroup R2;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_kejin);

        initFunc();
    }

    /**
     * 初始化本act里的按钮功能
     */
    private void initFunc() {
        TextView t1 = (TextView) findViewById(R.id.confirm);
        t1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainGameActivity.currentPlayer.getMoney()<getmoney()){
                    chargeFailedDialog();
                }
                switch (getR2SelectedButtonID()) {
                    case R.id.GameCoin: {
                        int whatChID = getR1SelectedButtonID();
                        int SecondID = getR2SelectedButtonID();
                        //whatch获得被激活按钮的ID
                        isChargeSuccess= true;
                        if(isChargeSuccess == true) {
                            //使用playerdao操作玩家数据，减钱
                            boolean isKeJinSuccess = PlayerDao.strengthSomeAtt(whatChID,getmoney()/100,getmoney());
                            if (isKeJinSuccess ==true)
                            {
                                chargeSuccessDialog();
                            }
                            else {
                                chargeFailedDialog();
                            }
                        }
                        else{
                        }
                            break;
                    }
                    case R.id.RMB: {
                        //待完善，支付宝/微信支付页面
                        break;
                    }
                }
            }
        });

    }

    private void chargeFailedDialog() {
        new AlertDialog.Builder(this).setTitle("充值失败")
                .setMessage("失败").setPositiveButton("确定", null)
                .show();
    }

    private void chargeSuccessDialog() {
        new AlertDialog.Builder(this).setTitle("充值成功")
                .setMessage("你已经充值成功").setPositiveButton("确定", null)
                .show();
    }

    public void backMain(View view) {
        Intent i = new Intent(this, MainGameActivity.class);
        startActivity(i);
    }
    /**
     * 获得需要氪的钱
     * return int 钱
     */
    private int getmoney(){
        EditText e1 = (EditText)findViewById(R.id.edit_money);
        return Integer.parseInt(e1.getText().toString());
    }

    /**
     * 获得R1 按钮组被选中的按钮ID--什么属性
     * @return ID that checked in RadioGroup named R1
     */
    public int getR1SelectedButtonID() {
        R1 = (RadioGroup) findViewById(R.id.kejin_R1);
        return R1.getCheckedRadioButtonId();
    }
    /**
     * 获得R2 按钮组被选中的按钮ID--是否RMB
     * @return ID that checked in RadioGroup named R1
     */
    public int getR2SelectedButtonID() {
        R2 = (RadioGroup) findViewById(R.id.kejin_R2);
        return R2.getCheckedRadioButtonId();
    }


    public void addtextnum(View v){
        EditText e1 = (EditText)findViewById(R.id.edit_money);
        int num = Integer.parseInt(e1.getText().toString());
        num+=100 ;
        e1.setText(num+"");
    }
    public void deTextnum(View v){
        EditText e1 = (EditText)findViewById(R.id.edit_money);
        int num = Integer.parseInt(e1.getText().toString());
        num-=100;
        e1.setText(num+"");
    }
}

