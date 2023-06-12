package org.demo.medium;

import java.io.IOException;
import java.util.Arrays;

import org.demo.QUtils;

/**
 * 2352. 相等行列对
 * https://leetcode.cn/problems/equal-row-and-column-pairs/
 * 
 * 给你一个下标从 0 开始、大小为 n x n 的整数矩阵 grid ，返回满足 Ri 行和 Cj 列相等的行列对 (Ri, Cj) 的数目。
 * 如果行和列以相同的顺序包含相同的元素（即相等的数组），则认为二者是相等的。
 * 
 * 示例 1：
 * 输入：grid = [[3,2,1],[1,7,6],[2,7,7]]
 * 输出：1
 * 解释：存在一对相等行列对：
 * - (第 2 行，第 1 列)：[2,7,7]
 * 
 * 示例 2：
 * 输入：grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
 * 输出：3
 * 解释：存在三对相等行列对：
 * - (第 0 行，第 0 列)：[3,1,2,2]
 * - (第 2 行, 第 2 列)：[2,4,2,2]
 * - (第 3 行, 第 2 列)：[2,4,2,2]
 * 
 * 提示：
 * n == grid.length == grid[i].length
 * 1 <= n <= 200
 * 1 <= grid[i][j] <= 105
 */
public class EqualRowAndColumnPairs {
	private static final boolean DEBUG = false;

	public int equalPairs(int[][] grid) {
		int result = 0;
		int length = grid.length;
		if (DEBUG) {
			System.out.println("length:" + length);
		}
		int[] row;
		for (int i = 0; i < length; i++) { // row
			row = grid[i];
			for (int j = 0; j < length; j++) { // column
				boolean isMatch = true;
				for (int k = 0; k < length; k++) {
					if (row[k] != grid[k][j]) {
						isMatch = false;
						break;
					}
				}
				if (isMatch) {
					if (DEBUG) {
						System.out.println("row[" + i + "]:" + Arrays.toString(row));
						System.out.print("column[" + j + "]:[");
						for (int k = 0; k < length; k++) {
							System.out.print(grid[k][j]);
							if (k != length - 1) {
								System.out.print(" ,");
							}
						}
						System.out.println("]");
					}
					result++;
				}
			}
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		// 1
		// int[][] grid = { { 3, 2, 1 }, { 1, 7, 6 }, { 2, 7, 7 } };
		// 3
		// int[][] grid = { { 3, 1, 2, 2 }, { 1, 4, 4, 5 }, { 2, 4, 2, 2 }, { 2, 4, 2, 2 } };

		final String[] stringArray = QUtils.getStringArrayFromFile("EqualRowAndColumnPairs", "\\],\\[");
		int n = stringArray.length;
		int[][] grid = new int[n][n];
		String[] array;
		for (int i = 0; i < n; i++) {
			array = stringArray[i].split(",");
			for (int j = 0; j < n; j++) {
				grid[i][j] = Integer.valueOf(array[j]);
			}
		}

		System.out.println("grid:" + Arrays.deepToString(grid));
		EqualRowAndColumnPairs solution = new EqualRowAndColumnPairs();
		long start = System.currentTimeMillis();
		int result = solution.equalPairs(grid);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", Result:" + result);
	}
}
