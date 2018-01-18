package by.epam.naumovich.task163.locks.reentrantlock;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

	// в примере моделируется ситуация, когда студенты стоят в куче и каждый хочет записаться в некоторый журнал,
	// указав там свое имя и некоторое число от 4 до 10 (например, свою рейтинговую оценку)
	// записываться в журнал можно по одному
	// терпеливые студенты стоят и ждут, пока не получат доступ к журналу
	// нетерпеливые студенты уходят, если не смогли получить доступ в течение некоторого времени
	
	private static final int STUDENTS_AMOUNT = 10; // количество студентов

	public static void main(String[] args) throws InterruptedException {
		
		Lock lock = new ReentrantLock();								// объект-блокировка
		Map<String, Integer> records = new LinkedHashMap<String, Integer>(); // журнал
		
		List<Runnable> students = new ArrayList<Runnable>(STUDENTS_AMOUNT); // создаются студенты 
		for (int i = 0; i < STUDENTS_AMOUNT; i++) {
			Runnable student = null;
			if (i % 2 == 0) {
				student = new PatientStudent("PatientStudent" + (i), records, lock);
			}
			else {
				student = new ImpatientStudent("ImpatientStudent" + (i), records, lock);
			}
			students.add(student);											 // добавляем в коллецию
		}
		
		List<Thread> threads = new ArrayList<Thread>(STUDENTS_AMOUNT); // создаем потоки и запускаем их
		for (int i = 0; i < STUDENTS_AMOUNT; i++) {
			Thread t = new Thread(students.get(i));
			threads.add(t);
			t.start();
		}
		
		Thread.sleep(1000); // поспим, пока все студенты не уйдут и выведем журнал, в котором будут записи
							// только от тех студентов, которые дождались
		
		System.out.println("====== Record table =====");
		Object[] keys = records.keySet().toArray(); // массив ключей (то есть имен студентов)
		for (int i = 0; i < keys.length; i++) {
			System.out.println(keys[i] + ": My mark = " + records.get(keys[i]));
		}

	}

}
