package ru.ydubovitsky.engineerBlog.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class ApplicationVariablesUtils {

    private static final String regexPattern = "[\\s,]";

    public static String[] splitStringToArrayByPattern(String variable) {
        if (!variable.isBlank()) {
            return Set.of(variable.split(regexPattern)).toArray(new String[0]);
        }
        log.error(String.format("Environment variable = %s, is a blank! Check it", variable));
        return new String[0];
    }

}
