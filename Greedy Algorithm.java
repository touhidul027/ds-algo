Greedy Algorithm
---------------------
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
           if (item.weight > remainingWeight) {
               // take only remaining  weight quantity from item
               itemProfit = item.perKgProfit * remainingWeight;
               remainingWeight = 0;
           } else {
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