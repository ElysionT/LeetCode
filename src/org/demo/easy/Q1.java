package org.demo.easy;

import java.util.Arrays;

/**
 * 1. 两数之和
 * https://leetcode.cn/problems/two-sum/
 * 
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * 
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 
 * 示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 
 * 示例 3：
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 * 
 * 提示：
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 */
public class Q1 {
	@Deprecated
	public int[] twoSum(int[] nums, int target) {
		int ii = -1;
		int temp = -1;
		for (int i = 0, j = 0; i < nums.length;) {
			if (ii != i) {
				ii = i;
				temp = target - nums[i];
				j = i + 1;
			}

			if (temp == nums[j]) {
				return new int[] { i, j };
			} else if (j == nums.length - 1) {
				i++;
				j = 0;
			} else {
				j++;
			}

		}
		return null;
	}

	public int[] twoSum2(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			int temp = target - nums[i];
			for (int j = i + 1; j < nums.length; j++) {
				if (temp == nums[j]) {
					String result = "[" + i + "+" + "j" + "]";
					System.out.println("result:" + result);
					return new int[] { i, j };
				}
			}

		}
		return null;
	}

	private int[] mNums;
	private int mLength;

	public int[] twoSum3(int[] nums, int target) {
		mNums = nums;
		mLength = mNums.length;

		for (int i = 0; i < mLength; i++) {
			int target_ = target - mNums[i];
			int temp = twoSumInternal(i + 1, target_);
			if (-1 != temp) {
				return new int[] { i, temp };
			}
		}

		throw new RuntimeException("Not found! nums:" + Arrays.toString(nums) + ", target:" + target);
	}

	public int twoSumInternal(int start, int target) {
		for (int i = start, j = mLength - 1; i <= j; i++, j--) {
			int temp = mNums[i];
			if (target == temp)
				return i;

			temp = mNums[j];
			if (target == temp)
				return j;
		}
		return -1;
	}

	public static void main(String[] args) {
		// [0,1]
		// int[] nums = { 2, 7, 11, 15 };
		// int target = 9;

		// [1,2]
		// int[] nums = { 3,2,4 };
		// int target = 6;

		// [0,1]
		int[] nums = { 3, 3 };
		int target = 6;

		System.out.println("nums:" + Arrays.toString(nums) + ", target:" + target);
		Q1 solution = new Q1();
		long start = System.currentTimeMillis();
		int[] result = solution.twoSum3(nums, target);
		System.out.println("result:" + Arrays.toString(result) + ", Time:" + (System.currentTimeMillis() - start));
	}

}
