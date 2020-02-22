//implementing Radix sort
package ds_algo_github;

class RadixSort {
    private int maxNumbers;
    private int currentNumbers;
    private int[] arr;
    private int[] outputArray;
    private int rangeNumber;

    public RadixSort (int maxNumbers) {
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
        for (int digit = 1; this.rangeNumber/digit > 0; digit *= 10) {
            countSort(digit);
        }
    }
    public void countSort(int digit) {
        int k = 10;
        int[] tempArray = new int[k];

        for (int i = 0; i < k; i++) {
            tempArray[i] = 0;
        }
        for (int i = 0; i < currentNumbers; i++) {
            tempArray[(arr[i]/digit)%10] = tempArray[(arr[i]/digit)%10] + 1; 
        }    
        for (int i = 1; i < k; i++) {
            tempArray[i] = tempArray[i] + tempArray[i-1];
        }
        for( int i = currentNumbers-1; i >= 0 ; i--){            
            outputArray[ tempArray[(arr[i]/digit)%10]-1] = arr[i];
            tempArray[(arr[i]/digit)%10] -= 1;
        }
        for (int i = 0; i < currentNumbers; i++) {
            arr[i] = outputArray[i];
        }
    }
}

public class MainDriver {
    public static void main(String[] agrs) {
        int a[] = {170, 45, 75, 90, 802, 24, 2, 66};
                //{329 , 457 , 657 , 839 , 436 , 720 , 355}; 
        int n = a.length;
        RadixSort rsObj = new RadixSort(n+10);
        for (int i = 0; i < n; i++) {
            rsObj.insert(a[i]);
        }
        rsObj.sort();
        rsObj.display();
    }
}