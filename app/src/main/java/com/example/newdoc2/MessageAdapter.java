package com.example.newdoc2;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.newdoc2.FirstAct.yourFilePath;

public class MessageAdapter extends BaseAdapter {

    List<Message> messages = new ArrayList<Message>();
    Context context;

    public MessageAdapter(Context context) {
        this.context = context;
    }

    public void add(Message message) {
        this.messages.add(message);
       // notifyDataSetChanged(); // to render the list we need to notify
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    // This is the backbone of the class, it handles the creation of single ListView row (chat bubble)
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        MessageViewHolder holder = new MessageViewHolder();
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        Message message = messages.get(i);

        if (message.isBelongsToCurrentUser()) { // this message was sent by us
            convertView = messageInflater.inflate(R.layout.my_masseg, null);
            holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
            holder.imagpro = (CircleImageView) convertView.findViewById(R.id.profilmass);
            convertView.setTag(holder);
            holder.messageBody.setText(message.getText());
            holder.imagpro.setImageBitmap(BitmapFactory.decodeFile( yourFilePath+"profil.jpg"));
        } else { // this message was sent by other
            convertView = messageInflater.inflate(R.layout.their_masseg, null);
            holder.avatar = (View) convertView.findViewById(R.id.avatar);
            holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);


            holder.messageBody.setText(message.getText());
           /* GradientDrawable drawable = (GradientDrawable) holder.avatar.getBackground();
            drawable.setColor(Color.parseColor(message.getMemberData().getColor()));*/
        }

        return convertView;
    }

}

class MessageViewHolder {
    public View avatar;
    public TextView name;
    public CircleImageView imagpro;
    public TextView messageBody;
}
