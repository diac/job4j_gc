package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.*;

public class UserGenerator implements Generate {

    private static final String PATH_NAMES = "src/main/java/ru/job4j/gc/leak/files/names.txt";
    private static final String PATH_SURNAMES = "src/main/java/ru/job4j/gc/leak/files/surnames.txt";
    private static final String PATH_PATRONS = "src/main/java/ru/job4j/gc/leak/files/patr.txt";

    private static final String SEPARATOR = " ";
    private static final int NEW_USERS = 1000;

    private List<String> names;
    private List<String> surnames;
    private List<String> patrons;
    private List<User> users = new ArrayList<>();
    private Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    @Override
    public void generate() {
        users.clear();
        for (int i = 0; i < NEW_USERS; i++) {
            StringBuilder builder = new StringBuilder();
            String userName = builder.append(random.nextInt(surnames.size()))
                    .append(SEPARATOR)
                    .append(names.get(random.nextInt(names.size())))
                    .append(SEPARATOR)
                    .append(patrons.get(random.nextInt(patrons.size())))
                    .toString();
            users.add(new User(userName));
        }
    }

    private void readAll() {
        try {
            names = read(PATH_NAMES);
            surnames = read(PATH_SURNAMES);
            patrons = read(PATH_PATRONS);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }

    public List<User> getUsers() {
        return users;
    }
}
