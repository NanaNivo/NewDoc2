package com.example.newdoc2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import static com.example.newdoc2.ElementMain.devicehigh;

public class Chat_activity extends AppCompatActivity {
EditText editText;
    MessageAdapter adap;
    ListView simpleList;
   static int high_chat,width_chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_activity);

        //to chang high dynamic
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) this).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        //if you need three fix imageview in width
        width_chat = displaymetrics.widthPixels / 12;
        high_chat=displaymetrics.heightPixels / 22;


        editText = (EditText) findViewById(R.id.editText);
        simpleList = (ListView)findViewById(R.id.messages_view);
        adap=new MessageAdapter(this);
        simpleList.setAdapter(adap);
        Message mass=new Message("مرحبا نيفو","samer",false,false);
        adap.add(mass);
        Message mass2=new Message("كيف حالك انا آنا","samer",false,false);
        adap.add(mass2);
        Message mass3=new Message("سنقضي وقتا ممتعاً لتحسين مزاجك","samer",false,false);

        adap.add(mass3);

    }

    public void sendMessage(View view) {
        String message = editText.getText().toString();
        if (message.length() > 0) {
            //scaledrone.publish("observable-room", message);
           Message mass=new Message(message,"i",true,false);
            adap.add(mass);
            simpleList.setSelection(simpleList.getCount() - 1);
            editText.getText().clear();
            Message masss=new Message("","",false,true);
            adap.add(masss);
        }
    }
}
