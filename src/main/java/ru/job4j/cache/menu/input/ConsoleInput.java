package ru.job4j.cache.menu.input;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String readString(String promptMessage) {
        System.out.print(promptMessage);
        return scanner.nextLine();
    }

    @Override
    public int readInt(String promptMessage) {
        return Integer.parseInt(readString(promptMessage));
    }
}
