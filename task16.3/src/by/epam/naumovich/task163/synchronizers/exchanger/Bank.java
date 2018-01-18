package by.epam.naumovich.task163.synchronizers.exchanger;

import java.util.concurrent.Exchanger;

public class Bank implements Runnable {

	public static final int exchangingRate = 20000; // курс валют (сколько рублей в долларе)
	
	private int dollarsSum; // сумма долларов в банке
	private int rublesSum; // сумма рублей в банке
	private String name; // имя потока
	private Exchanger<Integer> exchanger1;
	private Exchanger<Integer> exchanger2;
	
	
	public Bank(String name, int dollarsSum, int rublesSum, Exchanger<Integer> exchanger1, Exchanger<Integer> exchanger2) {
		this.name = name;
		this.dollarsSum = dollarsSum;
		this.rublesSum = rublesSum;
		this.exchanger1 = exchanger1;
		this.exchanger2 = exchanger2;
	}
	
	@Override
	public void run() {
		try {
			System.out.println(name + ": Waiting for clients. We have " + dollarsSum + "$ and " + rublesSum + " BYR");
			
			int dollarsReceived = exchanger1.exchange(null); // принимаем доллары клиента
			int rublesToSell = exchangingRate * dollarsReceived; // считаем по курсу, сколько это в BYR
			
			if (rublesSum < rublesToSell) { // бывает и такое
				System.out.println(name + ": Sorry, we don't have enought rubles");
				
			}
			else {
				
				System.out.println(name + ": We are ready to exchange your " + dollarsReceived + "$ to " + rublesToSell + " BYR");
				dollarsSum += dollarsReceived; // принимаем доллары
				
				exchanger2.exchange(rublesToSell); // отдаем их
				rublesSum -= rublesToSell; // уменьшаем сумму рублей в банке
				System.out.println(name + ": Exchanging successful. Now we have " + dollarsSum + "$ and " + rublesSum + " BYR");
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
