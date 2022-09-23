package ru.job4j.cache.menu.action;

import ru.job4j.cache.menu.input.Input;

public interface UserAction {

    String name();

    boolean execute(Input input);
}