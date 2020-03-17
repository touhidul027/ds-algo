Dynamic Programming
-------------------
//implementing Rod cutting algorithm : top-down approach
Program : A
package ds_algo_github;

    class CutRodDyanamic {
        int[] profitArr;
        int length;
        int[] revenuArr;

        public CutRodDyanamic(int[] profitArr, int len) {
            this.length = len;
            this.profitArr = new int[len+1];
            this.revenuArr = new int[len+1];
            for(int i=1; i<= this.length; i++) {
                this.profitArr[i] = profitArr[i];
            }
        }
        public void memoizedCutRod () {
            for (int i = 0; i <= this.length; i++) {
                this.revenuArr[i] = -1;
            }
            int maxProfit = this.memoizedCutRodAux(this.length);
            System.out.println("Maxprofit : " + maxProfit);
        }
        public int memoizedCutRodAux (int currentLength) {
            if( this.revenuArr[currentLength]>=0 ) {
                return this.revenuArr[currentLength];
            }
            int q = -1;
            if (currentLength == 0) {
                q = 0;
            } else {
                for (int i = 1; i <= currentLength; i++) {
                    int a = this.profitArr[i];
                    int b = memoizedCutRodAux(currentLength-i);
                    if((a + b) > q) {
                        q = a + b;
                    }
                }
            }
            this.revenuArr[currentLength] = q;
            return q;
        }
    }
public class MainDriver {
    public static void main(String[] agrs) {
        int[] arr = {0,1,5,8,9};
        CutRodDyanamic obj = new CutRodDyanamic( arr, 4);
        obj.memoizedCutRod();
    }
}
//END : implementing Rod cutting algorithm : top-down approach

program B : 
//implementing Rod cutting algorithm : bottom-up approach
package ds_algo_github;

    class CutRodDyanamic {
        int[] profitArr;
        int length;
        int[] revenuArr;

        public CutRodDyanamic(int[] profitArr, int len) {
            this.length = len;
            this.profitArr = new int[len+1];
            this.revenuArr = new int[len+1];
            for(int i=1; i<= this.length; i++) {
                this.profitArr[i] = profitArr[i];
            }
        }
        public void cutRod () {
            for (int i = 0; i <= this.length; i++) {
                this.revenuArr[i] = -1;
            }
            int maxProfit = this.bottomUpCutRod(this.length);
            System.out.println("Maxprofit : " + maxProfit);
        }
        public int bottomUpCutRod (int currentLength) {
            this.revenuArr[0] = 0;
            for (int i = 1; i<= currentLength; i++) {
                int maxLengthRevenue = -1;
                for (int j = 1; j <= i; j++) {
                    int a = this.profitArr[j];
                    int b = this.revenuArr[i-j];
                    if (maxLengthRevenue < (a+b)) {
                        maxLengthRevenue = a + b;
                    }
                }
                this.revenuArr[i] = maxLengthRevenue;
            }
            return this.revenuArr[currentLength];
        }
    }
public class MainDriver {
    public static void main(String[] agrs) {
        int[] arr = {0,1,5,8,9};
        CutRodDyanamic obj = new CutRodDyanamic( arr, 4);
        obj.cutRod();
    }
}
B: END:

Program C: 
//implementing Rod cutting algorithm : extended - bottom-up approach
package ds_algo_github;

    class CutRodDyanamic {
        private int[] profitArr;
        private int length;
        private int[] revenuArr;
        private int[] unitsCutPosition;

        public CutRodDyanamic(int[] profitArr, int len) {
            this.length = len;
            this.profitArr = new int[len+1];
            this.revenuArr = new int[len+1];
            this.unitsCutPosition= new int[len+1];
            for(int i=1; i<= this.length; i++) {
                this.profitArr[i] = profitArr[i];
            }
        }
        public void cutRod () {
            for (int i = 0; i <= this.length; i++) {
                this.revenuArr[i] = -1;
            }
            this.extendedBottomUpCutRod(this.length);
        }
        public void extendedBottomUpCutRod (int units) {
            this.revenuArr[0] = 0;
            for (int unit = 1; unit<= units; unit++) {
                int unitRevenue = -1;
                int unitsCuttingPoint = -1;
                for (int j = 1; j <= unit; j++) {
                    int a = this.profitArr[j];
                    int b = this.revenuArr[unit-j];
                    if (unitRevenue < (a+b)) {
                        unitRevenue = a + b;
                        unitsCuttingPoint = j;
                    }
                }
                this.revenuArr[unit] = unitRevenue;
                this.unitsCutPosition[unit] = unitsCuttingPoint;
            }
        }
        public void printSolution (int nUnits) {
            int n = nUnits;
            while (n > 0) {
                System.out.println("Cut units: " + this.unitsCutPosition[n]);
                n = n - this.unitsCutPosition[n];
            }
            System.out.println("Maximum profit: " + this.revenuArr[nUnits]);
        } 
    }
