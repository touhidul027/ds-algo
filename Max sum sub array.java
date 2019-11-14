package main;

import java.util.Scanner;
import java.util.Arrays;

    class MaximumSumSubArray{
        private int[] theArr;
        private int size;
        private int nElems;
        private int maxSum;
        private int startIndex;
        private int endIndex;
        
        public MaximumSumSubArray(int size) {
            this.size = size;
            this.theArr = new int[size];
            this.nElems = 0;
            this.maxSum = 0;
        }
        
        public void insertItems(int item) {
            theArr[nElems] = item;
            nElems++;
        }
        
        public void display() {
            System.out.print("\nElements : ");
            for (int i = 0; i < nElems; i++) {
                System.out.print(theArr[i] + " ");
            }
        }
        
        public void displaySubArray() {
            if (startIndex > endIndex) {
                System.out.print("\nNo subarray found");
                return;
            }
            System.out.print("\nSub array is : ");
            for (int i = startIndex; i <= endIndex; i++) {
                System.out.print(theArr[i] + " ");
            }
        }
        
        public void displaySubArrayIndex() {
            System.out.println("Start Index : " + startIndex + "  End Index : " + endIndex);
        }
        
        public void displaySum() {
            System.out.println("\nMax Sum : " + maxSum);
        }
        
        public void findSubArrayBruteForceMethod() {
            System.out.println("\nFinding is ongoing.");
            for (int i = 0; i < nElems; i++) {
                int tempSum = 0;
                
                if (theArr[i] > maxSum) {
                    maxSum = theArr[i];
                    startIndex = i;
                    endIndex = i;
                }
                tempSum += theArr[i];
                int j = i;
                
                while (tempSum > 0) {
                    j++;
                    if (j >= nElems) {
                        break;
                    }
                    
                    tempSum += theArr[j];
                    
                    if (tempSum > maxSum) {
                        maxSum = tempSum;
                        endIndex = j;
                    }
                }
            }
        }
    }

public class Main {    
    public static void main(String[] args) {
     Scanner scan = new Scanner(System.in);
	
      int maxSize = 100; // array size
      
      MaximumSumSubArray util ;
      
      util = new MaximumSumSubArray(maxSize);
      // -2 -3 4 -1 -2 1 5 -3
      // −2, 1, −3, 4, −1, 2, 1, −5
      util.insertItems(-2);
      util.insertItems(1);
      util.insertItems(-3);
      util.insertItems(4);
      util.insertItems(-1);
      util.insertItems(2);
      util.insertItems(1);
      util.insertItems(-5);
      
      util.display();
      util.findSubArrayBruteForceMethod();
      util.displaySubArray();
      util.displaySum();
    }
}