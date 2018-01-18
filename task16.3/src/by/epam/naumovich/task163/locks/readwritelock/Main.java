package by.epam.naumovich.task163.locks.readwritelock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
	
	// пример моделирует ситуацию, когда к одному общему ресурсу ( в данном случае это массив double)
	// обращаются потоки-читатели и один поток-писатель
	// писатель пишет реже, чем читатели читают данные

	private static final int READERS_AMOUNT = 3; // количество читателей
	
	public static void main(String[] args) throws InterruptedException {
		
		ReadWriteLock lock = new ReentrantReadWriteLock(); // блокировка
		double[] array = new double[3]; // общий ресурс
		
		Runnable writer = new Writer("Writer", lock, array); // создается писатель (он один)
		
		List<Runnable> readers = new ArrayList<Runnable>(); // создаются читатели 
		for (int i = 0; i < READERS_AMOUNT; i++) {
			Runnable reader = new Reader("Reader" + (i), lock, array);
			
			readers.add(reader);  // добавляем в коллекцию
		}
		List<Thread> threads = new ArrayList<Thread>(); // создаем потоки и запускаем их
		for (int i = 0; i < READERS_AMOUNT; i++) {
			Thread t = new Thread(readers.get(i));
			threads.add(t);
			t.start();
		}
		
		Thread writerThread = new Thread(writer); // создает пишущий поток
		threads.add(writerThread);
		writerThread.start(); // запускаем
		
		Thread.sleep(5000); // поспим 5 с, пока система поработает немного
		
		
		for (int i = 0; i < READERS_AMOUNT; i++) { // всем потокам ставим флаг работы на false
			Reader reader = (Reader)(readers.get(i)); // чтобы они завершались
			reader.setWorkingFlag(false);
		}
		
		Writer wr = (Writer)writer;
		wr.setWorkingFlag(false);
		

	}

}
