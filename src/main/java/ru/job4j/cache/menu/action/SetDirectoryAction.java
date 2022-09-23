package ru.job4j.cache.menu.action;

import ru.job4j.cache.DirFileCache;
import ru.job4j.cache.menu.input.Input;
import ru.job4j.cache.menu.output.Output;

import java.nio.file.Path;

public class SetDirectoryAction implements UserAction {

    private final Output output;

    private Path directoryPath;

    public SetDirectoryAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Указать кэшируемую директорию";
    }

    public DirFileCache getCache() {
        return new DirFileCache(directoryPath.toFile().getAbsolutePath());
    }

    @Override
    public boolean execute(Input input) {
        boolean validDirectory = false;
        while (!validDirectory) {
            directoryPath = Path.of(input.readString("Введите путь к директории: "));
            validDirectory = directoryPath.toFile().exists() || directoryPath.toFile().exists();
            if (!validDirectory) {
                directoryPath = null;
                output.println("Путь к директории указан неверно");
            }
        }
        return true;
    }
}
