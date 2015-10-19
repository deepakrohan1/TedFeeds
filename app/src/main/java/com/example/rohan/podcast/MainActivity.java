package com.example.rohan.podcast;

import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetFeedsAsync.IGetPodCasts {
    private int i = 0;
    private String type = "L";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<PodCasts> podcastL = new ArrayList<>();
    public static ImageView playButton, pauseButton;
    public static ProgressBar episodeProgress;
    static Boolean isPlaying = false;
    static Handler handlerI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ted_icon);
        actionBar.setTitle("TED Radio Hour Podcast");

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        playButton = (ImageView) findViewById(R.id.imageViewPlayButton);
        pauseButton = (ImageView) findViewById(R.id.imageViewPauseButton);
        episodeProgress = (ProgressBar) findViewById(R.id.progressBarEpisodeLength);

        playButton.setVisibility(View.INVISIBLE);
        pauseButton.setVisibility(View.INVISIBLE);
        episodeProgress.setVisibility(View.INVISIBLE);

//
//        mRecyclerView.setLayoutManager(mLayoutManager);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //Setting Up the Adapter


        new GetFeedsAsync(this).execute("http://www.npr.org/rss/podcast.php?id=510298");
//        getListofPodCasts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
    }

    /////////////////////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_search:
                if (type == "L") {
                    type = "G";
                } else {
                    type = "L";
                }
                playButton.setVisibility(View.INVISIBLE);
                pauseButton.setVisibility(View.INVISIBLE);
                episodeProgress.setVisibility(View.INVISIBLE);
                switchViews();
                Log.d("demo", "search clicked");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    ///////////////////////////
    @Override
    public void getListofPodCasts(ArrayList<PodCasts> podCastsArrayList) {
        for (PodCasts p : podCastsArrayList) {
            Log.d("demo", "Check" + p.toString());
        }
        podcastL = (ArrayList<PodCasts>) podCastsArrayList.clone();
        switchViews();

    }

    ///////////////
    private void switchViews() {
        if (type == "G") {
            Log.d("demo", "inside grid");
            mLayoutManager = new GridLayoutManager(this, 2);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new RecyclerGridViewAdaPod(podcastL, this);
            mRecyclerView.setAdapter(mAdapter);
            if (isPlaying) {
                Log.d("demo", "stopping grid");
                RecycListViewAdaPod.getMediaPlayer().stop();
                isPlaying = false;
            }
        } else if (type == "L") {
            Log.d("demo", "inside list");
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new RecycListViewAdaPod(podcastL, this);
            mRecyclerView.setAdapter(mAdapter);
            if (isPlaying) {
                Log.d("demo", "stopping linear");
                RecyclerGridViewAdaPod.getMediaPlayer().stop();
                isPlaying = false;
            }
        }
    }

    /////////////////////////
    public void playing() {
        pauseButton.setVisibility(View.VISIBLE);
        playButton.setVisibility(View.INVISIBLE);
        episodeProgress.setVisibility(View.VISIBLE);
        isPlaying = true;
        handlerI = new Handler();
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int lCurrentPosition = 0;
                ///////////////////
                if (type == "G") {
                    if (RecyclerGridViewAdaPod.getMediaPlayer() != null) {
                        lCurrentPosition = ((RecyclerGridViewAdaPod.getMediaPlayer().getCurrentPosition()) * 100) / RecyclerGridViewAdaPod.getMediaPlayer().getDuration();
                    }
                } else {
                    if (RecycListViewAdaPod.getMediaPlayer() != null) {
                        lCurrentPosition = ((RecycListViewAdaPod.getMediaPlayer().getCurrentPosition()) * 100) / RecycListViewAdaPod.getMediaPlayer().getDuration();
                    }
                }
                //////////////////////////
                episodeProgress.setProgress(lCurrentPosition);
                handlerI.postDelayed(this, 1000);
            }
        });
    }

    public void playOnClickM(View aView) {
        if (type == "G")
            RecyclerGridViewAdaPod.getMediaPlayer().start();
        else
            RecycListViewAdaPod.getMediaPlayer().start();
        pauseButton.setVisibility(View.VISIBLE);
        playButton.setVisibility(View.INVISIBLE);
    }

    public void pauseOnClickM(View aView) {
        if (type == "G")
            RecyclerGridViewAdaPod.getMediaPlayer().pause();
        else
            RecycListViewAdaPod.getMediaPlayer().pause();
        pauseButton.setVisibility(View.INVISIBLE);
        playButton.setVisibility(View.VISIBLE);
    }
}
