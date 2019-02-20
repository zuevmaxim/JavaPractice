package com.hse.java.serialization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
                return  name == ((Product) obj).name &&
                        hasName == ((Product) obj).hasName &&
                        hasInfo == ((Product) obj).hasInfo &&
                        weight == ((Product) obj).weight &&
                        number == ((Product) obj).number &&
                        id == ((Product) obj).id &&
                        fats == ((Product) obj).fats &&
                        sugar == ((Product) obj).sugar &&
                        vitamin == ((Product) obj).vitamin;
            }
            return false;
        }
    }

    @Test
    void serialize() {

    }

    @Test
    void deserialize() {
    }
}