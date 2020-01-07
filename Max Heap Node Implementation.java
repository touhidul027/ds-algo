package ds_algo_github;

class Node {
    private int data;

    public Node(int key) {
         data = key;
    }
    public int getKey() {
        return data;
    }
    public void setKey(int id) {
        data = id;
    }
}

class MaxHeap {
    private Node[] heapArray;
    private int maxSize; // max size of heap array
    private int currentSize; // number of nodes in array

    public MaxHeap(int maxSize) {
        this.maxSize = maxSize;
        this.currentSize = 0;
        this.heapArray = new Node[maxSize];
    }
    public boolean isEmpty() {
        return currentSize==0;
    }

    // insert at last and going up
    public boolean insert(int key) {
        if (currentSize == maxSize) {
            return false;
        }
        Node newNode = new Node(key);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    }

    public void trickleUp(int index) {
        int parentIndex = (index-1) / 2;
        Node currentNode = heapArray[index];
        while(index > 0 && heapArray[parentIndex].getKey() < currentNode.getKey()) {
            heapArray[index] = heapArray[parentIndex]; // move it down
            index = parentIndex;
            parentIndex = (parentIndex-1) / 2;
        }
        heapArray[index] = currentNode;
    }

    // remove the root,took the last element at root element position and checking down
    public Node remove() {
        Node root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }
    
    public void trickleDown(int index) {
        int largerChild;
        Node currentNode = heapArray[index];
        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            // find larger child
            if (rightChild < currentSize
                    && // (rightChild exists?)
                    heapArray[leftChild].getKey()
                    < heapArray[rightChild].getKey()) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }
            // top >= largerChild?
            if (currentNode.getKey() >= heapArray[largerChild].getKey()) {
                break;
            }
            // shift child up
            heapArray[index] = heapArray[largerChild];
            index = largerChild; // go down
        } // end while
        heapArray[index] = currentNode;
    } // end trickleDown()

    public boolean change(int index, int newValue) {
        if (index < 0 || index >= currentSize) {
            return false;
        }
        int oldValue = heapArray[index].getKey();
        heapArray[index].setKey(newValue);
        if ( newValue > oldValue) {
            trickleUp(index);
        } else {
            trickleDown(index);
        }
        return true;
    }
    
    public void displayHeap() {
        System.out.print("heapArray: "); // array format
        for (int m = 0; m < currentSize; m++) {
            if (heapArray[m] != null) {
                System.out.print(heapArray[m].getKey() + " ");
            } else {
                System.out.print("-- ");
            }
        }
        System.out.println();
        // heap format
        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0; // current item
        String dots = "...............................\" ";
        System.out.println(dots + dots); // dotted top line
        while (currentSize > 0) {// for each heap item
            if (column == 0) {// first item in row?
                for (int k = 0; k < nBlanks; k++) {// preceding blanks
                    System.out.print(" ");
                }
            }
            // display item
            System.out.print(heapArray[j].getKey());

            if (++j == currentSize) {// done?
                break;
            }
            if (++column == itemsPerRow) {// end of row?
                nBlanks /= 2; // half the blanks
                itemsPerRow *= 2; // twice the items
                column = 0; // start over on
                System.out.println(); // new row
            } else {// next item on row
                for (int k = 0; k < nBlanks * 2 - 2; k++) {
                    System.out.print(" "); // interim blanks
                }
            }
        } // end for
        System.out.println("\n" + dots + dots); // dotted bottom line
    } // end displayHeap()
}


public class MainDriver {
    public static void main(String[] agrs) {
        System.out.println("Welcome to MaxHeap Programming");
        int value, value2;
        MaxHeap theHeap = new MaxHeap(31); // make a Heap; max size 31
        boolean success;
        theHeap.insert(70);
        theHeap.insert(40);
        theHeap.insert(50);
        theHeap.insert(20);
        theHeap.insert(60);
        theHeap.insert(100);
        theHeap.insert(80);
        theHeap.insert(30);
        theHeap.insert(10);
        theHeap.insert(90);
        theHeap.displayHeap();
//        theHeap.remove();
//        theHeap.displayHeap();
//        theHeap.change(3, 65);
//        theHeap.displayHeap();
    }
    
}
