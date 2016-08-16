package com.udacity.gradle.builditbigger;

import android.test.ActivityInstrumentationTestCase2;
import android.test.AndroidTestCase;
import android.widget.ProgressBar;

import java.util.concurrent.ExecutionException;

/**
 * This class tests the endpoints async task to ensure it does not return a null string
 * Created by rose on 10/8/16.
 */
public class EndpointsAsyncTaskTest extends ActivityInstrumentationTestCase2{

    public EndpointsAsyncTaskTest() {
        super("com.udacity.gradle.builditbigger",MainActivity.class);
    }

    /* Please note for the test to work correctly you need to comment out the code for displaying progress dialog box from the async task*/
    public void testAsyncTask(){
        EndpointsAsyncTask task = new EndpointsAsyncTask(getActivity());
        task.execute();
        String result = null;
        try {
            result = task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertNotNull(result);
    }
}
