package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.
                of("C:\\Users\\Fred\\IdeaProjects\\job4j_design\\1"), duplicatesVisitor);
        for (Path path : duplicatesVisitor.getRsl()) {
            System.out.println(path);
        }
    }
}
