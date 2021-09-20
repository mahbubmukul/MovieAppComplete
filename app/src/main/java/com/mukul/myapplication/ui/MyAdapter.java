package com.mukul.myapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mukul.myapplication.R;
import com.mukul.myapplication.model.Result;
import com.mukul.myapplication.ui.home.MovieFragment;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    List<Result> movieList;

    public MyAdapter(Context context, List<Result> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View mItemView = LayoutInflater.from(context)
                .inflate(R.layout.movie_list, parent, false);
        return new MyViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(movieList.get(position).getTitle());

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500"+movieList.get(position).getPosterPath())
                .into(holder.flag);
        holder.rating.setText(String.valueOf(movieList.get(position).getVoteAverage()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(context, DeatailsActivity.class);
                    intent.putExtra("movie_id", movieList.get(position).getId());
                    context.startActivity(intent);
            }
        });
            /*holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, MovieDetail.class);

                    String send = null;
                    try {
                        send = run("https://api.themoviedb.org/3/movie/19404/credits?api_key=3fa9058382669f72dcb18fb405b7a831");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        intent.putExtras(Intent.getIntent(send));
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }

                }
            });*/

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


}