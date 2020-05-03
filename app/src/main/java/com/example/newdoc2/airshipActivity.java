package com.example.newdoc2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.newdoc2.FirstAct.db;

//import com.developer.kalert.KAlertDialog;
//import com.developer.kalert.KAlertDialog;
//import com.google.android.material.dialog.MaterialAlertDialogBuilder;
//import com.rahman.dialog.Activity.SmartDialog;
//import com.rahman.dialog.ListenerCallBack.SmartDialogClickListener;
//import com.rahman.dialog.Utilities.SmartDialogBuilder;


public class airshipActivity extends AppCompatActivity implements Balloon.BalloonListener {

    private static final int MIN_ANIMATION_DELAY = 500;
    private static final int MAX_ANIMATION_DELAY = 1500;
    private static final int MIN_ANIMATION_DURATION = 1000;
    private static final int MAX_ANIMATION_DURATION = 20000;
    private static  int BALLOONS_PER_LEVEL = 3;
    private static  int BALLOONS_PER_Round= 3;
    private static final String TAG ="nnnn" ;
    public  float starthight = 0;


    private ViewGroup mContentView;
    private int[] mBalloonColors = new int[3];
    private int[] mBalloonform = {R.drawable.ballon1,R.drawable.ballon2,R.drawable.ballon44,R.drawable.ballon7,R.drawable.ballon6};
    public  int mNextColor, mNextpath,mNextform ;
    public  static int mNextword=1;
    public String[] allpaths = {"circle","toleft","toright"};
    public static int mScreenWidth,mScreenHeight;
    TextView mScoreDisplay,mScorehighDisplay, mLevelDisplay;
    private List<Balloon> mBalloons = new ArrayList<>();
    public static List<String> currword=new ArrayList<>();
    public static ArrayList<String> appearwords=new ArrayList<>();
    ImageView volum,puese,close;

    private boolean mPlaying;
    private boolean mGameStopped = true;
    private int mLevel, mScore,mScorehigh, mPinsUsed;
    private int mBalloonsPopped;
    myCustomDialog customDialog;
    private SoundHelper mSoundHelper;


