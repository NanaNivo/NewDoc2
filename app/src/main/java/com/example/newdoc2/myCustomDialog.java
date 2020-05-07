package com.example.newdoc2;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

public class  myCustomDialog extends Activity {
  View dialogView;
  AlertDialog  alertDialog ;
  Button ok;
  TextView title;
  TextView content;
  public myCustomDialog(Activity a) {
    alertDialog = null;
  }

  public void showDialogg(final Activity a, String titlle, String contentt, String but) {

    AlertDialog.Builder builder = new AlertDialog.Builder(a,R.style.AlertDialogTheme);
    LayoutInflater customInflater = (LayoutInflater)a.getSystemService(LAYOUT_INFLATER_SERVICE);
    ViewGroup viewGroup = a.findViewById(R.id.content);
    View dialogView = customInflater.inflate(R.layout.cusrom_dialog, viewGroup, true);

    builder.setView(dialogView);
    alertDialog = builder.create();
    alertDialog.setCanceledOnTouchOutside(false);
    content=(TextView) dialogView.findViewById(R.id.content);
    // alertDialog.getWindow().requestFeature(Window.FEATURE_CONTEXT_MENU);
    ok=(Button)dialogView.findViewById(R.id.buttonOk);
    title=(TextView) dialogView.findViewById(R.id.title);
    title.setText(titlle);
    content.setText(contentt);
    ok.setText(but);


    //sizeWindow
    DisplayMetrics displayMetrics = new DisplayMetrics();
    a.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    int screenHeight = displayMetrics.heightPixels;
    int screenWidth = displayMetrics.widthPixels;

    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
    lp.copyFrom( alertDialog.getWindow().getAttributes());

// If we need to set background color of activity
// We need to set this before calling show() method
    lp.dimAmount = 0.7f;
    alertDialog.getWindow().setAttributes(lp);
    alertDialog.show();

// we need to set width, height after calling show() method
    Window window =  alertDialog.getWindow();
    window.setLayout(screenWidth - (screenWidth/4), WindowManager.LayoutParams.WRAP_CONTENT);
    alertDialog.show();
    // return customDialog;

  }
}
