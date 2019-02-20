package com.hse.java.serialization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SerializationTest {

    private static class Product {
        public String name;
        public boolean hasName;
        private static byte hasInfo = 1;
        short weight;
        final int number = 10;
        public long id;
        private float fats;
        double sugar;
        char vitamin;

        public Product() { };

        public Product(String name, boolean hasName, short weight, long id, float fats, double sugar, char vitamin) {
            this.name = name;
            this.hasName = hasName;
            this.weight = weight;
            this.id = id;
            this.fats = fats;
            this.sugar = sugar;
            this.vitamin = vitamin;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Product) {
                var other = (Product) obj;
                return  name.equals(other.name) &&
                        hasName == other.hasName &&
                        hasInfo == other.hasInfo &&
                        weight == other.weight &&
                        number == other.number &&
                        id == other.id &&
                        fats == other.fats &&
                        sugar == other.sugar &&
                        vitamin == other.vitamin;
            }
            return false;
        }
    }

    private static class Fruit extends Product {
        private String country;

        public Fruit () {
            super();
        }

        public Fruit (String name, boolean hasName, short weight, long id, float fats, double sugar, char vitamin, String country) {
            super(name, hasName, weight, id, fats, sugar, vitamin);
            this.country = country;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Product) {
                var other = (Fruit) obj;
                return super.equals(obj) && country.equals(other.country);
            }
            return false;
        }
    }

    @Test
    void serialize() throws IOException, IllegalAccessException, ClassNotFoundException {
        var product = new Product("Max", true, (short) 3, 454234, (float) 5.9, 5.88, 'a');
        try (var os = new ByteArrayOutputStream()) {
            Serialization.serialize(product, os);
            try (var is = new ByteArrayInputStream(os.toByteArray())) {
                var otherProduct = Serialization.deserialize(is, Product.class);
                assertTrue(product.equals(otherProduct));
            }
        }
    }

    @Test
    void deserialize() {
    }
}