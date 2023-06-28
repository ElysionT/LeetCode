package org.demo.easy;

import java.util.Arrays;

/**
 * 2485. 找出中枢整数
 * https://leetcode.cn/problems/find-the-pivot-integer/
 * 
 * 给你一个正整数 n ，找出满足下述条件的 中枢整数 x ：
 * 1 和 x 之间的所有元素之和等于 x 和 n 之间所有元素之和。
 * 返回中枢整数 x 。如果不存在中枢整数，则返回 -1 。题目保证对于给定的输入，至多存在一个中枢整数。
 * 
 * 示例 1：
 * 输入：n = 8
 * 输出：6
 * 解释：6 是中枢整数，因为 1 + 2 + 3 + 4 + 5 + 6 = 6 + 7 + 8 = 21 。
 * 
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * 解释：1 是中枢整数，因为 1 = 1 。
 * 
 * 示例 3：
 * 输入：n = 4
 * 输出：-1
 * 解释：可以证明不存在满足题目要求的整数。
 * 
 * 提示：
 * 1 <= n <= 1000
 */
public class Q2485_PivotInteger {

	/*
	 * 1.
	 * 1+2+3+...+x = x+x+1+x+2+...+n
	 * 
	 * 2.
	 * x*(x+1)/2 = n*(n+1)/2-(x-1)*(x-1+1)/2
	 * 
	 * 3.
	 * x = Math.sqrt(n*(n+1)/2)
	 */
	public int pivotInteger(int n) {
		double x = Math.sqrt(n * (n + 1) / 2);
		int x_ = (int) x;
		return x - x_ > 0 ? -1 : x_;
	}

	public static void main(String[] args) {
		// 6
		int n1 = 8;
		// 1
		int n2 = 1;
		// -1
		int n3 = 4;

		Q2485_PivotInteger solution = new Q2485_PivotInteger();
		System.out.println("n1:" + n1);
		long start = System.currentTimeMillis();
		int result = solution.pivotInteger(n1);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {6} - Result:" + result);
		
		System.out.println("n2:" + n2);
		start = System.currentTimeMillis();
		result = solution.pivotInteger(n2);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {1} - Result:" + result);
		
		System.out.println("n3:" + n3);
		start = System.currentTimeMillis();
		result = solution.pivotInteger(n3);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {-1} - Result:" + result);

	}

}
