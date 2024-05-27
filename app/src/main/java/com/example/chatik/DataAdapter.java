package com.example.chatik;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<ViewHolader> {
    ArrayList<String> messeges;
    LayoutInflater inf;

    public DataAdapter(Context context, ArrayList<String> messeges) {
        this.messeges = messeges;
        this.inf = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolader onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inf.inflate(R.layout.item_messeng,parent,false);
        return new ViewHolader(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolader holder, int position) {
        String mesg = messeges.get(position);
        holder.message.setText(mesg);
    }

    @Override
    public int getItemCount() {
        return messeges.size();
    }
}
