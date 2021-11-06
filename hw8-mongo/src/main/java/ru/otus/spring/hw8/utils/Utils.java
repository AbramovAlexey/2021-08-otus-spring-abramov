package ru.otus.spring.hw8.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Utils {

    public static String stringFromList(List<? extends Object> list) {
        return list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public static boolean checkAnyNull(Object ... objects) {
        return Arrays.stream(objects).anyMatch(Objects::isNull);
    }

}
