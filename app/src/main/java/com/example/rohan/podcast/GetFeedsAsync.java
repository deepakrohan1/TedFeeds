package com.example.rohan.podcast;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by rohan on 10/16/15.
 */
public class GetFeedsAsync extends AsyncTask<String, Void, ArrayList<PodCasts>> {

    IGetPodCasts mactivity;
    ProgressDialog progressDialog;

    public GetFeedsAsync(IGetPodCasts activity) {
        this.mactivity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog((Context)mactivity);
        progressDialog.setMessage("Loading..");
        progressDialog.setTitle("Ted Feeds");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(ArrayList<PodCasts> podCastses) {
        super.onPostExecute(podCastses);
        mactivity.getListofPodCasts(podCastses);
        progressDialog.dismiss();
    }

    @Override
    protected ArrayList<PodCasts> doInBackground(String... params) {

        try {
            Log.d("Demo", "Inside the Async");
            URL url = new URL(params[0]);
            Log.d("demo",params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
                Log.d("Demo", "Response code OK");
                InputStream in = con.getInputStream();
                return XmlParser.XmlParserPodcasts.getList(in);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static interface IGetPodCasts{
        public void getListofPodCasts(ArrayList<PodCasts> podCastsArrayList);
    }
}
