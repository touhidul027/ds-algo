Greedy Algorithm
---------------------
revise version 0.1[1,2]

Program 1: Activity selection program
package ds_algo_github;

import java.util.HashMap;
import java.util.Map;

class Activity{
    public int activityNumber;
    public int start;
    public int end;

    public Activity(int activityNumber, int start, int end) {
        this.activityNumber = activityNumber;
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString() {
        return "Activity number:" + this.activityNumber + " -> Start:" + this.start + " , End:" + this.end;
    }
}
class ActivitySelection {
    private Activity[] activities;
    private int maxActivities;
    private int currentActivities;
    private Activity[] selectedActivities;
    private int selectedActivitiesnumber;

    public ActivitySelection(int maxActivities) {
        this.maxActivities = maxActivities;
        this.currentActivities = 0;
        this.activities = new Activity[maxActivities];
        this.selectedActivities = new Activity[maxActivities];
    }
    public void selectActivities() {
        this.selectedActivitiesnumber = 0;
        Activity key = this.activities[this.selectedActivitiesnumber];
        this.selectedActivities[this.selectedActivitiesnumber++] = key;
        for (int i = 1; i < this.currentActivities; i++ ) {
            if (key.end >= this.activities[i].start ) {
                continue;
            }
            this.selectedActivities[this.selectedActivitiesnumber++] = this.activities[i];
            key = this.activities[i];
        }
    }
	// recursive implementation
    public void selectActivitiesRec() {
        this.selectedActivitiesnumber = 0;
        Activity key = this.activities[this.selectedActivitiesnumber];
        this.selectedActivities[this.selectedActivitiesnumber++] = key;
        this.selectActivitiesRec(1, key.end);
    }
    private void selectActivitiesRec(int index, int previousEndTime) {
        if (index == this.currentActivitiesNumber) {
            return;
        }
        if (this.activities[index].start > previousEndTime) {
            this.selectedActivities[this.selectedActivitiesnumber++] = this.activities[index];
            previousEndTime = this.activities[index].end;
        }
        index++;  
        this.selectActivitiesRec(index,previousEndTime);
    }
    public void insert(Activity activity) {
        if (this.currentActivities == this.maxActivities) {
            System.out.println("No more insertion");
            return;
        }
        this.activities[currentActivities++] = activity;
        
        // sort using Insertion Sort(Any sorting applications)
        Activity key;
        int j;
        for (int i = 1; i < this.currentActivities; i++) {
            key = this.activities[i];
            j = i - 1;
            while ((j >= 0) && (this.activities[j].end > key.end)) {
                this.activities[j + 1] = this.activities[j];
                j--;
            }
            this.activities[j + 1] = key;
        }
    }
    public void displayActivities() {
        System.out.println("All activities:");
        for(int i = 0; i < this.currentActivities; i++) {
            System.out.println(" " + this.activities[i]);
        }
    }
    public void displaySelectedActivities () {
        System.out.println("Selected Activities are Followed :" + this.selectedActivitiesnumber);
        for (int i = 0; i < this.selectedActivitiesnumber; i++) {
            System.out.println(" " + this.selectedActivities[i]);
        }
    }
}
public class MainDriver {
    public static void main(String[] agrs) {
        ActivitySelection actSecObj = new ActivitySelection(50);
        actSecObj.insert(new Activity(1, 1, 4));
        actSecObj.insert(new Activity(2, 3, 5));
        actSecObj.insert(new Activity(3, 0, 6));
        actSecObj.insert(new Activity(4, 5, 7));
        actSecObj.insert(new Activity(5, 3, 9));
        actSecObj.insert(new Activity(6, 5, 9));
        actSecObj.insert(new Activity(7, 6, 10));
        actSecObj.insert(new Activity(8, 8, 11));
        actSecObj.insert(new Activity(9, 8, 12));
        actSecObj.insert(new Activity(10, 2, 14));
        actSecObj.insert(new Activity(11, 12, 16));
        actSecObj.displayActivities();
        actSecObj.selectActivities();
        actSecObj.displaySelectedActivities();
		actSecObj.selectActivitiesRec();
        actSecObj.displaySelectedActivities();
    }
}
:END

Program 2 : FractionalSnapsack
// FractionalSnapSack implemenation - greedy algorithm
// source: geekforgeeks
package ds_algo_github;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Item {
    public int weight;
    public int profit;
    public Float perKgProfit;

    public Item(int weight, int profit) {
        this.profit = profit;
        this.weight = weight;
        this.perKgProfit = new Float(profit / weight);
    }

    @Override
    public String toString() {
        return "{" + this.weight + ":" + this.profit + "}";
    }
}

class FractionalSnapSack {
    private Item[] items;
    private int maxItemNumbers;
    private int totalWeight;
    private int currentItemsnumber;
    private int maxProfit;

