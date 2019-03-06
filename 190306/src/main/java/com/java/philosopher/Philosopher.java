package com.java.philosopher;

import java.util.Random;

import static java.lang.Math.abs;

public class Philosopher {
    private final int i;
    private final Fork leftFork;
    private final Fork rightFork;

    public Philosopher(int number, Fork l, Fork r) {
        i = number;
        if (i % 2 == 0) {
            leftFork = l;
            rightFork = r;
        } else {
            leftFork = r;
            rightFork = l;
        }
    }

    public void lifeCycle() throws InterruptedException {
        final var random = new Random();
        while (true) {
            think(abs(random.nextInt()) % 10);
            eat(abs(random.nextInt()) % 10);
        }
    }

    public void think(int time) throws InterruptedException {
        System.out.println("Philosopher " + i + " is thinking.");
        Thread.sleep(time);
    }

    public void eat(int time) throws InterruptedException {
        System.out.println("Philosopher " + i + " is going to eat.");
        synchronized (leftFork) {
            System.out.println("Philosopher " + i + " took left fork.");
            synchronized (rightFork) {
                System.out.println("Philosopher " + i + " took right fork.");
                System.out.println("Philosopher " + i + " is eating.");
                Thread.sleep(time);
                System.out.println("Philosopher " + i + " finished eating.");
            }
        }
    }
}
