package com.epam.naumovich.aerl_concur.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * Created by Dzmitry_Naumovich on 3/10/2017.
 */
public class RandomWriter implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());
    private static final String CLASS_SIMPLE_NAME = RandomWriter.class.getSimpleName();
    private static final String SUCCESS_MSG = CLASS_SIMPLE_NAME + ": The number %s has been succesfully written";
    private static final String INTERRUPTED_MSG = CLASS_SIMPLE_NAME + ": Oops";

    private List<Integer> numbers;
    private ReadWriteLock lock;

    public RandomWriter(List<Integer> numbers, ReadWriteLock lock) {
        this.numbers = numbers;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            sleepMuch();
            lock.writeLock().lock();
            writeNumber();
            lock.writeLock().unlock();
        }
    }

    private void writeNumber() {
        int randNum = new Random().nextInt(100);
        numbers.add(randNum);
        LOGGER.debug(String.format(SUCCESS_MSG, randNum));
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            LOGGER.error(INTERRUPTED_MSG);
        }
    }

    private void sleepMuch() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            LOGGER.error(INTERRUPTED_MSG);
        }
    }
}
