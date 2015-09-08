//package multithreading.cmpe273.shruti;

/* Perform serial and parallel matrix multiplication.
 * Run 4 threads in parallel to demonstrate speed up.
 */

import java.lang.Runnable;
import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;

public class MatrixMult  {
    
    public static void main(String[] args) {
	
	// Size of matrix
	int size = 20;
	
	
	// Matrices
	int[][] inpA;
	int[][] inpB;
	int [][] result;

	// Store threads
	ArrayList<Thread> threadList = new ArrayList<Thread>();

	System.out.println("Multiplying " + size + "X" + size + " matrices");
	
	// Generate two input random matrices
	inpA = new int[size][size];
	inpB = new int[size][size];
	result = new int[size][size];

	Random randomGenerator = new Random();
	for (int row = 0; row < size; row++) {
	    for (int col = 0; col < size; col++) {
		inpA[row][col] = randomGenerator.nextInt(10);
		inpB[row][col] = randomGenerator.nextInt(10);
	    }
	}
	
	System.out.println("Matrix 1:");
	printMatrix(inpA, size);
	System.out.println("\nMatrix 2:");
	printMatrix(inpB,size);
	System.out.println("");

	// Start time
	long startTime = System.nanoTime();

	// Multiply the matrices
	int[] rowArr = new int[size];
	int[] colArr = new int[size];
	int i = 0;
	for (int row = 0; row < size; row++) {
	    for (int col = 0; col < size; col++) {
		for (i = 0; i < size; i++) {
		    rowArr[i] = inpA[row][i];
		    colArr[i] = inpB[i][col];
		}
		
		Runnable task = new RowColMult(rowArr, colArr, size, row, col);
		Thread t = new Thread(task);
		String threadName = "Thread_" + row + "_" + col;
		t.setName(threadName);
		//System.out.println("Running thread " + t.getName());
		threadList.add(t);
		t.start();
	    }
	}

	//System.out.println("Resultant Matrix:");
	//printMatrix(result, size);
	//System.out.println(rowColMult(inpA, inpB, size, 1, 1));

	boolean run = true;
	Iterator itr = threadList.iterator();
	while(run) {
	    run = false;
	    while(itr.hasNext()) {
		Thread tmp = (Thread) itr.next();
		if (tmp.isAlive()) {
		    run = true;
		}
	    }
	}
	long endTime = System.nanoTime();
	System.out.println("Used " + (endTime - startTime) + " nano seconds to complete using multi-threading");

	long startTimeSerial = System.nanoTime();
	serialMultiply(inpA, inpB, size);
	long endTimeSerial = System.nanoTime();
	System.out.println("Used " + (endTimeSerial - startTimeSerial) + " nano seconds to complete using multi-threading");

    }

    // Multiply in single thread
    public static void serialMultiply(int[][] arr1, int[][] arr2, int size) {
	
	for (int i = 0; i < size; i++) {
	    for (int j = 0; j < size; j++) {
		int sum = 0;
		for (int k = 0; k < size; k++) {
		    int eleA = arr1[i][k];
		    int eleB = arr2[k][j];
		    sum = sum + (eleA * eleB);
		}
		//System.out.println("Row: " + i + " Col: " + j + " result: " + sum);
	    }
	}
    }
		
   
    /*
     * Print a 2D array / Matrix
     */
    public static void printMatrix(int[][] array, int dim) {
	
	for (int i = 0; i < dim; i++) {
	    for (int j = 0; j < dim; j++) {
		System.out.print(array[i][j] + " ");
	    }
	    System.out.println("");
	}	
    }
}
