package edu.duke.compsci290.albumviewer;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

/**
 * Created by tevin on 1/27/2018.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    public Context mContext;
    public String[] mSongs;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mLinearLayout;
        public TextView mSongName;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mLinearLayout = itemView.findViewById(R.id.album_holder_linear_layout);
            this.mSongName = itemView.findViewById(R.id.song_text_view);
        }
    }
    public SongAdapter(final Context context, String[] songs){
        mContext = context;
        mSongs = songs;
    };
    @Override
    public int getItemCount(){
        return this.mSongs.length;
    };
    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = mInflater.inflate(R.layout.song_viewer, parent, false);
        final SongAdapter.ViewHolder albumHolder = new SongAdapter.ViewHolder(row);

        return albumHolder;
    };
    @Override
    public void onBindViewHolder(SongAdapter.ViewHolder holder, int position){
        holder.mSongName.setText(mSongs[position]);
    };
}

