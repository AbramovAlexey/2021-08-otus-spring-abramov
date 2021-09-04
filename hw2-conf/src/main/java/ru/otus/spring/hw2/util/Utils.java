package ru.otus.spring.hw2.util;

import java.io.InputStream;

public class Utils {

    public static InputStream getResourceByName(String name) {
        ClassLoader classLoader = Utils.class.getClassLoader();
        return classLoader.getResourceAsStream(name);
    }

}
