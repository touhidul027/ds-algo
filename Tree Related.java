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
