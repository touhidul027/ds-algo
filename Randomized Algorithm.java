
Program:1 Reservoir
Ref: https://www.geeksforgeeks.org/reservoir-sampling/
// An efficient Java program to randomly
// select k items from a stream of items
import java.util.Arrays;
import java.util.Random;
public class ReservoirSampling {

	// A function to randomly select k items from
	// stream[0..n-1].
	static void selectKItems(int stream[], int n, int k)
	{
		int i; // index for elements in stream[]

		// reservoir[] is the output array. Initialize it
		// with first k elements from stream[]
		int reservoir[] = new int[k];
		for (i = 0; i < k; i++)
			reservoir[i] = stream[i];

		Random r = new Random();

		// Iterate from the (k+1)th element to nth element
		for (; i < n; i++) {
			// Pick a random index from 0 to i.
			int j = r.nextInt(i + 1);

			// If the randomly picked index is smaller than
			// k, then replace the element present at the
			// index with new element from stream
			if (j < k)
				reservoir[j] = stream[i];
		}

		System.out.println(
			"Following are k randomly selected items");
		System.out.println(Arrays.toString(reservoir));
	}

	// Driver Program to test above method
	public static void main(String[] args)
	{
		int stream[]
			= { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
		int n = stream.length;
		int k = 5;
		selectKItems(stream, n, k);
	}
}
Output:
Following are k randomly selected items
6 2 11 8 12



Program:2 Fisher–Yates shuffle Algorithm
// Java Program to shuffle a given array
import java.util.Random;
import java.util.Arrays;
public class ShuffleRand
{
	// A Function to generate a random permutation of arr[]
	static void randomize( int arr[], int n)
	{
		// Creating a object for Random class
		Random r = new Random();
		
		// Start from the last element and swap one by one. We don't
		// need to run for the first element that's why i > 0
		for (int i = n-1; i > 0; i--) {
			
			// Pick a random index from 0 to i
			int j = r.nextInt(i+1);
			
			// Swap arr[i] with the element at random index
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
		// Prints the random array
		System.out.println(Arrays.toString(arr));
	}
	
	// Driver Program to test above function
	public static void main(String[] args)
	{
		
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
		int n = arr.length;
		randomize (arr, n);
	}
}
Output:
7 8 4 6 3 1 2 5

