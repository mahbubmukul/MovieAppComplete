package com.mukul.myapplication.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mukul.myapplication.R;

public class MyViewHolder extends RecyclerView.ViewHolder{
    View cardView;
    TextView name, rating;
    ImageView flag;


    public MyViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.title);
        rating = itemView.findViewById(R.id.rating);
        flag = itemView.findViewById(R.id.poster);
        cardView = itemView;
    }
}
