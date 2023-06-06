package org.demo.array;

import java.util.Arrays;

/**
 * 48. 旋转图像
 * https://leetcode.cn/problems/rotate-image/
 * https://leetcode.cn/leetbook/read/array-and-string/clpgd/
 * 
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * 
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 * 
 * 示例 2：
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * 
 * 提示：
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 */
public class Q48_rotateMatrix {
	private static final boolean DEBUG = true;

	private int[][] mMatrix;
	private int[][] mResult;
	private int mLength;

	// [i][j] -> [j][length - 1 - i]
	public void rotate2(int[][] matrix) {
		mMatrix = matrix;
		mLength = mMatrix.length;
		mResult = new int[mLength][mLength];
		for (int i = 0, j = mLength - 1; i <= j; i++, j--) {
			rotateInternal2(i);
			if (i != j)
				rotateInternal2(j);
		}

		for (int i = 0; i < mLength; i++) {
			matrix[i] = mResult[i];
		}

	}

	private void rotateInternal2(int i) {
		for (int j = 0, k = mLength - 1; j <= k; j++, k--) {
			mResult[j][mLength - 1 - i] = mMatrix[i][j];
			if (j != k)
				mResult[k][mLength - 1 - i] = mMatrix[i][k];
		}
	}

	// [i][j]
	// k < length/2
	// i = k
	// k <= j < (length - 1 - k)
	public void rotate(int[][] matrix) {
		mMatrix = matrix;
		int length = mMatrix.length;
		int n = length - 1;
		for (int k = 0, limit1 = length / 2; k < limit1; k++) {
			for (int i = k, j = k, limit2 = n - k; j < limit2; j++) {
				rotateInternal(i, j, n);
			}
		}
	}

	private void rotateInternal(int i, int j, int n) {
		if (DEBUG)
			System.out.println("i:" + i + ", j:" + j + ", n:" + n);
		int startI = i;
		int startJ = j;
		int temp = mMatrix[i][j];
		int temp_;
		int i_;
		do {
			// [i][j] -> [j][length - 1 - i]
			i_ = i;
			i = j;
			j = n - i_;

			temp_ = mMatrix[i][j];
			mMatrix[i][j] = temp;
			temp = temp_;
			System.out.println("i:" + i + ", j:" + j + ", startI:" + startI + ", startJ:" + startJ);
		} while (i != startI || j != startJ);
	}

	public static void main(String[] args) {
		// [[7,4,1],[8,5,2],[9,6,3]]
		// int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		// [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
		int[][] matrix = { { 5, 1, 9, 11 }, { 2, 4, 8, 10 }, { 13, 3, 6, 7 }, { 15, 14, 12, 16 } };

		System.out.println("matrix:" + Arrays.deepToString(matrix));
		Q48_rotateMatrix solution = new Q48_rotateMatrix();
		long start = System.currentTimeMillis();
		solution.rotate2(matrix);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", Result:" + Arrays.deepToString(matrix));
	}
}
