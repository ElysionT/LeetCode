package org.demo.easy;

/**
 * 1281. 整数的各位积和之差
 * https://leetcode.cn/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
 * 给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。
 * 
 * 示例 1：
 * 输入：n = 234
 * 输出：15
 * 解释：
 * 各位数之积 = 2 * 3 * 4 = 24
 * 各位数之和 = 2 + 3 + 4 = 9
 * 结果 = 24 - 9 = 15
 * 
 * 示例 2：
 * 输入：n = 4421
 * 输出：21
 * 解释：
 * 各位数之积 = 4 * 4 * 2 * 1 = 32
 * 各位数之和 = 4 + 4 + 2 + 1 = 11
 * 结果 = 32 - 11 = 21
 * 
 * 提示：
 * 1 <= n <= 10^5
 */
public class Q1281_SubtractProductAndSum {
	private static final boolean DEBUG = true;

	/*
	 * 时间: 0ms 击败 100%使用 Java 的用户
	 * 内存: 37.22mb 击败 60.21%使用 Java 的用户
	 */
	public int subtractProductAndSum(int n) {
		long sum = 0, product = 1;
		while (n > 0) {
			int remainder = n % 10;
			sum += remainder;
			product *= remainder;
			n = n / 10;
		}
		if (DEBUG)
			System.out.println("product:" + product + ", sum:" + sum);
		return (int) (product - sum);
	}

	public static void main(String[] args) {
		// 15
		int n1 = 234;
		// 21
		int n2 = 4421;

		Q1281_SubtractProductAndSum solution = new Q1281_SubtractProductAndSum();

		System.out.println("n1:" + n1);
		long start = System.currentTimeMillis();
		int result = solution.subtractProductAndSum(n1);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {15} - Result:" + result + "\n");

		System.out.println("n2:" + n2);
		start = System.currentTimeMillis();
		result = solution.subtractProductAndSum(n2);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {21} - Result:" + result + "\n");

	}
}