package com.example.newdoc2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class Chat_activity extends AppCompatActivity {
EditText editText;
    MessageAdapter adap;
    ListView simpleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_activity);
        editText = (EditText) findViewById(R.id.editText);
        simpleList = (ListView)findViewById(R.id.messages_view);
        adap=new MessageAdapter(this);
        simpleList.setAdapter(adap);
        Message mass=new Message("مرحبا نيفو","samer",false);
        adap.add(mass);
        Message mass2=new Message("كيف حالك انا آنا","samer",false);
        adap.add(mass2);
        Message mass3=new Message("سنقضي وقتا ممتعاً لتحسين مزاجك","samer",false);
        adap.add(mass3);

    }

    public void sendMessage(View view) {
        String message = editText.getText().toString();
        if (message.length() > 0) {
            //scaledrone.publish("observable-room", message);
           Message mass=new Message(message,"i",true);
            adap.add(mass);
            simpleList.setSelection(simpleList.getCount() - 1);
            editText.getText().clear();
        }
    }
}
