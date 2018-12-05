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

public class TrackAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Track> trackArr;

    public TrackAdapter(Context context, ArrayList<Track> trackArr){
        this.context = context;
        this.trackArr = trackArr;
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
        return trackArr.size();
    }

    @Override
    public Object getItem(int position) {
        return trackArr.get(position);
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
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService((Context.LAYOUT_INFLATER_SERVICE));
            convertView = inflater.inflate(R.layout.new_list,null,true);
            holder.iv = (ImageView) convertView.findViewById(R.id.imgThumbs);
            holder.artistName = (TextView) convertView.findViewById(R.id.artistNames);

            convertView.setTag(holder);
        }else{
            //getTag() returns ViewHolder object set as a tag to the view.
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.get().load(trackArr.get(position).getImageURL2()).into(holder.iv);
        holder.artistName.setText("Track Name: "+ trackArr.get(position).getName());

        return convertView;
    }

    public class ViewHolder{
        protected TextView artistName;
        protected ImageView iv;
    }
}

