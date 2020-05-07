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
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first);
    // happy = (TextView) findViewById(R.id.hap);
    db=new myDbAdapter(this);
    logIn = (Button) findViewById(R.id.login);
    reg=(Button) findViewById(R.id.regester);
    logIn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), login.class);
        startActivity(intent);
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

