package com.example.newdoc2;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.flatdialoglibrary.dialog.FlatDialog;


import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;
import com.mindorks.placeholderview.PlaceHolderView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import static com.example.newdoc2.FirstAct.db;
import static com.example.newdoc2.R.color.colororangetoskil;

//import com.bernaferrari.emojislider.EmojiSlider;

public class ElementMain extends AppCompatActivity implements    NavigationView.OnNavigationItemSelectedListener {

    private Context mContext;

    private RelativeLayout mRelativeLayout;
    public static String[] osNameList={" لعبة المناطيد "," تمارين الإسترخاء "," التحدث مع الروبوت "," التثقيف النفسي "} ;
    public static String[] osImages;
    private SeekBar mSeekBar,mSeekBar2,mSeekBar3,mSeekBar4,mSeekBar5;
    public View mTextView;
            TextView mTextViewtxt,txt_toolbar;


    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    static View.OnClickListener myOnClickListener;
    static String yourFilePath ;


   /* private PlaceHolderView mDrawerView;

    private PlaceHolderView mGalleryView;*/

    private NavigationView mDrawer;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private  ActionBarDrawerToggle drawerToggle;
    private int mSelectedId;


    static int devicehigh;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        // Request window feature action bar
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_main);


        //to chang high dynamic
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) this).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        //if you need three fix imageview in width
        devicehigh = displaymetrics.widthPixels / 5;






        // Get the application context
        mContext = getApplicationContext();

        // Change the action bar color
      /*  getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#ff7a00"))
        );*/

        //to navegation
        setToolbar();
        initView();

        drawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.open_drawer,R.string.close_drawer);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //default it set first item as selected
        mSelectedId=savedInstanceState ==null ? R.id.navigation_home: savedInstanceState.getInt("SELECTED_ID");



// implement setNavigationSelectedListener event
        mDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

// add code here what you need on click of items.
                Toast.makeText(getApplicationContext(), "menuItem" + menuItem, Toast.LENGTH_LONG).show();

                int itemId = menuItem.getItemId();


               if(itemId==R.id.navigation_home) {


                   Intent intent = new Intent(getApplicationContext(), ElementMain.class);
                   startActivity(intent);
                   txt_toolbar.setText("الصفحة الرئيسية");
               }
                if(itemId==R.id.navigation_profil) {

                    Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                    startActivity(intent);
                    txt_toolbar.setText("الصفحة الشخصية");
                }
               // mDrawerLayout.closeDrawer(GravityCompat.START);

                return false;
            }
        });
     //   itemSelection(mSelectedId);



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
        mSeekBar.setThumb(getThumb(1,R.drawable.seekbaethumb));
        mSeekBar2.setThumb(getThumb(1,R.drawable.seekthumb2));
        mSeekBar3.setThumb(getThumb(1,R.drawable.seekthumb3));
        mSeekBar4.setThumb(getThumb(1,R.drawable.seekthumb4));
        mSeekBar5.setThumb(getThumb(1,R.drawable.seekthumb5));

       //mSeekBar.setMinimumHeight(155);




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
        mSeekBar2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;

            }

        });
        mSeekBar3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;

            }

        });
        mSeekBar4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;

            }

        });
        mSeekBar5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;

            }

        });



    }




    //to add number to thumb seekbar
    public Drawable getThumb(int progress,int ba) {
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
            else  if(selectedItemId == 2)
            {
                Intent intent = new Intent(getApplicationContext(), Chat_activity.class);
                startActivity(intent);
            }

        }

    }








//all fun to nivagation
    @SuppressLint("WrongViewCast")
    private void setToolbar() {
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
       txt_toolbar= (TextView) findViewById(R.id.Txt_toolbar);
        if (mToolbar != null) {
         setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle(null);
        }
    }

    private void initView() {
        mDrawer= (NavigationView) findViewById(R.id.main_drawer);
        mDrawer.setNavigationItemSelectedListener(this);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);

    }

    private void itemSelection(int mSelectedId) {

        switch(mSelectedId){

            case R.id.navigation_home:

               mDrawerLayout.closeDrawer(GravityCompat.START);

                break;

            case R.id.navigation_profil:
               mDrawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.navigation_log_out:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;


        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mSelectedId=menuItem.getItemId();
        itemSelection(mSelectedId);


        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //save selected item so it will remains same even after orientation change
        outState.putInt("SELECTED_ID",mSelectedId);
    }




}



