package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.*;

public class UserGenerator implements Generate {

    public static final String PATH_NAMES = "src/main/java/ru/job4j/gc/leak/files/names.txt";
    public static final String PATH_SURNAMES = "src/main/java/ru/job4j/gc/leak/files/surnames.txt";
    public static final String PATH_PATRONS = "src/main/java/ru/job4j/gc/leak/files/patr.txt";

    public static final String SEPARATOR = " ";
    public static final int NEW_USERS = 1000;

    public static List<String> names;
    public static List<String> surnames;
    public static List<String> patrons;
    private static Set<User> users = new HashSet<>();
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
        int currentIndex = 0;
        int randomNumber = random.nextInt(users.size());
        User randomUser = null;
        Iterator<User> iterator =  users.iterator();
        while (iterator.hasNext()) {
            if (currentIndex == randomNumber) {
                randomUser = iterator.next();
                break;
            }
            iterator.next();
            currentIndex++;
        }
        return randomUser;
    }

    public static List<User> getUsers() {
        return new ArrayList<>(users);
    }
}
