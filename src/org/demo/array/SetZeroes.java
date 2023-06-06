package org.demo.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 零矩阵
 * https://leetcode.cn/leetbook/read/array-and-string/ciekh/
 * 
 * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 * 
 * 示例 1：
 * 输入：
 * [
 * [1,1,1],
 * [1,0,1],
 * [1,1,1]
 * ]
 * 输出：
 * [
 * [1,0,1],
 * [0,0,0],
 * [1,0,1]
 * ]
 * 
 * 示例 2：
 * 输入：
 * [
 * [0,1,2,0],
 * [3,4,5,2],
 * [1,3,1,5]
 * ]
 * 输出：
 * [
 * [0,0,0,0],
 * [0,4,5,0],
 * [0,3,1,0]
 * ]
 */
public class SetZeroes {
	public void setZeroes(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;

		Set<Integer> rows = new HashSet();
		Set<Integer> columns = new HashSet();
		for (int row = 0; row < m; row++) {
			for (int column = 0; column < n; column++) {
				if (0 == matrix[row][column]) {
					rows.add(row);
					columns.add(column);
				}
			}
		}
		for (int row : rows) {
			System.out.println("row:" + row);
			for (int j = 0; j < n; j++) {
				matrix[row][j] = 0;
			}
		}

		for (int column : columns) {
			System.out.println("column:" + column);
			for (int i = 0; i < m; i++) {
				matrix[i][column] = 0;
			}
		}
	}

	public static void main(String[] args) {
		// [[1,0,1],[0,0,0],[1,0,1]]
		// int[][] matrix = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		// [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
		int[][] matrix = { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } };

		System.out.println("matrix:" + Arrays.deepToString(matrix));
		SetZeroes solution = new SetZeroes();
		long start = System.currentTimeMillis();
		solution.setZeroes(matrix);
		System.out.println("Result:" + Arrays.deepToString(matrix));

	}

}
