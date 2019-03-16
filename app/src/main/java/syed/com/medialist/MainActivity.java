package syed.com.medialist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import syed.com.medialist.adapter.MediaListAdapter;
import syed.com.medialist.entity.Media;
import syed.com.medialist.viewmodel.MediaViewModel;

public class MainActivity extends AppCompatActivity {

    MediaViewModel mMediaViewModel;
    public static final String MEDIA_URL = "MEDIA_URL";
//    private DataSource.Factory dataSourceFactory;
//    private SimpleExoPlayer player;
//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final MediaListAdapter adapter = new MediaListAdapter(this, new MediaListAdapter.MediaOnItemClickListener() {
            @Override
            public void onItemClickListener(View view, Media media) {
                Intent intent = new Intent(getApplicationContext(), MediaPlayerActivity.class);
                intent.putExtra(MEDIA_URL, media.getUrl());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMediaViewModel = ViewModelProviders.of(this).get(MediaViewModel.class);
        mMediaViewModel.getMediaList().observe(this, new Observer<List<Media>>() {
            @Override
            public void onChanged(@Nullable List<Media> mediaList) {
                adapter.setMediaList(mediaList);
            }
        });


    }
}
