package by.epam.naumovich.task162.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

import by.epam.naumovich.task162.util.Util;


public class Philosopher implements Runnable {

	private final Lock waiter; // 
    private final AtomicInteger eaten;
    private final long eatTime;
    private final long thinkTime;
    private int count; // счетчик: философ подсчитывает, сколько раз он покушал

    public Philosopher(Lock waiter, AtomicInteger eaten, long eatTime, long thinkTime) {
        this.waiter = waiter;
        this.eaten = eaten;
        this.eatTime = eatTime;
        this.thinkTime = thinkTime;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) { // работаем, пока нас не прервут
            try {
                waiter.lock(); // пробуем запросить блокировку у официанта
                eat(); // кушаем
            } finally {
                waiter.unlock(); // неважно, получили ли блокировку или нет, в любом случае обязательно отдаем блокировку
                think(); // думаем
            }
        }
        eaten.set(count); // после того как нас прервали, записываем счетчик
    }

    private void eat() { // кушаем
        count++; 
        Util.waitMillis(eatTime); 
    }

    private void think() { // думаем
        Util.waitMillis(thinkTime);
    }

}
