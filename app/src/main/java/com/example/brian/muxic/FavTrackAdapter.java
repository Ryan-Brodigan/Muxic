package com.example.brian.muxic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class FavTrackAdapter extends RecyclerView.Adapter<FavTrackAdapter.FavTrackHolder> {

    class FavTrackHolder extends RecyclerView.ViewHolder {
        private final TextView favTrackView;

        private FavTrackHolder(View itemView) {
            super(itemView);
            favTrackView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Track> mTracks = Collections.emptyList(); // Cached copy of artists

    FavTrackAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public FavTrackHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new FavTrackHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavTrackHolder holder, int position) {
        Track current = mTracks.get(position);
        holder.favTrackView.setText(current.getName());
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
