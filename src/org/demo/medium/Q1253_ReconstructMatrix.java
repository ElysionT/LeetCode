package org.demo.medium;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.demo.QUtils;

/**
 * 1253. 重构 2 行二进制矩阵
 * https://leetcode.cn/problems/reconstruct-a-2-row-binary-matrix/
 * 
 * 给你一个 2 行 n 列的二进制数组：
 * 矩阵是一个二进制矩阵，这意味着矩阵中的每个元素不是 0 就是 1。
 * 第 0 行的元素之和为 upper。
 * 第 1 行的元素之和为 lower。
 * 第 i 列（从 0 开始编号）的元素之和为 colsum[i]，colsum 是一个长度为 n 的整数数组。
 * 你需要利用 upper，lower 和 colsum 来重构这个矩阵，并以二维整数数组的形式返回它。
 * 如果有多个不同的答案，那么任意一个都可以通过本题。
 * 如果不存在符合要求的答案，就请返回一个空的二维数组。
 * 
 * 示例 1：
 * 输入：upper = 2, lower = 1, colsum = [1,1,1]
 * 输出：[[1,1,0],[0,0,1]]
 * 解释：[[1,0,1],[0,1,0]] 和 [[0,1,1],[1,0,0]] 也是正确答案。
 * 
 * 示例 2：
 * 输入：upper = 2, lower = 3, colsum = [2,2,1,1]
 * 输出：[]
 * 
 * 示例 3：
 * 
 * 输入：upper = 5, lower = 5, colsum = [2,1,2,0,1,0,1,2,0,1]
 * 输出：[[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]
 * 
 * 
 * 提示：
 * 
 * 1 <= colsum.length <= 10^5
 * 0 <= upper, lower <= colsum.length
 * 0 <= colsum[i] <= 2
 */
public class Q1253_ReconstructMatrix {
	private static final boolean DEBUG = false;
	private static final boolean INFO = true;

	/*
	 * SUM(colsum(0)+...+colsum(n)) = (upper + lower).
	 * 
	 * 执行用时：10 ms, 在所有 Java 提交中击败了53.00%的用户
	 * 内存消耗：53.8 MB, 在所有 Java 提交中击败了93.00%的用户
	 * 通过测试用例：69 / 69
	 */
	public List<List<Integer>> reconstructMatrix2(int upper, int lower, int[] colsum) {
		int length = colsum.length;
		if (INFO)
			System.out.println("length:" + length);
		final Integer[][] twoRowArray = new Integer[2][length];
		final List<List<Integer>> matrix = new ArrayList<>();

		final List<Integer> oneIndex = new ArrayList<>();
		long start;
		if (INFO)
			start = System.currentTimeMillis();

		for (int i = 0; i < length; i++) {
			if (DEBUG)
				System.out.println("colsum[" + i + "]:" + colsum[i] + ", upper:" + upper + ", lower:" + lower);
			switch (colsum[i]) {
			case 2:
				if (0 == upper || 0 == lower) {
					return matrix;
				}
				twoRowArray[0][i] = 1;
				upper--;
				twoRowArray[1][i] = 1;
				lower--;
				break;
			case 1:
				if (0 == upper && 0 == lower) {
					return matrix;
				}
				oneIndex.add(i);
				break;
			case 0:
				twoRowArray[0][i] = 0;
				twoRowArray[1][i] = 0;
				break;
			default:
				throw new IllegalAccessError("Invalid colsum:" + colsum[i] + "!");
			}
		}
		if (INFO)
			System.out.println("Time1:" + (System.currentTimeMillis() - start));

		if (DEBUG)
			System.out.println("oneIndex:" + oneIndex);

		if (INFO)
			start = System.currentTimeMillis();
		for (int index : oneIndex) {
			if (DEBUG)
				System.out.println("index:" + index + ", upper:" + upper + ", lower:" + lower);
			if (0 == upper && 0 == lower) {
				return matrix;
			}
			if (upper > 0) {
				twoRowArray[0][index] = 1;
				upper--;
				twoRowArray[1][index] = 0;
			} else {
				twoRowArray[0][index] = 0;
				lower--;
				twoRowArray[1][index] = 1;
			}
		}
		if (INFO)
			System.out.println("Time2:" + (System.currentTimeMillis() - start));

		if (0 != upper || 0 != lower) {
			return matrix;
		}

		if (INFO)
			start = System.currentTimeMillis();
		matrix.add(Arrays.asList(twoRowArray[0]));
		matrix.add(Arrays.asList(twoRowArray[1]));
		if (INFO)
			System.out.println("Time3:" + (System.currentTimeMillis() - start));

		return matrix;
	}