public class MainDriver {
    public static void main(String[] agrs) {
        int[] arr = {0,1,5,8,9};
        CutRodDyanamic obj = new CutRodDyanamic( arr, 4);
        obj.cutRod();
        obj.printSolution(4);
    }
}
C: END

Program D:
Fibonnaci Series using Dynamic problem
package ds_algo_github;

class Fibonacci {
    private int[] series;

    public Fibonacci (int n) {
        series = new int[n];
        for (int i = 2; i < n; i++) {
            series[i] = -1;
        }
        series[0] = 0;
        series[1] = 1;
    }

    public int fibonacci (int nthTerm) {
        if (series[nthTerm] >= 0) {
            return series[nthTerm];
        }
        int result = fibonacci(nthTerm-1) + fibonacci(nthTerm-2);
        series[nthTerm] = result;
        return result;
    }
    public void printSeries(int nthTerm) {
        for (int i = 0; i < nthTerm; i++) {
            System.out.print( series[i] + " ");
        }
    }
}

public class MainDriver {
    public static void main(String[] agrs) {
        Fibonacci fib  = new Fibonacci(50);
        fib.fibonacci(10);
        fib.printSeries(10);
    }
}
END:

Program E:
Longest common subsequence : Top down approach(CS Dojo)
package ds_algo_github;

//top down approach
class LongestCommonSubsequence {
    char[] a;
    char[] b;
    private int[][] ab;
    private int len_a;
    private int len_b;
    
    public LongestCommonSubsequence(String str1,String str2) {
        this.a = str1.toCharArray();
        this.b = str2.toCharArray();
        this.len_a = str1.length();
        this.len_b = str2.length();
        this.ab = new int[this.len_a+1][this.len_b+1];
        for (int i = 0; i <= this.len_a; i++) {
            for (int j = 0; j <= this.len_b; j++) {
                if (i ==0 || j == 0) {
                    this.ab[i][j] = 0;
                } else {
                    this.ab[i][j] = -1;
                }
            }
        }
    }
    private int lcs() {
        return lcsAux(this.a,this.b,this.len_a,this.len_b);
    }
    private int lcsAux(char[] a,char[] b, int lenA, int lenB) {
        System.out.println("for " + lenA + "" + lenB);
        if(ab[lenA][lenB] >= 0) {
            System.out.println("known "+ lenA +""+ lenB +" " + ab[lenA][lenB] );
            return ab[lenA][lenB];
        }
        int result = 0;
        if( lenA == 0 || lenB == 0) {
            result = 0;
        }else if (a[lenA-1] == b[lenB-1]) {
            System.out.println("1 ->" + result);
            result = 1 + lcsAux(a,b,lenA-1,lenB-1);
        } else {
            System.out.println("unequal");
            int tmp1 = lcsAux(a,b,lenA-1,lenB);
            int tmp2 = lcsAux(a,b,lenA,lenB-1);
            result = (tmp1 > tmp2) ? tmp1 : tmp2;
            System.out.println("2 -> " + result);
        }
        this.ab[lenA][lenB] = result;
        return result;
    }
    public void lcsLength() {
        System.out.println(this.a + " " + this.b.toString() + " " + this.lcs());
    }
}

public class MainDriver {
    public static void main(String[] agrs) {
        LongestCommonSubsequence obj  = new LongestCommonSubsequence("ABCSDE", "AdBxCxSxDxE");
        obj.lcsLength();
    }
}
END:

Program F : Finding LongestCommonSubsequence
//  using bottom-up approach
package ds_algo_github;

class LongestCommonSubsequence {
    private char[] a;
    private char[] b;
    private char[] sequence;
    private int[][] ab;
    private int len_a;
    private int len_b;