    public FractionalSnapSack(int maxItemNumbers, int totalWeight) {
        this.maxItemNumbers = maxItemNumbers;
        this.totalWeight = totalWeight;
        this.items = new Item[maxItemNumbers];
        this.currentItemsnumber = 0;
    }
    
    public void insert (int weight, int profit) {
        if (this.currentItemsnumber != this.maxItemNumbers) {
            this.items[this.currentItemsnumber++] = new Item(weight, profit);
        }
    }
    
    public void maxProfit() {
        this.sortItems();
        int remainingWeight = this.totalWeight;
        float fracProfit = 0;
        for (int i= 0; remainingWeight > 0 && i< this.currentItemsnumber; i++) {
           Item item = this.items[i];
           float itemProfit;
           // if item weight is greater than remaining weight, then take fractional weight of this item
           if (item.weight > remainingWeight) {
               // take only remaining  weight quantity from item
               itemProfit = item.perKgProfit * remainingWeight;
               remainingWeight = 0;
           } else {
               // take the total item
               itemProfit = item.profit;
               remainingWeight = remainingWeight - item.weight;
           }
           fracProfit += itemProfit;
        }
        this.maxProfit  = (int) fracProfit;
    }
    
    public void display() {
        System.out.println("Max Profit is : " + this.maxProfit);
    }

    private void sortItems() {
//        Arrays.sort(this.items, 0, this.currentItemsnumber,
//            new Comparator<Item>() {
//                @Override
//                public int compare(Item t, Item t1) {
//                    return t1.perKgProfit.compareTo(t.perKgProfit);
//                }               
//            }
//        );
        Arrays.sort(this.items,  0, this.currentItemsnumber,(t, t1) -> t1.perKgProfit.compareTo(t.perKgProfit));
    }
    
    public void displayItems() {
        for (int i = 0; i < this.currentItemsnumber; i++) {
            System.out.println(this.items[i]);
        }
    }
}

public class MainDriver {
    public static void main(String[] agrs) {
        FractionalSnapSack snapSack = new FractionalSnapSack(10, 15);
        snapSack.insert(1, 5);
        snapSack.insert(3, 10);
        snapSack.insert(5, 15);
        snapSack.insert(4, 7);
        snapSack.insert(1, 8);
        snapSack.insert(3, 9);
        snapSack.insert(2, 4);
        snapSack.maxProfit();
        snapSack.display();// 51
    }
}
:END

Program 3: Huffman coding
package ds_algo_github;

import java.io.*;
import java.util.*;

class HuffmanNode implements Comparable<Object> {

    public String sData;//data item (key)
    public Integer iData;//frequency of character
    public HuffmanNode leftChild;
    public HuffmanNode rightChild;

    public void displayNode() {
        System.out.print("{" + sData + ", " + iData + "}");
    }

    public int compareTo(Object o) {
        HuffmanNode node = (HuffmanNode) o;
        return (this.iData - node.iData);
    }
}

class HuffmanTree {

    private HuffmanNode root;//first node of tree
    private String[] huffManPrefixCodeTable;
    private String uniquePrefixCode;	//used to generate table with "0"s and "1"s

    public HuffmanTree(String initString) {
        uniquePrefixCode = new String();
        huffManPrefixCodeTable = new String[128]; //going to keep it ASCII for simplicity
        System.out.print("Initializing Huffman tree...");

        //Find frequencies of each character in input string
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        String s = initString;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer val = map.get(new Character(c));
            if (val != null) {
                map.put(c, new Integer(val + 1));
            } else {
                map.put(c, 1);
            }
        }

        //Insert each character into a node and fill a priority queue with nodes
        PriorityQueue<HuffmanNode> nodeQueue = new PriorityQueue<HuffmanNode>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            String key = Character.toString(entry.getKey());
            Integer value = entry.getValue();
            HuffmanNode tempNode = new HuffmanNode();
            tempNode.sData = key; //node contains character
            tempNode.iData = value; //and frequency of character
            nodeQueue.add(tempNode);
        }