	// Error
	public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
		int length = colsum.length;
		Integer[][] twoRowArray = new Integer[2][length];
		List<List<Integer>> matrix = new ArrayList<>();
		boolean useUpper = true;
		for (int i = 0, sum = 0; i < length; i++) {
			sum = colsum[i];
			switch (sum) {
			case 2:
				if (0 == upper || 0 == lower) {
					return matrix;
				}
				twoRowArray[0][i] = 1;
				upper--;
				twoRowArray[1][i] = 1;
				lower--;
				break;
			case 1:
				if (0 == upper && 0 == lower) {
					return matrix;
				}
				if (upper > 0 && useUpper) {
					twoRowArray[0][i] = 1;
					upper--;
					twoRowArray[1][i] = 0;
					useUpper = false;
				} else {
					twoRowArray[0][i] = 0;
					lower--;
					twoRowArray[1][i] = 1;
					useUpper = true;
				}
				break;
			case 0:
				twoRowArray[0][i] = 0;
				twoRowArray[1][i] = 0;
				break;
			default:
				throw new IllegalAccessError("Invalid colsum:" + sum + "!");
			}
		}
		if (0 != upper || 0 != lower) {
			return matrix;
		}

		matrix.add(Arrays.asList(twoRowArray[0]));
		matrix.add(Arrays.asList(twoRowArray[1]));

		return matrix;
	}

	public static void main(String[] args) throws IOException {

		// [[1,1,0],[0,0,1]]
		int upper1 = 2, lower1 = 1;
		int[] colsum1 = { 1, 1, 1 };
		// []
		int upper2 = 2, lower2 = 3;
		int[] colsum2 = { 2, 2, 1, 1 };
		// [[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]
		int upper3 = 5, lower3 = 5;
		int[] colsum3 = { 2, 1, 2, 0, 1, 0, 1, 2, 0, 1 };
		// [[1,1,1,1,0],[0,1,0,1,0]]
		int upper4 = 4, lower4 = 2;
		int[] colsum4 = { 1, 2, 1, 2, 0 };

		long start;
		List<List<Integer>> result;
		Q1253_ReconstructMatrix solution = new Q1253_ReconstructMatrix();

		System.out.println("upper1:" + upper1 + ", lower1:" + lower1 + ", colsum1:" + Arrays.toString(colsum1));
		start = System.currentTimeMillis();
		result = solution.reconstructMatrix2(upper1, lower1, colsum1);
		System.out.println(
				"Time:" + (System.currentTimeMillis() - start) + ", [[1,1,0],[0,0,1]] - Result:" + result + "\n");

		System.out.println("upper2:" + upper2 + ", lower2:" + lower2 + ", colsum2:" + Arrays.toString(colsum2));
		start = System.currentTimeMillis();
		result = solution.reconstructMatrix2(upper2, lower2, colsum2);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", [] - Result:" + result + "\n");

		System.out.println("upper3:" + upper3 + ", lower3:" + lower3 + ", colsum3:" + Arrays.toString(colsum3));
		start = System.currentTimeMillis();
		result = solution.reconstructMatrix2(upper3, lower3, colsum3);
		System.out.println("Time:" + (System.currentTimeMillis() - start)
				+ ",  [[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]] - Result:" + result + "\n");

		System.out.println("upper4:" + upper4 + ", lower4:" + lower4 + ", colsum4:" + Arrays.toString(colsum4));
		start = System.currentTimeMillis();
		result = solution.reconstructMatrix2(upper4, lower4, colsum4);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ",  [[1,1,1,1,0],[0,1,0,1,0]] - Result:"
				+ result + "\n");

		int upper5 = 49908, lower5 = 49852;
		String[] tempArray = QUtils.getStringArrayFromFile("Q1253", ",");
		final int[] colsum5 = new int[tempArray.length];
		for (int i = 0, length = colsum5.length; i < length; i++) {
			colsum5[i] = Integer.valueOf(tempArray[i]);
		}
		System.out.println("upper5:" + upper5 + ", lower5:" + lower5 + ", colsum5:" + Arrays.toString(colsum5));
		start = System.currentTimeMillis();
		result = solution.reconstructMatrix2(upper5, lower5, colsum5);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ",  [Too Long] - Result:" + result + "\n");

	}

}
