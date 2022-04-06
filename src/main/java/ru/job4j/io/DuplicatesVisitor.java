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
        if (file.toFile().isFile()) {
            FileProperty keySearchRsl = null;
            for (FileProperty fileProperty : rsl.keySet()) {
                if (fileProperty.getName().equals(file.getFileName().toString())
                        && fileProperty.getSize() == file.toFile().length()) {
                    keySearchRsl = fileProperty;
                }
            }
            if (rsl.get(keySearchRsl) == null) {
                rsl.put(new FileProperty(file.toFile()
                        .length(), file.getFileName().toString()), new ArrayList<>(List
                        .of(file.toAbsolutePath())));
            } else {
                rsl.get(keySearchRsl).add(file.toAbsolutePath());

            }
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