        //Huffman Tree from priority  queue
        while (nodeQueue.size() > 1) {
            HuffmanNode temp1 = nodeQueue.remove();
            HuffmanNode temp2 = nodeQueue.remove();
            HuffmanNode newCombinedTree = new HuffmanNode();
            newCombinedTree.iData = temp1.iData + temp2.iData;
            newCombinedTree.leftChild = temp1;
            newCombinedTree.rightChild = temp2;
            nodeQueue.add(newCombinedTree);
        }
        //get 1 remaining node as root of entire tree
        root = nodeQueue.remove();
        System.out.println(" done");
    }

    public void createTable() {
        createTable(root);
    }

    private void createTable(HuffmanNode localRoot) {
        if (localRoot.sData != null) {
            char ch = localRoot.sData.charAt(0);
            int charASCIIValueToInt = (int) ch;
            huffManPrefixCodeTable[charASCIIValueToInt] = uniquePrefixCode;
            return;
        } else {
            uniquePrefixCode += "0";
            createTable(localRoot.leftChild);
            uniquePrefixCode = uniquePrefixCode.substring(0, uniquePrefixCode.length() - 1);

            uniquePrefixCode += "1";
            createTable(localRoot.rightChild);
            uniquePrefixCode = uniquePrefixCode.substring(0, uniquePrefixCode.length() - 1);
        }
    }

    // for encoding using huffman code is better 
    public String encode(String message) {
        String encodedResult = new String();
        //fill result with codes for each letter of message
        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);
            int charASCIIValueToIntAsIndex = (int) ch;
            encodedResult += huffManPrefixCodeTable[charASCIIValueToIntAsIndex];
        }
        return encodedResult;
    }

    // for decoding using huffman tree is better
    public String decode(String codedMessage) {
        String decodedResult = new String();
        HuffmanNode temp = root;
        int i = 0;
        while (i < codedMessage.length()) {
            if (temp.sData == null) {
                if (codedMessage.charAt(i) == '0') {
                    //System.out.println("going Left");
                    temp = temp.leftChild;
                } else if (codedMessage.charAt(i) == '1') {
                    //System.out.println("going Right");
                    temp = temp.rightChild;
                }
                i++;
            } else {
                //System.out.println("Found:" + temp.sData);
                decodedResult += temp.sData;
                temp = root;
            }
        }
        //System.out.println(temp.sData);
        decodedResult += temp.sData; //parse the last character
        return decodedResult;
    }
} //end class HuffmanTree

public class MainDriver {

    public static void main(String[] agrs) throws IOException {
        String originalString = "ABACA";
        String encodedString = new String();
        String decodedString = new String();
        HuffmanTree theTree = new HuffmanTree(originalString); // huffman-tree built  
        // create huffman prefix code Table
        theTree.createTable();
        System.out.println("Original String : " + originalString + " ,Length:" + originalString.length());
        encodedString = theTree.encode(originalString);
        System.out.println("Encoded String: " + encodedString + " ,Length:" + encodedString.length());
        decodedString = theTree.decode(encodedString);
        System.out.println("Decoded String: " + decodedString + " ,Length:" + decodedString.length());
    }
}
/*
output:
Initializing Huffman tree... done
Original String : ABACA ,Length:5
Encoded String: 1001011 ,Length:7
Decoded String: ABACA ,Length:5
*/
:END

Program 4: Job Sequence problem
package ds_algo_github;

import java.io.*;
import java.util.*;

class Task {

    public int id;
    public int deadLine;
    public int profit;

    public Task(int id, int deadLine, int profit) {
        this.id = id;
        this.deadLine = deadLine;
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "[" + this.id + " " + this.deadLine + " " + this.profit + "]";
    }
}

class TaskScheduler {

    private List<Task> tasks;
    private int maxDeadLine;
    private int[] slots;
    private int maxProfits;
    private List<Task> selectedTasks;

    public TaskScheduler(List<Task> tasks, int maxDeadLine) {
        this.tasks = tasks;
        this.maxDeadLine = maxDeadLine;
        this.slots = new int[maxDeadLine];
        this.maxProfits = 0;
        this.selectedTasks = new ArrayList<>();
        Collections.sort(tasks, (i, j) -> j.profit - i.profit);
        Arrays.fill(slots, -1);
    }

    public void scheduleTasks() {
        this.maxProfits = 0;
        for (Task task : tasks) {
            for (int i = task.deadLine; task.deadLine <= maxDeadLine && i > 0; i--) {
                if (this.slots[i - 1] == -1) {
                    this.slots[i - 1] = task.id;
                    this.maxProfits += task.profit;
                    this.selectedTasks.add(task);
                    break;
                }
            }
        }
        Collections.sort(this.selectedTasks, (i, j) -> i.deadLine - j.deadLine);
    }

    public void display() {
        System.out.println("Max Profit : " + this.maxProfits);
        for (Task task : this.selectedTasks) {
            System.out.println(task);
        }
    }
}

public class MainDriver {

    public static void main(String[] agrs) throws IOException {
        List<Task> tasks = Arrays.asList(
                new Task(1, 9, 15),
                new Task(2, 2, 2),
                new Task(3, 5, 18),
                new Task(4, 7, 1),
                new Task(5, 4, 25),
                new Task(6, 2, 20),
                new Task(7, 5, 8),
                new Task(8, 7, 10),
                new Task(9, 4, 12),
                new Task(10, 3, 5)
        );

        // stores maximum deadline that can be associated with a job
        int maxDeadLine = 15;
        TaskScheduler taskScheduler = new TaskScheduler(tasks, maxDeadLine);
        taskScheduler.scheduleTasks();
        taskScheduler.display();
    }
}
:END