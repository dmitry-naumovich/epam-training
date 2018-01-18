package by.epam.naumovich.task163.locks.readwritelock;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;

public class Writer implements Runnable {

	private String name; // имя
	private ReadWriteLock lock; // блокировка
	private double[] array; // общий ресурс
	private boolean workingFlag; // флаг работы
	private Random rand = new Random(31); // генератор рандома
	
	public Writer(String name, ReadWriteLock lock, double[] array) {
		this.name = name;
		this.lock = lock;
		this.array = array;
		workingFlag = true; // при создании флаг true
	}
	
	public void setWorkingFlag(boolean workingFlag) {
		this.workingFlag = workingFlag;
	}
	
	@Override
	public void run() {
		while (workingFlag) { // пока флаг true
			try {
				Thread.sleep(2000); // спим
				try {
					lock.writeLock().lock(); // берем блокировку на запись
					writeToResource(); // пишем в ресурс
				} finally {
					lock.writeLock().unlock(); // обязательно разблокируем
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name + ": I've finished writing");
	}
	
	private void writeToResource() { // пишем в ресурс, просто изменяем значения массива
		for (int i = 0; i < array.length; i++) {
			array[i] += rand.nextInt(100) * 0.5;
		}
		System.out.println(name + ": I've written data to the resource");
	}

}
