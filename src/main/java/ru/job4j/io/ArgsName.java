package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Не задано значение");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        Arrays.stream(args).filter(this::checkString)
                .map(e -> e.split("=", 2))
                .forEach(s -> values.put(s[0].substring(1), s[1]));
    }

    private boolean checkString(String string) {
        int count = 0;
        int index = string.indexOf("=");
        for (int i = 0; i < string.length(); i++) {
            if (String.valueOf(string.charAt(i)).equals("=")) {
                count++;
            }
        }
        if (index == -1 || index == 0
                || (count == 1 && string.endsWith("=")) || !string.startsWith("-"))  {
            throw new IllegalArgumentException("Нарушение шаблона файла");
        }
        return true;
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
