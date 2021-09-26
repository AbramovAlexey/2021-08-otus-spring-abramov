package ru.otus.spring.hw5.utils;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static String stringFromList(List<? extends Object> list) {
        return list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

}
