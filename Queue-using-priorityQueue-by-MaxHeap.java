// implementing Queue using Priority Queue(Max Heap)
package ds_algo_github;

class Node {

    private int key;
    private int value;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

class MaxHeap {

    private Node[] heapArray;
    private int maxSize; // max size of heap array
    private int currentSize; // number of nodes in array
    private int priorityKey;

    public MaxHeap(int maxSize) {
        this.maxSize = maxSize;
        this.currentSize = 0;
        this.heapArray = new Node[maxSize];
        this.priorityKey = maxSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    // insert at last and going up
    public boolean insert(int value) {
        if (currentSize == maxSize) {
            return false;
        }
        Node newNode = new Node(priorityKey--, value);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    }

    public void trickleUp(int index) {
        int parentIndex = (index - 1) / 2;
        Node currentNode = heapArray[index];
        while (index > 0 && heapArray[parentIndex].getKey() < currentNode.getKey()) {
            heapArray[index] = heapArray[parentIndex]; // move it down
            index = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        heapArray[index] = currentNode;
    }

    // same as the tricleUp method
    public void recursiveTricleUp(int index) {
        if (index <= 0) {
            System.out.println("quit1");
            return;
        }
        int parentIndex = (index - 1) / 2;
        if (heapArray[parentIndex].getKey() > heapArray[index].getKey()) {
            return;
        }
        int temp = heapArray[index].getKey();
        heapArray[index].setKey(heapArray[parentIndex].getKey());
        heapArray[parentIndex].setKey(temp);
        recursiveTricleUp(parentIndex);
    }

    // remove the root,took the last element at root element position and checking down
    public Node remove() {
        Node root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        //trickleDown(0);
        recursiveTricleDown(0);
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

    public void recursiveTricleDown(int index) {
        // run till have at least one childs
        if ((index * 2 + 1) >= currentSize) {
            return;
        }
        Node currentNode = heapArray[index];
        int largerChildIndex;
        int leftChildIndex = index * 2 + 1;
        int rightChildIndex = leftChildIndex + 1;
        if (rightChildIndex < currentSize && heapArray[leftChildIndex].getKey() >= heapArray[rightChildIndex].getKey()) {
            largerChildIndex = leftChildIndex;
        } else {
            largerChildIndex = rightChildIndex;
        }

        if (currentNode.getKey() >= heapArray[largerChildIndex].getKey()) {
            return;
        }
        // shift up largerchild to its parent position
        heapArray[index] = heapArray[largerChildIndex];
        heapArray[largerChildIndex] = currentNode;
        recursiveTricleDown(largerChildIndex);
    }

    public boolean change(int index, int newValue) {
        if (index < 0 || index >= currentSize) {
            return false;
        }
        int oldValue = heapArray[index].getKey();
        heapArray[index].setKey(newValue);
        if (newValue > oldValue) {
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

    void displayStack() {
        System.out.print("Stack: "); // array format
        for (int m = 0; m < currentSize; m++) {
            if (heapArray[m] != null) {
                System.out.print(heapArray[m].getValue() + " ");
            } else {
                System.out.print("-- ");
            }
        }
    }
}

class HeapPriorityQ {

    MaxHeap theMaxHeap;

    public HeapPriorityQ(int size) {
        theMaxHeap = new MaxHeap(size);
    }

    public boolean insert(int value) {
        return theMaxHeap.insert(value);
    }

    public int remove() {
        int temp = theMaxHeap.remove().getValue();
        return temp;
    }

    public boolean isEmpty() {
        return theMaxHeap.isEmpty();
    }

    public void display() {
        theMaxHeap.displayHeap();
    }

    // this method is not property of maxheap; used demonstration purpose only
    void displayStack() {
        System.out.println("");
        theMaxHeap.displayStack();
    }
}

public class MainDriver {

    public static void main(String[] agrs) {
        System.out.println("Welcome to General Queue Implementation build with Priority Queue(Maxheap");
        HeapPriorityQ theHeapPriorityQueueAsQueue = new HeapPriorityQ(31);
        boolean success;
        System.out.println("Number Serial : 70 , 40 , 50, 20 , 60 , 100 , 80 , 30 , 10 , 90" );
        theHeapPriorityQueueAsQueue.insert(70);
        theHeapPriorityQueueAsQueue.insert(40);
        theHeapPriorityQueueAsQueue.insert(50);
        theHeapPriorityQueueAsQueue.insert(20);
        theHeapPriorityQueueAsQueue.insert(60);
        theHeapPriorityQueueAsQueue.insert(100);
        theHeapPriorityQueueAsQueue.insert(80);
        theHeapPriorityQueueAsQueue.insert(30);
        theHeapPriorityQueueAsQueue.insert(10);
        theHeapPriorityQueueAsQueue.insert(90);
        theHeapPriorityQueueAsQueue.remove(); //remove 70
        theHeapPriorityQueueAsQueue.displayStack();// it should display all numbers except 70
        theHeapPriorityQueueAsQueue.remove(); // remove 40
        theHeapPriorityQueueAsQueue.displayStack();//it should display all numbers except 40
        theHeapPriorityQueueAsQueue.remove(); // remove 50
        theHeapPriorityQueueAsQueue.displayStack();//it should display all numbers except 50
    }
}
