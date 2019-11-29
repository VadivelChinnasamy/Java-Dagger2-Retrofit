package com.javadagger2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.javadagger2.dataclass.PopularMovies;
import com.javadagger2.dataclass.Result;

import java.util.ArrayList;
import java.util.List;

class PopularMoviesAdapter  extends RecyclerView.Adapter<PopularMoviesAdapter.MovieViewHolder> {
   private List<Result> mList;
    private Context mContext;

    public PopularMoviesAdapter(  Context mContext,List<Result> mList) {
        this.mList=mList;
        this.mContext= mContext;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_popular_movies,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        holder.rating_txt.setText(""+ mList.get(position).getVoteAverage()+"/10");
        Glide.with(mContext).load("http://image.tmdb.org/t/p/w500/" + mList.get(position).getPosterPath())
                .into(holder.movie_pic);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView rating_txt;
        ImageView movie_pic;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            rating_txt= itemView.findViewById(R.id.rating_txt);
            movie_pic= itemView.findViewById(R.id.movie_pic);


        }
    }
}
