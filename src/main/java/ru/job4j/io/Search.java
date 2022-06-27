package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Не заданы параметры");
        }
        if (!chekDir(args[0])) {
            throw new IllegalArgumentException("Не задана дериктория");
        }
        if (!isExtension(args[1])) {
            throw new IllegalArgumentException("Не задано расширение");
        }
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static boolean chekDir(String args) {
        return Paths.get(args).toFile().isDirectory();
    }

    private static boolean isExtension(String args) {
        return args.startsWith(".");
    }

}
