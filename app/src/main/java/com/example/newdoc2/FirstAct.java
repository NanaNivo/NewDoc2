package com.example.newdoc2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FirstAct extends AppCompatActivity {
  TextView happy;
  Button logIn,reg;
  static myDbAdapter db;
  Boolean isFirstRun;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first);
    // happy = (TextView) findViewById(R.id.hap);
    db=new myDbAdapter(this);



     isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", false);


   // getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).commit();

    logIn = (Button) findViewById(R.id.login);
    reg=(Button) findViewById(R.id.regester);
    logIn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (!isFirstRun) {
          Intent intent = new Intent(getApplicationContext(), MainActivity.class);
          startActivity(intent);
        }
        else
        {
          Intent intent = new Intent(getApplicationContext(), ElementMain.class);
          startActivity(intent);
        }
      }
    });
    reg.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), regester.class);
        startActivity(intent);
      }
    });
  }
}

