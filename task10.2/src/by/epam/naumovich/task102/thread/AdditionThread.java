package by.epam.naumovich.task102.thread;

public class AdditionThread implements Runnable, IResultGetter {

	private float[] numbers;
	private float result;
	
	public AdditionThread(float[] numbers) {
		this.numbers = numbers;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < numbers.length; i++) {
			result += numbers[i];
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public float getResult() {
		return result;
	}
}
