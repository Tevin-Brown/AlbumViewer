package edu.duke.compsci290.albumviewer;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

public class AlbumActivity extends AppCompatActivity {
    public ImageView mImageView;
    public TextView mAlbumName;
    public TextView mArtistName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        Intent receivedIntent = this.getIntent();
        String albumName = receivedIntent.getStringExtra("album_name_key");
        String artistName = receivedIntent.getStringExtra("artist_name_key");
        this.mImageView = this.findViewById(R.id.album_artwork_image_view_songs);
        this.mAlbumName = this.findViewById(R.id.album_name_text_view_songs);
        this.mArtistName = this.findViewById(R.id.artist_name_text_view_songs);
        int drawableId = this.getResources().getIdentifier(albumName.toLowerCase().replaceAll("\\W+", ""), "drawable", this.getPackageName());
        Drawable albumArtwork = this.getDrawable(drawableId);
        this.mImageView.setImageDrawable(albumArtwork);
        this.mAlbumName.setText(albumName);
        this.mArtistName.setText("By " + artistName);
        int songID= this.getResources().getIdentifier(albumName.toLowerCase().replaceAll("\\W+", ""),"array",this.getPackageName());
        String[] songs = this.getResources().getStringArray(songID);

       for(String s: songs){
           Log.d(TAG, "Song:" + s);
       }

        RecyclerView rv = findViewById(R.id.activity_album_recycler_view);
        rv.setAdapter(new SongAdapter(this, songs));
        rv.setLayoutManager(new LinearLayoutManager(this));
    }


}
