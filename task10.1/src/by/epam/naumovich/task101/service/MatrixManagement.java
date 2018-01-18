package by.epam.naumovich.task101.service;

import by.epam.naumovich.task101.domain.ResultMatrix;
import by.epam.naumovich.task101.service.exception.MatrixManagementException;
import by.epam.naumovich.task101.thread.MultiplicationBenefitThread;
import by.epam.naumovich.task101.thread.MultiplicationSimpleThread;

public class MatrixManagement {

	private static final String UNBOUNDED_THREADS = "unbounded";
	private static final String TWO_THREADS = "two";
	
	private static int lastGivenIndex = 0;
	private static int[][] firstMatrix;
	private static int[][] secondMatrix;
	
	public int[][] multipleTwoMatrices(int[][] a, int [][] b, String threadNum) throws MatrixManagementException, InterruptedException {
		if (!checkMatrices(a, b)) {
			throw new MatrixManagementException("Check both matrices again! Both must be at least 1 element and "
					+ "columnt count of first matrix must be equal to row count of second matrix");
		}
		firstMatrix = a;
		secondMatrix = b;
		
		ResultMatrix resultMatrix = new ResultMatrix(a.length, b[0].length);
		
		switch (threadNum) {
		case TWO_THREADS:
			multipleWithTwoThreads(resultMatrix);
			break;
		
		case UNBOUNDED_THREADS:
			multipleWithUnboundedThreads(resultMatrix);
			break;
		}
		
		return resultMatrix.getResultMatrix();
	}
	
	public static synchronized boolean takeNextVector(MultiplicationBenefitThread thread) {
		if (lastGivenIndex  == firstMatrix.length) {
			return false;
		}
		thread.setVector(firstMatrix[lastGivenIndex]);
		thread.setvIndex(lastGivenIndex);
		lastGivenIndex++;
		return true;
	}
	
	private void multipleWithTwoThreads(ResultMatrix resultMatrix) throws InterruptedException {
		
		MultiplicationBenefitThread thread1 = new MultiplicationBenefitThread(secondMatrix, resultMatrix);
		thread1.start();
		MultiplicationBenefitThread thread2 = null;
		if (firstMatrix.length > 1) {
			thread2 = new MultiplicationBenefitThread(secondMatrix, resultMatrix);
			thread2.start();
		}
		
		thread1.join();
		if (firstMatrix.length > 1) {
			thread2.join();
		}
	}
	private void multipleWithUnboundedThreads(ResultMatrix resultMatrix) throws InterruptedException {
		MultiplicationSimpleThread[] threads = new MultiplicationSimpleThread[firstMatrix.length];
		
		for (int i = 0; i < firstMatrix.length; i++) {
			threads[i] = new MultiplicationSimpleThread(firstMatrix[i], i, secondMatrix, resultMatrix);
			threads[i].start();
		}
		for (Thread t : threads) {
			t.join();
		}
	}
	
	private static boolean checkMatrices(int[][]a, int[][] b) {
		if (a.length == 0 | b.length == 0) {
			return false;
		}
		
		int aColumnCount = a[0].length;
		int bColumnCount = b[0].length;
		
		if (aColumnCount == 0 | bColumnCount == 0) {
			return false;
		}
		
		for (int i = 1; i < a.length; i++) { // check that all rows contain same number of elements (matrix a)
			if (a[i].length != aColumnCount) {
				return false;
			}
		}
		
		for (int i = 1; i < b.length; i++) { // check that all rows contain same number of elements (matrix b)
			if (b[i].length != bColumnCount) {
				return false;
			}
		}
		
		if (aColumnCount != b.length) { // check that column count of first matrix equals row count of second matrix
			return false;
		}
		
		return true;
	}
	
	@SuppressWarnings("unused")
	private static int[][] transposeMatrix(int[][] x) {
		int[][] transposed = new int[x[0].length][x.length];
		for (int i = 0; i < x[0].length; i++) {
			for (int j = 0; j < x.length; j++) {
				transposed[i][j] = x[j][i];
			}
		}
		return transposed;
	}
}
