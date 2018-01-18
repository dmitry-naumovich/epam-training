package by.epam.naumovich.task163.synchronizers.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class WorkingThread implements Runnable {

	private CommonResource resource; // общий ресурс
	private Semaphore semaphore; // семафор
	private String name; // имя потока
	
	public WorkingThread(String name, Semaphore semaphore, CommonResource resource) {
		this.semaphore = semaphore;
		this.name = name;
		this.resource = resource;
	}
	
	@Override
	public void run() {
		
		boolean acquired = false;
		
	    try {
	    	acquired = semaphore.tryAcquire(1, TimeUnit.MILLISECONDS); // в течение 1 секунды пробуем получить разрешение
	        															// если семафор его не дал, то мы уходим, не будем ждать
	        
	        if (acquired) {
	            System.out.println(name + ": Semaphore acquired"); // дождались
	            resource.increaseResourceVariable(1); // увеличиваем переменную в ресурсе на единичку
	            Thread.sleep(5); // спим 5 мс
	        } else {
	            System.out.println(name + ": Could not acquire semaphore. I'm leaving"); // уходим
	        }
	        
	    } catch (InterruptedException e) {
	        throw new IllegalStateException(e);
	    
	    } finally { 
	        if (acquired) {
	            semaphore.release(); // если брали семафор, то пора отпускать его, мы свое дело сделали
	            System.out.println(name + ": Semaphore released");
	        }
	    }
		
	}

}
