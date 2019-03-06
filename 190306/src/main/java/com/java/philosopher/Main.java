package com.java.philosopher;

public class Main {
    public static void main(String[] args) {
        int n = 2;

        Fork[] forks = new Fork[n];
        for (int i = 0; i < n; i++) {
            forks[i] = new Fork(i);
        }

        Philosopher[] philosophers = new Philosopher[n];
        for (int i = 0; i < n; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % n]);
        }

        Thread[] threads = new Thread[n];

        for (int i = 0; i < n; i++) {
            final int j = i;
            threads[i] = new Thread(() -> {
                try {
                    philosophers[j].lifeCycle();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        for (int i = 0; i < n; i++) {
            threads[i].start();
        }
    }
}
