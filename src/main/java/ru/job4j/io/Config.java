package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            read.lines()
                    .filter(diez -> !diez.contains("#"))
                    .filter(e -> !e.isEmpty())
                    .map(s -> s.split("="))
                    .forEach(a -> values.put(a[0], a[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public Map<String, String> getValues() {
        return values;
    }

    public static void main(String[] args) {
        Config config = new Config("./data/IllegalArgumentException.properties");
        System.out.println(config);
        config.load();
        config.values.forEach((k, v) -> System.out.println("Key: " + k + " Value: " + v));
        System.out.println(config.value("name"));

    }
}