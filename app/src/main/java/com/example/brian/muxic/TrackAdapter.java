package com.example.brian.muxic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TrackAdapter extends BaseAdapter {

    private Context context;
    private List<Track> trackArr;
    LayoutInflater inflater;

    public TrackAdapter(Context context, ArrayList<Track> trackArr){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.trackArr = new ArrayList<Track>();
        this.trackArr.addAll(RetrieveTopTracksTask.libraryList);
    }

    public class ViewHolder{
        protected TextView artistName;
        protected ImageView iv;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return RetrieveTopTracksTask.libraryList.size();
    }

    @Override
    public Object getItem(int position) {
        return RetrieveTopTracksTask.libraryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            inflater = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
            convertView = inflater.inflate(R.layout.new_list,null,true);
            holder.iv = (ImageView) convertView.findViewById(R.id.imgThumbs);
            holder.artistName = (TextView) convertView.findViewById(R.id.artistNames);
            convertView.setTag(holder);
        }else{
            //getTag() returns ViewHolder object set as a tag to the view.
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.get().load(RetrieveTopTracksTask.libraryList.get(position).getImageURL2()).into(holder.iv);
        holder.artistName.setText(RetrieveTopTracksTask.libraryList.get(position).getName());
        return convertView;
    }

    void setTracks(List<Track> tracks){
        trackArr = tracks;
        notifyDataSetChanged();
    }

    //Search Function
    //In filter(String charText) method, text present in edittext of searchview which works as a search query,
    // is passed as a parameter.
    //If length charText is 0, means user have not entered search query and the following will be run:
    public void filter (String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        RetrieveTopTracksTask.libraryList.clear();

        if(charText.length() == 0){
            RetrieveTopTracksTask.libraryList.addAll(trackArr);
        }
        else{
            for(Track trck : trackArr){
                if(trck.getName().toLowerCase(Locale.getDefault()).contains(charText)){
                    RetrieveTopTracksTask.libraryList.add(trck);
                }
            }
        }
        notifyDataSetChanged();
    }
}

