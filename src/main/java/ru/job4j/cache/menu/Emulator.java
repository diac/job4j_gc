package ru.job4j.cache.menu;

import ru.job4j.cache.menu.action.*;
import ru.job4j.cache.menu.input.ConsoleInput;
import ru.job4j.cache.menu.input.Input;
import ru.job4j.cache.menu.output.ConsoleOutput;
import ru.job4j.cache.menu.output.Output;

import java.util.Arrays;
import java.util.List;

public class Emulator {

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Output output = new ConsoleOutput();
        List<UserAction> actions = Arrays.asList(
                new SetDirectoryAction(output),
                new CacheFileAction(output),
                new LoadFileContentsAction(output),
                new QuitAction(output)
        );
        Menu menu = new Menu(input, output, actions);
        menu.run();
        System.out.println("DONE!");
    }
}
