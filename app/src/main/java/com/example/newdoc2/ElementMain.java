package com.example.newdoc2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flatdialoglibrary.dialog.FlatDialog;

import java.util.ArrayList;
import java.util.List;

import static com.example.newdoc2.FirstAct.db;

//import com.bernaferrari.emojislider.EmojiSlider;

public class ElementMain extends AppCompatActivity {

    private Context mContext;

    private RelativeLayout mRelativeLayout;
    public static String[] osNameList={" لعبة المناطيد "," تمارين الإسترخاء "," التحدث مع الروبوت "," التثقيف النفسي "} ;
    public static String[] osImages;
    private SeekBar mSeekBar,mSeekBar2,mSeekBar3,mSeekBar4,mSeekBar5;
    public View mTextView;
            TextView mTextViewtxt;


    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    static View.OnClickListener myOnClickListener;
    static String yourFilePath ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        // Request window feature action bar
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_main);


        // Get the application context
        mContext = getApplicationContext();

        // Change the action bar color
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#FF738CCA"))
        );


        mTextView = LayoutInflater.from(this).inflate(R.layout.txtseekbar, null, false);
        // Get the widgets reference from XML layout
        //  mRelativeLayout = (RelativeLayout) findViewById(R.id.rl);
        mSeekBar = (SeekBar) findViewById(R.id.seekbar);
        mSeekBar2 = (SeekBar) findViewById(R.id.seekbar2);
        mSeekBar3 = (SeekBar) findViewById(R.id.seekbar3);
        mSeekBar4 = (SeekBar) findViewById(R.id.seekbar4);
        mSeekBar5 = (SeekBar) findViewById(R.id.seekbar5);
        mTextViewtxt=((TextView) findViewById(R.id.tv));

        mSeekBar.setProgress(16);
        mSeekBar2.setProgress(16);
        mSeekBar3.setProgress(16);
        mSeekBar4.setProgress(16);
        mSeekBar5.setProgress(16);
        mSeekBar.setThumb(getThumb(1,R.drawable.seekbaethumb,R.color.colorpurple));
        mSeekBar2.setThumb(getThumb(1,R.drawable.seekthumb2,R.color.colororangetoskil));
        mSeekBar3.setThumb(getThumb(1,R.drawable.seekthumb3,R.color.colorobluetoskil));
        mSeekBar4.setThumb(getThumb(1,R.drawable.seekthumb4,R.color.colorogreentoskil));
        mSeekBar5.setThumb(getThumb(1,R.drawable.seekthumb5,R.color.colorredtoskil));


        myOnClickListener = new MyOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view2);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        yourFilePath = this.getFilesDir() + "/" + "images" + "/";
        osImages=new String[4];
        osImages[0]=yourFilePath+"ballon1.png";
        osImages[1]=yourFilePath+"relax.xml";
        osImages[2]=yourFilePath+"hi.jpg";
        osImages[3]=yourFilePath+"food.jpg";
        adapter = new CustomAdapter(this, osNameList, osImages,R.layout.item_element_main,false);
        recyclerView.setAdapter(adapter);





        // Set a SeekBar change listener
     /*   mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // Display the current progress of SeekBar
              //  seekBar.setThumb(getThumb(i));
               // setText(i);
                //  mTextView.setText(""+i);
              /* int val = (i * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                mTextView.setText("" + i);
                mTextView.setX(seekBar.getX()+ val + seekBar.getThumbOffset() / 2);
                mTextView.setY(100);*/
                //

                //seekBar.setThumb(getThumb(progress));
         /*   }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });*/

        mSeekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;

            }

        });


    }

    public Drawable getThumb(int progress,int ba,int coler) {
        ((TextView) mTextView.findViewById(R.id.tvProgress)).setText("" + progress);
        mTextView.setBackgroundResource(ba);
        mTextView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        Bitmap bitmap = Bitmap.createBitmap(mTextView.getMeasuredWidth(), mTextView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        mTextView.layout(0, 0, mTextView.getMeasuredWidth(), mTextView.getMeasuredHeight());
        mTextView.draw(canvas);

        return new BitmapDrawable(getResources(), bitmap);
    }


    private class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            clicItem(v);
        }

        private void  clicItem(View v) {
            int selectedItemPosition = recyclerView.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForPosition(selectedItemPosition);

            TextView textViewName
                    = (TextView) viewHolder.itemView.findViewById(R.id.os_texts);
            String selectedName = (String) textViewName.getText();
            int selectedItemId = -1;
            for (int i = 0; i < osNameList.length; i++) {
                if (selectedName.equals(osNameList[i])) {
                    selectedItemId = i;
                }

            }
            if(selectedItemId == 0)
            {
                Intent intent = new Intent(getApplicationContext(), airshipActivity.class);
                startActivity(intent);
            }
          else  if(selectedItemId == 1)
            {
                Intent intent = new Intent(getApplicationContext(), VidioPage.class);
                startActivity(intent);
            }


        }

    }

}



