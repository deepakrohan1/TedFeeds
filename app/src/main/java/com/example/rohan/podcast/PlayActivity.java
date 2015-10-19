package com.example.rohan.podcast;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

public class PlayActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener{

    TextView textViewTitle;
    TextView textViewDescription;
    TextView textViewPubDate;
    TextView textViewDuration;
    ImageView imageViewPodcast;
    PodCasts podCast;
    String audioFileLink;
    MediaPlayer mediaP;
    ImageView playButton, pauseButton;
    Boolean isPlayed;
    ProgressBar episodeProgress;
    int episodeDuration;
    static Handler handlerI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_activity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ted_icon);
        actionBar.setTitle("Play!");

        textViewTitle = (TextView)findViewById(R.id.textViewTitle);
        textViewDescription = (TextView)findViewById(R.id.textViewDescription);
        textViewPubDate = (TextView)findViewById(R.id.textViewPubDate);
        textViewDuration = (TextView)findViewById(R.id.textViewDuration);
        imageViewPodcast = (ImageView)findViewById(R.id.imageViewPodcast);
        playButton = (ImageView) findViewById(R.id.imageViewPlayButton);
        pauseButton = (ImageView) findViewById(R.id.imageViewPauseButton);
        episodeProgress = (ProgressBar) findViewById(R.id.progressBarEpisodeLength);

        if(getIntent().getExtras() != null){
            podCast = (PodCasts) getIntent().getExtras().getSerializable(RecyclerGridViewAdaPod.PODCAST_INFO);
        }
        audioFileLink = podCast.getUrl();
        /////////////////////
        String Dura = podCast.getDuration();
        if (Dura == null){
            episodeDuration = 3000 * 1000;
        }else{
            episodeDuration = Integer.parseInt(Dura)*1000;
        }
        ////////////////////
        episodeProgress.setMax(100);
        textViewTitle.setText(podCast.getTitle());
        textViewDescription.setText("Description: "+podCast.getDescription());
        textViewDuration.setText("Duration: " + podCast.getDuration());
        textViewPubDate.setText("Published Date: "+podCast.getPublishDate());

        Picasso.with(this).load(podCast.getImageURL()).resize(250,200).into(imageViewPodcast);

        mediaP = new MediaPlayer();
        pauseButton.setVisibility(View.INVISIBLE);
        isPlayed = false;



    }

    public void playOnClick(View aView){
        if(!isPlayed){
            mediaP.setOnPreparedListener(PlayActivity.this);
            try {
                mediaP.setDataSource(audioFileLink);
                mediaP.prepare();
                mediaP.start();
                isPlayed = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            mediaP.start();
        }

        handlerI = new Handler();
        PlayActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaP !=null){
                    int lCurrentPosition = ((mediaP.getCurrentPosition())*100)/ episodeDuration;
                    episodeProgress.setProgress(lCurrentPosition);
                }

                handlerI.postDelayed(this, 1000);
            }
        });

        playButton.setVisibility(View.INVISIBLE);
        pauseButton.setVisibility(View.VISIBLE);
    }

    public void pauseOnClick(View aView){
        mediaP.pause();

        pauseButton.setVisibility(View.INVISIBLE);
        playButton.setVisibility(View.VISIBLE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_play_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }
}
