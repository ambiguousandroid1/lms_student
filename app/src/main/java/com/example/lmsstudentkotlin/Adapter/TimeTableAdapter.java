package com.example.lmsstudentkotlin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lmsstudentkotlin.R;

import java.util.List;

public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.Myholder> {
    final Context context;
    final List<String> class_schedule ;
    final List<String> teachername ;
    final List<String> times ;
    final List<String> subName ;
    final List<String> topic ;

    public TimeTableAdapter(Context context, List<String> class_schedule, List<String> teachername, List<String> times, List<String> subName, List<String> topic) {
        this.context = context;
        this.class_schedule = class_schedule;
        this.teachername = teachername;
        this.times = times;
        this.subName = subName;
        this.topic = topic;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.recyclertimetable,parent,false);
       return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {
//        holder.scheduleR.setText(class_schedule.get(position));
//        holder.timeR.setText(times.get(position));
//        holder.subR.setText(subName.get(position));
//        holder.topicR.setText(topic.get(position));
//        holder.tnR.setText(teachername.get(position));
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class Myholder extends RecyclerView.ViewHolder {
        final TextView scheduleR;
        final TextView timeR;
        final TextView subR;
        final TextView tnR;
        final TextView topicR;
        public Myholder(@NonNull View itemView) {
            super(itemView);

            scheduleR = itemView.findViewById(R.id.scheduleR);
            timeR = itemView.findViewById(R.id.timeR);
            subR = itemView.findViewById(R.id.subAssign);
            tnR = itemView.findViewById(R.id.teachernameAssign);
            topicR = itemView.findViewById(R.id.topicAssign);

        }
    }
}
