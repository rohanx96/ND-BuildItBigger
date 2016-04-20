package com.rOhanX96.jokeactivitylib;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by rose on 20/4/16.
 * This activity displays the joke passed to the activity as intent extra
 */
public class JokeTellerActivity extends AppCompatActivity {
    public static final String INTENT_EXTRA_JOKE = "Joke";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_teller);
        String joke = getIntent().getStringExtra(INTENT_EXTRA_JOKE);
        TextView jokeText = (TextView) findViewById(R.id.joke_text);
        Log.i("Joke: ",joke);
        jokeText.setText(joke);
    }
}
