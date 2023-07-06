package org.demo.easy;

/**
 * 2600. K 件物品的最大和
 * https://leetcode.cn/problems/k-items-with-the-maximum-sum/
 * 
 * 袋子中装有一些物品，每个物品上都标记着数字 1 、0 或 -1 。
 * 给你四个非负整数 numOnes 、numZeros 、numNegOnes 和 k 。
 * 袋子最初包含：
 * numOnes 件标记为 1 的物品。
 * numZeroes 件标记为 0 的物品。
 * numNegOnes 件标记为 -1 的物品。
 * 现计划从这些物品中恰好选出 k 件物品。返回所有可行方案中，物品上所标记数字之和的最大值。
 * 
 * 示例 1：
 * 输入：numOnes = 3, numZeros = 2, numNegOnes = 0, k = 2
 * 输出：2
 * 解释：袋子中的物品分别标记为 {1, 1, 1, 0, 0} 。取 2 件标记为 1 的物品，得到的数字之和为 2 。
 * 可以证明 2 是所有可行方案中的最大值。
 * 
 * 示例 2：
 * 输入：numOnes = 3, numZeros = 2, numNegOnes = 0, k = 4
 * 输出：3
 * 解释：袋子中的物品分别标记为 {1, 1, 1, 0, 0} 。取 3 件标记为 1 的物品，1 件标记为 0 的物品，得到的数字之和为 3 。
 * 可以证明 3 是所有可行方案中的最大值。
 * 
 * 提示：
 * 0 <= numOnes, numZeros, numNegOnes <= 50
 * 0 <= k <= numOnes + numZeros + numNegOnes
 */
public class Q2600_KItemsWithMaximumSum {

	/*
	 * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
	 * 内存消耗：38.9 MB, 在所有 Java 提交中击败了75.24%的用户
	 * 通过测试用例：1310 / 1310
	 */
	public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
		if (numOnes >= k) {
			return k;
		}

		int temp = k - numOnes;
		if (numZeros >= temp) {
			return numOnes;
		}
		temp = temp - numZeros;
		return numOnes - temp;
	}

	public static void main(String[] args) {
		// 2
		int numOnes1 = 3, numZeros1 = 2, numNegOnes1 = 0, k1 = 2;
		// 3
		int numOnes2 = 3, numZeros2 = 2, numNegOnes2 = 0, k2 = 4;
		// 5
		int numOnes3 = 6, numZeros3 = 6, numNegOnes3 = 6, k3 = 13;

		long start;
		int result;
		Q2600_KItemsWithMaximumSum solution = new Q2600_KItemsWithMaximumSum();

		System.out.println("numOnes1:" + numOnes1 + ", numZeros1:" + numZeros1 + ", numNegOnes1:" + numNegOnes1);
		start = System.currentTimeMillis();
		result = solution.kItemsWithMaximumSum(numOnes1, numZeros1, numNegOnes1, k1);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {2} - Result:" + result + "\n");

		System.out.println("numOnes2:" + numOnes2 + ", numZeros2:" + numZeros2 + ", numNegOnes2:" + numNegOnes2);
		start = System.currentTimeMillis();
		result = solution.kItemsWithMaximumSum(numOnes2, numZeros2, numNegOnes2, k2);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {3} - Result:" + result + "\n");

		System.out.println("numOnes3:" + numOnes3 + ", numZeros3:" + numZeros3 + ", numNegOnes3:" + numNegOnes3);
		start = System.currentTimeMillis();
		result = solution.kItemsWithMaximumSum(numOnes3, numZeros3, numNegOnes3, k3);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {5} - Result:" + result + "\n");
	}
}
