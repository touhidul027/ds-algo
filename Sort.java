
Program:1 Counting Sort
-----------------------
Reference: {
    Idea: Introduction to Algorithms
    Implementation: geeksforgeeks
}
START:
package solution;

import java.util.Arrays;

public class CountingSort {

    public static int[] countingSort(int[] originalArr, int maxNumberOfArr) {
        maxNumberOfArr++;
        int len = originalArr.length;
        int[] sortedArr = new int[len]; // default value 0
        int[] tempArr = new int[maxNumberOfArr]; // by default value is 0

        for (int i = 0; i < len; i++) {
            int value = originalArr[i];
            //System.out.println(value);
            tempArr[value] = tempArr[value] + 1;
        }
        System.out.println("Counter: " + Arrays.toString(tempArr));
        for (int i = 1; i < maxNumberOfArr; i++) {
            tempArr[i] = tempArr[i] + tempArr[i - 1];
        }
        System.out.println("Cumulating Sum: " + Arrays.toString(tempArr));
        System.out.println("Oiriginal " + Arrays.toString(originalArr));
        for (int i = len - 1; i >= 0; i--) {
            int value = originalArr[i];
            System.out.println("value " + value);
            int index = tempArr[value] - 1;
            System.out.println("index " + index);
            sortedArr[index] = value;
            System.out.println(Arrays.toString(sortedArr));
            tempArr[value] = tempArr[value] - 1;
            System.out.println("Cumulating Sum: " + Arrays.toString(tempArr));
        }
        //System.out.println(Arrays.toString(sortedArr));
        return sortedArr;
    }

    public static void main(String[] args) {
        int[] unsortedArray = {2, 5, 3, 0, 2, 3, 0, 3};
        int maxNum = 5;
        CountingSort.countingSort(unsortedArray, maxNum);
    }
}
:END

Program:2 Radix Sort
----------------------
Reference :{
    Idea: Data Structured and Algorithms in Java
    Running Time: Algorithms unlocked - book page:70
    Why not leftmost character first :  Algorithms unlocked - book page:69
    Implementation: Direct, https://www.geeksforgeeks.org/radix-sort/
}
START:
package solution;

import java.util.Arrays;

class ModifiedCountingSort {

    static void countSort(int arr[], int n, int exp) {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to current digit
        for (i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

}

public class RadixSort {

    public int[] radixSort(int[] arr) {
        int largestNumber = this.largestNumber(arr);

        // iterate till max digit position
        for (int basePosition = 1; largestNumber / basePosition > 0; basePosition *= 10) {
            ModifiedCountingSort.countSort(arr, arr.length, basePosition);
        }

        return arr;
    }

    private int largestNumber(int[] arr) {
        int mx = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > mx) {
                mx = arr[i];
            }
        }
        return mx;
    }

    public static void main(String[] args) {
        int arr[] = {170, 45, 75, 90, 802, 24, 2, 66};
        RadixSort rs = new RadixSort();
        int[] sortedArr = rs.radixSort(arr);
        System.out.println(Arrays.toString(sortedArr));
    }
}
Output:
[2, 24, 45, 66, 75, 90, 170, 802]
:END
