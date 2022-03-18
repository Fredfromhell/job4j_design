package ru.job4j.io;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bespalov Alexy
 * @version 0.2
 * Класс описывает простейшее чтение из текстового лог файла,
 * его филтрацию и запись отфилтрованных данных в файл.
 */

public class LogFilter {
    /**
     * Обработка и фильтрация файла с помощью Stream Api.
     *
     * @param file Путь к файлу.
     * @return Отфилтрованный список по тексту 404.
     */
    public List<?> filter(String file) {
        List<?> rsl = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("log.txt"))) {
            rsl = reader.lines()
                    .map(str -> str.split(" "))
                    .filter(t -> t[t.length - 2].equals("404"))
                    .map(Arrays::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void save(List<?> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (Object rsl : log) {
                out.println((String) rsl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<?> log = logFilter.filter("log.txt");
        save(log, "404.txt");
        for (Object rsl : log) {
            System.out.println(rsl);
        }
    }
}

