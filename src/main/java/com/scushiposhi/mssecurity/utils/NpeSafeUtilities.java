package com.scushiposhi.mssecurity.utils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

public final class NpeSafeUtilities {
    public static <T> Stream<T> npeSafeStream(Collection<T> collection) {
        return Optional.ofNullable(collection).map(Collection::stream).orElseGet(Stream::empty);
    }
    public static <T> List<T> npeSafeList(List<T> list){
        return Optional.ofNullable(list).orElseGet(Collections::emptyList);
    }
    public static <T> String npeSafeStringProperty(T object, Function<T,String> getter){
        return Optional.ofNullable(object).map(getter).orElseGet(null);
    }
}
