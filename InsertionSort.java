package main;

import java.util.Scanner;

class InsertionSort {

    private long[] arr;
    private int nElems;

    public InsertionSort(int max) {
        arr = new long[max];
        nElems = 0;
    }

    public void insert(long value) {
        arr[nElems] = value;
        nElems++;
    }

    public void display() {
        for (int j = 0; j < nElems; j++) {
            System.out.print(arr[j] + " ");
        }
        System.out.println("");
    }

    public void sort() {
        long key;
        int j;
        for (int i = 1; i < nElems; i++) {
            key = arr[i];
            j = i - 1;
            while ((j >= 0) && (arr[j] > key)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public void insertionSort() {
        recursiveInsertionSort(nElems - 1);// nElems-1 indicates the last array index
    }

    public void recursiveInsertionSort(int end) {
        if (end == 0) {
            return;
        } else {
            recursiveInsertionSort(end - 1);
            long key = arr[end];
            int currentIndex = end - 1;
            while ((currentIndex >= 0) && (arr[currentIndex] > key)) {
                arr[currentIndex + 1] = arr[currentIndex];
                currentIndex--;
            }
            arr[currentIndex + 1] = key;
        }
    }

}

class Main {

    public static void main(String[] args) {
        int maxSize = 100; // array size
        InsertionSort arr; // reference to array
        arr = new InsertionSort(maxSize); // create the array
        arr.insert(77); // insert 10 items
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(33);
        arr.display(); // display items
        //arr.sort(); // insertion-sort them
        arr.insertionSort();//sort recursively
        arr.display();
    }
}
