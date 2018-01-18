package by.epam.naumovich.task163.locks.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChessDeck {

	private Lock moveLock = new ReentrantLock(); // блокировка на доску
	private Condition condition = moveLock.newCondition(); // объект, с помощью которого 
														   // один шахматист оповещает другого
	private boolean isBusy = true; // флаг, занята ли доска
	
	public void makeAMove() throws InterruptedException { // сделать ход
		moveLock.lock(); // блокируем
		try {
			think();     // думаем
			move(); 	 // ходим
			isBusy = false; // доска свободна
			condition.signal(); // оповещаем об этом другого шахматиста
		} finally {
			moveLock.unlock();
		}
	}
	
	public void waitForMoving() throws InterruptedException { // ждем, пока другой ходит, и думаем
		moveLock.lock(); // блокируем
		try {
			while (isBusy) {
				condition.await(); // ждем, пока занято
			}
		} finally {
			moveLock.unlock();
		}
	}
	private void think() throws InterruptedException {
		Thread.sleep(1000); // думаем, как походить
	}
	
	private void move() throws InterruptedException {
		Thread.sleep(10); // ходим
	}
}
