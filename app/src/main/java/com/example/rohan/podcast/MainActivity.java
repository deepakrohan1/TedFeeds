package com.example.rohan.podcast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetFeedsAsync.IGetPodCasts {
    private int i = 0;
    private String type = "L";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<PodCasts> podcastL = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);


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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_search:
                i++;
                if(i%2 != 0){

                    mLayoutManager = new GridLayoutManager(this,2);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    type = "G";
                    switchViews();


                }else {

                    mLayoutManager = new LinearLayoutManager(this);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    type = "L";
                    switchViews();

                }
                Log.d("demo", "search clicked");

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void getListofPodCasts(ArrayList<PodCasts> podCastsArrayList) {
        for(PodCasts p : podCastsArrayList){
            Log.d("demo","Check"+p.toString());
        }
        podcastL = (ArrayList<PodCasts>) podCastsArrayList.clone();
        mAdapter = new RecycListViewAdaPod(podcastL,this);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void switchViews(){
        if(type == "G") {
            Log.d("demo", "inside grid");
            mAdapter = new RecyclerGridViewAdaPod(podcastL, this);
        }else if(type == "L"){
            Log.d("demo", "inside list");
            mAdapter = new RecycListViewAdaPod(podcastL,this);
        }
        mRecyclerView.setAdapter(mAdapter);
    }
}
