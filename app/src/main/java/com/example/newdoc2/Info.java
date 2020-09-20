package com.example.newdoc2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

//import static com.example.newdoc2.FirstAct.db;

public class Info extends AppCompatActivity implements View.OnClickListener {
    RadioGroup groupInfo;
    String selectedSuperStar;
    Button submit;
    TextView quesInfo;
    LinearLayout len;
    String TAG = "TestActivity";
    int numqQues=1;

    String[] temp;

    myDbAdapter   db;
    // String ans[]={"aaa","sss","qqqq"};
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        groupInfo = (RadioGroup) findViewById(R.id.groupInfo);
        submit = (Button) findViewById(R.id.submitButtonInfo);
        quesInfo = (TextView) findViewById(R.id.quesInfo);
        len = (LinearLayout) findViewById(R.id.InfoLin);

           db=new myDbAdapter(this);
        temp = db.getQuesFroInfo(numqQues);
        // db.insertData("وجدت صعوبة في الهدوء و الراحة",2);
        quesInfo.setText(temp[0]);
        String number= temp[1];
        addRadioButtons(Integer.parseInt(number));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isclick) {
                    isclick=false;
                    numqQues++;
                    temp = null;
                    groupInfo.removeAllViews();
                    if (numqQues < 8) {
                        Toast.makeText(Info.this, "ccc" + numqQues, Toast.LENGTH_SHORT).show();
                        temp = db.getQuesFroInfo(numqQues);
                        // db.insertData("وجدت صعوبة في الهدوء و الراحة",2);

                        quesInfo.setText(temp[0]);
                        String number = temp[1];
                        addRadioButtons(Integer.parseInt(number));
                    }

                    if (numqQues == 8) {
                        quesInfo.setText(null);
                        //for inter as once first
                        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", true).commit();

                        Intent intent = new Intent(getApplicationContext(), exam.class);
                        startActivity(intent);
                    }
                }
            }
        });





    }
    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)

    public void addRadioButtons(int number) {
        groupInfo.setOrientation(LinearLayout.VERTICAL);
        //
        for (int i = 0; i < number; i++) {
            RadioButton rdbtn = new RadioButton(this);
           rdbtn.setPadding(30,30,30,0);
            rdbtn.setId(View.generateViewId());
            rdbtn.setText(temp[i+2]);
            rdbtn.setTextColor(R.color.colordarkligt);
            rdbtn.setTextSize(18);

rdbtn.setTypeface(null, Typeface.BOLD);
            RadioGroup.LayoutParams params_soiled = new RadioGroup.LayoutParams(getBaseContext(), null);
            params_soiled.setMargins(40, 15, 40, 45);
            rdbtn.setLayoutParams( params_soiled);
            rdbtn.setOnClickListener( this);
            groupInfo.addView(rdbtn);
        }
    }
   boolean isclick=false;

    @Override
    public void onClick(View v) {
        // Log.d(TAG, " Name " + ((RadioButton)v).getText() +" Id is "+v.getId());
        db.updateAnsToInfo(numqQues, (String) ((RadioButton)v).getText());
        isclick=true;

    }
}
