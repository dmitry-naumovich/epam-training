package by.epam.naumovich.task101.thread;

import by.epam.naumovich.task101.domain.ResultMatrix;
import by.epam.naumovich.task101.service.MatrixManagement;

public class MultiplicationBenefitThread extends Thread {

	private int[] vector;
	private int[][] matrix;
	public int myNum = 0;
	private int vIndex;
	
	private ResultMatrix resultMatrix;
	private boolean run;

	public MultiplicationBenefitThread(int[][] matrix, ResultMatrix resultMatrix) {
		this.matrix = matrix;
		this.resultMatrix = resultMatrix;
		run = true;
	}

	public void setVector(int[] vector) {
		this.vector = vector;
	}

	public void setvIndex(int vIndex) {
		this.vIndex = vIndex;
	}

	@Override
	public void run() {
		while (run) {
			if (MatrixManagement.takeNextVector(this)) {
				int[] tmp = new int[matrix[0].length];
				for (int i = 0; i < matrix[0].length; i++) {
					for (int j = 0; j < vector.length; j++) {
						tmp[i] += vector[j] * matrix[j][i];
					}
					
					resultMatrix.writeResult(vIndex, i, tmp[i]);
				}
				
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			else {
				run = false;
			}
		}	
	}
}
