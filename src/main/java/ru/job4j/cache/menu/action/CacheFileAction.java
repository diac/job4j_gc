package ru.job4j.cache.menu.action;

import ru.job4j.cache.DirFileCache;
import ru.job4j.cache.menu.input.Input;
import ru.job4j.cache.menu.output.Output;

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
        cache.get(fileName);
        output.println("Файл добавлен в кеш");
        return true;
    }
}
