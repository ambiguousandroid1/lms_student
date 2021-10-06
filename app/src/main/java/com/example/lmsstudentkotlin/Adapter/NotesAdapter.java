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

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.Myholder> {
    final Context context;
    final List<String> id ;
    final List<String> name ;
    final List<String> desc ;
    final List<String> link ;
    String nameSub ;

    public NotesAdapter(Context context, List<String> id, List<String> name, List<String> desc, List<String> link, String nasub) {
        this.context = context;
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.link = link;
        this.nameSub = nasub;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reconotes,parent,false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {

        holder.descNotes.setText(desc.get(position));
        holder.topicNotes.setText(name.get(position));
        holder.subNotes.setText(nameSub);

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public static class Myholder extends RecyclerView.ViewHolder {
        final TextView subNotes;
        final TextView topicNotes;
        final TextView descNotes;
        final Button viewButtonNotes;
//        final CardView cardNotesSelect;

        public Myholder(@NonNull View itemView) {
            super(itemView);
            subNotes = itemView.findViewById(R.id.subNotesName);
            topicNotes = itemView.findViewById(R.id.topicNotes);
            descNotes = itemView.findViewById(R.id.descNotes);
            viewButtonNotes = itemView.findViewById(R.id.viewButtonNotes);
//            cardNotesSelect = itemView.findViewById(R.id.cardNotes);


        }
    }
}
