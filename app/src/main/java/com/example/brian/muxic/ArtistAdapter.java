package com.example.brian.muxic;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Locale;

public class ArtistAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<Artist> artistArr;
    LayoutInflater inflater;

    public ArtistAdapter(Context context, ArrayList<Artist> artistArr){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.artistArr = new ArrayList<Artist>();
        this.artistArr.addAll(RetrieveTopArtistsTask.artistList);
    }

    public class ViewHolder{
         TextView artistName;
         ImageView iv;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {return position; }

    @Override
    public int getCount() {
        return RetrieveTopArtistsTask.artistList.size();
    }

    @Override
    public Object getItem(int position) {
        return RetrieveTopArtistsTask.artistList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //getView() method will create the each row of the listview.
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
        Picasso.get().load(RetrieveTopArtistsTask.artistList.get(position).getImageURL()).fit().into(holder.iv);
        holder.artistName.setText("Artist Name: "+ RetrieveTopArtistsTask.artistList.get(position).getName());
        return convertView;
    }

    //Search Function
    //In filter(String charText) method, text present in edittext of searchview which works as a search query,
    // is passed as a parameter.
    //If length charText is 0, means user have not entered search query and the following will be run:
    public void filter (String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        RetrieveTopArtistsTask.artistList.clear();

        if(charText.length() == 0){
            RetrieveTopArtistsTask.artistList.addAll(artistArr);
        }
        else{
            for(Artist artst : artistArr){
                if(artst.getName().toLowerCase(Locale.getDefault()).contains(charText)){
                    RetrieveTopArtistsTask.artistList.add(artst);
                }
            }
        }
        notifyDataSetChanged();
    }
}
