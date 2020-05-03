package com.example.newdoc2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Map;

public class Info extends AppCompatActivity implements View.OnClickListener {
    RadioGroup groupInfo;
    String selectedSuperStar;
    Button submit;
    TextView quesInfo;
    LinearLayout len;
    String TAG = "TestActivity";
    int numqQues=1;
    myDbAdapter  dbb;
    String[] temp;
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

       dbb = new myDbAdapter(this);
        temp = dbb.getQuesFroInfo(numqQues);
        // db.insertData("وجدت صعوبة في الهدوء و الراحة",2);
        quesInfo.setText(temp[0]);
       String number= temp[1];
        addRadioButtons(Integer.parseInt(number));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numqQues++;
                temp=null;
                groupInfo.removeAllViews();
                if(numqQues!=12) {
                    temp = dbb.getQuesFroInfo(numqQues);
                    // db.insertData("وجدت صعوبة في الهدوء و الراحة",2);
                    quesInfo.setText(temp[0]);
                    String number = temp[1];
                    addRadioButtons(Integer.parseInt(number));
                }

            }
        });





    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)

    public void addRadioButtons(int number) {
        groupInfo.setOrientation(LinearLayout.VERTICAL);
        //
        for (int i = 0; i < number; i++) {
            RadioButton rdbtn = new RadioButton(this);
            rdbtn.setPadding(33,33,33,33);
            rdbtn.setId(View.generateViewId());
            rdbtn.setText(temp[i+2]);
            rdbtn.setOnClickListener( this);
            groupInfo.addView(rdbtn);
        }
    }

    @Override
    public void onClick(View v) {
       // Log.d(TAG, " Name " + ((RadioButton)v).getText() +" Id is "+v.getId());
        dbb.updateAnsToInfo(numqQues, (String) ((RadioButton)v).getText());

    }
}
