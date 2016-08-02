/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.rohanx96.joketellerbackend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.ArrayList;
import java.util.Random;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "joketellerbackend.rohanx96.com",
    ownerName = "joketellerbackend.rohanx96.com",
    packagePath=""
  )
)
public class MyEndpoint {
    // Stores the various jokes
    public static ArrayList<String> jokes = new ArrayList<>();
    static {
        jokes.add("Doctor: \"I'm sorry but you suffer from a terminal illness and have only 10 to live.\"\n" +
                "Patient: \"What do you mean, 10? 10 what? Months? Weeks?!\"\n" +
                "Doctor: \"Nine.\"\n");
        jokes.add("Mother, “How was school today, Patrick?”\n" +
                "Patrick, “It was really great mum! Today we made explosives!”\n" +
                "Mother, “Ooh, they do very fancy stuff with you these days. And what will you do at school tomorrow?”\n" +
                "Patrick, “What school?”");
        jokes.add("I'd like to buy a new boomerang please. Also, can you tell me how to throw the old one away?\n");
        jokes.add("Pessimist: \"Things just can't get any worse!\"\n" +
                "Optimist: \"Nah, of course they can!\"\n");
        jokes.add("A naked women robbed a bank. Nobody could remember her face.\n");
    }
    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "sayHi")
    public MyJoke sayHi(@Named("name") String name) {
        MyJoke response = new MyJoke();
        response.setData("Hi, " + name);
        return response;
    }

    @ApiMethod(name = "tellJoke")
    public MyJoke tellJoke(){
        MyJoke response = new MyJoke();
        Random random = new Random();
        response.setData(jokes.get(random.nextInt(jokes.size())));
        return response;
    }

}
