package by.epam.naumovich.task163.locks.condition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// пример моделирует шахматную партию
		// два шахматиста ходят по очереди
		// и не могут иметь одновременный доступ к доске
		
		ChessDeck deck = new ChessDeck(); // шахматная доска
		
		ExecutorService exec = Executors.newCachedThreadPool(); // пул потоков
		exec.execute(new Chessplayer("Boris Spassky", deck)); // один шахматист
		exec.execute(new Chessplayer2("Bobby Fisher", deck)); // второй шахматист
		Thread.sleep(20000); // некоторое время играют играют
		exec.shutdownNow(); // прерываем потоки принудительно
		System.out.println("Game over");
		
	}

}
