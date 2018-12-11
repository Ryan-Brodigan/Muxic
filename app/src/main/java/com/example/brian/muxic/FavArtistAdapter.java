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

public class FavArtistAdapter extends RecyclerView.Adapter<FavArtistAdapter.FavArtistHolder> {

    class FavArtistHolder extends RecyclerView.ViewHolder {
        private final TextView favArtistView;
        private final ImageView artistThumbnail;
        private final RelativeLayout parentLayout;

        private FavArtistHolder(View itemView) {
            super(itemView);
            favArtistView = itemView.findViewById(R.id.itemName);
            artistThumbnail = itemView.findViewById(R.id.imgThumbnail);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

    private final LayoutInflater mInflater;
    private final Context mContext;
    private List<Artist> mArtists = Collections.emptyList(); // Cached copy of artists

    FavArtistAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public FavArtistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new FavArtistHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavArtistHolder holder, final int position) {
        Artist current = mArtists.get(position);
        holder.favArtistView.setText(current.getName());
        Picasso.get().load(current.getImageURL2().get(0)).fit().into(holder.artistThumbnail);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, DetailedArtistViewActivity.class);
                i.putExtra("ArtistID", mArtists.get(position).getID());
                i.putExtra("ArtistName", mArtists.get(position).getName());
                i.putExtra("ArtistUrl", mArtists.get(position).getLastFMUrl());
                i.putExtra("ArtistListeners", mArtists.get(position).getListeners());
                i.putExtra("ArtistPlaycount", mArtists.get(position).getPlaycount());
                i.putExtra("ArtistImage", mArtists.get(position).getImageURL2().get(0));
                mContext.startActivity(i);
            }
        });
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