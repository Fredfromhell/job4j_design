package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        System.out.println(search(start, p -> p.toFile().getName().endsWith(".xml")));
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles();
        return List.of(Files.walkFileTree(root, searcher))
                .stream()
                .filter(condition)
                .collect(Collectors.toList());
    }
}
