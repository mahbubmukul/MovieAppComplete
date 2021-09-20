package com.mukul.myapplication.ui;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mukul.myapplication.R;
import com.mukul.myapplication.model.CastItem;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastHolder> {

    private Context context;
    private List<CastItem> CastList;

    public CastAdapter(Context context, List<CastItem> casts){
        this.context=context;
        CastList=casts;
    }

    @NonNull
    @Override
    public CastHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cast_view,parent,false);
        return new CastHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CastHolder holder, int position) {
        CastItem cast=CastList.get(position);

        System.out.println("Json_response "+cast.getName() +" "+" "+cast.getProfilePath());
        holder.textView.setText(cast.getName());
        if(cast.getProfilePath() != null)
            Glide.with(context).load("https://image.tmdb.org/t/p/w500"+cast.getProfilePath()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return CastList.size();
    }

    public class CastHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public CastHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.photo);
           textView=itemView.findViewById(R.id.name);

        }
    }
}
