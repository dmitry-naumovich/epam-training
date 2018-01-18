package by.epam.naumovich.task163.synchronizers.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {

	private static final int THREAD_NUMBER = 5;
	
	public static void main(String[] args) throws InterruptedException {
		
		CountDownLatch cdl = new CountDownLatch(THREAD_NUMBER);
		
		ArrayList<Runnable> runnables = new ArrayList<Runnable>(THREAD_NUMBER);
		
		for (int i = 0; i < THREAD_NUMBER; i++) { // создаем объекты класса working thread
			runnables.add(new WorkingThread("Thread" + i, cdl));
        }
        List<Thread> threads = new ArrayList<>(); 
        for (int i = 0; i < THREAD_NUMBER; i++) { // оборачиваем эти объекты в потоки и запускаем
            Thread thread = new Thread(runnables.get(i));
            threads.add(thread);
            thread.start();
        }
        
        cdl.await(); // главный поток будет ждать, пока счетчик дойдет до 0 (пока все 5 дочерних не отработают)

	}

}
