package by.epam.naumovich.task163.locks.reentrantlock;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class ImpatientStudent implements Runnable {

	private String name;
	private Map<String, Integer> records; // журнал
	private Lock lock;
	
	public ImpatientStudent(String name, Map<String, Integer> map, Lock lock) {
		this.name = name;
		this.records = map;
		this.lock = lock;
	}
	
	@Override
	public void run() {
		try {
			if (lock.tryLock(120, TimeUnit.MILLISECONDS)) { // попробуем в течение 120 мс взять блокировку
				try {
					records.put(name, new Random().nextInt(6) + 4); // дождались, тогда записываемся
					Thread.sleep(40); // время на запись
				} finally {
					lock.unlock(); // раз уж взяли монитор, освобождаем
				}
			}
			else {
				System.out.println(name + ": I can't wait. Bye"); // не дождались, ну и уходим
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}

}
