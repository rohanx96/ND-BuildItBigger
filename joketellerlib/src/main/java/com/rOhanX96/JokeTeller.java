package com.rOhanX96;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.rohanx96.joketellerbackend.myApi.MyApi;

import java.io.IOException;

public class JokeTeller {
    private static MyApi myApiService = null;
    private static String joke = "default";
    public static String tellJoke(){
        //Log.i("endpoints ", " backgound");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
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
                    joke =  myApiService.tellJoke().execute().getData();
                } catch (IOException e) {
                    joke =  e.getMessage();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return joke;
    }
}
