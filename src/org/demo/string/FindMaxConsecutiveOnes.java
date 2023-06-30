package org.demo.string;

import java.util.Arrays;

/**
 * 最大连续1的个数
 * https://leetcode.cn/leetbook/read/array-and-string/cd71t/
 * 
 * 给定一个二进制数组 nums ， 计算其中最大连续 1 的个数。
 * 
 * 示例 1：
 * 输入：nums = [1,1,0,1,1,1]
 * 输出：3
 * 解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
 * 
 * 示例 2:
 * 输入：nums = [1,0,1,1,0,1]
 * 输出：2
 * 
 * 提示：
 * 1 <= nums.length <= 105
 * nums[i] 不是 0 就是 1.
 */
public class FindMaxConsecutiveOnes {

	/*
	 * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
	 * 内存消耗：43.5 MB, 在所有 Java 提交中击败了14.82%的用户
	 * 通过测试用例：42 / 42
	 */
	public int findMaxConsecutiveOnes2(int[] nums) {
		int maxConsecutive = 0;
		int temp;
		for (int start = -1, end = 0, limit = nums.length - 1; end <= limit; end++) {
			if (1 != nums[end]) {
				temp = end - start - 1;
			} else if (end == limit) {
				temp = end - start;
			} else {
				continue;
			}
			if (temp > maxConsecutive) {
				maxConsecutive = temp;
			}
			start = end;
		}
		return maxConsecutive;
	}

	/*
	 * 执行用时：2 ms, 在所有 Java 提交中击败了45.13%的用户
	 * 内存消耗：43.3 MB, 在所有 Java 提交中击败了37.37%的用户
	 * 通过测试用例：42 / 42
	 */
	public int findMaxConsecutiveOnes(int[] nums) {
		int maxConsecutive = 0;
		for (int i = 0, limit = nums.length - 1, count = 0; i <= limit; i++) {
			if (1 == nums[i]) {
				count++;
				if (i == limit) {
					if (count > maxConsecutive) {
						maxConsecutive = count;
					}
				}
			} else {
				if (count > maxConsecutive) {
					maxConsecutive = count;
				}
				count = 0;
			}
		}
		return maxConsecutive;
	}

	public static void main(String[] args) {
		// 3
		int[] nums1 = { 1, 1, 0, 1, 1, 1 };
		// 2
		int[] nums2 = { 1, 0, 1, 1, 0, 1 };
		// 0
		int[] nums3 = { 0 };
		// 0
		int[] nums4 = { 0, 0 };

		long start;
		int result;
		FindMaxConsecutiveOnes solution = new FindMaxConsecutiveOnes();

		System.out.println("nums1:" + Arrays.toString(nums1));
		start = System.currentTimeMillis();
		result = solution.findMaxConsecutiveOnes2(nums1);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {3} - Result:" + result + "\n");

		System.out.println("nums2:" + Arrays.toString(nums2));
		start = System.currentTimeMillis();
		result = solution.findMaxConsecutiveOnes2(nums2);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {2} - Result:" + result + "\n");

		System.out.println("nums3:" + Arrays.toString(nums3));
		start = System.currentTimeMillis();
		result = solution.findMaxConsecutiveOnes2(nums3);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {0} - Result:" + result + "\n");

		System.out.println("nums4:" + Arrays.toString(nums4));
		start = System.currentTimeMillis();
		result = solution.findMaxConsecutiveOnes2(nums4);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {0} - Result:" + result + "\n");

	}

}
