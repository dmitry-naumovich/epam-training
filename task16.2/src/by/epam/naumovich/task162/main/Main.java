package by.epam.naumovich.task162.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import by.epam.naumovich.task162.thread.Philosopher;
import by.epam.naumovich.task162.util.Util;

public class Main {

	public static int N = 5; // количество философов
	static final int SECONDS = 2; // время, которое система работает
    static final List<Philosopher> philosophers = new ArrayList<>(); //array list философов

    public static void main(String[] args) throws InterruptedException {
        run(0, 1);
    }

    private static void run(long eatTime, long thinkTime) { // время в мс, которое каждый философ, соответственно, ест и думает
    	
    	ReentrantLock commonLock = new ReentrantLock(false); // создаем официанта; false в конструкторе означает, что он будет нечестным
    	// то есть планировщик ОС будет выдавать монитор не в том порядке, в каком он запрашивается философами, а рандомно
        AtomicInteger[] eaten = new AtomicInteger[N]; // для подсчета количества раз, которое каждый философ покушал
        for (int i = 0; i < N; i++) { // создаем философов
            eaten[i] = new AtomicInteger(0);
            philosophers.add(new Philosopher(commonLock, eaten[i], eatTime, thinkTime));
        }
        List<Thread> philosopherThreads = new ArrayList<>(); 
        for (int i = 0; i < N; i++) { // создаем потоки-философы и запускаем их
            Thread philosopherThread = new Thread(philosophers.get(i));
            philosopherThreads.add(philosopherThread);
            philosopherThread.start();
        }

        Util.waitMillis(1000L * SECONDS); // ждем, чтобы система проработала 
        philosopherThreads.stream().forEach(Thread::interrupt); // прерываем все потоки

        Util.waitMillis(100); // опять ждем, чтобы все завершились
        Util.printResult(eaten); // выводим результат, сколько раз каждый философ поел
    }
}
