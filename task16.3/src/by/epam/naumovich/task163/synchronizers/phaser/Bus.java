package by.epam.naumovich.task163.synchronizers.phaser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;

public class Bus {
	
	//пример моделирует туристический автобус, путешествующий по Европе
	// туристы выходят из автобуса, гуляют по городу, и возвращаются в автобус
	// автобус не может ехать дальше, пока не дождется возвращения всех гуляющих туристов 
	
	private static final String[] TRIP_CITIES = {"Minsk", "Warszhaw", "Berlin", "Paris", "Leon", "Madrid", "Minsk"}; 
	
	private static final int TOURIST_GROUP_SIZE = 5; // количество туристов в группе
	private String name;
	
	{
		name = "Bus";
	}
	
	public static void main(String[] args) {
		
		Bus bus = new Bus(); // создаем автобус
		Phaser phaser = new Phaser(); // создаем фазер, который будет синхронизировать действия
		phaser.register(); // регистрируем автобус у фазера
		
		List<Runnable> busGroup = new ArrayList<Runnable>(TOURIST_GROUP_SIZE); // список туристов в автобусе 
		for (int i = 0; i < TOURIST_GROUP_SIZE; i++) {
			Runnable tourist = new Tourist("Tourist" + (i+1), phaser);
			busGroup.add(tourist);											 // загружаем их в автобус
		}
		
		System.out.println(bus.name + ": The trip has started. We've left " + TRIP_CITIES[0]); // отправляемся
		System.out.println(bus.name + ": Arrived to " + TRIP_CITIES[1]);
		
		List<Thread> threads = new ArrayList<Thread>(TOURIST_GROUP_SIZE); // приехали в первый город, создаем потоки и запускаем их
		for (int i = 0; i < TOURIST_GROUP_SIZE; i++) {
			Thread t = new Thread(busGroup.get(i));
			threads.add(t);
			t.start();
		}
		
		for (int i = 2; i < TRIP_CITIES.length; i++) { // путешествуем по городам
			phaser.arriveAndAwaitAdvance();	
			System.out.println("\n" + bus.name + ": Left " + TRIP_CITIES[i-1] + " and arrived to " + TRIP_CITIES[i]);
			if (i < TRIP_CITIES.length - 1) {
				phaser.arriveAndAwaitAdvance();
			}
		}
		
		System.out.println(bus.name + ": The trip is over");
		
		for (int i = 0; i < TOURIST_GROUP_SIZE; i++) { // сообщаем туристам, что поездка закончилась
			Tourist t = (Tourist)(busGroup.get(i));
			t.setTrip(false);
		}
		phaser.arriveAndDeregister(); // дерегистрируем автобус у синхронизатора
		
	}

}
