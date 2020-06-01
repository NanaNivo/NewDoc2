package com.example.newdoc2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidget.ContentAlignment;

public class  myCustomDialog extends Activity {
  View dialogView;
  AlertDialog alertDialog;
  Button ok;
  TextView title;
  TextView content;
  RadioGroup groupben;
  boolean dialogShown,dialogShown2;
  public myCustomDialog(Activity a) {
    alertDialog = null;
  }

  public void showDialogg(final Activity a, String titlle, String contentt, String but) {



    if(dialogShown)
    {
      return;
    }
else {
      dialogShown = true;


      AlertDialog.Builder builder = new AlertDialog.Builder(a, R.style.AlertDialogTheme);
      LayoutInflater customInflater = (LayoutInflater) a.getSystemService(LAYOUT_INFLATER_SERVICE);
      ViewGroup viewGroup = a.findViewById(R.id.content);
      View dialogView = customInflater.inflate(R.layout.cusrom_dialog, viewGroup, true);

      builder.setView(dialogView);
      alertDialog = builder.create();
      alertDialog.setCanceledOnTouchOutside(false);
      content = (TextView) dialogView.findViewById(R.id.content);
      // alertDialog.getWindow().requestFeature(Window.FEATURE_CONTEXT_MENU);
      ok = (Button) dialogView.findViewById(R.id.buttonOk);
      title = (TextView) dialogView.findViewById(R.id.title);
      title.setText(titlle);
      content.setText(contentt);
      ok.setText(but);


      //sizeWindow
      DisplayMetrics displayMetrics = new DisplayMetrics();
      a.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
      int screenHeight = displayMetrics.heightPixels;
      int screenWidth = displayMetrics.widthPixels;

      WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
      lp.copyFrom(alertDialog.getWindow().getAttributes());

// If we need to set background color of activity
// We need to set this before calling show() method
      lp.dimAmount = 0.7f;
      alertDialog.getWindow().setAttributes(lp);
      alertDialog.show();

// we need to set width, height after calling show() method
      Window window = alertDialog.getWindow();
      window.setLayout(screenWidth - (screenWidth / 4), WindowManager.LayoutParams.WRAP_CONTENT);
      alertDialog.show();
      // return customDialog;
    }
  }


  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
  public void showDialoggben(final Activity a, String titlle, String[] contentt, String but, int sizebenfet) {


    if(dialogShown2)
    {
      return;
    }
    else {
      dialogShown2 = true;
      AlertDialog.Builder builder = new AlertDialog.Builder(a, R.style.AlertDialogTheme);
      LayoutInflater customInflater = (LayoutInflater) a.getSystemService(LAYOUT_INFLATER_SERVICE);
      ViewGroup viewGroup = a.findViewById(R.id.content);
      View dialogView = customInflater.inflate(R.layout.dialog_benfit, viewGroup, true);

      builder.setView(dialogView);
      alertDialog = builder.create();
      alertDialog.setCanceledOnTouchOutside(false);
      groupben = (RadioGroup) dialogView.findViewById(R.id.groupben);
      // alertDialog.getWindow().requestFeature(Window.FEATURE_CONTEXT_MENU);
      ok = (Button) dialogView.findViewById(R.id.buttonOkben);
      title = (TextView) dialogView.findViewById(R.id.titlebn);
      title.setText(titlle);
      addRadioButtons(sizebenfet, contentt, a);
      ok.setText(but);


      //sizeWindow
      DisplayMetrics displayMetrics = new DisplayMetrics();
      a.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
      int screenHeight = displayMetrics.heightPixels;
      int screenWidth = displayMetrics.widthPixels;

      WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
      lp.copyFrom(alertDialog.getWindow().getAttributes());

// If we need to set background color of activity
// We need to set this before calling show() method
      lp.dimAmount = 0.7f;
      alertDialog.getWindow().setAttributes(lp);
      alertDialog.show();

// we need to set width, height after calling show() method
      Window window = alertDialog.getWindow();
      window.setLayout(screenWidth - (screenWidth / 9), screenHeight - (screenHeight / 3));
      alertDialog.show();
      // return customDialog;
    }

  }

  @SuppressLint({"ResourceAsColor", "WrongConstant"})
  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
  public void addRadioButtons(int number, String[] txt, Activity a) {
   groupben.setOrientation(LinearLayout.VERTICAL);
    //
    for (int i = 0; i < number; i++) {
      RadioButton rdbtn = new RadioButton(a);
    rdbtn.setPadding(30, 30, 30, 30);
      rdbtn.setId(View.generateViewId());
      rdbtn.setText(txt[i]);
      rdbtn.setTextColor(R.color.colorblack);
      rdbtn.setTextSize(18);
      rdbtn.setTypeface(null, Typeface.ITALIC);
      rdbtn.setButtonDrawable(R.drawable.radio_bot_drw);
     // rdbtn.setGravity(Gravity.START);
//rdbtn.setTextAlignment(mud);


        RadioGroup.LayoutParams params_soiled = new RadioGroup.LayoutParams(a, null);
   //  params_soiled.setMargins(40, 10, 40, 10);
      rdbtn.setLayoutParams(params_soiled);
      groupben.addView(rdbtn);
    }
  }
}
