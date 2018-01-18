package by.epam.naumovich.task163.synchronizers.cyclicbarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class Main {

	private static final int THREAD_NUMBER = 10; // количество потоков-работников
	
	public static void main(String[] args) {
		
		ArrayList<Runnable> runnables = new ArrayList<Runnable>(THREAD_NUMBER); // создаем лист всех раннейблов
		
		Runnable resultThread = new Runnable() { // создаем поток, который запустится после того, как работащие потоки соберутся 
		    public void run() {					 // в одной точке, то есть на барьере. Те потоки перестанут что-либо делать, а 
		    	int n = 0; 						 // этот поток посчитает сумму чисел, взятых из каждого проработавшего потока
		    	if (runnables.isEmpty()) {
		    		System.out.println("Thread list is empty. Nothing to count");
		    	}
		    	else {
			    	for (Runnable r : runnables) {
			    		n += ((WorkingThread)r).getInnerSum(); // считаем сумму всех внутренних переменных потоков
			    	}
			        System.out.println("ResultThread started and counted the total sum of inner sums of all threads which = " + n);
		    	}
		    }
		};
		
		CyclicBarrier barrier = new CyclicBarrier(THREAD_NUMBER, resultThread); // первый параметр - количество потоков, которое
																				// должно собраться на барьере, то есть каждый из них
																				// будет ждать всех остальных
																				// второй параметр - поток, который запустится после
																				// того, как все остальные соберутся на барьере
		
		
		for (int i = 0; i < THREAD_NUMBER; i++) { // создаем объекты класса working thread
			runnables.add(new WorkingThread("Thread" + i, barrier)); // в конструктор передаем ссылку на барьер
        }
		
        List<Thread> threads = new ArrayList<>(); 
        for (int i = 0; i < THREAD_NUMBER; i++) { // оборачиваем эти объекты в потоки и запускаем
            Thread thread = new Thread(runnables.get(i));
            threads.add(thread);
            thread.start();
        }
	}

}
