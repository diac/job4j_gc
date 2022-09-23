package ru.job4j.cache.menu.action;

import ru.job4j.cache.DirFileCache;
import ru.job4j.cache.menu.input.Input;
import ru.job4j.cache.menu.output.Output;

public class LoadFileContentsAction implements UserFileCacheAction {

    private final Output output;

    private DirFileCache cache;

    public LoadFileContentsAction(Output output) {
        this.output = output;
    }

    @Override
    public void setCache(DirFileCache cache) {
        this.cache = cache;
    }

    @Override
    public String name() {
        return "Получить содержимое файла из кэша";
    }

    @Override
    public boolean execute(Input input) {
        String fileName = input.readString("Введите имя файла: ");
        output.println(cache.get(fileName));
        return true;
    }
}
