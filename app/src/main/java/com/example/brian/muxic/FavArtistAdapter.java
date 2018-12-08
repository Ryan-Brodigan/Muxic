package com.example.brian.muxic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class FavArtistAdapter extends RecyclerView.Adapter<FavArtistAdapter.FavArtistHolder> {

    class FavArtistHolder extends RecyclerView.ViewHolder {
        private final TextView favArtistView;

        private FavArtistHolder(View itemView) {
            super(itemView);
            favArtistView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Artist> mArtists = Collections.emptyList(); // Cached copy of artists

    FavArtistAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public FavArtistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new FavArtistHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavArtistHolder holder, int position) {
        Artist current = mArtists.get(position);
        holder.favArtistView.setText(current.getName());
    }

    void setArtists(List<Artist> words) {
        mArtists = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mArtists.size();
    }
}