Program 1: 2-3-4 Tree

package ds_algo_github;

import java.io.*;
import java.util.*;

class DataItem {

    public long dData;

    public DataItem(long dd) {
        dData = dd;
    }

    public void displayItem() {
        System.out.print("/" + dData);
    }
}

class Node {

    private static final int ORDER = 4;
    private int numItems;
    private Node parent;
    private Node childArray[] = new Node[ORDER];
    private DataItem itemArray[] = new DataItem[ORDER - 1];
// connect child to this node

    public void connectChild(int childNum, Node child) {
        childArray[childNum] = child;
        if (child != null) {
            child.parent = this;
        }
    }
// disconnect child from this node, return it

    public Node disconnectChild(int childNum) {
        Node tempNode = childArray[childNum];
        childArray[childNum] = null;
        return tempNode;
    }

    public Node getChild(int childNum) {
        return childArray[childNum];
    }

    public Node getParent() {
        return parent;
    }

    public boolean isLeaf() {
        return (childArray[0] == null) ? true : false;
    }

    public int getNumItems() {
        return numItems;
    }

    public DataItem getItem(int index) // get DataItem at index
    {
        return itemArray[index];
    }

    public boolean isFull() {
        return (numItems == ORDER - 1) ? true : false;
    }

    public int findItem(long key) // return index of
    { // item (within node)
        for (int j = 0; j < ORDER - 1; j++) // if found,
        { // otherwise,
            if (itemArray[j] == null) // return -1
            {
                break;
            } else if (itemArray[j].dData == key) {
                return j;
            }
        }
        return -1;
    } // end findItem

    public int insertItem(DataItem newItem) {
        // assumes node is not full
        numItems++; // will add new item
        long newKey = newItem.dData; // key of new item

        // start from right,
        for (int j = ORDER - 2; j >= 0; j--) { // examine items
            if (itemArray[j] == null) {
                continue; // go left one cell
            } else { // get its key
                long itsKey = itemArray[j].dData;
                if (newKey < itsKey) {
                    itemArray[j + 1] = itemArray[j]; // shift it right
                } else {
                    itemArray[j + 1] = newItem; // insert new item
                    return j + 1; // return index to
                } // new item
            }
        } // end for // shifted all items,
        itemArray[0] = newItem; // insert new item
        return 0;
    } // end insertItem()

    // remove largest item
    public DataItem removeItem() {
        // assumes node not empty
        DataItem temp = itemArray[numItems - 1]; // save item
        itemArray[numItems - 1] = null; // disconnect it
        numItems--; // one less item
        return temp; // return item
    }

    // format “/24/56/74/”
    public void displayNode() {
        for (int j = 0; j < numItems; j++) {
            itemArray[j].displayItem(); // “/56”
        }
        System.out.println("/"); // final “/”
    }
}

class Tree234 {

    private Node root = new Node(); // make root node

    public int find(long key) {
        Node curNode = root;
        int childNumber;
        while (true) {
            if ((childNumber = curNode.findItem(key)) != -1) {
                return childNumber; // found it
            } else if (curNode.isLeaf()) {
                return -1; // can’t find it
            } else // search deeper
            {
                curNode = getNextChild(curNode, key);
            }
        }
    }

    // insert a DataItem
    public void insert(long dValue) {
        Node curNode = root;
        DataItem tempItem = new DataItem(dValue);
        while (true) {
            if (curNode.isFull()) {
                split(curNode); // split it
                curNode = curNode.getParent(); // back up
                // search once
                curNode = getNextChild(curNode, dValue);
            } else if (curNode.isLeaf()) {
                break; // go insert
            } else {// node is not full, not a leaf; so go to lower level
                curNode = getNextChild(curNode, dValue);
            }
        } // end while
        curNode.insertItem(tempItem); // insert new DataItem
    } // end insert()

    public void split(Node thisNode) {
        // assumes node is full
        DataItem itemB;
        DataItem itemC;
        Node parent, child2, child3;
        int itemIndex;
        itemC = thisNode.removeItem(); // remove items from
        itemB = thisNode.removeItem(); // this node
        child2 = thisNode.disconnectChild(2); // remove children
        child3 = thisNode.disconnectChild(3); // from this node
        Node newRight = new Node(); // make new node
        if (thisNode == root) {
            root = new Node(); // make new root
            parent = root; // root is our parent
            root.connectChild(0, thisNode); // connect to parent
        } else {
            parent = thisNode.getParent(); // get parent
        }// deal with parent
        itemIndex = parent.insertItem(itemB); // item B to parent
        int n = parent.getNumItems(); // total items?
        for (int j = n - 1; j > itemIndex; j--) { //move parent’s connections
            Node temp = parent.disconnectChild(j); // one child
            parent.connectChild(j + 1, temp); // to the right
        }
        // connect newRight to parent
        parent.connectChild(itemIndex + 1, newRight);

        // deal with newRight
        newRight.insertItem(itemC); // item C to newRight
        newRight.connectChild(0, child2); // connect to 0 and 1
        newRight.connectChild(1, child3); // on newRight
    } // end split()

