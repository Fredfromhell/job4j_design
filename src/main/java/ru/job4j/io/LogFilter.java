package ru.job4j.io;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<?> filter(String file) {
        List<?> rsl = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("log.txt"))) {
            rsl = reader.lines()
                    .map(str -> str.split(" "))
                    .filter(t -> t[8].contains("404"))
                    .map(Arrays::toString)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void save(List<?> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            out.write(String.valueOf(log));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<?> log = logFilter.filter("log.txt");
        save(log, "404.txt");
        System.out.println(log);
    }
}

