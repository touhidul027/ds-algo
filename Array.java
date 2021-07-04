
Program:1 SuffixArray
----------------------
// Naive Implementation
package solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Suffix implements Comparable<Suffix> {

    private int index;
    private String suffix;

    public Suffix(int index, String suffix) {
        this.index = index;
        this.suffix = suffix;
    }

    @Override
    public int compareTo(Suffix t) {
        return this.getSuffix().compareTo(t.getSuffix());
    }

    public int getIndex() {
        return index;
    }

    public String getSuffix() {
        return suffix;
    }

    @Override
    public String toString() {
        String info = this.index + " " + this.suffix;
        return info;
    }

}

public class SuffixArray {

    private static final int MAX = 20;
    private List<Suffix> suffixes;

    public SuffixArray() {
        this.suffixes = new ArrayList<>();
    }

    public void printSuffixArray(String str) {
        int strLen = str.length();

        //collect the suffix array linearly
        for (int i = 0; i < strLen; i++) {
            this.suffixes.add(new Suffix(i, str.substring(i, strLen)));
        }
        // sort the <code>this.suffixes</code> lexicographically
        // collections doesn't work with array
        Collections.sort(this.suffixes);

        System.out.println("Suffix array:");
        for (Suffix s : this.suffixes) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        SuffixArray suffixArray = new SuffixArray();
        suffixArray.printSuffixArray("banana");
    }
}
Output:
Suffix array:
5 a
3 ana
1 anana
0 banana
4 na
2 nana
