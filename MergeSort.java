package main;

import java.util.Scanner;

class MergeSortClass {

    private long[] mainArr; // ref to array mainArr
    private int numOfMainArr; // number of data items

    public MergeSortClass(int max) {
        mainArr = new long[max];
        numOfMainArr = 0;
    }

    public void insert(long value) {
        mainArr[numOfMainArr] = value; // insert it
        numOfMainArr++; // increment size
    }

    public void display() { // displays array contents{
        for (int j = 0; j < numOfMainArr; j++) {
            System.out.print(mainArr[j] + " ");
        }
        System.out.println("");
    }

    public void mergeSort() { // called by main() 
        long[] tempArr = new long[numOfMainArr];// provides tempArr
        recursiveMergeSort(tempArr, 0, numOfMainArr - 1);
    }

    private void recursiveMergeSort(long[] tempArr, int lowerBound, int upperBound) {
        if (lowerBound == upperBound) { // if range is 1
            return; // no use sorting
        } else { // find midpoint
            int mid = (lowerBound + upperBound) / 2;
            // sort low half
            recursiveMergeSort(tempArr, lowerBound, mid);
            // sort high half
            recursiveMergeSort(tempArr, mid + 1, upperBound);
            // mergeTwoHalfs them
            mergeTwoHalfs(tempArr, lowerBound, mid + 1, upperBound);
        } // end else
    } // end recursiveMergeSort()

    private void mergeTwoHalfs(long[] tempArr, int left, int midStart, int right) {
        int j = 0; // tempArr index
        int lowerBound = left;
        int leftEndIndex = midStart - 1;
        int n = right - lowerBound + 1; // # of items
        while (left <= leftEndIndex && midStart <= right) {
            if (mainArr[left] < mainArr[midStart]) {
                tempArr[j] = mainArr[left];
                j++;
                left++;
            } else {
                tempArr[j] = mainArr[midStart];
                j++;
                midStart++;
            }
        }
        while (left <= leftEndIndex) {
            tempArr[j] = mainArr[left];
            j++ ;
            left++ ;
        }
        while (midStart <= right) {
            tempArr[j] = mainArr[midStart];
            j++ ;
            midStart++;
        }
        // copy elements to main/original array
        for (j = 0; j < n; j++) {
            mainArr[lowerBound + j] = tempArr[j];
        }
    }// end mergeTwoHalfs()

}// end class MergeSortClass

class Main {
    public static void main(String[] args) {
       int maxSize = 100; // array size
        MergeSortClass arr; // reference to array
        arr = new MergeSortClass(maxSize); // create the array
        arr.insert(64); // insert items
        arr.insert(21);
        arr.insert(33);
        arr.insert(70);
        arr.insert(12);
        arr.insert(85);
        arr.insert(44);
        arr.insert(3);
        arr.insert(99);
        arr.insert(0);
        arr.insert(108);
        arr.insert(36);
        arr.display(); // display items
        arr.mergeSort(); // merge sort the array
        arr.display(); 
    }
}
