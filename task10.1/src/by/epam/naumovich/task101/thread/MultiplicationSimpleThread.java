package by.epam.naumovich.task101.thread;

import by.epam.naumovich.task101.domain.ResultMatrix;

public class MultiplicationSimpleThread extends Thread {

	private int[] vector;
	private int[][] matrix;
	
	private int vIndex;
	
	private ResultMatrix resultMatrix;
	
	public MultiplicationSimpleThread(int[] vector, int vIndex, int[][] matrix, ResultMatrix resultMatrix) {
		this.vector = vector;
		this.matrix = matrix;
		
		this.vIndex = vIndex;
		
		this.resultMatrix = resultMatrix;
	}
	public void setVector(int[] vector) {
		this.vector = vector;
	}

	public void setvIndex(int vIndex) {
		this.vIndex = vIndex;
	}

	@Override
	public void run() {
		int[] tmp = new int[matrix[0].length];
		for (int i = 0; i < matrix[0].length; i++) {
			for (int j = 0; j < vector.length; j++) {
				tmp[i] += vector[j] * matrix[j][i];
			}
			resultMatrix.writeResult(vIndex, i, tmp[i]);
		}
	}
}