    // gets appropriate child of node during search for value
    public Node getNextChild(Node theNode, long theValue) {
        int j;
        // assumes node is not empty, not full, not a leaf
        int numItems = theNode.getNumItems();
        for (j = 0; j < numItems; j++) { //for each item in nodeare we less?
            if (theValue < theNode.getItem(j).dData) {
                return theNode.getChild(j); // return left child
            }
        } // end for // we’re greater, so
        return theNode.getChild(j); // return right child
    }

    public void displayTree() {
        recDisplayTree(root, 0, 0);
    }

    private void recDisplayTree(Node thisNode, int level,
            int childNumber) {
        System.out.print("level=" + level + " child=" + childNumber + " ");
        thisNode.displayNode(); // display this node
        // call ourselves for each child of this node
        int numItems = thisNode.getNumItems();
        for (int j = 0; j < numItems + 1; j++) {
            Node nextNode = thisNode.getChild(j);
            if (nextNode != null) {
                recDisplayTree(nextNode, level + 1, j);
            } else {
                return;
            }
        }
    }
} // end class Tree234

public class MainDriver {

    public static void main(String[] agrs) throws IOException {
        long value;
        Tree234 theTree = new Tree234();
        theTree.insert(50);
        theTree.insert(40);
        theTree.insert(60);
        theTree.insert(30);
        theTree.insert(70);
        while (true) {
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, or find: ");
            char choice = getChar();
            switch (choice) {
                case 's':
                    theTree.displayTree();
                    break;
                case 'i':
                    System.out.print("Enter value to insert: ");
                    value = getInt();
                    theTree.insert(value);
                    break;
                case 'f':
                    System.out.print("Enter value to find: ");
                    value = getInt();
                    int found = theTree.find(value);
                    if (found != -1) {
                        System.out.println("Found " + value);
                    } else {
                        System.out.println("Could not find " + value);
                    }
                    break;
                default:
                    System.out.print("Invalid entry\n");
            } // end switch
        } // end while
    } // end main()

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}
:END


Program 2: Trie
package main;

// ref: https://www.geeksforgeeks.org/trie-insert-and-search/
// Implementation of Trie data structure
class Node {

    private Node[] childs;
    private boolean isEndOfWord;
    private char label;

    public Node() {
        this.childs = new Node[26]; //working with a-z characters
        this.isEndOfWord = false;
        for (int i = 0; i < 26; i++) {
            this.childs[i] = null;
        }
    }

    public Node(char ch) {
        this();
        this.label = ch;
    }

    public Node[] getChilds() {
        return this.childs;
    }

    public char getLabel() {
        return this.label;
    }

    public boolean isIsEndOfWord() {
        return this.isEndOfWord;
    }

    public void setIsEndOfWord(boolean isEndOfWord) {
        this.isEndOfWord = isEndOfWord;
    }
}

class Trie {

    private Node root;

    public Trie() {
        this.root = new Node();
    }

    public void insert(String str) {
        Node current = this.root;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            Node n = this.getCharNode(current, ch);
            if (n == null) {
                // times to add characters
                current = this.insertChar(current, ch);
            } else {
                current = n;
            }
        }
        current.setIsEndOfWord(true);
    }

    public boolean isExist(String str) {
        Node current = this.root;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            current = this.getCharNode(current, ch);
            if (current == null) {
                return false;
            }
            //System.out.print(current.getLabel() + ",");
        }
        //System.out.println();
        return current.isIsEndOfWord();
    }

    public boolean isExistBackup(String str) {
        Node current = this.root;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            Node n = this.getCharNode(current, ch);
            if (n != null) {
                System.out.print(n.getLabel() + ",");
                current = n;
            }
        }
        System.out.println();
        return current.isIsEndOfWord();
    }

    /**
     * if char is present in current childs then return the child node
     *
     * @param current searching for ch node of its childs
     * @param ch
     * @return
     */
    private Node getCharNode(Node current, char ch) {
        int nodeIndex = this.generateNodeIndex(ch);
        return current.getChilds()[nodeIndex];
    }

    private Node insertChar(Node current, char ch) {
        int nodeIndex = this.generateNodeIndex(ch);
        current.getChilds()[nodeIndex] = new Node(ch);
        return current.getChilds()[nodeIndex];
    }

    private int generateNodeIndex(char ch) {
        return (int) ch - 97;
    }
}

public class Main {

