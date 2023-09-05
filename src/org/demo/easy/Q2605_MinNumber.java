package org.demo.easy;

import java.util.Arrays;

/**
 * 2605. 从两个数字数组里生成最小数字
 * https://leetcode.cn/problems/form-smallest-number-from-two-digit-arrays/
 * 
 * 给你两个只包含 1 到 9 之间数字的数组 nums1 和 nums2 ，每个数组中的元素 互不相同 ，请你返回 最小 的数字，两个数组都 至少 包含这个数字的某个数位。
 * 
 * 示例 1：
 * 输入：nums1 = [4,1,3], nums2 = [5,7]
 * 输出：15
 * 解释：数字 15 的数位 1 在 nums1 中出现，数位 5 在 nums2 中出现。15 是我们能得到的最小数字。
 * 
 * 示例 2：
 * 输入：nums1 = [3,5,2,6], nums2 = [3,1,7]
 * 输出：3
 * 解释：数字 3 的数位 3 在两个数组中都出现了。
 * 
 * 提示：
 * 1 <= nums1.length, nums2.length <= 9
 * 1 <= nums1[i], nums2[i] <= 9
 * 每个数组中，元素 互不相同 。
 */
public class Q2605_MinNumber {
	/*
	 * 时间: 1ms 击败 65.22%使用 Java 的用户
	 * 内存: 38.70MB 击败 14.78%使用 Java 的用户
	 */
	public int minNumber2(int[] nums1, int[] nums2) {
		int result = 10, min1 = 10, min2 = 10;
		int length1 = nums1.length, length2 = nums2.length;
		int[] sub1 = new int[10], sub2 = new int[10];

		for (int i = 0, j = 0, index = -1; i < length1 || j < length2; i++, j++) {
			if (i < length1) {
				index = nums1[i];
				if (index < result) {
					min1 = Math.min(min1, index);
					sub1[index] = 1;
					if (1 == sub2[index]) {
						result = index;
					}
				}
			}
			if (j < length2) {
				index = nums2[j];
				if (index < result) {
					min2 = Math.min(min2, index);
					sub2[index] = 1;
					if (1 == sub1[index]) {
						result = index;
					}
				}
			}
		}

		if (result < 10) {
			return result;
		}

		if (min1 > min2) {
			return min2 * 10 + min1;
		} else {
			return min1 * 10 + min2;
		}
	}

	/*
	 * 时间: 1ms 击败 65.22%使用 Java 的用户
	 * 内存: 38.46MB 击败 55.22%使用 Java 的用户
	 */
	public int minNumber(int[] nums1, int[] nums2) {
		int result = -1;
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int a = nums1[0];
		int b = nums2[0];
		if (a == b) {
			return a;
		} else if (a > b) {
			result = b * 10 + a;
		} else {
			result = a * 10 + b;
		}
		int length1 = nums1.length, length2 = nums2.length;
		for (int i = 0; i < length1; i++) {
			a = nums1[i];
			for (int j = 0; j < length2; j++) {
				b = nums2[j];
				if (a == b) {
					return a;
				}
				if (b > a) {
					break;
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {
		// 15
		int[] nums1 = { 4, 1, 3 }, nums2 = { 5, 7 };
		// 3
		int[] nums3 = { 3, 5, 2, 6 }, nums4 = { 3, 1, 7 };

		Q2605_MinNumber solution = new Q2605_MinNumber();
		long start;
		int result;

		System.out.println("nums1:" + Arrays.toString(nums1) + ", nums2:" + Arrays.toString(nums2));
		start = System.currentTimeMillis();
		result = solution.minNumber2(nums1, nums2);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", [15] - Result:" + result + "\n");

		System.out.println("nums3:" + Arrays.toString(nums3) + ", nums4:" + Arrays.toString(nums4));
		start = System.currentTimeMillis();
		result = solution.minNumber2(nums3, nums4);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", [3] - Result:" + result + "\n");

	}

}
