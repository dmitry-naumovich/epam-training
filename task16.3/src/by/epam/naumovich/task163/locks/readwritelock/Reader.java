package by.epam.naumovich.task163.locks.readwritelock;

import java.util.concurrent.locks.ReadWriteLock;

public class Reader implements Runnable {

	private String name; // имя потока 
	private ReadWriteLock lock; // блокировка
	private double[] array; // общий ресурс
	private boolean workingFlag; // флаг работы

	public Reader(String name, ReadWriteLock lock,  double[] array) {
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
		while (workingFlag) {
			try {
				Thread.sleep(1000); // немного спим
				try {
					lock.readLock().lock(); // берем блокировку на чтение
					readResource(); // читаем
				} finally {
					lock.readLock().unlock(); // снимаем блокировку
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name + ": I've finished reading");
	}

	private void readResource() { // просто выводим массив на консоль
		System.out.println(name + ": I've read data from the resource: " + getStringArray(array));
	}
	
	private String getStringArray(double[] array) { // метод, преобразующий массив в строку
		StringBuffer sb = new StringBuffer(String.valueOf(array[0]));
		for (int i = 1; i < array.length; i++) {
			sb.append(", " + array[i]);
		}
		return sb.toString();
	}
}
