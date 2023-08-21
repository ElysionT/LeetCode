package org.demo.medium;

import java.util.Arrays;

/**
 * 2087. 网格图中机器人回家的最小代价
 * https://leetcode.cn/problems/minimum-cost-homecoming-of-a-robot-in-a-grid/
 * 
 * 给你一个 m x n 的网格图，其中 (0, 0) 是最左上角的格子，(m - 1, n - 1) 是最右下角的格子。给你一个整数数组 startPos ，startPos = [startrow, startcol] 表示 初始 有一个 机器人 在格子 (startrow, startcol) 处。同时给你一个整数数组 homePos ，homePos = [homerow, homecol] 表示机器人的 家 在格子 (homerow, homecol) 处。
 * 
 * 机器人需要回家。每一步它可以往四个方向移动：上，下，左，右，同时机器人不能移出边界。每一步移动都有一定代价。再给你两个下标从 0 开始的额整数数组：长度为 m 的数组 rowCosts  和长度为 n 的数组 colCosts 。
 * 如果机器人往 上 或者往 下 移动到第 r 行 的格子，那么代价为 rowCosts[r] 。
 * 如果机器人往 左 或者往 右 移动到第 c 列 的格子，那么代价为 colCosts[c] 。
 * 请你返回机器人回家需要的 最小总代价 。
 * 
 *  
 *  示例 1：
 *  输入：startPos = [1, 0], homePos = [2, 3], rowCosts = [5, 4, 3], colCosts = [8, 2, 6, 7]
 *  输出：18
 *  解释：一个最优路径为：
 *  从 (1, 0) 开始
 *  -> 往下走到 (2, 0) 。代价为 rowCosts[2] = 3 。
 *  -> 往右走到 (2, 1) 。代价为 colCosts[1] = 2 。
 *  -> 往右走到 (2, 2) 。代价为 colCosts[2] = 6 。
 *  -> 往右走到 (2, 3) 。代价为 colCosts[3] = 7 。
 *  总代价为 3 + 2 + 6 + 7 = 18
 *  
 *  示例 2：
 *  输入：startPos = [0, 0], homePos = [0, 0], rowCosts = [5], colCosts = [26]
 *  输出：0
 *  解释：机器人已经在家了，所以不需要移动。总代价为 0 。
 *  
 *  提示：
 *  m == rowCosts.length
 *  n == colCosts.length
 *  1 <= m, n <= 105
 *  0 <= rowCosts[r], colCosts[c] <= 104
 *  startPos.length == 2
 *  homePos.length == 2
 *  0 <= startrow, homerow < m
 *  0 <= startcol, homecol < n
 */
public class Q2087_MinimumCostHomecomingRobot {
	/*
	 * 时间: 1ms 击败 100.00%使用 Java 的用户 
	 * 内存: 56.49MB 击败 32.95%使用 Java 的用户
	 */
	public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
		int result = 0;

		int m = rowCosts.length;
		int n = colCosts.length;

		int startX = startPos[0];
		int homeX = homePos[0];
		int start = 0, end = 0;
		if (startX == homeX) {

		} else if (startX > homeX) {
			start = homeX;
			end = startX - 1;
			for (; start <= end; start++) {
				result += rowCosts[start];
			}
		} else {
			start = startX + 1;
			end = homeX;
			for (; start <= end; start++) {
				result += rowCosts[start];
			}
		}

		int startY = startPos[1];
		int homeY = homePos[1];
		start = 0;
		end = 0;
		if (startY == homeY) {

		} else if (startY > homeY) {
			start = homeY;
			end = startY - 1;
			for (; start <= end; start++) {
				result += colCosts[start];
			}
		} else {
			start = startY + 1;
			end = homeY;
			for (; start <= end; start++) {
				result += colCosts[start];
			}
		}

		return result;
	}

	public static void main(String[] args) {
		// 18
		int[] startPos1 = { 1, 0 }, homePos1 = { 2, 3 }, rowCosts1 = { 5, 4, 3 }, colCosts1 = { 8, 2, 6, 7 };
		// 0
		int[] startPos2 = { 0, 0 }, homePos2 = { 0, 0 }, rowCosts2 = { 5 }, colCosts2 = { 26 };

		Q2087_MinimumCostHomecomingRobot solution = new Q2087_MinimumCostHomecomingRobot();
		long start;
		int result;

		System.out.println("startPos1:" + Arrays.toString(startPos1) + ", homePos1:" + Arrays.toString(homePos1)
				+ ", rowCosts1:" + Arrays.toString(rowCosts1) + ", colCosts1:" + Arrays.toString(colCosts1));
		start = System.currentTimeMillis();
		result = solution.minCost(startPos1, homePos1, rowCosts1, colCosts1);
		System.out.print("Time:" + (System.currentTimeMillis() - start) + ", [18] - Result:" + result + "\n");

		System.out.println("startPos2:" + Arrays.toString(startPos2) + ", homePos2:" + Arrays.toString(homePos2)
				+ ", rowCosts2:" + Arrays.toString(rowCosts2) + ", colCosts2:" + Arrays.toString(colCosts2));
		start = System.currentTimeMillis();
		result = solution.minCost(startPos2, homePos2, rowCosts2, colCosts2);
		System.out.print("Time:" + (System.currentTimeMillis() - start) + ", [0] - Result:" + result + "\n");

	}

}
