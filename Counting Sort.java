//implementing Counting sort
package ds_algo_github;

class CountingSort {
    private int maxNumbers;
    private int currentNumbers;
    private int[] arr;
    private int[] outputArray;
    private int rangeNumber;

    public CountingSort (int maxNumbers) {
        this.maxNumbers = maxNumbers;
        this.currentNumbers = 0;
        arr = new int[maxNumbers];
        outputArray = new int[maxNumbers];
        this.rangeNumber = -1;
        for (int i = 0; i<maxNumbers; i++) {
            arr[i] = 0;
            outputArray[i] = 0;
        }
    }
    
    public void display() {
        System.out.println("Original array : ");
        for(int i = 0; i < currentNumbers; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("\nSorted array : ");
        for(int i = 1; i <= currentNumbers; i++) {
            System.out.print(outputArray[i] + " ");
        }
    }

    public void insert (int n) {
        if(currentNumbers >= maxNumbers) {
            System.out.println("Max limit reached.");
            return;
        }
        if(n > this.rangeNumber)  {
            this.rangeNumber = n;
        }
        this.arr[currentNumbers++] = n;
    }

    public void sort() {
        int k = this.rangeNumber + 1;
        int[] tempArray = new int[k];

        for (int i = 0; i < k; i++) {
            tempArray[i] = 0;
        }

        for (int i = 0; i < currentNumbers; i++) {
            tempArray[arr[i]] = tempArray[arr[i]] + 1; 
        }
            
        for (int i = 1; i < k; i++) {
            tempArray[i] = tempArray[i] + tempArray[i-1];
        }
                
        for( int i = currentNumbers-1; i >= 0 ; i--){
            outputArray[ tempArray[arr[i] ]] = arr[i];
            tempArray[arr[i]] -= 1;
        }
    }
}

public class MainDriver {
    public static void main(String[] agrs) {
        CountingSort csObj = new CountingSort(10);
        csObj.insert(2);
        csObj.insert(5);
        csObj.insert(3);
        csObj.insert(0);
        csObj.insert(2);
        csObj.insert(3);
        csObj.insert(0);
        csObj.insert(3);
        csObj.sort();
        csObj.display();
    }
}
