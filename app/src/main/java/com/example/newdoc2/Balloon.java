package com.example.newdoc2;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;


import static com.example.newdoc2.FirstActivity.db;
import static com.example.newdoc2.airshipActivity.appearwords;
import static com.example.newdoc2.airshipActivity.currword;
import static com.example.newdoc2.airshipActivity.mNextword;


@SuppressLint("AppCompatCustomView")
public class Balloon extends TextView implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {
    public ValueAnimator  mAnimator;
    private BalloonListener mListener;
    private boolean mPopped;
    public  float curunthigh =1;

    private Paint mPaint;
    public  int dur;
    public  String curpath,typeword,curword;
    public  int ballonform;
    int timer=0;
    boolean istop=false;
    boolean isdown=false;
    public Balloon(Context context) {
        super(context);
    }

    @SuppressLint({"NewApi", "ResourceAsColor"})
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Balloon(@NonNull Context context, int rawHeight, String word,String typewordd,int balform) {
        super(context);
        mListener = (BalloonListener) context;
        ballonform=balform;
        curword=word;
        this.setText(curword);
        this.setBackgroundResource(ballonform);
        typeword=typewordd;
        if(typeword.equals("positive"))
        {
            curunthigh=-400;
        }else if(typeword.equals("negative"))
        {
            curunthigh=400;
        }else
        {
            curunthigh=0;
        }
        //this.setBackgroundColor(color);
        this.setPadding(0,50,0,0);
        this.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL);

