package com.my.framework.customConfig.entity;

import com.my.framework.customConfig.error.CustomException;

import java.util.Collection;
import java.util.function.Consumer;

public class EntitySetUtil {
    public static <T> void notEmptySet(Consumer<T> function, T value) {
        if (value != null && !value.toString().isEmpty()) {
            function.accept(value);
        }
    }

    public static <T> void notEmptySet(Consumer<Collection<T>> function, Collection<T> value) {
        if (value != null && !value.toString().isEmpty()) {
            function.accept(value);
        }
    }

    public static <T> void notEmptySet(Consumer<T> function, T value, String errorMessage) {
        if (value != null && !value.toString().isEmpty()) {
            function.accept(value);
        } else {
            throw new CustomException(errorMessage);
        }
    }

    public static <T> void notEmptySet(Consumer<Collection<T>> function, Collection<T> value, String errorMessage) {
        if (value != null && !value.toString().isEmpty()) {
            function.accept(value);
        } else {
            throw new CustomException(errorMessage);
        }
    }

    public static <T> void notNullSet(Consumer<T> function, T value) {
        if (value != null) {
            function.accept(value);
        }
    }

    public static <T> void notNullSet(Consumer<Collection<T>> function, Collection<T> value) {
        if (value != null) {
            function.accept(value);
        }
    }

    public static <T> void notNullSet(Consumer<T> function, T value, String errorMessage) {
        if (value != null) {
            function.accept(value);
        } else {
            throw new CustomException(errorMessage);
        }
    }

    public static <T> void notNullSet(Consumer<Collection<T>> function, Collection<T> value, String errorMessage) {
        if (value != null) {
            function.accept(value);
        } else {
            throw new CustomException(errorMessage);
        }
    }
}
