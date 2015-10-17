package com.example.rohan.podcast;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rohan on 10/17/15.
 */
public class RecyclerViewAdaPod extends RecyclerView.Adapter<RecyclerViewAdaPod.ViewHolder> {

    private String[] mDataSet;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;

        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public RecyclerViewAdaPod(String[] mDataSet){
        this.mDataSet = mDataSet;

    }

    @Override
    public RecyclerViewAdaPod.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
