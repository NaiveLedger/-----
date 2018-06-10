package com.example.opaque.GAME;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.example.opaque.GAME.Bean.PlayerBean;
import com.example.opaque.GAME.Tool.AddSomeText;

import java.util.ArrayList;

/**
 * Created by Opaque on 2018/6/10.
 */

public class EndingActivity extends AppCompatActivity {
    static Boolean isEnding=false;
    ArrayList endArray;
    TextView tempText;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ending_activity);
        endArray = judgeWhatEndPlayerTo(MainGameActivity.currentPlayer);//获取结局数组
        TextView PageTitle = (TextView) findViewById(R.id.endingTitle);
        PageTitle.setText("" + endArray.get(0));

        try {
            showEnding(endArray);
        }
        catch (Exception e){
        }
        finally {
            MainGameActivity.currentPlayer =null;//new PlayerBean("",5,20,5,5,5,5,1000);
            isEnding=true;
        }
    }



    public ArrayList judgeWhatEndPlayerTo(PlayerBean p1) {
        ArrayList endList = new ArrayList();
        endList = AddSomeText.EndingListAdding(endList);

        if (p1.getMoney() < 0) {
            String[] a1 =(String[])endList.get(0);
            endList.clear();
            for(int y = 0;y<a1.length;y++){if(a1[y]!=null)endList.add(a1[y]);}

            return endList;
        } else if (p1.getSpeed() > 3 * 10E8) {
            return (ArrayList) endList.get(5);
        } else if (p1.getPower() >= 65535) {
            return (ArrayList) endList.get(4);

        } else if (p1.getCorporeity() >= 65535) {
            return (ArrayList) endList.get(3);

        } else if (p1.getLuck() >= 65535) {
            return (ArrayList) endList.get(2);

        } else if (p1.getWit() >= 65535) {
            return (ArrayList) endList.get(1);
        }
        if(MainGameActivity.currentPlayer.equals(new PlayerBean("",5,20,5,5,5,5,1000))){
            return  (ArrayList)endList.get(0);

        }
        ArrayList temp = (ArrayList) endList.get(6);
        temp.set(1, "当你百岁之时，闭上眼睛时");
        return temp;
    }

        public AlphaAnimation initAnima(AlphaAnimation alp,long time,final TextView text){
            alp.setDuration(time);
            alp.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    text.setVisibility(View.INVISIBLE);
                }
            });
        return alp;
        }
    public void showEnding(ArrayList a1) throws InterruptedException {
        final TextView pageN1Line=(TextView) findViewById(R.id.N1Line);
        final TextView pageN2Line=(TextView) findViewById(R.id.N2Line);
        final TextView pageN3Line=(TextView) findViewById(R.id.N3Line);
        final TextView pageN4Line=(TextView) findViewById(R.id.N4Line);
        final TextView pageN5Line=(TextView) findViewById(R.id.N5Line);
        final TextView pageN6Line=(TextView) findViewById(R.id.N6Line);
        final TextView pageN7Line=(TextView) findViewById(R.id.N7Line);
        final TextView pageN8Line=(TextView) findViewById(R.id.N8Line);
        pageN1Line.setText("" + a1.get(1));
        pageN2Line.setText("" + a1.get(2));
        pageN3Line.setText("" + a1.get(3));
        if (a1.size() > 4)
            pageN4Line.setText("" + a1.get(4));
        if (a1.size() > 5)
            pageN5Line.setText("" + a1.get(5));
        if (a1.size() > 6)
            pageN6Line.setText("" + a1.get(6));
        if (a1.size() > 7)
            pageN7Line.setText("" + a1.get(7));
        if (a1.size() > 8)
            pageN8Line.setText("" + a1.get(8));


        AlphaAnimation alp = new AlphaAnimation(1.0f, 0.0f);
        AlphaAnimation alp2 = initAnima(alp,2000,pageN1Line);
        pageN1Line.setAnimation(alp2);
        alp2 =initAnima(alp,4000,pageN2Line);
        pageN2Line.setAnimation(alp2);
        alp2 =initAnima(alp,6000,pageN3Line);
        pageN3Line.setAnimation(alp2);
        alp2 =initAnima(alp,8000,pageN4Line);
        pageN4Line.setAnimation(alp2);
        alp2 =initAnima(alp,10000,pageN5Line);
        pageN5Line.setAnimation(alp2);
        alp2 =initAnima(alp,12000,pageN6Line);
        pageN6Line.setAnimation(alp2);
        alp2 =initAnima(alp,14000,pageN7Line);
        pageN7Line.setAnimation(alp2);
        alp2 =initAnima(alp,16000,pageN8Line);
        pageN8Line.setAnimation(alp2);
    }


    public void backToStart(View v) {
        Intent i = new Intent(this, StartActivity.class);
        startActivity(i);

    }
}

