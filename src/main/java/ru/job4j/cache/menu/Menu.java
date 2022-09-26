package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;
import ru.job4j.cache.menu.action.SetDirectoryAction;
import ru.job4j.cache.menu.action.UserAction;
import ru.job4j.cache.menu.action.UserFileCacheAction;
import ru.job4j.cache.menu.input.Input;
import ru.job4j.cache.menu.output.Output;

import java.util.List;

public class Menu {

    private final Input input;
    private final Output output;
    private final List<UserAction> actions;

    private DirFileCache cache = null;

    public Menu(Input input, Output output, List<UserAction> actions) {
        this.input = input;
        this.output = output;
        this.actions = actions;
    }

    public void run() {
        boolean run = true;
        while (run) {
            if (cache == null) {
                run = createCache();
            } else {
                run = actions();
            }
        }
    }

    private boolean createCache() {
        SetDirectoryAction action = new SetDirectoryAction(output);
        boolean run = action.execute(input);
        this.cache = action.getCache();
        return run;
    }

    private boolean actions() {
        boolean run;
        System.out.println("Выберите действие: ");
        for (int index = 0; index < actions.size(); index++) {
            output.println(String.format("%d. %s", index, actions.get(index).name()));
        }
        int choice = input.readInt("> ");
        if (choice < 0 || choice >= actions.size()) {
            System.out.println("Ошибка ввода");
        }
        UserAction action = actions.get(choice);
        if ("set".equals(action.cacheBehavior())) {
            ((UserFileCacheAction) action).setCache(cache);
        }
        if ("create".equals(action.cacheBehavior())) {
            run = createCache();
        } else {
            run = action.execute(input);
        }
        return run;
    }
}
