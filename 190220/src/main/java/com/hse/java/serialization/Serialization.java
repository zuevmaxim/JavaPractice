package com.hse.java.serialization;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Serialization {
    public static void serialize(Object object, OutputStream out) throws IOException, IllegalAccessException {
        try (var dataOut = new ObjectOutputStream(out)) {
            var clazz = object.getClass();
            while (clazz != Object.class) {
                Field[] fields = clazz.getDeclaredFields();
                for (var field : fields) {
                    field.setAccessible(true);
                    var value = field.get(object);
                    dataOut.writeObject(value);
                }
                clazz = clazz.getSuperclass();
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
        Class<?> tmpClass = clazz;
        try (var dataIn = new ObjectInputStream(in)) {
            while (tmpClass != Object.class) {
                Field[] fields = tmpClass.getDeclaredFields();
                for (var field : fields) {
                    field.setAccessible(true);
                    var value = dataIn.readObject();
                    field.set(result, value);
                }
                tmpClass = tmpClass.getSuperclass();
            }
        }
        return result;
    }
}
