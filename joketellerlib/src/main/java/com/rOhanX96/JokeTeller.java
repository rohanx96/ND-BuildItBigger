package com.rOhanX96;

import java.util.ArrayList;
import java.util.Random;

public class JokeTeller {
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

    public static String tellJoke() {
        Random random = new Random();
        return (jokes.get(random.nextInt(jokes.size())));
        //Log.i("endpoints ", " backgound");
    }
}
