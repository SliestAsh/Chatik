package com.example.chatik;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolader extends RecyclerView.ViewHolder {
    TextView message;
    public ViewHolader(View itemView){
        super(itemView);
        message = itemView.findViewById(R.id.MItem);
    }
}
