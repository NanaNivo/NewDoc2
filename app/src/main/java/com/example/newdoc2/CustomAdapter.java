package com.example.newdoc2;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

  //  private ArrayList<listitem> dataSet;

    String [] result;
    Context context;
    String [] imageId;
    LinearLayout len;

   int ll;
   boolean activ;
    private static LayoutInflater inflater=null;
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.os_texts);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.os_images);
        }
    }

    public CustomAdapter(Context context, String[] osNameList, String[] osImages,int l,boolean a) {
        result=osNameList;
        context=context;
        imageId=osImages;
       ll=l;
       activ=a;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(ll, parent, false);
        if(activ) {
            view.setOnClickListener(VidioPage.myOnClickListener);
        }
        if(!activ) {
            view.setOnClickListener(ElementMain.myOnClickListener);
        }

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        ImageView imageView = holder.imageViewIcon;

        textViewName.setText(result[listPosition]);
        imageView.setImageBitmap(BitmapFactory.decodeFile(imageId[listPosition]));

    }

    @Override
    public int getItemCount() {
        return result.length;
    }
    }