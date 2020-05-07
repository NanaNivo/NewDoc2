package com.example.newdoc2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.newdoc2.FirstAct.db;

public class MainActivity extends AppCompatActivity {
  RadioButton ans1, ans2, ans3,ans4;
  RadioGroup group;
  String selectedSuperStar;
  Button submit;
  static int numQues=1;
  int verAswer=-1;

  int[]result;
  TextView ques;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    group= (RadioGroup) findViewById(R.id.group);
    ans1= (RadioButton) findViewById(R.id.ans1);
    ans2 = (RadioButton) findViewById(R.id.ans2);
    ans4 = (RadioButton) findViewById(R.id.ans4);
    ans3 = (RadioButton) findViewById(R.id.ans3);
    submit = (Button) findViewById(R.id.submitButton);
    ques = (TextView) findViewById(R.id.ques);

    String temp =db.getQues(numQues);
    ques.setText(temp+"؟");
    // db.insertData("وجدت صعوبة في الهدوء و الراحة",2);

    submit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {


        if (ans2.isChecked()) {
          selectedSuperStar = ans2.getText().toString();
          verAswer = 1;
        } else if (ans1.isChecked()) {
          selectedSuperStar = ans1.getText().toString();
          verAswer = 0;
        } else if (ans4.isChecked()) {
          selectedSuperStar = ans4.getText().toString();
          verAswer = 3;
        } else if (ans3.isChecked()) {
          selectedSuperStar = ans3.getText().toString();
          verAswer = 2;
        }
        Toast.makeText(getApplicationContext(), selectedSuperStar, Toast.LENGTH_LONG).show();
        db.updateAns(numQues, verAswer);
        //selectedPosition=-1;
        if (numQues == 21)
        {
          result=db.getthesum();
          String[] all=db.resultdiagnosis(result);
          Toast.makeText(getApplicationContext(), "the result of stress"+all[0]+",,the result of anxiety,,,"+all[1]+"the result of depression"+all[2], Toast.LENGTH_LONG).show();

        }
        else {
          numQues++;
          System.out.println("numQues" + numQues);
          String temp = db.getQues(numQues);
          System.out.println("temp" + temp);
          ques.setText(temp+"؟");


        }
        group.clearCheck();
      }
    });
  }

}