        this.setTextColor(getResources().getColor(R.color.colorwight));
        this.setTypeface(null, Typeface.BOLD);
        this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f);
        this.setTag(word);
        setLayoutParams(new ViewGroup.LayoutParams(PixelHelper.pixelsToDp((float) (rawHeight /2), context), PixelHelper.pixelsToDp((float) ((rawHeight)/1.5), context)));

        timebaloon(1000,false);


    }

    PathMeasure pathMeasure;


    /**
     * This method is responsible for create releasing balloons animation during gameplay.
     *
     * @param screenHeight represents screen height
     * @param duration     represents number of milliseconds for animation duration. With increasing level, this number is reduces.
     * @see ValueAnimator
     */

    public void releaseBalloon(float screenHeight,float x, int duration,String pathtype) {
        /*mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.BLACK);*/
        Path p = new Path();
        curpath=pathtype;
        if(curpath.equals("circle"))
        {

            p.addCircle(x, screenHeight, 200, Path.Direction.CW);

        }
        else if(curpath.equals("toright"))
        {
            x=x+200;
            p.moveTo(x, screenHeight);
            p.lineTo(x+200, screenHeight);
        }
        else if(curpath.equals("toleft"))
        {
            x=x+200;
            p.moveTo(x, screenHeight);
            p.lineTo(x-200, screenHeight);
        }
        else if(curpath.equals("totop"))
        {
          /*  x=x+200;
            p.moveTo(x, screenHeight);
            p.lineTo(x, screenHeight-400);*/
            istop=true;

        }
        else if(curpath.equals("todown"))
        {
          /*  x=x+200;
            p.moveTo(x, screenHeight);
            p.lineTo(x, screenHeight-400);*/
            isdown=true;

        }

        if(istop)
        {
          //  pathMeasure = new PathMeasure(p, true);
            mAnimator = new ValueAnimator();
            mAnimator = ValueAnimator.ofFloat(screenHeight,screenHeight-400);
            mAnimator.setDuration(1000);
            mAnimator.setRepeatCount(0);
        }
        else  if(isdown)
        {
            //  pathMeasure = new PathMeasure(p, true);
            mAnimator = new ValueAnimator();
            mAnimator = ValueAnimator.ofFloat(screenHeight,screenHeight+400);
            mAnimator.setDuration(1000);
            mAnimator.setRepeatCount(0);
        }
        else
        {
            pathMeasure = new PathMeasure(p, true);
            mAnimator = new ValueAnimator();
            mAnimator = ValueAnimator.ofFloat(pathMeasure.getLength(),0f);
            mAnimator.setDuration(duration);
            mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        }

        //  mAnimator.setFloatValues(screenHeight, 0f);
        // mAnimator.setFrameDelay(5000000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setTarget(this);
        mAnimator.addListener(this);
        mAnimator.addUpdateListener(this);
        mAnimator.start();
        dur=duration;

    }
    private float[] coordinates = new float[2];

    /**
     * This method is calling every time when animation is update. Y axis of balloon is then increasing.
     *
     * @param valueAnimator represent object to animate.
     * @see ValueAnimator#getAnimatedValue()
     * @see ValueAnimator.AnimatorUpdateListener#onAnimationUpdate(ValueAnimator)
     */
    @Override
    public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {

        if(istop||isdown)
        {
            float distance = (float) valueAnimator.getAnimatedValue();
            this.setTranslationY(distance);
        }
      else {
            float distance = (float) valueAnimator.getAnimatedValue();
            pathMeasure.getPosTan(distance, coordinates, null);
            this.setTranslationX(coordinates[0]);
            this.setTranslationY(coordinates[1]);
            postInvalidate();
        }
        //System.out.println("xxxxxxxxxx"+valueAnimator.getAnimatedValue());
        if(timer==1)
        {
            // List<String> xx=db.getinfoFroairship(mNextword);
            if (!db.isclickallword())
            {
                while (currword.size() == 0) {

                    currword = db.getinfoFroairship(mNextword);


                }
                appearwords.remove(curword);
                this.curword=currword.get(0);
                appearwords.add(curword);
                // Toast.makeText(getContext(), " appearwords"+appearwords.size(), Toast.LENGTH_LONG).show();

                this.setText(currword.get(0));
                this.typeword=currword.get(1);
                if(typeword.equals("positive"))
                {
                    curunthigh=-400;
                }else if(typeword.equals("negative"))
                {
                    curunthigh=400;
                }else
                {
                    curunthigh=0;
                }
                timer=0;
                timebaloon(1000,false);
                currword = new ArrayList<>();
                // Toast.makeText(this, balloon.curunthigh+"curunthigh"+balloon.getX(), Toast.LENGTH_LONG).show();
//Toast.makeText(this, mNextword+"mNextword", Toast.LENGTH_LONG).show();
                // System.out.println("mNextword"+mNextword);
            }
            else
            {
                Toast.makeText(getContext(), "finished word", Toast.LENGTH_LONG).show();
                timer=0;
            }

        }
        else if(timer==2)
        {
            appearwords.remove(curword);
            this.setText(null);
            this.typeword="null";
            if(typeword.equals("positive"))
            {
                curunthigh=-400;
            }else if(typeword.equals("negative"))
            {
                curunthigh=400;
            }else
            {
                curunthigh=0;
            }
            timer=0;
            timebaloon(1000,true);
        }




    }

    @Override
    public void onAnimationStart(Animator animator) {

    }

    /**
     * This method is called when animation is finished. If user failed to pop balloon, balloon is popped mannualy and user
     * lose one life or lose the game depends on number of life that he has.
     */
  // Boolean istopfinish=false;
    @Override
    public void onAnimationEnd(Animator animator) {
        // if (!mPopped)
        mListener.popBalloon(this, true);


    }

    @Override
    public void onAnimationCancel(Animator animator) {
    }

    @Override
    public void onAnimationRepeat(Animator animator) {
    }
    long mAnimationTime;
    // float widthballon,highballon;
    public void stopAnimation() {
        if(mAnimator != null) {
            mAnimationTime = mAnimator.getCurrentPlayTime();

            mAnimator.cancel();
        }
    }

    public void playAnimation() {
        if (mAnimator != null) {

            mAnimator.start();
            mAnimator.setCurrentPlayTime(mAnimationTime);
        }
    }


    public int timebaloon(int clock, final boolean user) {

        timer=0;
        new CountDownTimer(clock, 1000) {

            public void onTick(long millisUntilFinished) {
                // mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                if(user)
                    timer=1;
                else
                    timer=2;
                // timer=true;

            }

        }.start();
        return timer;

    }
    /**
     * This method is called when user touch the screen. If user pop balloon, method popBalloon is called.
     *
     * @param event represent which action user take.
     * @return boolean which return method onTouchEvent of superclass.
     * @see BalloonListener#popBalloon(Balloon, boolean)
     * @see Activity#onTouchEvent(MotionEvent)
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (!mPopped && event.getAction() == MotionEvent.ACTION_DOWN) {
            if(this.curunthigh!=0) {
                mPopped = true;

                mAnimator.cancel();
                mListener.jumpBalloon(this, true);
            }


        }

        return super.onTouchEvent(event);
    }

    /**
     * This method set state that represent if balloon is popped or not and if it is cancel animation.
     *
     * @param isBalloonPopped indicate if balloon is popped or not.
    // * @see Animation#cancel()
     */
    public void setPopped(boolean isBalloonPopped) {
        mPopped = isBalloonPopped;
        if (isBalloonPopped) mAnimator.cancel();
    }


    /**
     * This interface define one method, popBalloon which is implemented by GamePlayActivity.
     *
     * //@see GameplayActivity#popBalloon(Balloon, boolean)
     */
    public interface BalloonListener {

        /**
         * @param balloon   represent Balloon object which should be popped.
         * @param userTouch boolean which indicate if user pop balloon or not.
         */
        void popBalloon(Balloon balloon, boolean userTouch);

        void jumpBalloon(Balloon balloon, boolean userTouch);

    }
}