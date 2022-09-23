package ru.job4j.cache.menu.action;

import ru.job4j.cache.menu.input.Input;
import ru.job4j.cache.menu.output.Output;

public class QuitAction implements UserAction {

    private final Output output;

    public QuitAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Завершить работу";
    }

    @Override
    public boolean execute(Input input) {
        output.println("Завершение работы...");
        return false;
    }
}
