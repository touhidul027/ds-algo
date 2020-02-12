//implementing how many numbers in a range a,b in an given array
package ds_algo_github;

class NumbersInRange {
    private int maxNumber;
    private int currentNumbers;
    private int[] arr;
    private int[] cumulativeArray;
    private int rangeNumber;

    public NumbersInRange (int maxNumber) {
        this.maxNumber = maxNumber;
        this.currentNumbers = 0;
        arr = new int[maxNumber];
        this.rangeNumber = -1;
        for (int i = 0; i<maxNumber; i++) {
            arr[i] = 0;
        }
    }

    public void display() {
        System.out.println("Original array : ");
        for(int i = 0; i < currentNumbers; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public void insert (int n) {
        if(currentNumbers >= maxNumber) {
            System.out.println("Max limit reached.");
            return;
        }
        if(n > this.rangeNumber) {
            this.rangeNumber = n;
        }
        this.arr[currentNumbers++] = n;
    }

    public void cumulativeArranging() {
        int k = this.rangeNumber + 1;
        cumulativeArray = new int[k];

        for (int i = 0; i < k; i++) {
            cumulativeArray[i] = 0;
        }

        for (int i = 0; i < currentNumbers; i++) {
            cumulativeArray[arr[i]] = cumulativeArray[arr[i]] + 1; 
        }
            
        for (int i = 1; i < k; i++) {
            cumulativeArray[i] = cumulativeArray[i] + cumulativeArray[i-1];
        }
    }
    
    public int getNumbers (int rangeA,int rangeB) {
        int n = 0;
        if(rangeA == 0 ){
            return cumulativeArray[rangeB];
        }
        n = cumulativeArray[rangeB] - cumulativeArray[rangeA-1];
        return n;
    }
}

public class MainDriver {
    public static void main(String[] agrs) {
        NumbersInRange obj = new NumbersInRange(10);
        obj.insert(2);
        obj.insert(5);
        obj.insert(3);
        obj.insert(0);
        obj.insert(2);
        obj.insert(3);
        obj.insert(0);
        obj.insert(3);
        obj.cumulativeArranging();
        obj.display();
        System.out.println("");
        System.out.println( obj.getNumbers(1, 4));
    }
}
