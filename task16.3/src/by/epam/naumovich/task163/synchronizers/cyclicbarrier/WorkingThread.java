package by.epam.naumovich.task163.synchronizers.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class WorkingThread implements Runnable {

	private CyclicBarrier barrier; // ссылка на барьер
	private int innerSum; // внутрення переменная
	private String name; // имя потока
	
	public WorkingThread(String name, CyclicBarrier barrier) {
		this.name = name;
		this.barrier = barrier;
	}
	
	public int getInnerSum() {
		return innerSum;
	}
	public void setInnerSum(int innerSum) {
		this.innerSum = innerSum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		try {
			work(); // поработаем
			
			System.out.println(name + ": I am waiting at barrier");
            this.barrier.await(); // тут будем ждать все остальные потоки, которые должны собраться на барьере
            
            Thread.sleep(1000); // дождались, поспим еще немного
            
		} catch (InterruptedException e){
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
	private void work() {
		this.innerSum += 40; // добавим к переменной число
	}

}
