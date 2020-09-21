package com.example.newdoc2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.newdoc2.FirstActivity.db;


public class MainActivity extends AppCompatActivity {
  RadioButton ans1, ans2, ans3,ans4;
  RadioGroup group;
  String selectedSuperStar;
  Button submit;
  static int numQues=1;
  int verAswer=-1;
Boolean isclicable2=false;
  int[]result;
  TextView ques;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    getSupportActionBar().setTitle("كن سعيداً");

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
isclicable2=false;



    submit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {


          isclicable2=false;
          if (ans2.isChecked()) {
            selectedSuperStar = ans2.getText().toString();
            verAswer = 1;
            isclicable2=true;
         //   Toast.makeText(getApplicationContext(),isclicable2+"isclick", Toast.LENGTH_LONG).show();
          } else if (ans1.isChecked()) {
            selectedSuperStar = ans1.getText().toString();
            verAswer = 0;
            isclicable2=true;
        //    Toast.makeText(getApplicationContext(),isclicable2+"isclick", Toast.LENGTH_LONG).show();

          } else if (ans4.isChecked()) {
            selectedSuperStar = ans4.getText().toString();
            verAswer = 3;
            isclicable2=true;
        //    Toast.makeText(getApplicationContext(),isclicable2+"isclick", Toast.LENGTH_LONG).show();
          } else if (ans3.isChecked()) {
            selectedSuperStar = ans3.getText().toString();
            verAswer = 2;
            isclicable2=true;
       //     Toast.makeText(getApplicationContext(),isclicable2+"isclick", Toast.LENGTH_LONG).show();
          }
        if (isclicable2) {
       //     Toast.makeText(getApplicationContext(), selectedSuperStar, Toast.LENGTH_LONG).show();
          db.updateAns(numQues, verAswer);
          //selectedPosition=-1;
          if (numQues == 21) {
            result = db.getthesum();
            String[] all = db.resultdiagnosis(result);
           // Toast.makeText(getApplicationContext(), "the result of stress" + all[0] + ",,the result of anxiety,,," + all[1] + "the result of depression" + all[2], Toast.LENGTH_LONG).show();
            getSharedPreferences("PREFERENCEExam1", MODE_PRIVATE).edit().putString("Exam1", all[0]).commit();
            getSharedPreferences("PREFERENCEExam2", MODE_PRIVATE).edit().putString("Exam2", all[1]).commit();
            getSharedPreferences("PREFERENCEExam3", MODE_PRIVATE).edit().putString("Exam3", all[2]).commit();
            Intent intent = new Intent(getApplicationContext(), Info.class);
            startActivity(intent);
          } else {
            numQues++;
            System.out.println("numQues" + numQues);
            String temp = db.getQues(numQues);
            System.out.println("temp" + temp);
            ques.setText(temp + "؟");


          }
          group.clearCheck();
          isclicable2=false;
        }
      }

    });
  }

}

