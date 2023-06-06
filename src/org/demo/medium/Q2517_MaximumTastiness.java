package org.demo.medium;

import java.util.Arrays;

/**
 * 2517. 礼盒的最大甜蜜度
 * https://leetcode.cn/problems/maximum-tastiness-of-candy-basket/
 * 
 * 给你一个正整数数组 price ，其中 price[i] 表示第 i 类糖果的价格，另给你一个正整数 k 。
 * 商店组合 k 类 不同 糖果打包成礼盒出售。礼盒的 甜蜜度 是礼盒中任意两种糖果 价格 绝对差的最小值。
 * 返回礼盒的 最大 甜蜜度。
 * 
 * 示例 1：
 * 输入：price = [13,5,1,8,21,2], k = 3
 * 输出：8
 * 解释：选出价格分别为 [13,5,21] 的三类糖果。
 * 礼盒的甜蜜度为 min(|13 - 5|, |13 - 21|, |5 - 21|) = min(8, 8, 16) = 8 。
 * 可以证明能够取得的最大甜蜜度就是 8 。
 * 
 * 示例 2：
 * 输入：price = [1,3,1], k = 2
 * 输出：2
 * 解释：选出价格分别为 [1,3] 的两类糖果。
 * 礼盒的甜蜜度为 min(|1 - 3|) = min(2) = 2 。
 * 可以证明能够取得的最大甜蜜度就是 2 。
 * 
 * 示例 3：
 * 输入：price = [7,7,7,7], k = 2
 * 输出：0
 * 解释：从现有的糖果中任选两类糖果，甜蜜度都会是 0 。
 * 
 * 提示：
 * 1 <= price.length <= 105
 * 1 <= price[i] <= 109
 * 2 <= k <= price.length
 */
public class Q2517_MaximumTastiness {
	private static final boolean DEBUG = true;

	public int maximumTastiness(int[] price, int k) {
		int length = price.length;
		int maximum = 0;
		int[] subPrice = new int[k];
		for (int i = 0, limit1 = length - k; i <= limit1; i++) {
			subPrice[0] = price[i];
			for (int j = i + 1, limit2 = length - (k - 1); j <= limit2; j++) {
				for (int m = 0; m < k - 1; m++) {
					subPrice[m + 1] = price[j + m];
				}

				maximum = Math.max(maximum, maximumTastinessInternal(subPrice));
			}
		}
		return maximum;
	}

	private int maximumTastinessInternal(int... subPrice) {
		if (DEBUG)
			System.out.println("subPrice:" + Arrays.toString(subPrice));
		int length = subPrice.length;
		int minimum = -1;
		for (int i = 0, limit = length - 1; i < limit; i++) {
			for (int j = i + 1; j < length; j++) {
				int iv = subPrice[i];
				int jv = subPrice[j];
				int temp;
				if (iv >= jv) {
					temp = iv - jv;
				} else {
					temp = jv - iv;
				}
				if (DEBUG)
					System.out.print("|" + iv + "-" + jv + "|=" + temp + ", ");
				if (-1 == minimum) {
					minimum = temp;
				} else {
					minimum = Math.min(minimum, temp);
				}
			}
		}
		if (DEBUG)
			System.out.println(", minimum:" + minimum);
		return minimum;
	}

	public static void main(String[] args) {
		// 8
		// int[] price = { 13, 5, 1, 8, 21, 2 };
		// int k = 3;
		// 2
		// int[] price = { 1, 3, 1 };
		// int k = 2;
		// 0
		// int[] price = { 7, 7, 7, 7 };
		// int k = 2;
		// 55
		int[] price = { 144, 69, 103, 148, 184, 50, 129, 154, 2 };
		int k = 4;

		System.out.println("price:" + Arrays.toString(price) + ", k:" + k);
		Q2517_MaximumTastiness solution = new Q2517_MaximumTastiness();
		long start = System.currentTimeMillis();
		int result = solution.maximumTastiness(price, k);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", Result:" + result);
	}

}
