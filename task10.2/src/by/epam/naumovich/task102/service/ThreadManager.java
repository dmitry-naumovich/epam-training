package by.epam.naumovich.task102.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.task102.service.exception.ThreadManagerException;
import by.epam.naumovich.task102.thread.*;

public class ThreadManager {

	private static final String OUT_FILE_NAME = "out.dat";
	private static final String FILE_PREFIX = "in";
	private static final String FILE_EXTENSION = ".dat";
	private static final String SPACE = " ";
	
	private String directory;
	private List<Thread> threadList;
	private List<IResultGetter> resultGetterThreadList;

	private enum Operation {
		ADDITION, MULTIPLICATION, SQUARE_SUM
	}
	
	public ThreadManager(String directory) {
		this.directory = directory;
		threadList = new ArrayList<Thread>();
		resultGetterThreadList = new ArrayList<IResultGetter>();
	}
	
	public void manage() throws ThreadManagerException {
		int i = 1;
		while (true) {
			String fileName = directory + FILE_PREFIX + i + FILE_EXTENSION;
			if (new File(fileName).exists()) {
				i++;
				manageFile(fileName);
			}
			else {
				break;
			}
		}
		try {
			letAllThreadJoin();
			float result = countResult();
			writeResult(result);
		} catch (InterruptedException e) {
			throw new ThreadManagerException("Interrupted exception while all threads joining parent thread", e);
		}
		
	}
	
	private void manageFile(String fileName) throws ThreadManagerException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String operation = br.readLine();
			String floatNumsLine = br.readLine();
			
			String[] floatNumsStringArray = floatNumsLine.split(SPACE);
			float[] numArray = new float[floatNumsStringArray.length];
			for (int i = 0; i < numArray.length; i++) {
				numArray[i] = Float.parseFloat(floatNumsStringArray[i]);
			}
			
			proceedOperation(operation, numArray);
			
		} catch (FileNotFoundException e) {
			throw new ThreadManagerException("File was not found in ThreadManager", e);
		} catch (IOException e) {
			throw new ThreadManagerException("IOException occured in ThreadManager", e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					throw new ThreadManagerException("BufferedReader could not be closed", e);
				}
			}
		}
	}
	
	private void proceedOperation(String oper, float[] nums) throws ThreadManagerException {
		Operation operation = Operation.valueOf(oper.toUpperCase());
		switch (operation) {
		case ADDITION:
			AdditionThread additionThread = new AdditionThread(nums);
			resultGetterThreadList.add(additionThread);
			Thread addThread = new Thread(additionThread);
			threadList.add(addThread);
			addThread.start();
			break;
		case MULTIPLICATION:
			MultiplicationThread multiplicationThread = new MultiplicationThread(nums);
			resultGetterThreadList.add(multiplicationThread);
			Thread multiThread = new Thread(multiplicationThread);
			threadList.add(multiThread);
			multiThread.start();
			break;
		case SQUARE_SUM:
			SquareSumThread squareSumThread = new SquareSumThread(nums);
			resultGetterThreadList.add(squareSumThread);
			Thread sqSumThread = new Thread(squareSumThread);
			threadList.add(sqSumThread);
			sqSumThread.start();
			break;
		default:
			throw new EnumConstantNotPresentException(operation.getDeclaringClass(), operation.name());
		}
	}
	
	private void letAllThreadJoin() throws InterruptedException {
		for (Thread t : threadList) {
			t.join();
		}
	}
	
	private float countResult() {
		float res = 0;
		for (IResultGetter thread : resultGetterThreadList) {
			res += thread.getResult();
		}
		return res;
	}
	
	private void writeResult(float result) throws ThreadManagerException {
		BufferedWriter bw = null;
		try {
			
			bw = new BufferedWriter(new FileWriter(directory + OUT_FILE_NAME, false)); // creates file if it does not exist
			bw.write(String.valueOf(result));
			
		} catch (IOException e) {
			throw new ThreadManagerException("IOException occured in ThreadManager", e);
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					throw new ThreadManagerException("BufferedWriter could not be closed", e);
				}
			}
		}
	}
	
	
}