    public static void main(String[] args) {
        // Input keys (use only 'a' through 'z' and lower case)
        String keys[] = {"the", "a", "there", "answer", "any",
            "by", "bye", "their"};
        String output[] = {"Not present in trie", "Present in trie"};

        Trie trie = new Trie();

        for (int i = 0; i < keys.length; i++) {
            trie.insert(keys[i]);
        }

        // Search for different keys
        if (trie.isExist("the") == true) {
            System.out.println("the --- " + output[1]);
        } else {
            System.out.println("the --- " + output[0]);
        }

        if (trie.isExist("these") == true) {
            System.out.println("these --- " + output[1]);
        } else {
            System.out.println("these --- " + output[0]);
        }

        if (trie.isExist("their") == true) {
            System.out.println("their --- " + output[1]);
        } else {
            System.out.println("their --- " + output[0]);
        }

        if (trie.isExist("thaw") == true) {
            System.out.println("thaw --- " + output[1]);
        } else {
            System.out.println("thaw --- " + output[0]);
        }
    }
}

Output:
the --- Present in trie
these --- Not present in trie
their --- Present in trie
thaw --- Not present in trie

Future1: Compress Trie / Suffix Tree

Program 3: Segment Tree
-----------------------
START:
// http://www.shafaetsplanet.com/?p=1557
// https://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
package main;

import java.util.Arrays;

class SegmentTree {

    private int segmentTree[];
    private int arrLength = 0;
    private int[] arr;

    public void buildTree(int[] arr) {
        this.arr = arr;
        int len = arr.length * 3;
        this.arrLength = arr.length;
        this.segmentTree = new int[len];//java, all cells default value is 0
        this.recBuildTree(arr, 0, 0, arr.length - 1);
    }

    private int recBuildTree(int[] arr, int currentNode, int leftNodeIndex, int rightNodeIndex) {
        if (leftNodeIndex == rightNodeIndex) {
            this.segmentTree[currentNode] = arr[leftNodeIndex];
            return arr[leftNodeIndex];
        }
        int midIndex = leftNodeIndex + (rightNodeIndex - leftNodeIndex) / 2;
        int leftNode = currentNode * 2 + 1;
        int rightNode = currentNode * 2 + 2;
        int leftSideSum = this.recBuildTree(arr, leftNode, leftNodeIndex, midIndex);
        int rightSideSum = this.recBuildTree(arr, rightNode, midIndex + 1, rightNodeIndex);
        return this.segmentTree[currentNode] = leftSideSum + rightSideSum;
    }

    public int query(int rangeIndex1, int rangeIndex2) {
        // checking whether range 
        int leftTreeIndex = 0;
        int rightTreeIndex = this.arrLength - 1;
        int currentNode = 0;
        int sum = this.recQuery(rangeIndex1, rangeIndex2, currentNode, leftTreeIndex, rightTreeIndex);
        System.out.println("Sum: " + sum);
        return sum;
    }

    void displayTree() {
        System.out.println(Arrays.toString(this.segmentTree));
    }

    private int recQuery(int rangeIndex1, int rangeIndex2, int currentNode, int leftTreeIndex, int rightTreeIndex) {
        // range check query
        if (rangeIndex1 > rightTreeIndex || rangeIndex2 < leftTreeIndex) {
            return 0;
        } else if ((rangeIndex1 <= leftTreeIndex) && (rightTreeIndex <= rangeIndex2)) {
            return this.segmentTree[currentNode];
        }
        int midIndex = leftTreeIndex + (rightTreeIndex - leftTreeIndex) / 2;
        int leftNode = currentNode * 2 + 1;
        int rightNode = currentNode * 2 + 2;
        int leftSum = this.recQuery(rangeIndex1, rangeIndex2, leftNode, leftTreeIndex, midIndex);
        int rightSum = this.recQuery(rangeIndex1, rangeIndex2, rightNode, midIndex + 1, rightTreeIndex);
        return leftSum + rightSum;
    }

    public void updateValue(int index, int value) {
        int oldValue = this.arr[index];
        this.arr[index] = value;
        int diff = value - oldValue;
        int leftIndex = 0;
        int rightIndex = this.arrLength - 1;
        this.recUpdate(this.segmentTree, 0, index, diff, leftIndex, rightIndex);
    }

    /**
     * updating node at backtracking time(bottom to up time). We can also add
     * before going to call recursive function(top to bottom time)
     *
     * @param segmentTree
     * @param currentNode
     * @param updateIndex
     * @param diff - adding this in each eligible node
     * @param leftIndex
     * @param rightIndex
     */
    private void recUpdate(int[] segmentTree, int currentNode, int updateIndex, int diff, int leftIndex, int rightIndex) {
        if (leftIndex == rightIndex && updateIndex == leftIndex) {
            this.segmentTree[currentNode] = this.segmentTree[currentNode] + diff;
            return;
        }

        if (updateIndex < leftIndex || rightIndex < updateIndex) {
            return;
        }
        int midIndex = leftIndex + (rightIndex - leftIndex) / 2;
        int leftNode = currentNode * 2 + 1;
        int rightNode = currentNode * 2 + 2;
        this.recUpdate(segmentTree, leftNode, updateIndex, diff, leftIndex, midIndex);
        this.recUpdate(segmentTree, rightNode, updateIndex, diff, midIndex + 1, rightIndex);
        this.segmentTree[currentNode] = this.segmentTree[currentNode] + diff;
    }
}

