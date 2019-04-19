package com.example.android.popularmoviesapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmoviesapp.Constants;
import com.example.android.popularmoviesapp.Models.TrailerModel;
import com.example.android.popularmoviesapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {
    Context context;
    private ArrayList<TrailerModel> mTrailerModelData;

    public TrailerAdapter(Context context, ArrayList<TrailerModel> trailerModelData) {
        this.mTrailerModelData = trailerModelData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.trailer_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final TrailerModel thumbnail = mTrailerModelData.get(position);
        Picasso.get().load("https://img.youtube.com/vi/" + thumbnail.getmVideoKey() + "/default.jpg").into(viewHolder.mImageView);
        viewHolder.mTextView.setText(mTrailerModelData.get(position).getmVideoTitle());
    }

    @Override
    public int getItemCount() {
        return mTrailerModelData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_trailer)
        ImageView mImageView;
        @BindView(R.id.tv_trailer_name)
        TextView mTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(Constants.YOUTUBE_BASE_URL
                                    + mTrailerModelData.get(getAdapterPosition()).getmVideoKey()));
                    intent.putExtra(Intent.EXTRA_TEXT, mTrailerModelData.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }

    }
}
