package com.epam.naumovich.aerl_concur.main;

import com.epam.naumovich.aerl_concur.thread.RandomWriter;
import com.epam.naumovich.aerl_concur.thread.SquareSumRooter;
import com.epam.naumovich.aerl_concur.thread.Summator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Dzmitry_Naumovich on 3/10/2017.
 */
public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());
    private static final String INTERRUPTED_MSG = "Unexpected main thread interruption. Something went wrong!";
    private static final int APP_WORKTIME_SECONDS = 10;

    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        List<Integer> numbers = new ArrayList<>();

        Thread writer = new Thread(new RandomWriter(numbers, lock));
        Thread reader_root = new Thread(new SquareSumRooter(numbers, lock));
        Thread reader_sum = new Thread(new Summator(numbers, lock));

        reader_root.start();
        reader_sum.start();
        writer.start();

        try {
            TimeUnit.SECONDS.sleep(APP_WORKTIME_SECONDS);
        } catch (InterruptedException e) {
            LOGGER.error(INTERRUPTED_MSG);
        }

        System.exit(0);
    }

}
