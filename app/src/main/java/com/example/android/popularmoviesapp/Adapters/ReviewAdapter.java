package com.example.android.popularmoviesapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.popularmoviesapp.R;
import com.example.android.popularmoviesapp.Models.ReviewModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private ArrayList<ReviewModel> mReviewModelArrayList;
    Context context;

    public ReviewAdapter(Context context, ArrayList<ReviewModel> reviewModels) {
        this.mReviewModelArrayList = reviewModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.review_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final ReviewModel contents = mReviewModelArrayList.get(position);
        viewHolder.mAuthorTextView.setText(contents.getAuthor());
        viewHolder.mContentsTextView.setText(contents.getContent());
    }

    @Override
    public int getItemCount() {
        return mReviewModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_author) TextView mAuthorTextView;
        @BindView(R.id.tv_contents) TextView mContentsTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
