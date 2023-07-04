package org.demo.medium;

import java.util.Arrays;

/**
 * 2679. 矩阵中的和
 * https://leetcode.cn/problems/sum-in-a-matrix/
 * 
 * 给你一个下标从 0 开始的二维整数数组 nums 。一开始你的分数为 0 。你需要执行以下操作直到矩阵变为空：
 * 矩阵中每一行选取最大的一个数，并删除它。如果一行中有多个最大的数，选择任意一个并删除。
 * 在步骤 1 删除的所有数字中找到最大的一个数字，将它添加到你的 分数 中。
 * 请你返回最后的 分数 。
 * 
 * 示例 1：
 * 输入：nums = [[7,2,1],[6,4,2],[6,5,3],[3,2,1]]
 * 输出：15
 * 解释：第一步操作中，我们删除 7 ，6 ，6 和 3 ，将分数增加 7 。下一步操作中，删除 2 ，4 ，5 和 2 ，将分数增加 5 。最后删除 1 ，2 ，3 和 1 ，将分数增加 3 。
 * 所以总得分为 7 + 5 + 3 = 15 。
 * 
 * 示例 2：
 * 输入：nums = [[1]]
 * 输出：1
 * 解释：我们删除 1 并将分数增加 1 ，所以返回 1 。
 * 
 * 提示：
 * 1 <= nums.length <= 300
 * 1 <= nums[i].length <= 500
 * 0 <= nums[i][j] <= 103
 */
public class Q2679_MatrixSum {

	/*
	 * 执行用时：13 ms, 在所有 Java 提交中击败了96.93%的用户
	 * 内存消耗：55.4 MB, 在所有 Java 提交中击败了50.62%的用户
	 * 通过测试用例：1057 / 1057
	 */
	public int matrixSum(int[][] nums) {
		int m = nums.length;
		for (int i = 0; i < m; i++) {
			Arrays.sort(nums[i]);
		}

		int n = nums[0].length;
		int sum = 0;
		for (int i = n - 1; i >= 0; i--) {
			int temp = 0;
			for (int j = 0; j < m; j++) {
				temp = Math.max(temp, nums[j][i]);
			}
			sum += temp;
		}
		return sum;
	}

	public static void main(String[] args) {
		// 15
		int[][] nums1 = { { 7, 2, 1 }, { 6, 4, 2 }, { 6, 5, 3 }, { 3, 2, 1 } };
		// 1
		int[][] nums2 = { { 1 } };

		long start;
		int result;
		Q2679_MatrixSum solution = new Q2679_MatrixSum();

		System.out.println("nums1:" + Arrays.deepToString(nums1));
		start = System.currentTimeMillis();
		result = solution.matrixSum(nums1);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {15} - Result:" + result + "\n");

		System.out.println("nums1:" + Arrays.deepToString(nums2));
		start = System.currentTimeMillis();
		result = solution.matrixSum(nums2);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {1} - Result:" + result + "\n");
	}
}
