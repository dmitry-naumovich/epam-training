package by.epam.naumovich.task163.locks.reentrantlock;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class PatientStudent implements Runnable {

	private String name;
	private Map<String, Integer> records; // журнал
	private Lock lock;
	
	public PatientStudent(String name, Map<String, Integer> map, Lock lock) {
		this.name = name;
		this.records = map;
		this.lock = lock;
	}
	
	@Override
	public void run() {
		lock.lock(); // терпеливый студент дождется возможности взять монитор
		try {
			records.put(name, new Random().nextInt(6) + 4); // запишется туда
			Thread.sleep(40); // время на запись
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock(); // обязательно снимет блокировку
		} 
		
	}

}
