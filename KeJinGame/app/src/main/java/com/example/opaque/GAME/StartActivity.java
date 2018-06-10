package com.example.opaque.GAME;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.opaque.GAME.Tool.progressDialog;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_begin);
        Button btn = (Button) findViewById(R.id.start_game_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, RegisterActivity.class);
                try {
                    progressDialog p1 = new progressDialog(StartActivity.this);
                    this.wait(500);

                }
                catch(Exception e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                }
            }
        });
    }
    public void toLoad(View v){
        Intent i = new Intent(this,SaveAndLoadActivity.class);
        i.putExtra("type","load");
        startActivity(i);
    }




}
