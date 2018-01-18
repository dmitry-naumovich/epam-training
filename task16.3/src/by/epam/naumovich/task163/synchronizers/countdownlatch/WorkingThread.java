package by.epam.naumovich.task163.synchronizers.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class WorkingThread implements Runnable {

	private CountDownLatch cdl;
	private String name;
	
	public WorkingThread(String name, CountDownLatch cdl) {
		this.name = name;
		this.cdl = cdl;
	}
	
	@Override
	public void run() {
		try {
			work(); // работаем немного
			cdl.countDown(); // уменьшаем счетчик
		} catch (InterruptedException e) {
			// logging;
			e.printStackTrace();
		}
		
	}
	
	public void work() throws InterruptedException {
		System.out.println(name + ": I'm working 5 ms");
		Thread.sleep(5);
	}

}
