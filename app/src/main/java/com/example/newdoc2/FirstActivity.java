package com.example.newdoc2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;


import com.fivehundredpx.android.blur.BlurringView;


public class FirstActivity extends AppCompatActivity {
     View cardfirst;

    static myDbAdapter db;
    Boolean isFirstRun;

    static String yourFilePath ;
    static String  yourvideoPath ;
    static int highFirst;
    static int widthFirst;
   BlurringView mBlurringView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first2);
        cardfirst=findViewById(R.id.cardfirst);


        getSupportActionBar().setTitle("كن سعيداً");






      mBlurringView = (BlurringView) findViewById(R.id.blurring_view);
        View blurredView = findViewById(R.id.blurred_view);

        // Give the blurring view a reference to the blurred view.
        mBlurringView.setBlurredView(blurredView);


        mBlurringView.invalidate();

        //to chang high dynamic
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) this).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        //if you need three fix imageview in width
        highFirst = displaymetrics.widthPixels ;
        widthFirst=displaymetrics.heightPixels ;

        yourFilePath = this.getFilesDir() + "/" + "images" + "/";
        yourvideoPath = this.getFilesDir() + "/" + "vedio" + "/";
        db=new myDbAdapter(this);

        isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", false);

        ObjectAnimator animation = ObjectAnimator.ofFloat(cardfirst, "translationY", 300f);
        animation.setDuration(2000);
        animation.start();

        mBlurringView.invalidate();



    }

    public void txtFirst(View view) {

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
}
