package ru.job4j.cache.menu.action;

import ru.job4j.cache.DirFileCache;
import ru.job4j.cache.menu.input.Input;
import ru.job4j.cache.menu.output.Output;

import java.io.File;
import java.nio.file.Path;

public class CacheFileAction implements UserFileCacheAction {

    private final Output output;

    private DirFileCache cache;

    public CacheFileAction(Output output) {
        this.output = output;
    }

    @Override
    public void setCache(DirFileCache cache) {
        this.cache = cache;
    }

    @Override
    public String name() {
        return "Загрузить содержимое файла в кэш";
    }

    @Override
    public boolean execute(Input input) {
        String fileName = input.readString("Введите имя файла: ");
        Path path = Path.of(cache.getCachingDir()
                + File.separator
                + fileName
        );
        File file = path.toFile();
        if (!file.exists() || !file.isFile()) {
            output.println(String.format("Файл %s не найден", path.getFileName()));
        } else {
            cache.get(fileName);
            output.println("Файл добавлен в кеш");
        }
        return true;
    }
}
