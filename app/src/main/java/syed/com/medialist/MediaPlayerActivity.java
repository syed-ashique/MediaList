package syed.com.medialist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class MediaPlayerActivity extends AppCompatActivity {
    public static final String TAG = "MediaRepo";

    private PlayerView playerView;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;

    private DataSource.Factory dataSourceFactory;
    private SimpleExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        playerView = findViewById(R.id.player_view);

        Intent intent = getIntent();
        String url;

        if (intent != null) {
            url = intent.getStringExtra(MainActivity.MEDIA_URL);
            Log.d(TAG, url);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //--------------------------------------
        //Creating default track selector
        //and init the player
        TrackSelection.Factory adaptiveTrackSelection = new AdaptiveTrackSelection.Factory();
        //AdaptiveTrackSelectionFactory(new DefaultBandwidthMeter());
        player = ExoPlayerFactory.newSimpleInstance(this,
                new DefaultRenderersFactory(getApplicationContext()),
                new DefaultTrackSelector(new AdaptiveTrackSelection.Factory()),
                new DefaultLoadControl());

        //init the player
        playerView.setPlayer(player);

        //-------------------------------------------------
        DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getApplicationContext(),
                Util.getUserAgent(getApplicationContext(), "MediaPlayar"), defaultBandwidthMeter);

        //-----------------------------------------------
        //Create media source

        String url = "YOUR STREAMING URL HERE";
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra(MainActivity.MEDIA_URL);
            Log.d(TAG, url);
        }
        Uri uri = Uri.parse(url);
        Handler mainHandler = new Handler();

        MediaSource mediaSource = new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
//                .setPlaylistParserFactory(new DefaultHlsPlaylistParserFactory(getOfflineStreamKeys(uri)));


//                new HlsMediaSource(uri,
//                dataSourceFactory, mainHandler, null);
        player.prepare(mediaSource);


        player.setPlayWhenReady(playWhenReady);
        player.addListener(new Player.EventListener() {
//            @Override
//            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {
//
//            }
//
//            @Override
//            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
//
//            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
//                switch (playbackState) {
//                    case ExoPlayer.STATE_READY:
//                        loading.setVisibility(View.GONE);
//                        break;
//                    case ExoPlayer.STATE_BUFFERING:
//                        loading.setVisibility(View.VISIBLE);
//                        break;
//                }
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

//            @Override
//            public void onPlayerError(ExoPlaybackException error) {
//
//            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

//            @Override
//            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
//
//            }

            @Override
            public void onSeekProcessed() {

            }
        });
        player.seekTo(currentWindow, playbackPosition);
        player.prepare(mediaSource, true, false);
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }
}
