package by.epam.naumovich.task163.synchronizers.exchanger;

import java.util.Random;
import java.util.concurrent.Exchanger;

public class Client implements Runnable {

	private int dollarsSum; // сумма долларов у клиента
	private int rublesSum; // сумма рублей у клиента
	private String name; // имя потока
	private Exchanger<Integer> exchanger1;
	private Exchanger<Integer> exchanger2;
	
	public Client(String name, int dollarsSum, int rublesSum, Exchanger<Integer> exchanger1, Exchanger<Integer> exchanger2) {
		this.name = name;
		this.dollarsSum = dollarsSum;
		this.rublesSum = rublesSum;
		this.exchanger1 = exchanger1;
		this.exchanger2 = exchanger2;
	}
	
	@Override
	public void run() {
		try {
			int dollarsToSell = getSomeDollarSum(); // сколько долларов будем сдавать
			
			System.out.println(name + ": I've got " + dollarsSum + "$ and " + rublesSum + " BYR" + " and I wanna exchange " + dollarsToSell + "$");
			exchanger1.exchange(dollarsToSell); // сдаем доллары, в обмен пока что ничего
			
			System.out.println(name + ": I've given my dollars, now waiting for BYR");
			
			dollarsSum -= dollarsToSell; // уменьшается кол-во долларов клиента
			
			int rublesReceived = exchanger2.exchange(null); // получаем рубли, в обмен ничего не даем (уже сделали это)
			rublesSum += rublesReceived;
			
			System.out.println(name + ": Exchanging successful. Now I have " + dollarsSum + "$ and " + rublesSum + " BYR");
			
		} catch (InterruptedException e) {
			
		}
		
	}
	
	private int getSomeDollarSum() {
		Random rand = new Random();
		return rand.nextInt(dollarsSum);
	}

}
