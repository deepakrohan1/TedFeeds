package com.example.rohan.podcast;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by rohan on 10/17/15.
 */
public class RecycListViewAdaPod extends RecyclerView.Adapter<RecycListViewAdaPod.ViewHolder> {

    public static MediaPlayer fMediaPlayer = null;
    private ArrayList<PodCasts> mDataSet;
    private Context mContext;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View mView;

        public ViewHolder(View v) {
            super(v);
            mView = v;
        }
    }

    public RecycListViewAdaPod(ArrayList<PodCasts> mDataSet, Context mContext){
        this.mDataSet = mDataSet;
        this.mContext = mContext;

    }

    @Override
    public RecycListViewAdaPod.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.d("demo","ctrl adapter");

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.relative_list,viewGroup,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final int j = i;
        TextView title = (TextView) viewHolder.mView.findViewById(R.id.textViewTitle);
        TextView pubDate = (TextView) viewHolder.mView.findViewById(R.id.textViewDate);
        ImageView imagelogo = (ImageView)viewHolder.mView.findViewById(R.id.imageViewLogo);

        TextView playNow = (TextView)viewHolder.mView.findViewById(R.id.textViewPlay);
        ImageView playNowLogo = (ImageView)viewHolder.mView.findViewById(R.id.imageViewPlayButton);

        title.setText(mDataSet.get(i).getTitle());
        pubDate.setText(mDataSet.get(i).getPublishDate());
        Picasso.with(mContext).load(mDataSet.get(i).getImageURL()).resize(30, 30).into(imagelogo);
        final PodCasts podCast = mDataSet.get(i);

        playNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playTed(mDataSet.get(j).getUrl());
            }
        });

        playNowLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playTed(mDataSet.get(j).getUrl());
            }
        });

        imagelogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,PlayActivity.class);
                i.putExtra(RecyclerGridViewAdaPod.PODCAST_INFO,podCast);
                mContext.startActivity(i);
            }
        });
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,PlayActivity.class);
                i.putExtra(RecyclerGridViewAdaPod.PODCAST_INFO,podCast);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void playTed(String s){
        if(fMediaPlayer != null) {

            if (fMediaPlayer.isPlaying())
                fMediaPlayer.stop();
            fMediaPlayer.release();
            fMediaPlayer = null;
        }

        fMediaPlayer = new MediaPlayer();

        try {
            fMediaPlayer.setDataSource(s);
            fMediaPlayer.prepare();
            fMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ((MainActivity) mContext).playing();
//        new MainActivity().playing();
//        MainActivity.playing();
    }

    public static MediaPlayer getMediaPlayer(){
        return fMediaPlayer;
    }
}
