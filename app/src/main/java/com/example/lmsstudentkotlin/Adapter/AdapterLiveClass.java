package com.example.lmsstudentkotlin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.lmsstudentkotlin.R;

import java.util.List;

public class AdapterLiveClass extends RecyclerView.Adapter<AdapterLiveClass.Myholder> {
    Context context;
    final List<String> ids ;
    final List<String> classLink ;
    final List<String> topic ;
    final List<String> startDate ;
    final List<String> duration ;
    final List<String> password ;
    final List<String> meetingID ;
    final String subjectName ;

    public AdapterLiveClass(Context context, List<String> ids, List<String> classLink, List<String> topic,
                            List<String> startDate, List<String> duration, List<String> password, List<String> meetingID,
                            String subjectName) {
        this.context = context;
        this.ids = ids;
        this.classLink = classLink;
        this.topic = topic;
        this.startDate = startDate;
        this.duration = duration;
        this.password = password;
        this.meetingID = meetingID;
        this.subjectName = subjectName;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyliveclass, parent, false);
        return new AdapterLiveClass.Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {

        holder.dateLive.setText(startDate.get(position));
        holder.durationLive.setText(duration.get(position));
        holder.meetingLive.setText(meetingID.get(position));
        holder.topicLive.setText(topic.get(position));
        holder.subLive.setText(subjectName);
        holder.passwordLive.setText(password.get(position));

        holder.viewButtonLive.setOnClickListener(v->{
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(classLink.get(position)));
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {
        TextView passwordLive, meetingLive, durationLive, dateLive, topicLive, subLive, viewButtonLive;
        public Myholder(@NonNull View itemView) {
            super(itemView);

            passwordLive = itemView.findViewById(R.id.passwordLive);
            meetingLive = itemView.findViewById(R.id.meetingLive);
            durationLive = itemView.findViewById(R.id.durationLive);
            dateLive = itemView.findViewById(R.id.dateLive);
            topicLive = itemView.findViewById(R.id.topicLive);
            subLive = itemView.findViewById(R.id.subLive);
            viewButtonLive = itemView.findViewById(R.id.viewButtonLive);


        }
    }
}
