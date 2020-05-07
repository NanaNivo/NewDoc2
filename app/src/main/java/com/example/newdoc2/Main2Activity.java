package com.example.newdoc2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import static com.example.newdoc2.FirstAct.db;

public class Main2Activity extends AppCompatActivity {
  GridLayout gridLayout;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main2);
    gridLayout=(GridLayout)findViewById(R.id.grid);

    setSingleEvent(gridLayout);
  }

  private void setSingleEvent(GridLayout gridLayout) {
    for(int i = 0; i<gridLayout.getChildCount();i++){
      final CardView cardView=(CardView)gridLayout.getChildAt(i);
      final int finalI= i;
      cardView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Toast.makeText(Main2Activity.this,"Clicked at index "+ finalI,
                  Toast.LENGTH_SHORT).show();
          if(finalI==0)
          {
            Intent intent = new Intent(getApplicationContext(), VidioPage.class);
            startActivity(intent);
          }
          if(finalI==1)
          {
            Intent intent = new Intent(getApplicationContext(), airshipActivity.class);
            startActivity(intent);
          }
          if(finalI==2)
          {
            Intent intent = new Intent(getApplicationContext(), ElementMain.class);
            startActivity(intent);
          }


        }
      });
    }
  }
}
