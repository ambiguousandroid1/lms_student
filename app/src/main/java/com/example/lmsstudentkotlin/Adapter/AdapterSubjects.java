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

public class AdapterSubjects extends RecyclerView.Adapter<AdapterSubjects.Myholder> {
    final Context context;
    final List<String> ids;
    final List<String> subjectName;

    public AdapterSubjects(Context context, List<String> ids, List<String> subjectName) {
        this.context = context;
        this.ids = ids;
        this.subjectName = subjectName;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recysubjects, parent, false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {

        holder.subNameSubjects.setText(subjectName.get(position));
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }

    public static class Myholder extends RecyclerView.ViewHolder {

        final TextView subNameSubjects;

        public Myholder(@NonNull View itemView) {
            super(itemView);

            subNameSubjects = itemView.findViewById(R.id.subNameSubjects);

        }
    }
}
