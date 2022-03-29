package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<String, List<Path>> rsl = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.toFile().isFile()) {
            if (rsl.get(file.getFileName().toString()) == null) {
                rsl.put(file.getFileName()
                        .toString(), new ArrayList<>(List.of(file.toAbsolutePath())));
            } else {
                rsl.get(file.getFileName().toString()).add(file.toAbsolutePath());

            }
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> getRsl() {
        List<Path> list = new ArrayList<>();
        for (String name : rsl.keySet()) {
            if (rsl.get(name).size() > 0) {
                for (Path path : rsl.get(name)) {
                    list.add(path.toAbsolutePath());
                }
            }
        }
        return list;
    }
}

