package by.epam.naumovich.task102.thread;

public class MultiplicationThread implements Runnable, IResultGetter {

	private float[] numbers;
	private float result;
	
	public MultiplicationThread(float[] numbers) {
		this.numbers = numbers;
	}
	
	@Override
	public void run() {
		result = numbers[0];
		if (numbers.length > 1) {
			for (int i = 1; i < numbers.length; i++) {
				result *= numbers[i];
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public float getResult() {
		return result;
	}

}
