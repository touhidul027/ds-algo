Dynamic Programming
-------------------
//implementing Rod cutting algorithm : top-down approach
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
