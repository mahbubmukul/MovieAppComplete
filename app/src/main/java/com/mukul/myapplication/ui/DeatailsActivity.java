package com.mukul.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.mukul.myapplication.DatabaseHeleper;
import com.mukul.myapplication.R;
import com.mukul.myapplication.model.CasrResponse;
import com.mukul.myapplication.model.MovieDetailsResponse;
import com.mukul.myapplication.model.MovieTableData;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DeatailsActivity extends AppCompatActivity {

    final OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    int movieId;
    MovieDetailsResponse mr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatails);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        movieId =  getIntent().getIntExtra("movie_id", 0);

        ImageView postView = findViewById(R.id.tv_poster_path);
        ImageView covorView = findViewById(R.id.tv_backdrop_path);
        TextView titleView = findViewById(R.id.tv_name);
        TextView overView = findViewById(R.id.overview_detail);


        String response = null;
        try {
            response = run("https://api.themoviedb.org/3/movie/"+movieId+"?api_key=3fa9058382669f72dcb18fb405b7a831");
        } catch (IOException e) {
            e.printStackTrace();
        }



        mr = new Gson().fromJson(response, MovieDetailsResponse.class);

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500"+mr.getPosterPath())
                .into(postView);
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500"+mr.getBackdropPath())
                .into(covorView);

        titleView.setText(mr.getTitle());
        overView.setText(mr.getOverview());

        //for cast
        String response1 = null;
        try {
            response1 = run("https://api.themoviedb.org/3/movie/"+movieId+"/credits?api_key=3fa9058382669f72dcb18fb405b7a831");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Test Cast  "+response1);

        CasrResponse mr1 = new Gson().fromJson(response1, CasrResponse.class);

        RecyclerView countryRecyclerView = findViewById(R.id.castList);
        countryRecyclerView.setHasFixedSize(true);
        countryRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        countryRecyclerView.setAdapter(new CastAdapter(this, mr1.getCast()));

        DatabaseHeleper db = new DatabaseHeleper(this);


        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.insertNote(new MovieTableData((int)mr.getId(), mr.getPosterPath(), mr.getTitle(), mr.getVoteAverage()));
            }
        });


    }



}