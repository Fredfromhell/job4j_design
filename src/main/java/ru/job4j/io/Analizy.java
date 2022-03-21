package ru.job4j.io;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Analizy {

    private List<String[]> text;

    public void unavailable(String source, String target) {
        boolean serverState = true;
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            text = read.lines()
                    .map(s -> s.split(" "))
                    .collect(Collectors.toList());

        } catch (
                IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            for (String[] rsl : text) {
                if ((rsl[0].equals("400") || rsl[0].equals("500")) && serverState) {
                    serverState = false;
                    out.printf(rsl[1] + ";");

                }
                if (!serverState && rsl[0].equals("200")) {
                    serverState = true;
                    out.println(rsl[1] + ";");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy rsl = new Analizy();
        rsl.unavailable("server.log", "target.txt");
    }
}
