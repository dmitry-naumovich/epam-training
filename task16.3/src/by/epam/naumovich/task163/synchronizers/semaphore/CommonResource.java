package by.epam.naumovich.task163.synchronizers.semaphore;

public class CommonResource { // просто общий ресурс с одной переменной

	private int resourceVariable = 0;
	
	public CommonResource() {
		
	}
	
	public int getResourceVariable() {
		return resourceVariable;
	}

	public void setResourceVariable(int resourceVariable) {
		this.resourceVariable = resourceVariable;
	}
	
	public void increaseResourceVariable(int amount) { // увеличиваем значение внутренней переменной
		resourceVariable += amount;
	}
	
}
