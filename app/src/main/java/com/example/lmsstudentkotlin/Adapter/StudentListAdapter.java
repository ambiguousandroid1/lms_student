package com.example.lmsstudentkotlin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lmsstudentkotlin.R;
import com.example.lmsstudentkotlin.activity.Profile;

import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.Myholder> {
    final Context context;
    final List<String> name ;
    final List<String> status ;
    final List<String> image ;

    public StudentListAdapter(Context context, List<String> name, List<String> status, List<String> image) {
        this.context = context;
        this.name = name;
        this.status = status;
        this.image = image;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recystudentlist,parent,false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {

        holder.cardLi.setOnClickListener(v-> context.startActivity(new Intent(context, Profile.class)));

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class Myholder extends RecyclerView.ViewHolder {
        final TextView statusLi;
        final TextView nameLi;
        final ImageView imali;
        final CardView cardLi;
        public Myholder(@NonNull View itemView) {
            super(itemView);

            statusLi = itemView.findViewById(R.id.statusLi);
            nameLi = itemView.findViewById(R.id.nameLi);
            imali = itemView.findViewById(R.id.imali);
            cardLi = itemView.findViewById(R.id.cardLis);

        }
    }
}
