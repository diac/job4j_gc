package ru.job4j.cache;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String value = null;
        Path path = Path.of(cachingDir
                + File.separator
                + key
        );
        File file = path.toFile();
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException(String.format("Файл %s не найден", path.getFileName()));
        }
        try (Scanner scanner = new Scanner(file)) {
            StringBuilder builder = new StringBuilder((int) file.length());
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine())
                        .append(System.lineSeparator());
            }
            value = builder.toString();
            put(key, value);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}
