import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Kelas ini digunakan untuk eksperimentasi analisa algoritma MCSS
 * dengan kompleksitas Cubic, Quadratic, NLogN, dan Linear
 * 
 * @author Your Name
 * @NPM	Your Student ID
 * @version 2016.02.08
 */
public class SDA15162L {
	
	public static void main(String[] args) {

		BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			/* Read input from standard input */
			int numberOfDatum = Integer.parseInt(buffReader.readLine());
			int[] data = new int[numberOfDatum];

			for (int i = 0; i < numberOfDatum; i++) {
				data[i] = Integer.parseInt(buffReader.readLine());
			}

			/* MCSS Process */
			long startTime = 0;
			long endTime = 0;

			startTime = 0;
			startTime = System.nanoTime();
			int mcssResultLinear = mcssLinear(data);
			System.out.println("ResultLinear    : "+mcssResultLinear);
			endTime = System.nanoTime();
			double elapsedTimeLinear = (endTime - startTime) / 1000000.0;
			System.out.println("Elapsed Time Linear: " + elapsedTimeLinear);

			startTime = System.nanoTime();
			int mcssResultQuadratic = mcssQuadratic(data);
			System.out.println("ResultQuadratic : "+mcssResultQuadratic);
			endTime = System.nanoTime();
			double elapsedTimeQuadratic = (endTime - startTime) / 1000000.0;
			System.out.println("Elapsed Time Quadratic: " + elapsedTimeQuadratic);

			startTime = 0;

			startTime = System.nanoTime();
			int mcssResultNlogN = mcssNLogN(data);
			System.out.println("ResultNlogN     : "+mcssResultNlogN);
			endTime = System.nanoTime();
			double elapsedTimeNlogN = (endTime - startTime) / 1000000.0;
			System.out.println("Elapsed Time NlogN: " + elapsedTimeNlogN);

			startTime = 0;

			startTime = System.nanoTime();
			int mcssResultCubic = mcssCubic(data);
			System.out.println("ResultCubic     : "+mcssResultCubic);
			endTime = System.nanoTime();
			double elapsedTimeCubic = (endTime - startTime) / 1000000.0;
			System.out.println("Elapsed Time Cubic: " + elapsedTimeCubic);
			// Get elapsed time

			try{
				String report = "Report.txt";
				FileWriter fw = new FileWriter(report, true);
				fw.write("\nLinear        : "+elapsedTimeLinear);
				fw.write("\nCubic         : "+elapsedTimeCubic);
				fw.write("\nQuadratic     : "+elapsedTimeQuadratic);
				fw.write("\nNlogN         : "+elapsedTimeNlogN);
				fw.write("\n================EOF======================");
				fw.close();
			}catch(IOException ioex){
				System.out.println(ioex.getMessage());
			}


		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

	}

	/**
	 * Algoritma MCSS dengan kompleksitas O(n^3)
	 * 
	 * @param input input array untuk MCSS
	 * @return nilai MCSS
	 */
	public static int mcssCubic(int[] input) {
		// Implement!
		return mcssCubic(input, 0, input.length - 1);
	}

	/**
	 * Algoritma MCSS dengan kompleksitas O(n^2)
	 * 
	 * @param input input array untuk MCSS
	 * @return nilai MCSS
	 */
	public static int mcssQuadratic(int[] input) {
		// Implement!
		return mcssQuadratic(input, 0, input.length - 1);
	}
	
	/**
	 * Algoritma MCSS dengan kompleksitas O(n)
	 * 
	 * @param input input array untuk MCSS
	 * @return nilai MCSS
	 */
	public static int mcssLinear(int[] input) {
		// Implement!
		return mcssLinear(input, 0, input.length - 1);
		
	}
	
	/**
	 * Mencari bilangan maksimal dari 3 buah bilangan bulat
	 * @param a angka pertama yang ingin dibandingkan
	 * @param b angka kedua yang ingin dibandingkan
	 * @param c angka ketiga yang ingin dibandingkan
	 * @return angka yang maksimal dari a, b, dan c
	 */
	public static int max3(int a, int b, int c) {
		// Implement!
		int[] values = new int[3];
		values[0] = c;
		values[1] = a;
		values[2] = b;

		Arrays.sort(values);
		return values[2];
	}
	

	/**
	 * Driver method untuk algoritma MCSS dengan kompleksitas O(n log n)
	 * 
	 * @param input input array untuk MCSS
	 * @return nilai MCSS
	 */
	public static int mcssNLogN(int[] a) {
		return mcssNLogN(a, 0, a.length - 1);
	}
	
	/**
	 * Helper method untuk algoritma MCSS dengan kompleksitas O(n log n)
	 * 
	 * @param a input array
	 * @param left index kiri array
	 * @param right index kanan array
	 * @return nilai MCSS
	 */
	public static int mcssNLogN(int[] a, int left, int right) {
		int center = (left + right) / 2;
		int leftBorderSum = 0;
		int maxLeftBorderSum = 0;
		int rightBorderSum = 0;
		int maxRightBorderSum = 0;
		
		if (left == right) { 
			return a[left] > 0 ? a[left] : 0;
		}
		
		int maxLeftSum = mcssNLogN(a, left, center);
		
		int maxRightSum = mcssNLogN(a, center + 1, right);
		for (int ii = center; ii >= left; ii--) {
			leftBorderSum += a[ii];
			if (leftBorderSum > maxLeftBorderSum)
				maxLeftBorderSum = leftBorderSum;
		}

		for (int jj = center + 1; jj <= right; jj++) {
			rightBorderSum += a[jj];
			if (rightBorderSum > maxRightBorderSum)
				maxRightBorderSum = rightBorderSum;
		}
		return max3(maxLeftSum, maxRightSum, maxLeftBorderSum
				+ maxRightBorderSum);
	}

	public static int mcssCubic(int[] a, int left, int right) {
		int maxSum = 0;
		for (int i = 0; i < a.length;i++){
			for (int j = i; j < a.length; j++){
				int thisSum = 0;
				for(int k = i; k <= j; k++){
					thisSum += a[k];
					if(thisSum > maxSum){
						maxSum = thisSum;
					}
				}

			}

		}
		return maxSum;
	}

	public static int mcssQuadratic(int[] a, int left, int right) {
		int maxSum = 0;
		for (int i = 0; i < a.length;i++){
			int thisSum = 0;
			for (int j = i; j < a.length; j++){
				thisSum += a[j];
				if (thisSum > maxSum){
					maxSum = thisSum;
				}
			}
		}
		return maxSum;
	}

	public static int mcssLinear(int[] a, int left, int right) {
		int thisSum = 0;
		int maxSum = 0;
		for (int i = 1; i < a.length;i++){
			thisSum += a[i];
			if(thisSum > maxSum){
				maxSum = thisSum;
			}else if(thisSum < 0){
				thisSum = 0;
			}
		}
		return maxSum;
	}

	

}
