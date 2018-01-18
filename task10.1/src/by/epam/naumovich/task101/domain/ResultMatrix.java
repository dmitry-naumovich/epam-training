package by.epam.naumovich.task101.domain;

public class ResultMatrix {

	private int[][] resultMatrix;
	
	public ResultMatrix(int x, int y) {
		resultMatrix = new int[x][y];
	}
	
	public synchronized void writeResult(int x, int y, int result) {
		resultMatrix[x][y] = result;
	}

	public int[][] getResultMatrix() {
		return resultMatrix;
	}
	
}
