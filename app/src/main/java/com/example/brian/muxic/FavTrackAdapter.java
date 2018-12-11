package com.example.brian.muxic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class FavTrackAdapter extends RecyclerView.Adapter<FavTrackAdapter.FavTrackHolder> {

    class FavTrackHolder extends RecyclerView.ViewHolder {
        private final TextView favTrackView;
        private final ImageView trackThumbnail;
        private final RelativeLayout parentLayout;

        private FavTrackHolder(View itemView) {
            super(itemView);
            favTrackView = itemView.findViewById(R.id.itemName);
            trackThumbnail = itemView.findViewById(R.id.imgThumbnail);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

    private final LayoutInflater mInflater;
    private final Context mContext;
    private List<Track> mTracks = Collections.emptyList(); // Cached copy of artists

    FavTrackAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public FavTrackHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new FavTrackHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavTrackHolder holder, final int position) {
        Track current = mTracks.get(position);
        holder.favTrackView.setText(current.getName());
        Picasso.get().load(current.getImageUrl().get(0)).fit().into(holder.trackThumbnail);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, DetailedTrackViewActivity.class);
                i.putExtra("TrackName", mTracks.get(position).getName());
                i.putExtra("ArtistName", mTracks.get(position).getArtistName());
                i.putExtra("TrackPlaycount", mTracks.get(position).getPlaycount());
                i.putExtra("TrackUrl", mTracks.get(position).getLastFMUrl());
                i.putExtra("TrackImage", mTracks.get(position).getImageUrl().get(0));
                mContext.startActivity(i);
            }
        });
    }

    void setTracks(List<Track> words) {
        mTracks = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mTracks.size();
    }
}
