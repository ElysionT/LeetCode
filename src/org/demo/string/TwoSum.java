package org.demo.string;

import java.util.Arrays;

/**
 * 两数之和 II - 输入有序数组
 * https://leetcode.cn/leetbook/read/array-and-string/cnkjg/
 * 
 * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。
 * 如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
 * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 * 你所设计的解决方案必须只使用常量级的额外空间。
 * 
 * 示例 1：
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
 * 
 * 示例 2：
 * 输入：numbers = [2,3,4], target = 6
 * 输出：[1,3]
 * 解释：2 与 4 之和等于目标数 6 。因此 index1 = 1, index2 = 3 。返回 [1, 3] 。
 * 
 * 示例 3：
 * 输入：numbers = [-1,0], target = -1
 * 输出：[1,2]
 * 解释：-1 与 0 之和等于目标数 -1 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
 * 
 * 提示：
 * 2 <= numbers.length <= 3 * 104
 * -1000 <= numbers[i] <= 1000
 * numbers 按 非递减顺序 排列
 * -1000 <= target <= 1000
 * 仅存在一个有效答案
 */
public class TwoSum {

	/*
	 * 执行用时：1 ms, 在所有 Java 提交中击败了98.78%的用户
	 * 内存消耗：44.9 MB, 在所有 Java 提交中击败了39.70%的用户
	 * 通过测试用例：23 / 23
	 */
	public int[] twoSum(int[] numbers, int target) {
		int iNumber, diff, jNumber;
		for (int i = 0, length = numbers.length, j = length - 1; i < length; i++) {
			iNumber = numbers[i];
			diff = target - iNumber;
			// 每次循环 j 不需要重新初始化，因为升序排列 diff(i+1)<= diff(i). 所以可以继续从numbers[j] 倒序匹配。
			for (; j >= 0; j--) {
				jNumber = numbers[j];
				if (diff == jNumber) {
					return new int[] { i + 1, j + 1 };
				} else if (diff > jNumber) {
					break;
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		// [1,2]
		int[] numbers1 = { 2, 7, 11, 15 };
		int target1 = 9;
		// [1,3]
		int[] numbers2 = { 2, 3, 4 };
		int target2 = 6;
		// [1,2]
		int[] numbers3 = { -1, 0 };
		int target3 = -1;

		long start;
		int[] result;
		TwoSum solution = new TwoSum();

		System.out.println("numbers1:" + Arrays.toString(numbers1) + ", target1:" + target1);
		start = System.currentTimeMillis();
		result = solution.twoSum(numbers1, target1);
		System.out.println(
				"Time:" + (System.currentTimeMillis() - start) + ", [1,2] - Result:" + Arrays.toString(result) + "\n");

		System.out.println("numbers2:" + Arrays.toString(numbers2) + ", target2:" + target2);
		start = System.currentTimeMillis();
		result = solution.twoSum(numbers2, target2);
		System.out.println(
				"Time:" + (System.currentTimeMillis() - start) + ", [1,3] - Result:" + Arrays.toString(result) + "\n");

		System.out.println("numbers3:" + Arrays.toString(numbers3) + ", target3:" + target3);
		start = System.currentTimeMillis();
		result = solution.twoSum(numbers3, target3);
		System.out.println(
				"Time:" + (System.currentTimeMillis() - start) + ", [1,2] - Result:" + Arrays.toString(result) + "\n");
	}

}
