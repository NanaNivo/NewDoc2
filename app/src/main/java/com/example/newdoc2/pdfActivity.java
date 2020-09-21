package com.example.newdoc2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.newdoc2.ElementMain.devicehigh;

import static com.example.newdoc2.FirstActivity.db;
import static com.example.newdoc2.FirstActivity.yourFilePath;
import static com.example.newdoc2.PdfShowActivity.SAMPLE_FILE;
import static com.example.newdoc2.PdfShowActivity.pdfFileName;

public class pdfActivity extends AppCompatActivity {

    public static String[] osNameList={" مرض الإكتئاب ","مرض القلق "," مرض الضغط النفسي "} ;
    public static String[] osImages;
    public static String[]  showPdf={"depression.pdf","anxiety.pdf","stress.pdf"};

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<listitem> data;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;

    ArrayList<String>temp=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);


        getSupportActionBar().setTitle("التثقيفً");



        //to addback bottum to actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //to chang high dynamic
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) this).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        //if you need three fix imageview in width
        devicehigh = displaymetrics.widthPixels / 2;




        myOnClickListener = new pdfActivity.MyOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        osImages=new String[3];
        osImages[0]=yourFilePath+"dep.png";
        osImages[1]=yourFilePath+"anxiety.jpg";
        osImages[2]=yourFilePath+"stress.jpg";
        System.out.println("nino"+osNameList[0]);
        adapter = new CustomAdapter(this, osNameList, osImages,R.layout.cardviewlayoutpdf,2);
        recyclerView.setAdapter(adapter);

    }

    //override to addback bottum to actionbar
    @Override
    public boolean onSupportNavigateUp(){
        //code it to launch an intent to the activity you want
        finish();
        Intent intent = new Intent(getApplicationContext(), ElementMain.class);
        startActivity(intent);
        return true;
    }



    private class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        public void onClick(View v) {
            clicItem(v);
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
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
                    pdfFileName=osNameList[i];
                }

            }
            SAMPLE_FILE=showPdf[selectedItemId];
          //  Toast.makeText(getBaseContext(),"nnnn"+selectedName,Toast.LENGTH_LONG).show();
            db.updateshowTopdf(selectedName);
            Intent intent = new Intent(getApplicationContext(), PdfShowActivity.class);
            startActivity(intent);


        }

    }
}
