package by.epam.naumovich.task163.synchronizers.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;


public class Main {

	private static final int MAX_THREAD_AMOUNT = 5; // ограничение по количеству потоков, которым семафор разрешает одновременно работать
	private static final int THREAD_NUMBER = 30; // общее количество потоков, которые хотят поработать с ресурсом
	private static final boolean IS_FAIR = true; // честный или нечестный будет семафор
	
	public static void main(String[] args) throws InterruptedException {
		
		Semaphore semaphore = new Semaphore(MAX_THREAD_AMOUNT, IS_FAIR); // создаем семафор либо честный, либо нет
		CommonResource resource = new CommonResource();
		List<Runnable> runnables = new ArrayList<Runnable>(THREAD_NUMBER);
		
		for (int i = 0; i < THREAD_NUMBER; i++) { // создаем объекты класса working thread
			runnables.add(new WorkingThread("Thread" + i, semaphore, resource));
        }
        List<Thread> threads = new ArrayList<>(); 
        for (int i = 0; i < THREAD_NUMBER; i++) { // оборачиваем эти объекты в потоки и запускаем
            Thread thread = new Thread(runnables.get(i));
            threads.add(thread);
            thread.start();
        }
        
        Thread.sleep(1000); // поспим секунду, чтобы все доработали
        
        System.out.println("Resource variable = " + resource.getResourceVariable()); // посмотрим, сколько поток добавило 
        																			// по 1 в переменную
        																			//то есть, сколько потоков дождались семафора

	}

}
