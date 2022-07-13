package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Driver driver = new Driver(false, 30, new Car("Skoda " + "Rapid"),
                new String[]{"Worker", "Married"});

        final Gson gson = new GsonBuilder().create();
        String json = gson.toJson(driver);
        System.out.println(gson.toJson(driver));

        final Driver personMod = gson.fromJson(json, Driver.class);
        System.out.println(personMod);
    }
}