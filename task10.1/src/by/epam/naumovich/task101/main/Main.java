package by.epam.naumovich.task101.main;

import by.epam.naumovich.task101.service.MatrixManagement;
import by.epam.naumovich.task101.service.exception.MatrixManagementException;

public class Main {

	private static final String UNBOUNDED_THREADS = "unbounded";
	private static final String TWO_THREADS = "two";
	
	public static void main(String[] args) {
		int [][] a = {
				{1,2,3,3423424,43215,600000},
	            {4,5,6,7,8,9},
	            {7,8,9,10,11,12},
	            {7,8,9,10,11,12},
	            {13,14,15,16,17,18},
	            {10,11,12,13,14,15},
	            {101,121,112,113,114,115},
	            {50,51,52,53,54,55},
	            {110,111,121,134,134,125},
	            {10,11,12,13,14,15},
	            {10,11,12,13,14,13}};
	    int [][] b = {
	    		{10,11,12,13,14,15,172},
	            {13,14,15,16,17,18,183},
	            {16,17,18,19,20,21,101},
	            {10,11,12,13,14,15,202},
	            {13,14,15,16,17,18,91},
	            {1,2,3,4,5,6,44}};
	    
	    MatrixManagement m = new MatrixManagement();
	    int[][] result = new int[a.length][b[0].length];
	    
		try {
			result = m.multipleTwoMatrices(a, b, TWO_THREADS);
		    printMatrix(result);
		} catch (MatrixManagementException e) {
			// logging;
			e.printStackTrace();
		} catch (InterruptedException e) {
			// logging
			e.printStackTrace();
		}

	}
	private static void printMatrix(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

}
