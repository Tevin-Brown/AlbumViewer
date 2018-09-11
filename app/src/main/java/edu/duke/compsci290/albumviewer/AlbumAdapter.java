package edu.duke.compsci290.albumviewer;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

/**
 * Created by tevin on 1/25/2018.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    public Context mContext;
    public String[] mAlbums;
    public String[] mArtists;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mLinearLayout;
        public ImageView mImageView;
        public TextView mAlbumName;
        public TextView mArtistName;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mLinearLayout = itemView.findViewById(R.id.album_holder_linear_layout);
            this.mImageView = itemView.findViewById(R.id.album_artwork_image_view);
            this.mAlbumName = itemView.findViewById(R.id.album_name_text_view);;
            this.mArtistName = itemView.findViewById(R.id.artist_name_text_view);;
        }
    }
    public AlbumAdapter(final Context context, String[] albums, String[] artists){
        mContext = context;
        mAlbums = albums;
        mArtists = artists;
    };
    @Override
    public int getItemCount(){
        return this.mAlbums.length;
    };
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = mInflater.inflate(R.layout.album_holder, parent, false);
        final ViewHolder albumHolder = new ViewHolder(row);
        albumHolder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlbum(mAlbums[albumHolder.getAdapterPosition()], mArtists[albumHolder.getAdapterPosition()]);
            }
        });
        return albumHolder;
    };
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Log.d(TAG, "Position: " + position);
        String albumName = mAlbums[position].toLowerCase().replaceAll("\\W+", "");
        int drawableId = mContext.getResources().getIdentifier(albumName, "drawable", mContext.getPackageName());
        Drawable albumArtwork = mContext.getDrawable(drawableId);
        holder.mImageView.setImageDrawable(albumArtwork);
        holder.mAlbumName.setText(mAlbums[position]);
        holder.mArtistName.setText("By " + mArtists[position]);
    };
    private void openAlbum(String albumName, String artistName) {
        Log.d(TAG, "Opening album " + albumName);
        Intent intent = new Intent(mContext, AlbumActivity.class);
        intent.putExtra("album_name_key", albumName);
        intent.putExtra("artist_name_key", artistName);
        mContext.startActivity(intent);
    }
}