    @SuppressLint({"WrongConstant", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airship);
        mBalloonColors[0] = Color.argb(255, 255, 0, 0);
        mBalloonColors[1] = Color.argb(255, 0, 255, 0);
        mBalloonColors[2] = Color.argb(255, 0, 0, 255);



        //  getWindow().setBackgroundDrawableResource(R.drawable.modern_background);

        mContentView = (ViewGroup) findViewById(R.id.activity_main);
        mContentView.setBackgroundResource(R.drawable.background);
        setToFullScreen();
        volum = findViewById(R.id.volume_up);
        close = findViewById(R.id.close);
        puese = findViewById(R.id.pause);

        ViewTreeObserver viewTreeObserver = mContentView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onGlobalLayout() {
                    mContentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    mScreenHeight = mContentView.getHeight();
                    mScreenWidth = mContentView.getWidth();
                }
            });
        }


        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToFullScreen();
            }
        });

        mScoreDisplay = (TextView) findViewById(R.id.score_display);
        mScorehighDisplay = (TextView) findViewById(R.id.scorehigh_display);
        mLevelDisplay = (TextView) findViewById(R.id.level_display);


        updateDisplay();

        mSoundHelper = new SoundHelper(this);
        mSoundHelper.prepareMusicPlayer(this);
        db=new myDbAdapter(this);



        customDialog=new myCustomDialog(this);
        customDialog.showDialogg(this,"إبدأ الجولة 1","اضغط على الكلمات الإجابية","إبدأ الآن");
        customDialog.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //done what do you want to do
                startGame();
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
                customDialog.alertDialog.dismiss();
            }
        });
    }

    private void setToFullScreen() {

        ViewGroup rootLayout = (ViewGroup) findViewById(R.id.activity_main);

        rootLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToFullScreen();
    }

    public  void startGame() {
        setToFullScreen();
        mScore = 0;
        mLevel = 0;
        mPinsUsed = 0;
        mScorehigh=0;
        starthight = mScreenHeight  / 3;
       /* for (ImageView pin : mPinImages) {
            pin.setImageResource(R.drawable.ic_ts_map_pin);
        }*/

        mGameStopped = false;
        Toast.makeText(this, "end the game"+starthight, Toast.LENGTH_SHORT).show();
        startLevel();
        mSoundHelper.playMusic();
    }

    private void startLevel() {
        mLevel++;
        updateDisplay();
        BalloonLauncher balloonLauncher = new BalloonLauncher();
        balloonLauncher.execute(mLevel);
        mPlaying = true;
        mBalloonsPopped = 0;
        BALLOONS_PER_LEVEL=BALLOONS_PER_LEVEL+2;
        carrntcreatballon=0;
        // mGoButton.setText("Stop Game");
    }

    private void finishLevel() {
        if(mLevel<3) {
            BALLOONS_PER_Round++;
            int d = mLevel + 1;
            customDialog.showDialogg(this, "إبدأ الجولة " + d, "إطلاق " + BALLOONS_PER_Round + " بالونات لفتح هذه الجولة", "إبدأ الآن");
            customDialog.ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //done what do you want to do
                    startLevel();
                    System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
                    customDialog.alertDialog.dismiss();
                }
            });
            // Toast.makeText(this, String.format("You finished level %d", mLevel), Toast.LENGTH_SHORT).show();
            mPlaying = false;
        }
        else
        {
            gameend();
        }
    }


    @Override
    public void popBalloon(Balloon balloon, boolean userTouch) {

        mContentView.removeView(balloon);
        mBalloons.remove(balloon);


        if(balloon.curunthigh>0)
        {
            mScore=mScore-10;
        }
        else
        {
            mScore=mScore+10;
        }
        if(mScorehigh<mScore)
        {
            mScorehigh=mScore;
        }

        updateDisplay();

        if (mBalloonsPopped == BALLOONS_PER_LEVEL) {
            finishLevel();
        }
    }


    int carrntcreatballon;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void jumpBalloon(Balloon balloon, boolean userTouch) {

        float widh=  balloon.getX()-mScreenWidth;
        float high=  balloon.getY();
        String curpath=balloon.curpath;
        int balform=balloon.ballonform;
        db.iswordclick(balloon.curword);
        appearwords.remove(balloon.curword);
        Toast.makeText(this, "appearwords"+appearwords.size(), Toast.LENGTH_LONG).show();
        float highjumb = high+balloon.curunthigh;
        if ((highjumb > 0) && (highjumb < mScreenHeight-400)) {
            popBalloon(balloon, true);

            // Toast.makeText(this, balloon.getX()+"+"+balloon.getY()+"old", Toast.LENGTH_LONG).show();
            if (!db.isclickallword())
            {
                while (currword.size() == 0) {

                    currword = db.getinfoFroairship(mNextword);


                }

                appearwords.add(currword.get(0));
                Balloon bb = launchBalloon(widh, highjumb, currword.get(0), currword.get(1), balform, curpath, false);
                currword = new ArrayList<>();
                // Toast.makeText(this, balloon.curunthigh+"curunthigh"+balloon.getX(), Toast.LENGTH_LONG).show();


            }
            else
            {
                Toast.makeText(this, "finished word", Toast.LENGTH_LONG).show();
                if(appearwords.isEmpty())
                {
                    gameend();
                }
            }
        }
        else {

            mSoundHelper.playSound();
            mBalloonsPopped++;
            popBalloon(balloon, true);

            int d= BALLOONS_PER_Round+carrntcreatballon;
            Toast.makeText(this, "ok"+d, Toast.LENGTH_LONG).show();

            if (d<BALLOONS_PER_LEVEL) {
                carrntcreatballon++;
                if (!db.isclickallword()) {

                    new CountDownTimer(3000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            // mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                        }

                        public void onFinish() {

                            Random random = new Random();
                            int xPosition = random.nextInt(mScreenWidth);

                            while (currword.size() == 0) {

                                currword = db.getinfoFroairship(mNextword);


                            }

                            appearwords.add(currword.get(0));
                            Balloon bb = launchBalloon(xPosition - mScreenWidth, mScreenHeight - starthight, currword.get(0), currword.get(1), 0, "cccc", true);
                            currword = new ArrayList<>();
                        }

                    }.start();


                    // Toast.makeText(this, balloon.curunthigh+"curunthigh"+balloon.getX(), Toast.LENGTH_LONG).show();


                } else {
                    Toast.makeText(this, "finished word", Toast.LENGTH_LONG).show();
                }
            }
        }

    }



    private void gameend() {
        mSoundHelper.pauseMusic();
        Toast.makeText(this, "end the game", Toast.LENGTH_SHORT).show();

        for (Balloon balloon : mBalloons) {
            mContentView.removeView(balloon);
            balloon.setPopped(true);
        }

        mBalloons.clear();
        mPlaying = false;
        mGameStopped = true;

        if (HighScoreHelper.isTopScore(this, mScorehigh)) {
            HighScoreHelper.setTopScore(this, mScorehigh);

            customDialog.showDialogg(this,"أحسنت","نقطة"+mScorehigh+"لقد حققت ","حسناً");
            customDialog.ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //done what do you want to do
                    Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                    startActivity(intent);
                    customDialog.alertDialog.dismiss();
                }
            });
        }

    }

    private void updateDisplay() {
        mScoreDisplay.setText(String.valueOf(mScore));
        mLevelDisplay.setText(String.valueOf(mLevel));
        mScorehighDisplay.setText(String.valueOf(mScorehigh));
    }

    @Override
    protected void onStop() {
        super.onStop();
        gameend();
    }
    public  boolean comblate=false;


    long clicable=0;
    boolean clicablepause=false;
    public void volumeup(View view) {


        clicable++;
        if(clicable%2==0)
        {
            volum.setImageResource(R.drawable.ic_volume_up_black_24dp);
            mSoundHelper.playMusic();
        }
        else
        {
            volum.setImageResource(R.drawable.ic_volume_off_black_24dp);
            mSoundHelper.pauseMusic();
        }

    }

    public void close(View view) {
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void pauese(View view) {
        clicablepause=true;
        for(int i=0;i<mBalloons.size();i++)
        {
            mBalloons.get(i).mAnimator.pause();

            //mBalloons.get(i).stopAnimation();
        }
        mSoundHelper.pauseMusic();
        customDialog.showDialogg(this,"توقفت",null,"الاستمرار");
        customDialog.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //done what do you want to do
                for(int i=0;i<mBalloons.size();i++)
                {
                    mBalloons.get(i).mAnimator.resume();
                    // mBalloons.get(i).playAnimation();

                }
                mSoundHelper.playMusic();
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
                customDialog.alertDialog.dismiss();
                clicablepause=false;
            }
        });

    }

    private class BalloonLauncher extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(Integer... params) {

            if (params.length != 1) {
                throw new AssertionError(
                        "Expected 1 param for current level");
            }

            int level = params[0];
            int maxDelay = Math.max(MIN_ANIMATION_DELAY,
                    (MAX_ANIMATION_DELAY - ((level - 1) * 500)));
            int minDelay = maxDelay / 2;

            int balloonsLaunched = 0;
            while (mPlaying && balloonsLaunched < BALLOONS_PER_Round) {
                if (clicablepause == false) {
//              Get a random horizontal position for the next balloon
                    Random random = new Random();
                    int xPosition = random.nextInt(mScreenWidth);
                    publishProgress(xPosition);
                    balloonsLaunched++;
                    System.out.println("xPosition" + xPosition);
                    System.out.println("mScreenWidth" + mScreenWidth);
                    int t = xPosition - mScreenWidth;
                    System.out.println("mScreenWidth+xPosition" + t);
//              Wait a random number of milliseconds before looping
                    int delay = random.nextInt(minDelay + 5000) + minDelay;
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;

        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int xPosition = values[0];

            if (!db.isclickallword())
            {
                while (currword.size() == 0) {

                    currword = db.getinfoFroairship(mNextword);


                }


                System.out.println("mNextword"+mNextword);

                // System.out.println("currword"+currword.size());
                appearwords.add(currword.get(0));
                launchBalloon(xPosition-mScreenWidth,mScreenHeight-starthight,currword.get(0),currword.get(1),0,"cccc",true);
                currword=new ArrayList<>();
            }
            else
            {
                Toast.makeText(airshipActivity.this, "finished word", Toast.LENGTH_LONG).show();
            }


        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private Balloon launchBalloon(float x,float h,String word,String typword,int ballonnform,String cupath,boolean curpath) {
        //  if(mBalloons.size()<4)
        //   {
        Balloon balloon;
        if(curpath)
        {
            balloon = new Balloon(this, 200,word,typword,mBalloonform[mNextform]);
            if (mNextpath + 1 == allpaths.length) {
                mNextpath = 0;
            } else {
                mNextpath++;
            }
            if (mNextform + 1 == mBalloonform.length) {
                mNextform = 0;
            } else {
                mNextform++;
            }



            mBalloons.add(balloon);

            if (mNextColor + 1 == mBalloonColors.length) {
                mNextColor = 0;
            } else {
                mNextColor++;
            }




//      Set balloon vertical position and dimensions, add to container
            balloon.setX(x);
            //balloon.setY(mScreenHeight -balloon.getHeight());
            mContentView.addView(balloon);

//      Let 'er fly
            int duration = Math.max(MIN_ANIMATION_DURATION, MAX_ANIMATION_DURATION - (mLevel * 1000));

            balloon.releaseBalloon( h,x ,duration,allpaths[mNextpath]);
        }

        else {
            balloon = new Balloon(this, 180,word,typword,ballonnform);

            mBalloons.add(balloon);
//      Set balloon vertical position and dimensions, add to container
            balloon.setX(x);
            //balloon.setY(mScreenHeight -balloon.getHeight());
            mContentView.addView(balloon);

//      Let 'er fly
            int duration = Math.max(MIN_ANIMATION_DURATION, MAX_ANIMATION_DURATION - (mLevel * 1000));
            balloon.releaseBalloon(h, x, duration, cupath);
        }
        return balloon;
    }



}