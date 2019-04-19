package com.example.android.popularmoviesapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmoviesapp.Constants;
import com.example.android.popularmoviesapp.Models.MovieModel;
import com.example.android.popularmoviesapp.R;
import com.example.android.popularmoviesapp.Ui.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    Context context;
    private ArrayList<MovieModel> mMovieModelData;

    public MovieAdapter(Context context, ArrayList<MovieModel> movieModelData) {
        this.mMovieModelData = movieModelData;
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
        final MovieModel image = mMovieModelData.get(position);
        Picasso.get().load(Constants.POSTER_BASE_URL + image.getPosterPath()).into(viewHolder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mMovieModelData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_recycler_item)
        ImageView mImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(Intent.EXTRA_TEXT, mMovieModelData.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }

    }
}
