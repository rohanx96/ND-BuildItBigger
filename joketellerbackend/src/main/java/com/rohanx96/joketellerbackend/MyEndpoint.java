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
        jokes.add("Why should I tell you a joke");
        jokes.add("You gotta be kidding me");
        jokes.add("How can jokes be delicious?");
        jokes.add("You need to get high");
        jokes.add("Are you feeling low?");
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
