package com.example.rohan.podcast;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rohan on 10/17/15.
 */
public class RecyclerViewAdaPod extends RecyclerView.Adapter<RecyclerViewAdaPod.ViewHolder> {


    private ArrayList<PodCasts> mDataSet;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View mView;

        public ViewHolder(View v) {
            super(v);
            mView = v;
        }
    }

    public RecyclerViewAdaPod(ArrayList<PodCasts> mDataSet){
        this.mDataSet = mDataSet;

    }

    @Override
    public RecyclerViewAdaPod.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.d("demo","ctrl adapter");

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.relative_list,viewGroup,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        TextView title = (TextView) viewHolder.mView.findViewById(R.id.textViewTitle);
        TextView pubDate = (TextView) viewHolder.mView.findViewById(R.id.textViewDate);

        title.setText(mDataSet.get(i).getTitle());
        pubDate.setText(mDataSet.get(i).getPublishDate());

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
