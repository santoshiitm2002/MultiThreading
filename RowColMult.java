//package multithreading.cmpe273.shruti;

/* 
 * Multiplies and sums two arrays.
 * Returns: (Arr1[0] * Arr2[0]) + (Arr1[1] * Arr2[2]) + (Arr1[3] * Arr2[3]) + ... + (Arr1[n] * Arr2[n])
 */

import java.lang.Runnable;
import java.util.Random;

public class RowColMult implements Runnable {
    
    // Arrays to store rows and columns
    private int[] rowArr;
    private int[] colArr;

    // Array sizes
    private int size;
    
    // Vars
    private int row = -1;
    private int col = -1;

    // Constructor
    public RowColMult(int[] arr1, int[] arr2, int arrSize, int row, int col) {
	
	this.row = row;
	this.col = col;
	this.size = arrSize;
	//System.out.println("Multiplying arrays of length " + size);
	
	rowArr = new int[size];
	colArr = new int[size];
	// Assign the arrays
	for (int i = 0; i < size; i++) {
	    rowArr[i] = arr1[i];
	    colArr[i] = arr2[i];
	}
    }

   
    /*
     * Start the thread
     */
    /*
    public void start() {
	System.out.println(this.getName + " is multipying row " + row + " col " + col);
	this.run();
    }
    */

    /*
     * Work to perform
     */
    public void run() {
	
	int sum = 0;
	for (int i = 0; i < size; i++) {
	    sum = sum + (rowArr[i] * colArr[i]);
	}
	//System.out.println("Multipied row " + row + " col " + col + " to get the result " + sum);
    }
}