public class Main {

    public static void main(String[] args) {
        int arr[] = {1, 3, 5, 7, 9, 11};
        //int arr[] = {1, 2};
        System.out.println(Arrays.toString(arr));
        SegmentTree st = new SegmentTree();
        st.buildTree(arr);
        st.displayTree();
        st.query(1, 3);
        st.updateValue(1, 2);
        st.query(1, 3);
    }
}
output:
[1, 3, 5, 7, 9, 11]
[36, 9, 27, 4, 5, 16, 11, 1, 3, 0, 0, 7, 9, 0, 0, 0, 0, 0]
Sum: 15
Sum: 14
BUILD SUCCESSFUL (total time: 0 seconds)
:END


Program 4: Binary Index Tree/ Fenwick Tree
------------------------------------------
START:
package solution;

import java.util.Arrays;

/**
 * explanation http://www.shafaetsplanet.com/?p=1961 code help:
 * https://www.geeksforgeeks.org/binary-indexed-tree-or-fenwick-tree-2/
 *
 * @author touhidul027
 */
public class BinaryIndexTree {

    private int[] arr;
    private int[] biTreeArr;

    public BinaryIndexTree(int[] arr) {
        System.out.println("Arrays:" + Arrays.toString(arr));
        this.arr = new int[arr.length];
        this.biTreeArr = new int[this.arr.length + 1];// all elements value is 0
        this.buildBinaryIndexedTree(arr);
    }

    private void buildBinaryIndexedTree(int[] arr) {
        System.out.println("Preparing BI tree");
        for (int i = 0; i < this.arr.length; i++) {
            this.updateBinaryIndexedTree(this.arr.length, i, arr[i]);
        }
    }

    private void updateBinaryIndexedTree(int n, int index, int val) {
        //update original array first
        this.arr[index] += val;

        index = index + 1;
        // Traverse all ancestors and add 'val'
        while (index <= n) {
            // Add 'val' to current node of BIT Tree
            this.biTreeArr[index] += val;
            // assign child index
            index += index & (-index);
        }
    }

    int getSum(int index) {
        System.out.println("++++getSum++++ index=" + index);
        System.out.println("BI Array:" + Arrays.toString(this.biTreeArr));
        int sum = 0;

        // index in this.biTreeArr[] is 1 more than the index in arr[]
        index = index + 1;

        // Traverse ancestors of this.biTreeArr[index]
        while (index > 0) {
            System.out.print("indx:" + index + ", ");
            sum += this.biTreeArr[index];
            // assign child index
            index -= index & (-index);
        }
        System.out.println("\nSum :" + sum);
        System.out.println("---- getSum ----");
        return sum;
    }

    //computing the sum of a range
    public int getSum(int startIndex, int endIndex) {
        System.out.println("++++ rangeSum +++++");
        int rangeSum = (this.getSum(endIndex) - this.getSum(startIndex - 1));
        System.out.println("Range-Sum: " + rangeSum);
        System.out.println("--- rangeSum ---");
        return rangeSum;
    }

    public static void main(String args[]) {
        int arr[] = {2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9};
        int n = arr.length;
        BinaryIndexTree tree = new BinaryIndexTree(arr);
        tree.getSum(11); // 51
        tree.updateBinaryIndexedTree(n, 5, 1);
        tree.getSum(11); // 52

        tree.getSum(10, 11);//17
    }
}

run:
Arrays:[2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9]
Preparing BI tree
++++getSum++++ index=11
BI Array:[0, 2, 3, 1, 7, 2, 5, 4, 21, 6, 13, 8, 30]
indx:12, indx:8, 
Sum :51
---- getSum ----
++++getSum++++ index=11
BI Array:[0, 2, 3, 1, 7, 2, 6, 4, 22, 6, 13, 8, 30]
indx:12, indx:8, 
Sum :52
---- getSum ----
++++ rangeSum +++++
++++getSum++++ index=11
BI Array:[0, 2, 3, 1, 7, 2, 6, 4, 22, 6, 13, 8, 30]
indx:12, indx:8, 
Sum :52
---- getSum ----
++++getSum++++ index=9
BI Array:[0, 2, 3, 1, 7, 2, 6, 4, 22, 6, 13, 8, 30]
indx:10, indx:8, 
Sum :35
---- getSum ----
Range-Sum: 17
--- rangeSum ---
BUILD SUCCESSFUL (total time: 0 seconds)
:END
