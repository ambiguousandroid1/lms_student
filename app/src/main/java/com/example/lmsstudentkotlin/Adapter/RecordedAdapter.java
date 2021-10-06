package com.example.lmsstudentkotlin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.lmsstudentkotlin.R;

import java.util.ArrayList;
import java.util.List;

public class RecordedAdapter extends RecyclerView.Adapter<RecordedAdapter.Myholder> {
    final Context context;
    List<String> id ;

    List<String> saTitle ;
    List<String> saDescription ;
    List<String> saURL ;
    List<String> source ;
    List<String> subID ;
    String subNameSelected;

    public RecordedAdapter(Context context, List<String> id, List<String> saTitle, List<String> saDescription, List<String> saURL, List<String> source, List<String> subID,
    String subNameSelected) {
        this.context = context;
        this.id = id;
        this.saTitle = saTitle;
        this.saDescription = saDescription;
        this.saURL = saURL;
        this.source = source;
        this.subID = subID;
        this.subNameSelected = subNameSelected;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerrecorded,parent,false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {
        holder.descR.setText(saDescription.get(position));
        holder.topicREc.setText(saTitle.get(position));
        holder.subRec.setText(subNameSelected);

        holder.viewButtonR.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(saURL.get(position)));
            context.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public static class Myholder extends RecyclerView.ViewHolder {
        final TextView descR;
        final TextView topicREc;
        final TextView teachername;
        final TextView subRec;
        final Button viewButtonR;
        public Myholder(@NonNull View itemView) {
            super(itemView);

            descR = itemView.findViewById(R.id.descAssign);
            topicREc = itemView.findViewById(R.id.topicAssign);
            teachername = itemView.findViewById(R.id.teachernameAssign);
            subRec = itemView.findViewById(R.id.subAssign);
            viewButtonR = itemView.findViewById(R.id.viewButtonAssign);

        }
    }
}
