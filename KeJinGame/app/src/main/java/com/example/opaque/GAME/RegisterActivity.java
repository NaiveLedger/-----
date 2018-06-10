package com.example.opaque.GAME;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Opaque on 2018/4/27.
 */

public class RegisterActivity extends AppCompatActivity {
    public String name;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_register);
        Button btn1 = (Button) findViewById(R.id.toGameBtn);
        btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getNameFromEditText();
                        if (name != null) {
                            Intent intent = new Intent(RegisterActivity.this, MainGameActivity.class);
                            intent.putExtra("playerName", name);
                            startActivity(intent);
                        } else {
                    Intent intent = new Intent(RegisterActivity.this, MainGameActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    public void getNameFromEditText(){
            EditText e =  (EditText)this.findViewById(R.id.name_text);
            name = e.getText().toString();
            }

    }


