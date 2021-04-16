package main;

class MaximumSumSubarray {

    /**
     * used brute force method to find max sum sub-array. It won't track the
     * starting and ending index
     *
     * @param nums
     */
    static int maxSumBruteForce(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int arrLen = nums.length;

        for (int i = 0; i < arrLen; i++) {
            int tempSum = nums[i];
            if (tempSum > maxSum) {
                maxSum = tempSum;
            }
            int j = i + 1;
            while (tempSum > 0 && j < arrLen) {
                tempSum += nums[j];
                if (tempSum > maxSum) {
                    maxSum = tempSum;
                }
                j++;
            }
        }
        return maxSum;
    }
	
	/**
     * kadanes algorithm to find maximum sum of contiguous subarray
     *
     * @param nums
     */
    static int maxSumOfSubArrayKadanesAlgorithm(int[] nums) {
        int currentSum = nums[0];
        int bestSum = nums[0];
        int arrLen = nums.length;

        for (int i = 1; i < arrLen; i++) {
            currentSum = Integer.max(nums[i], currentSum + nums[i]);
            bestSum = Integer.max(bestSum, currentSum);
        }
        return bestSum;
    }
}

public class Main {

    public static void main(String[] args) {
        //int[] arr = {-2, 3, -1, 2};
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSum = MaximumSumSubarray.maxSumBruteForce(arr);
        System.out.println(maxSum);
    }
}
