package org.demo.easy;

import java.util.Arrays;

/**
 *  可被三整除的偶数的平均值
 *  https://leetcode.cn/problems/average-value-of-even-numbers-that-are-divisible-by-three/
 *  
 *  给你一个由正整数组成的整数数组 nums ，返回其中可被 3 整除的所有偶数的平均值。
 *  注意：n 个元素的平均值等于 n 个元素 求和 再除以 n ，结果 向下取整 到最接近的整数。
 *  
 *  示例 1：
 *  输入：nums = [1,3,6,10,12,15]
 *  输出：9
 *  解释：6 和 12 是可以被 3 整除的偶数。(6 + 12) / 2 = 9 。
 *  
 *  示例 2：
 *  输入：nums = [1,2,4,7,10]
 *  输出：0
 *  解释：不存在满足题目要求的整数，所以返回 0 。
 *  
 *  提示：
 *  1 <= nums.length <= 1000
 *  1 <= nums[i] <= 1000
 *  通过次数18,815提交次数29,2
 */
public class Q2455 {

	public int averageValue(int[] nums) {
		int count = 0;
		int sum = 0;
		for (int num : nums) {
			if (0 == num % 6) {
//			if (0 == num % 3 && 0 == num % 2) {
				count++;
				sum += num;
			}
		}

		if (0 == count)
			return 0;

		return sum / count;
	}

	public static void main(String[] args) {
		// int[] nums = { 1, 3, 6, 10, 12, 15 };
		// int[] nums = { 1, 2, 4, 7, 10 };
		int[] nums = { 4, 4, 9, 10 };

		System.out.println("nums:" + Arrays.toString(nums));
		Q2455 solution = new Q2455();
		int result = solution.averageValue(nums);
		System.out.println("Result:" + result);
	}
}
