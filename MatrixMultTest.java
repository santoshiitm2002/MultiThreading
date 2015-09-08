//package multithreading.cmpe273.shruti;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;

/*
 * Driver class for matric multiplication
 */

public class MatrixMultTest {
    
    private static final NTHREADS = 4;

    public static void main (String[] args) {

	//ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
	Runnable newMult = new MatrixMult(3);
	Thread worker = new Thread(newMult);
	worker.setName("Single Thread");
	worker.start();
	//System.out.println("Working");
    }
}
