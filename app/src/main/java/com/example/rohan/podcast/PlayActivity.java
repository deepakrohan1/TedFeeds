package com.example.rohan.podcast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PlayActivity extends AppCompatActivity {

    TextView textViewTitle;
    TextView textViewDescription;
    TextView textViewPubDate;
    TextView textViewDuration;
    ImageView imageViewPodcast;
    PodCasts podCast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_activity);

        textViewTitle = (TextView)findViewById(R.id.textViewTitle);
        textViewDescription = (TextView)findViewById(R.id.textViewDescription);
        textViewPubDate = (TextView)findViewById(R.id.textViewPubDate);
        textViewDuration = (TextView)findViewById(R.id.textViewDuration);
        imageViewPodcast = (ImageView)findViewById(R.id.imageViewPodcast);

        if(getIntent().getExtras() != null){
            podCast = (PodCasts) getIntent().getExtras().getSerializable(RecyclerGridViewAdaPod.PODCAST_INFO);
        }

        textViewTitle.setText(podCast.getTitle());
        textViewDescription.setText("Description: "+podCast.getDescription());
        textViewDuration.setText("Duration: "+podCast.getDuration());
        textViewPubDate.setText("Published Date: "+podCast.getPublishDate());

        Picasso.with(this).load(podCast.getImageURL()).resize(250,200).into(imageViewPodcast);

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
}
