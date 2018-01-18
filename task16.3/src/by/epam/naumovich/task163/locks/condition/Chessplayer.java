package by.epam.naumovich.task163.locks.condition;

public class Chessplayer implements Runnable {

	private ChessDeck deck; // ссылка на доску
	private String name;    // имя шахматиста
	
	public Chessplayer(String name, ChessDeck deck) {
		this.name = name;
		this.deck = deck;
	}
	
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {   		 // пока нас не прервут
				System.out.println(name + ": I move"); 
				deck.makeAMove(); 					 // ходим
				Thread.sleep(100);				
				deck.waitForMoving(); // ждем
			}
		} catch (InterruptedException e) {
			System.out.println(name + " I was interrupted");
		}

	}

}
