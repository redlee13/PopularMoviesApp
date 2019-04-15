package com.example.android.popularmoviesapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private ArrayList<Movie> mMovieData;
    Context context;
    private AdapterView.OnItemClickListener mClickListener;

    public MovieAdapter(Context context, ArrayList<Movie> movieData) {
        this.mMovieData = movieData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Movie image = mMovieData.get(position);
        Picasso.get().load(Constants.POSTER_BASE_URL + image.getPosterPath()).into(viewHolder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mMovieData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_recycler_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(Intent.EXTRA_TEXT, mMovieData.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }

    }
}
