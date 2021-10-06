package com.example.lmsstudentkotlin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lmsstudentkotlin.R;
import com.example.lmsstudentkotlin.activity.Profile;

import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.Myholder> {
    final Context context;
    final List<String> subjects ;
    final List<String> topic ;
    final List<String> desc ;
    final List<String> teacherName;
    final List<String> submissionDate;

    public AssignmentAdapter(Context context, List<String> subjects, List<String> topic, List<String> desc, List<String> teacherName, List<String> submissionDate) {
        this.context = context;
        this.subjects = subjects;
        this.topic = topic;
        this.desc = desc;
        this.teacherName = teacherName;
        this.submissionDate = submissionDate;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyassignment,parent,false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {

        holder.cardNotesSelect.setOnClickListener(v-> context.startActivity(new Intent(context, Profile.class)));

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class Myholder extends RecyclerView.ViewHolder {
        final TextView subNotes;
        final TextView topicNotes;
        final TextView descNotes;
        final TextView teachernameAssign;
        final TextView submissiionAssign;
        final Button viewButtonNotes;
        final CardView cardNotesSelect;

        public Myholder(@NonNull View itemView) {
            super(itemView);
            subNotes = itemView.findViewById(R.id.subAssign);
            topicNotes = itemView.findViewById(R.id.topicAssign);
            descNotes = itemView.findViewById(R.id.descAssign);
            viewButtonNotes = itemView.findViewById(R.id.viewButtonAssign);
            cardNotesSelect = itemView.findViewById(R.id.cardAssign);
            teachernameAssign = itemView.findViewById(R.id.teachernameAssign);
            submissiionAssign = itemView.findViewById(R.id.submissiionAssign);


        }
    }
}