    public LongestCommonSubsequence(String str1,String str2) {
        this.a = str1.toCharArray();
        this.b = str2.toCharArray();
        this.len_a = str1.length();
        this.len_b = str2.length();
        this.ab = new int[this.len_a+1][this.len_b+1];
        for (int i = 0; i <= this.len_a; i++) {
            for (int j = 0; j <= this.len_b; j++) {
                if (i ==0 || j == 0) {
                    this.ab[i][j] = 0;
                } else {
                    this.ab[i][j] = -1;
                }
            }
        }
    }
    public void lcsTable() {
        lcsAux();
        printLCSTable();
    }
    private int lcsAux() {
        for (int i = 1; i <= this.len_a ; i++) {
            for (int j = 1; j <= this.len_b; j++) {
                int cellValue = 0;
                if (this.a[i-1] == this.b[j-1]) {
                    cellValue = this.ab[i-1][j-1] + 1;
                } else if(this.ab[i][j-1] > this.ab[i-1][j]) {
                    cellValue = this.ab[i][j-1];
                } else {
                    cellValue = this.ab[i-1][j];
                }
                this.ab[i][j] = cellValue;
            }
        }
        return 0;
    }
    public void printLCSTable () {
        for (int i = 0 ; i <= this.len_a; i++) {
            for (int j = 0; j <= this.len_b; j++) {
                System.out.print(this.ab[i][j] + " ");
            }
            System.out.println("");
        }
    }
    public void printSequence() {
        int maxSeqNums = this.ab[this.len_a][this.len_b];
        int m = maxSeqNums;
        this.sequence = new char[maxSeqNums];
        System.out.println("Max: " + this.ab[this.len_a][this.len_b]);
        int aLen = this.len_a;
        int bLen = this.len_b;
        while(this.ab[aLen][bLen]>0) {
            int currentNumber  = this.ab[aLen][bLen];
            int leftCellNumber = this.ab[aLen][bLen-1];
            int topCellnumber = this.ab[aLen-1][bLen];
            if (currentNumber  == topCellnumber) {
                aLen--;
            } else if(currentNumber == leftCellNumber) {
                bLen--;
            } else {
                this.sequence[--maxSeqNums] = this.a[aLen-1];
                aLen--;
                bLen--;
            }
        }
        System.out.print("Sequence is :");
        for (int i = 0; i < m; i++) {
            System.out.print(" " + this.sequence[i]);
        }
    }
}
public class MainDriver {
    public static void main(String[] agrs) {
        //LongestCommonSubsequence obj  = new LongestCommonSubsequence("abcbdab", "bdcaba");
        LongestCommonSubsequence obj  = new LongestCommonSubsequence("bacdb", "bdcb");
        obj.lcsTable();
        obj.printSequence();
        System.out.println("");
    }
}

Program 7: Longest Increasing subsequence
// bottom-up approach
package ds_algo_github;

class LongestIncreasingSubsequence{
    private int[] sequences;
    private int[] LIS;
    private int seqLen;
    private int maxSequence;

    public LongestIncreasingSubsequence(int[] sequences) {
        this.sequences = sequences;
        this.seqLen = sequences.length;
        this.LIS = new int[this.seqLen];
        for (int i = 0; i < this.seqLen; i++) {
            this.LIS[i] = 1;
        }
    }
    public void displaySequences() {
        System.out.print("Sequences : ");
        for(int i = 0; i < this.seqLen; i++) {
            System.out.print(this.sequences[i]+ " ");
        }
    }
    public void calculateLISMax() {
        for(int i = 1; i < this.seqLen; i++) {
            for (int j = 0; j < i; j++) {
                if (this.sequences[j] < this.sequences[i]) {
                    int temp = this.LIS[j] + 1;
                    if (temp > this.LIS[i]) {
                        this.LIS[i] = temp;
                    }
                }
            }
        }
    }
    public void displayLIS() {
        System.out.print("\nLIS:");
        for (int i = 0; i < this.seqLen; i++) {
            System.out.print(" " + this.LIS[i]);
        }
    }
    public void  displayMaxLength() {
        // find the largest element from LIS array
        int max = -1;
        for (int i = 0; i < this.seqLen; i++) {
            if(this.LIS[i] > max) {
                max = this.LIS[i];
            }
        }
        System.out.println("\nMax Length : " + max);
    }
}
public class MainDriver {
    public static void main(String[] agrs) {
        int[] sequences = {5,3,6,4,7};
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence(sequences);
        lis.calculateLISMax();
        lis.displaySequences();
        lis.displayLIS();
        lis.displayMaxLength();
        System.out.println("");
    }
}
:END

problem 7.1
LIS using recursion- top-down approach
package ds_algo_github;

// using recursion - top down-upapproach
class LongestIncreasingSubsequence{
    private int[] sequences;
    private int seqLen;
    private int maxSequence;

    public LongestIncreasingSubsequence(int[] sequences) {
        this.sequences = sequences;
        this.seqLen = sequences.length;
    }
    public int calculateLISMax(int currentIndex,int previousElement) {
        if (currentIndex == this.seqLen) {
            return 0;
        }
        // case 1: exclude the current element and process the
        // remaining elements
        int a = this.calculateLISMax(currentIndex+1, previousElement);
        int b = 0;
        // case 2: include the current element if it is greater
        // than previous element in LIS
        if (this.sequences[currentIndex] > previousElement) {
            b = 1 + this.calculateLISMax(currentIndex+1, this.sequences[currentIndex]);
        }
        return Integer.max(a, b);
    }
    public void displayLISLength() {
        this.maxSequence = this.calculateLISMax(0, -100);
        System.out.println("Max sequence is :" + this.maxSequence);
    }
}
public class MainDriver {
    public static void main(String[] agrs) {
        int[] sequences = {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15};
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence(sequences);
        lis.displayLISLength();//6
    }
}
:END
