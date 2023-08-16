package org.demo.easy;

import java.util.Arrays;

/**
 * 2682. 找出转圈游戏输家
 * https://leetcode.cn/problems/find-the-losers-of-the-circular-game/
 * 
 * n 个朋友在玩游戏。这些朋友坐成一个圈，按 顺时针方向 从 1 到 n 编号。从第 i 个朋友的位置开始顺时针移动 1 步会到达第 (i + 1) 个朋友的位置（1 <= i < n），而从第 n 个朋友的位置开始顺时针移动 1
 * 步会回到第 1 个朋友的位置。
 * 
 * 游戏规则如下：
 * 第 1 个朋友接球。
 * 接着，第 1 个朋友将球传给距离他顺时针方向 k 步的朋友。
 * 然后，接球的朋友应该把球传给距离他顺时针方向 2 * k 步的朋友。
 * 接着，接球的朋友应该把球传给距离他顺时针方向 3 * k 步的朋友，以此类推。
 * 换句话说，在第 i 轮中持有球的那位朋友需要将球传递给距离他顺时针方向 i * k 步的朋友。
 * 当某个朋友第 2 次接到球时，游戏结束。
 * 在整场游戏中没有接到过球的朋友是 输家 。
 * 给你参与游戏的朋友数量 n 和一个整数 k ，请按升序排列返回包含所有输家编号的数组 answer 作为答案。
 * 
 * 示例 1：
 * 输入：n = 5, k = 2
 * 输出：[4,5]
 * 解释：以下为游戏进行情况：
 * 1）第 1 个朋友接球，第 1 个朋友将球传给距离他顺时针方向 2 步的玩家 —— 第 3 个朋友。
 * 2）第 3 个朋友将球传给距离他顺时针方向 4 步的玩家 —— 第 2 个朋友。
 * 3）第 2 个朋友将球传给距离他顺时针方向 6 步的玩家 —— 第 3 个朋友。
 * 4）第 3 个朋友接到两次球，游戏结束。
 * 
 * 示例 2：
 * 输入：n = 4, k = 4
 * 输出：[2,3,4]
 * 解释：以下为游戏进行情况：
 * 1）第 1 个朋友接球，第 1 个朋友将球传给距离他顺时针方向 4 步的玩家 —— 第 1 个朋友。
 * 2）第 1 个朋友接到两次球，游戏结束。
 * 
 * 提示：
 * 1 <= k <= n <= 50
 */
public class Q2682_CircularGameLosers {

	/*
	 * 时间: 1ms 击败 100.00%使用 Java 的用户
	 * 内存: 41.40mb 击败 94.66%使用 Java 的用户
	 */
	public int[] circularGameLosers(int n, int k) {
		int[] targets = new int[n];
		int rounds = 0;
		int index = 1;
		do {
			if (1 == targets[index - 1]) {
				break;
			}
			targets[index - 1] = 1;

			rounds++;
			// TODO:
			index = index + rounds * k;
			if (index > n) {
				index %= n;
			}
			if (0 == index) {
				index = n;
			}
		} while (true);
		int[] result = new int[n - rounds];
		for (int i = 0, j = 0; i < n; i++) {
			if (0 == targets[i]) {
				result[j] = i + 1;
				j++;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		// [4,5]
		int n1 = 5, k1 = 2;
		// [2,3,4]
		int n2 = 4, k2 = 4;
		// []
		int n3 = 1, k3 = 1;

		int n4 = 2, k4 = 1;

		Q2682_CircularGameLosers solution = new Q2682_CircularGameLosers();
		long start;
		int[] result;

		System.out.println("n1:" + n1 + ", k1:" + k1);
		start = System.currentTimeMillis();
		result = solution.circularGameLosers(n1, k1);
		System.out.println(
				"Time:" + (System.currentTimeMillis() - start) + ", [4,5] - Result:" + Arrays.toString(result) + "\n");

		System.out.println("n2:" + n2 + ", k2:" + k2);
		start = System.currentTimeMillis();
		result = solution.circularGameLosers(n2, k2);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", [2,3,4] - Result:"
				+ Arrays.toString(result) + "\n");

		System.out.println("n3:" + n3 + ", k3:" + k3);
		start = System.currentTimeMillis();
		result = solution.circularGameLosers(n3, k3);
		System.out.println(
				"Time:" + (System.currentTimeMillis() - start) + ", [] - Result:" + Arrays.toString(result) + "\n");

		System.out.println("n4:" + n4 + ", k4:" + k4);
		start = System.currentTimeMillis();
		result = solution.circularGameLosers(n4, k4);
		System.out.println(
				"Time:" + (System.currentTimeMillis() - start) + ", [] - Result:" + Arrays.toString(result) + "\n");
	}

}
