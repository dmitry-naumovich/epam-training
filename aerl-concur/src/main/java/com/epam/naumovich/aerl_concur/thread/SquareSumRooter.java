package com.epam.naumovich.aerl_concur.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * Created by Dzmitry_Naumovich on 3/10/2017.
 */
public class SquareSumRooter implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());
    private static final String CLASS_SIMPLE_NAME = SquareSumRooter.class.getSimpleName();
    private static final String EMPTY_RESOURCE_MSG = "The collection is empty yet";
    private static final String INTERRUPTED_MSG = CLASS_SIMPLE_NAME + ": Oops";
    private static final String SUCCESS_MSG = CLASS_SIMPLE_NAME + ": The root of sum of squares of all numbers equals to %s";

    private List<Integer> numbers;
    private ReadWriteLock lock;

    public SquareSumRooter(List<Integer> numbers, ReadWriteLock lock) {
        this.numbers = numbers;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            sleepMuch();
            lock.readLock().lock();
            squareSumRootNumbers();
            lock.readLock().unlock();
        }
    }

    private void squareSumRootNumbers() {
        if (numbers.isEmpty()) {
            LOGGER.debug(EMPTY_RESOURCE_MSG);
        } else {
            int squareSum = 0;
            for (Integer n : numbers) {
                squareSum += n * n;
            }
            LOGGER.debug(String.format(SUCCESS_MSG, String.format("%.3f", Math.sqrt(squareSum))));
        }

    }

    private void sleepMuch() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOGGER.error(INTERRUPTED_MSG);
        }
    }
}
