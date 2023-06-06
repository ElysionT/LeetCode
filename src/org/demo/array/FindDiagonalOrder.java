package org.demo.array;

import java.io.IOException;
import java.util.Arrays;

import org.demo.QUtils;

/**
 * 对角线遍历
 * https://leetcode.cn/leetbook/read/array-and-string/cuxq3/
 * 
 * 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
 * 
 * 示例 1：
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,4,7,5,3,6,8,9]
 * 
 * 示例 2：
 * 输入：mat = [[1,2],[3,4]]
 * 输出：[1,2,3,4]
 * 
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * -105 <= mat[i][j] <= 105
 */
public class FindDiagonalOrder {
	public int[] findDiagonalOrder(int[][] mat) {
		int m = mat.length;
		int n = mat[0].length;
		int[] result = new int[m * n];
		int limitM = m - 1, limitN = n - 1;

		int i = 0, j = 0;

		int index = 0;
		boolean isBottomUp = true;

		while (i != limitM || j != limitN) {
//			System.out.println("i:" + i + ", j:" + j);
			// if (i <= limitM && j <= limitN) {
			result[index++] = mat[i][j];
			// }
			if (isBottomUp) {
				if (limitN == j) {
					i++;
					isBottomUp = false;
				} else {
					if (0 == i) {
						j++;
						isBottomUp = false;
					} else {
						i--;
						j++;
					}
				}
			} else {
				if (limitM == i) {
					j++;
					isBottomUp = true;
				} else {
					if (0 == j) {
						i++;
						isBottomUp = true;
					} else {
						i++;
						j--;
					}
				}
			}
		}
		result[index] = mat[limitM][limitN];

		return result;
	}

	public static void main(String[] args) throws IOException {
		// [1,2,4,7,5,3,6,8,9]
		// int[][] mat = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		// [1,2,3,4]
		// int[][] mat = { { 1, 2 }, { 3, 4 } };
		// [1,2,6,11,7,3,4,8,12,16,17,13,9,5,10,14,18,19,15,20]
//		int[][] mat = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 20 } };

		final String[] stringArray = QUtils.getStringArrayFromFile("FindDiagonalOrder", "\\],\\[");
		int m = stringArray.length;
		String[] temp = stringArray[0].split(",");
		int n = temp.length;
		int[][] mat = new int[m][n];
		for (int i = 0; i < m; i++) {
			temp = stringArray[i].split(",");
			for (int j = 0; j < n; j++) {
				mat[i][j] = Integer.valueOf(temp[j]);
			}
		}

		System.out.println("mat:" + Arrays.deepToString(mat));
		FindDiagonalOrder solution = new FindDiagonalOrder();
		long start = System.currentTimeMillis();
		int[] result = solution.findDiagonalOrder(mat);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", Result:" + Arrays.toString(result));
	}

}
