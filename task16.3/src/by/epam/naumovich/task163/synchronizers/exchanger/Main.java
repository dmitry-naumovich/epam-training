package by.epam.naumovich.task163.synchronizers.exchanger;

import java.util.concurrent.Exchanger;

public class Main {

	//программа-пример моделирует обмен валют: продажу долларов клиентом банку
	
	public static void main(String[] args) {
		
		Exchanger<Integer> currencyExchanger1 = new Exchanger<Integer>(); // объект, с помощью которого клиент сдает
																		  // доллары банку
		
		Exchanger<Integer> currencyExchanger2 = new Exchanger<Integer>(); // объект, с помощью которого банк выдает
																		  // рубли клиенту
		
		Runnable client = new Client("Client1", 500, 200000, currencyExchanger1, currencyExchanger2); // клиент
		Runnable bank = new Bank("Bank", 10000, 30000000, currencyExchanger1, currencyExchanger2); // банк
		
		Thread thClient = new Thread(client); // оборачиваем
		Thread thBank = new Thread(bank);  
		
		thClient.start(); // запускаем
		thBank.start();

	}

}
