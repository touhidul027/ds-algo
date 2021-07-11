
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
}
