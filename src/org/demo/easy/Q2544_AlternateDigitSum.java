package org.demo.easy;

/**
 * 2544. 交替数字和
 * https://leetcode.cn/problems/alternating-digit-sum/
 * 
 * 给你一个正整数 n 。n 中的每一位数字都会按下述规则分配一个符号：
 * 最高有效位 上的数字分配到 正 号。
 * 剩余每位上数字的符号都与其相邻数字相反。
 * 返回所有数字及其对应符号的和。
 * 
 * 示例 1：
 * 输入：n = 521
 * 输出：4
 * 解释：(+5) + (-2) + (+1) = 4
 * 
 * 示例 2：
 * 输入：n = 111
 * 输出：1
 * 解释：(+1) + (-1) + (+1) = 1
 * 
 * 示例 3：
 * 输入：n = 886996
 * 输出：0
 * 解释：(+8) + (-8) + (+6) + (-9) + (+9) + (-6) = 0
 * 
 * 提示：
 * 1 <= n <= 109
 */
public class Q2544_AlternateDigitSum {
	/*
	 * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
	 * 内存消耗：38.2 MB, 在所有 Java 提交中击败了74.25%的用户
	 * 通过测试用例：118 / 118
	 */
	public int alternateDigitSum(int n) {
		String ns = String.valueOf(n);
		boolean isPositive = true;
		int sum = 0;
		for (int i = 0, length = ns.length(); i < length; i++) {
			sum = isPositive ? (sum + (ns.charAt(i) - 48)) : (sum - (ns.charAt(i) - 48));
			isPositive = !isPositive;
		}
		return sum;
	}

	public static void main(String[] args) {
		// 4
		int n1 = 521;
		// 1
		int n2 = 111;
		// 0
		int n3 = 886996;

		long start;
		int result;
		Q2544_AlternateDigitSum solution = new Q2544_AlternateDigitSum();

		System.out.println("n1:" + n1);
		start = System.currentTimeMillis();
		result = solution.alternateDigitSum(n1);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {4} - Result:" + result + "\n");

		System.out.println("n2:" + n2);
		start = System.currentTimeMillis();
		result = solution.alternateDigitSum(n2);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {1} - Result:" + result + "\n");

		System.out.println("n3" + n3);
		start = System.currentTimeMillis();
		result = solution.alternateDigitSum(n3);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {0} - Result:" + result + "\n");
	}

}
