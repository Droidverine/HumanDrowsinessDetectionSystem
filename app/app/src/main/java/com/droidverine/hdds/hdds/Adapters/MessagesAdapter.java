package com.droidverine.hdds.hdds.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.droidverine.hdds.hdds.R;
import com.droidverine.hdds.hdds.models.Messages;

import java.util.Calendar;
import java.util.List;


/**
 * Created by DELL on 25-11-2017.
 */

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.msgholder>
{
    Context context;
    List<Messages> msglist;
    public MessagesAdapter()
    {

    }
    public MessagesAdapter(Context context, List<Messages> msglist)
    {
        this.msglist=msglist;
        this.context=context;

    }
    @Override
    public MessagesAdapter.msgholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.messagelayout,null);
        return new msgholder(view);
    }

    @Override
    public void onBindViewHolder(MessagesAdapter.msgholder holder, final int position) {
        holder.msgtxt.setText(msglist.get(position).getMsgtext());
        holder.msgtitle.setText(msglist.get(position).getMsghead());
        Log.d("bcala",""+msglist.get(position).uid);
        Log.d("bcala",""+msglist.get(position).getUid());

        Calendar now = Calendar.getInstance();
        holder.msgitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,""+msglist.get(position).getUid(),Toast.LENGTH_LONG).show();

            }
        });


        holder.msgtime.setText(DateFormat.format("HH:mm",msglist.get(position).getMsgtime()));

    }

    @Override
    public int getItemCount() {
        return msglist.size();
    }
    class msgholder extends RecyclerView.ViewHolder
    {   TextView msgtitle,msgtxt,msgtime;
    CardView msgitem;

        public msgholder(View itemView) {
            super(itemView);

            msgtitle=(TextView)itemView.findViewById(R.id.msgtitle);
            msgtxt=(TextView)itemView.findViewById(R.id.msgdata);
            msgtime=(TextView)itemView.findViewById(R.id.msgtime);
            msgitem=(CardView)itemView.findViewById(R.id.msgitem);
        }


    }
}