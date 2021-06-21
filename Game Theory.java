Program 1: MinMax algorithm
START:
package solution;
//https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-1-introduction/
public class MinMax {

    static int minimax(int curDepth, int currentNodeIndex, boolean isMaximizer,
            int scores[], int h) {
        // Terminating condition. i.e leaf node is reached
        if (curDepth == h) {
            return scores[currentNodeIndex];
        }
        int leftLeaf = minimax(curDepth + 1, currentNodeIndex * 2, !isMaximizer, scores, h);
        int rightLeaf = minimax(curDepth + 1, currentNodeIndex * 2 + 1, !isMaximizer, scores, h);
        int result = 0;
        if (isMaximizer) {
            result = Math.max(leftLeaf, rightLeaf);
        } else {
            result = Math.min(leftLeaf, rightLeaf);
        }
        return result;
    }

    static int log2(int n) {
        return (n == 1) ? 0 : 1 + log2(n / 2);
    }

    public static void main(String[] args) {
        // The number of elements in scores must be a power of 2.
        int scores[] = {3, 5, 2, 9, 12, 5, 23, 23};
        int n = scores.length;
        int h = log2(n);
        int res = minimax(0, 0, true, scores, h);//12
        System.out.println("The optimal value is : " + res);
    }
}
:END