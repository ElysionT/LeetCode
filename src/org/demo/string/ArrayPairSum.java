package org.demo.string;

import java.util.Arrays;

/**
 * 数组拆分 I
 * https://leetcode.cn/leetbook/read/array-and-string/c24he
 * 
 * 给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。
 * 返回该 最大总和 。
 * 
 * 示例 1：
 * 输入：nums = [1,4,3,2]
 * 输出：4
 * 解释：所有可能的分法（忽略元素顺序）为：
 * 1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
 * 2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
 * 3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
 * 所以最大总和为 4
 * 
 * 示例 2：
 * 输入：nums = [6,2,6,5,1,2]
 * 输出：9
 * 解释：最优的分法为 (2, 1), (2, 5), (6, 6). min(2, 1) + min(2, 5) + min(6, 6) = 1 + 2 + 6 = 9
 * 
 * 提示：
 * 1 <= n <= 104
 * nums.length == 2 * n
 * -104 <= nums[i] <= 104
 */
public class ArrayPairSum {

	/*
	 * 执行用时：12 ms, 在所有 Java 提交中击败了97.84%的用户
	 * 内存消耗：44.2 MB, 在所有 Java 提交中击败了18.39%的用户
	 * 通过测试用例：83 / 83
	 */
	public int arrayPairSum2(int[] nums) {
		Arrays.sort(nums);

		int result = 0;
		for (int i = 0, length = nums.length; i < length; i += 2) {
			result += nums[i];
		}

		return result;
	}

	/*
	 * 执行用时：13 ms, 在所有 Java 提交中击败了37.19%的用户
	 * 内存消耗：44.3 MB, 在所有 Java 提交中击败了6.76%的用户
	 * 通过测试用例：83 / 83
	 */
	public int arrayPairSum(int[] nums) {
		Arrays.sort(nums);

		int result = 0;
		for (int i = 0, length = nums.length; i < length; i += 2) {
			result += Math.min(nums[i], nums[i + 1]);
		}

		return result;
	}

	public static void main(String[] args) {
		// 4
		int[] nums1 = { 1, 4, 3, 2 };
		// 9
		int[] nums2 = { 6, 2, 6, 5, 1, 2 };

		long start;
		int result;
		ArrayPairSum solution = new ArrayPairSum();

		System.out.println("nums1:" + Arrays.toString(nums1));
		start = System.currentTimeMillis();
		result = solution.arrayPairSum2(nums1);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {4} - Result:" + result + "\n");

		System.out.println("nums2:" + Arrays.toString(nums2));
		start = System.currentTimeMillis();
		result = solution.arrayPairSum2(nums2);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {9} - Result:" + result + "\n");
	}

}
