package org.demo.string;

import java.util.Arrays;

/**
 * 长度最小的子数组
 * https://leetcode.cn/leetbook/read/array-and-string/c0w4r/
 * 
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * 
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 * 
 * 提示：
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * 
 * 进阶：
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 */
public class MinSubArrayLength {

	/*
	 * 执行用时：1 ms, 在所有 Java 提交中击败了99.85%的用户
	 * 内存消耗：52.6 MB, 在所有 Java 提交中击败了33.16%的用户
	 * 通过测试用例：20 / 20
	 */
	public int minSubArrayLen2(int target, int[] nums) {
		int length = nums.length;
		int minLength = length;
		boolean isValid = false;

		/**
		 * 1. 
		 * { 1, 2, 3, 4, 5 }
		 *   i           j
		 * 2.  
		 * { 1, 2, 3, 4, 5 }
		 *      i        j
		 * 3.
		 * { 1, 2, 3, 4, 5 }
		 *         i     j
		 */
		for (int i = 0, j = 0, sum = 0, temp = 0; j < length; j++) {
			sum += nums[j];
			if (sum >= target) {
				isValid = true;
				temp = j - i + 1;
				if (temp < minLength) {
					minLength = temp;
				}
				sum -= nums[i++];
				sum -= nums[j--];
			}
		}

		return isValid ? minLength : 0;
	}

	// Error
	public int minSubArrayLen(int target, int[] nums) {
		int length = nums.length;
		int minLength = length;
		for (int i = 0, j = 0, sum = 0, temp = 0; j < length; j++) {
			sum += nums[j];
			if (sum >= target) {
				temp = j - i + 1;
				if (temp < minLength) {
					minLength = temp;
				}
				sum = 0;
				i = j + 1;
			}
		}

		return minLength == length ? 0 : minLength;
	}

	public static void main(String[] args) {
		// 2
		int[] nums1 = { 2, 3, 1, 2, 4, 3 };
		int target1 = 7;
		// 1
		int[] nums2 = { 1, 4, 4 };
		int target2 = 4;
		// 0
		int[] nums3 = { 1, 1, 1, 1, 1, 1, 1, 1 };
		int target3 = 11;
		// 3
		int target4 = 11;
		int[] nums4 = { 1, 2, 3, 4, 5 };

		long start;
		int result;
		MinSubArrayLength solution = new MinSubArrayLength();

		System.out.println("nums1:" + Arrays.toString(nums1) + ", target1:" + target1);
		start = System.currentTimeMillis();
		result = solution.minSubArrayLen2(target1, nums1);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {2} - Result:" + result + "\n");

		System.out.println("nums2:" + Arrays.toString(nums2) + ", target2:" + target2);
		start = System.currentTimeMillis();
		result = solution.minSubArrayLen2(target2, nums2);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {1} - Result:" + result + "\n");

		System.out.println("nums3:" + Arrays.toString(nums3) + ", target3:" + target3);
		start = System.currentTimeMillis();
		result = solution.minSubArrayLen2(target3, nums3);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {0} - Result:" + result + "\n");

		System.out.println("nums4:" + Arrays.toString(nums4) + ", target4:" + target4);
		start = System.currentTimeMillis();
		result = solution.minSubArrayLen2(target4, nums4);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {3} - Result:" + result + "\n");
	}

}
