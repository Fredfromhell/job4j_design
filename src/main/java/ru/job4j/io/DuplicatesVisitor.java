package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> rsl = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty key = new FileProperty(file.toFile()
                .length(), file.getFileName().toString());
        if (!rsl.containsKey(key)) {
            rsl.put(key, new ArrayList<>(List
                    .of(file.toAbsolutePath())));
        } else {
            rsl.get(key).add(file.toAbsolutePath());

        }
        return super.visitFile(file, attrs);
    }

    public List<Path> getRsl() {
        List<Path> list = new ArrayList<>();
        for (FileProperty name : rsl.keySet()) {
            if (rsl.get(name).size() > 1) {
                list.addAll(rsl.get(name));
            }
        }
        return list;
    }
}

