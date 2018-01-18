package by.epam.naumovich.task163.locks.condition;

public class Chessplayer2 implements Runnable {

	private ChessDeck deck; // ссылка на доску
	private String name;    // имя шахматиста
	
	public Chessplayer2(String name, ChessDeck deck) {
		this.name = name;
		this.deck = deck;
	}
	
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) { // пока нас не прервут
				
				deck.waitForMoving();   	// ждем
				System.out.println(name + ": I move");
				deck.makeAMove(); 			// ходим
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			System.out.println(name + " I was interrupted");
		}

	}

}
