package com.dicoding.associate.projectcataloguemovie1;

import android.content.Context;

import android.media.Image;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private ArrayList<MovieItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public MovieAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieItems> items){
        mData = items;
        notifyDataSetChanged();
    }
    public void addItem(final MovieItems item) {
        mData.add(item);
        notifyDataSetChanged();
    }
    public void clearData(){
        mData.clear();
    }
    @Override
    public int getItemViewType(int position) {
        return 0;
    }
    @Override
    public int getViewTypeCount() {
        return 1;
    }
    @Override
    public int getCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }
    @Override
    public MovieItems getItem(int position) {
        return mData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.movie_items, null);
            holder.textViewNamaMovie = (TextView)convertView.findViewById(R.id.textMovie);
            holder.textViewDescription = (TextView)convertView.findViewById(R.id.textDesc);
            holder.textViewReleaseDate = (TextView)convertView.findViewById(R.id.textDate);
            holder.imgPreview = (ImageView)convertView.findViewById(R.id.img_item);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textViewNamaMovie.setText(mData.get(position).getCurrentMovie());
        holder.textViewDescription.setText(mData.get(position).getDescription());
        holder.textViewReleaseDate.setText(mData.get(position).getReleaseDate());
        Glide.with(context)
                .load(mData.get(position).getImgPoster())
                .into(holder.imgPreview);


        return convertView;
    }
    private static class ViewHolder {
        TextView textViewNamaMovie;
        TextView textViewDescription;
        TextView textViewReleaseDate;
        ImageView imgPreview;
    }
}
