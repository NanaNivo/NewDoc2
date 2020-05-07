package com.example.newdoc2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.flatdialoglibrary.dialog.FlatDialog;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.newdoc2.FirstAct.db;

//import android.support.v7.widget.GridLayout;
public class VidioPage extends AppCompatActivity {
   /* ListView gridview;
    LinearLayout len;*/
    public static String[] osNameList ;
    public static String[] osImages;
    public static String[] osvedio;
    public static String path;
  static String yourFilePath ;
 static String  yourvideoPath ;
    static String benfit;
   private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<listitem> data;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vidio_page);

        myOnClickListener = new MyOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        yourFilePath = this.getFilesDir() + "/" + "images" + "/";
        yourvideoPath = this.getFilesDir() + "/" + "vedio" + "/";
        db=new myDbAdapter(this);
        List<listitem>list =db.getinfoFroVed();
        fillmatrix(list);
      System.out.println("nino"+osNameList[0]);
            adapter = new CustomAdapter(this, osNameList, osImages,R.layout.cardviewlayout,true);
            recyclerView.setAdapter(adapter);

        }



       /* gridview = (ListView) findViewById(R.id.customgrid);
        yourFilePath = this.getFilesDir() + "/" + "images" + "/";
        yourvideoPath = this.getFilesDir() + "/" + "vedio" + "/";
        db=new myDbAdapter(this);
        List<listitem>list =db.getinfoFroVed();
        fillmatrix(list);
        gridview.setAdapter(new cusAdap(this, osNameList, osImages));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
               path=osvedio[position];
                  // Send intent to SingleViewActivity
                  Intent i = new Intent(getApplicationContext(), VedioShow.class);
                  // Pass image index
                  i.putExtra("id", position);
                  startActivity(i);


            }
        });

    }*/
    public void fillmatrix(List<listitem>list)
    {
        osNameList=new String[list.size()];
        osImages=new String[list.size()];
        osvedio=new String[list.size()];
        for(int i=0;i<list.size();i++)
        {
            osNameList[i]=list.get(i).title;
            osImages[i]= yourFilePath+list.get(i).img;
            osvedio[i]=yourvideoPath+list.get(i).namVedio;
        }
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

            path=osvedio[ selectedItemId];
            benfit=db.getBenFroVed(selectedName);

           /* final FlatDialog flatDialog = new FlatDialog(VidioPage.this);
            flatDialog.setTitle("Benfit")
                    .setBackgroundColor(context.getResources().getColor(R.color.colorwight))
                    .setSubtitle( benfit)
                    .setTitleColor(context.getResources().getColor(R.color.colorblack))
                    .setSubtitleColor(context.getResources().getColor(R.color.colorblack))
                    .setFirstButtonText("START")
                    .setFirstButtonColor(context.getResources().getColor(R.color.colorAccent))
                    .setSecondButtonText("CANCEL")
                    .setSecondButtonColor(context.getResources().getColor(R.color.colorAccent))
                    .withFirstButtonListner(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(VidioPage.this, "nnnnnn", Toast.LENGTH_SHORT).show();
                            Intent ii = new Intent(VidioPage.this, VedioShow.class);
                            startActivity(ii);
                        }
                    })
                    .withSecondButtonListner(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            flatDialog.dismiss();
                        }
                    })
                    .show();*/
            // Send intent to SingleViewActivity
         /*  Intent ii = new Intent(this.context, VedioShow.class);
            // Pass image index
            ii.putExtra("id", selectedItemPosition);
            startActivity(ii);*/


        }

    }
    }




//}*/
//    }
