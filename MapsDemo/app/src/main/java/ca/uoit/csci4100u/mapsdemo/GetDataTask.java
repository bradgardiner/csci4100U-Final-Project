package ca.uoit.csci4100u.mapsdemo;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Brad on 2017-12-15.
 */

public class GetDataTask extends AsyncTask<String, Void, String> {
    public DataObserve observer;

    public void setObserver(DataObserve observer){
        this.observer = observer;
    }

    @Override
    protected String doInBackground(String... urls){
        String data = "";

        try{
            URL url = new URL(urls[0]);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            InputStream inputRaw = conn.getInputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(inputRaw));
            String line = input.readLine();
            while((line = input.readLine()) != null){
                data = data + line;
            }
            inputRaw.close();
            conn.disconnect();

        }
        catch(IOException e){
            e.printStackTrace();
        }
        return data;
    }
    protected void onPostExecute(String data) {
        // this is the UI thread

        // update the UI with the new data
        observer.updateData(data);
    }

}
