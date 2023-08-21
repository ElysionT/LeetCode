package org.demo.easy;

import java.util.Arrays;

/**
 * 2511. 最多可以摧毁的敌人城堡数目
 * https://leetcode.cn/problems/maximum-enemy-forts-that-can-be-captured/
 * 
 * 给你一个长度为 n ，下标从 0 开始的整数数组 forts ，表示一些城堡。forts[i] 可以是 -1 ，0 或者 1 ，其中：
 * -1 表示第 i 个位置 没有 城堡。
 * 0 表示第 i 个位置有一个 敌人 的城堡。
 * 1 表示第 i 个位置有一个你控制的城堡。
 * 
 * 现在，你需要决定，将你的军队从某个你控制的城堡位置 i 移动到一个空的位置 j ，满足：
 * 0 <= i, j <= n - 1
 * 军队经过的位置 只有 敌人的城堡。正式的，对于所有 min(i,j) < k < max(i,j) 的 k ，都满足 forts[k] == 0 。
 * 当军队移动时，所有途中经过的敌人城堡都会被 摧毁 。
 * 请你返回 最多 可以摧毁的敌人城堡数目。如果 无法 移动你的军队，或者没有你控制的城堡，请返回 0 。
 * 
 * 示例 1：
 * 输入：forts = [1,0,0,-1,0,0,0,0,1]
 * 输出：4
 * 解释：
 * - 将军队从位置 0 移动到位置 3 ，摧毁 2 个敌人城堡，位置分别在 1 和 2 。
 * - 将军队从位置 8 移动到位置 3 ，摧毁 4 个敌人城堡。
 * 4 是最多可以摧毁的敌人城堡数目，所以我们返回 4 。
 * 
 * 示例 2：
 * 输入：forts = [0,0,1,-1]
 * 输出：0
 * 解释：由于无法摧毁敌人的城堡，所以返回 0 。
 * 
 * 提示：
 * 1 <= forts.length <= 1000
 * -1 <= forts[i] <= 1
 */
public class Q2511_CaptureForts {
	/*
	 * 时间: 0ms 击败 100.00%使用 Java 的用户
	 * 内存: 38.39MB 击败 30.99%使用 Java 的用户
	 */
	public int captureForts(int[] forts) {
		int result = 0;
		int count = 0;
		boolean isEmpty = false;
		boolean found = false;
		for (int i = 0, length = forts.length; i < length; i++) {
			int fort = forts[i];
			if (-1 == fort) {
				if (!found) {
					found = true;
					isEmpty = true;
					count = 0;
				} else {
					if (isEmpty) {
						count = 0;
					} else {
						result = Math.max(result, count);
						found = true;
						isEmpty = true;
						count = 0;
					}
				}
			} else if (1 == fort) {
				if (!found) {
					found = true;
					isEmpty = false;
					count = 0;
				} else {
					if (!isEmpty) {
						count = 0;
					} else {
						result = Math.max(result, count);
						found = true;
						isEmpty = false;
						count = 0;
					}

				}
			} else {
				if (found) {
					count++;
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// 4
		int[] forts1 = { 1, 0, 0, -1, 0, 0, 0, 0, 1 };
		// 0
		int[] forts2 = { 0, 0, 1, -1 };
		// 0
		int[] forts3 = { -1, -1, 1, -1, -1, 0 };

		Q2511_CaptureForts solution = new Q2511_CaptureForts();
		long start;
		int result;

		System.out.println("forts1:" + Arrays.toString(forts1));
		start = System.currentTimeMillis();
		result = solution.captureForts(forts1);
		System.out.print("Time:" + (System.currentTimeMillis() - start) + ", [4] - Result:" + result + "\n");

		System.out.println("forts2:" + Arrays.toString(forts2));
		start = System.currentTimeMillis();
		result = solution.captureForts(forts2);
		System.out.print("Time:" + (System.currentTimeMillis() - start) + ", [0] - Result:" + result + "\n");

		System.out.println("forts3:" + Arrays.toString(forts3));
		start = System.currentTimeMillis();
		result = solution.captureForts(forts3);
		System.out.print("Time:" + (System.currentTimeMillis() - start) + ", [0] - Result:" + result + "\n");
	}
}
