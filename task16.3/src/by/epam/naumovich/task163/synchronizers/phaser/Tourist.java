package by.epam.naumovich.task163.synchronizers.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;

public class Tourist implements Runnable {

	private String name;
	private Phaser phaser;
	private boolean tripIsGoing; // флаг, продолжается ли еще поездка

	public Tourist(String name, Phaser phaser) {
		this.name = name;
		this.phaser = phaser;
		this.phaser.register(); // регистрируемся у фазера
		tripIsGoing = true; // поездка началась
	}

	public void setTrip(boolean tripIsGoing) {
		this.tripIsGoing = tripIsGoing;
	}
	
	@Override
	public void run() {
		
		while(tripIsGoing) { // пока поездка продолжается
			try {
				getOffBus(); // турист выходит из автобуса
				walk(); // гуляет по городу
				returnToBus(); // возвращается в автобус
				
				phaser.arriveAndAwaitAdvance(); // ждет возвращения остальных
				phaser.arriveAndAwaitAdvance(); // автобус в пути, турист ждет следующего города
				
			} catch (InterruptedException e) {
				 e.printStackTrace();
			}
		}
		phaser.arriveAndDeregister(); // когда поездка закончилась, убираем регистрацию у фазера
		System.out.println(name + ": Thanks for the trip. Goodbye!");
		
	}
	
	private void getOffBus() throws InterruptedException {
		System.out.println(name + ": I've got off the bus");
		Thread.sleep(new Random().nextInt(100));
	}
	private void walk() throws InterruptedException {
		System.out.println(name + ": I am walking around the city");
		Thread.sleep(new Random().nextInt(1000));
	}

	private void returnToBus() throws InterruptedException {
		System.out.println(name + ": I've returned to bus");
		Thread.sleep(new Random().nextInt(100));
	}
}

