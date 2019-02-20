package com.hse.java.serialization;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Serialization {
    public static void serialize(Object object, OutputStream out) throws IOException, IllegalAccessException {
        var clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try (var dataOut = new ObjectOutputStream(out)) {
            for (var field : fields) {
                field.setAccessible(true);
                var value = field.get(object);
                dataOut.writeObject(value);
            }
        }

    }

    public static <T> T deserialize(InputStream in, Class<T> clazz) throws IOException, ClassNotFoundException, IllegalAccessException {
        T result = null;
        try {
            result = clazz.getConstructor().newInstance();
        } catch (NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            assert false;
        }
        Field[] fields = clazz.getDeclaredFields();
        try (var dataIn = new ObjectInputStream(in)) {
            for (var field: fields) {
                field.setAccessible(true);
                var value = dataIn.readObject();
                field.set(result, value);
            }
        }
        return null;
    }
}
