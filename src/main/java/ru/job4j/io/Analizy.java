package ru.job4j.io;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Analizy {
    public void unavailable(String source, String target) {
        boolean serverState = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rsl = line.split(" ");
                if (("400".equals(rsl[0]) || "500".equals(rsl[0])) && serverState) {
                    serverState = false;
                    out.printf(rsl[1] + ";");

                }
                if (!serverState && ("200".equals(rsl[0]) || "300".equals(rsl[0]))) {
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
