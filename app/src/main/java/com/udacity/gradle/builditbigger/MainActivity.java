package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.rOhanX96.JokeTeller;
import com.rOhanX96.jokeactivitylib.JokeTellerActivity;
import com.rohanx96.joketellerbackend.myApi.MyApi;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new EndpointsAsyncTask(this,JokeTeller.tellJoke()).execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void tellJoke(View view){
        //Toast.makeText(this, JokeTeller.tellJoke(), Toast.LENGTH_SHORT).show();
        Intent jokeTeller = new Intent(this,JokeTellerActivity.class);
        jokeTeller.putExtra(JokeTellerActivity.INTENT_EXTRA_JOKE,JokeTeller.tellJoke());
        Log.i("Joke: ",JokeTeller.tellJoke());
        startActivity(jokeTeller);
    }

    public static class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
        private static MyApi myApiService = null;
        private Context context;
        String name;
        public EndpointsAsyncTask(Context context, String name){
            this.context = context;
            this.name = name;
        }
        @Override
        protected String doInBackground(Void ... params) {
            //Log.i("endpoints ", " backgound");
            if(myApiService == null) {  // Only do this once
                //Log.i("endpoints ", " initApi");
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.100.90.21:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                myApiService = builder.build();
            }

            try {
                return myApiService.tellJoke().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            //Log.i("endpoints ", " executed");
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
    }
}
