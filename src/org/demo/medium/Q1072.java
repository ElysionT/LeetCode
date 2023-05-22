package org.demo.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1072. 按列翻转得到最大值等行数
 * 
 * 给定 m x n 矩阵 matrix 。
 * 你可以从中选出任意数量的列并翻转其上的 每个 单元格。（即翻转后，单元格的值从 0 变成 1，或者从 1 变为 0 。）
 * 返回 经过一些翻转后，行与行之间所有值都相等的最大行数 。
 * 
 * 示例 1：
 * 输入：matrix = [[0,1],[1,1]]
 * 输出：1
 * 解释：不进行翻转，有 1 行所有值都相等。
 * 
 * 示例 2：
 * 输入：matrix = [[0,1],[1,0]]
 * 输出：2
 * 解释：翻转第一列的值之后，这两行都由相等的值组成。
 * 
 * 示例 3：
 * 输入：matrix = [[0,0,0],[0,0,1],[1,1,0]]
 * 输出：2
 * 解释：翻转前两列的值之后，后两行由相等的值组成。
 * 
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] == 0 或 1
 * 
 * https://leetcode.cn/problems/flip-columns-for-maximum-number-of-equal-rows
 */
public class Q1072 {
	private final static boolean DEBUG = true;
	private final static boolean INFO = true;

	private final static Integer[] EMPTY = new Integer[0];

	private int[][] mMatrix; // int[m][n]
	private int mM;
	private int mN;

	public Q1072() {
	}

	public int maxEqualRowsAfterFlips(int[][] matrix) {
		mMatrix = matrix;
		mM = mMatrix.length;
		mN = mMatrix[0].length;
		if (INFO) {
			System.out.print("mMatrix:" + Arrays.deepToString(matrix));
			System.out.print(", mM:" + mM);
			System.out.println(", mN:" + mN);
		}

		if (1 == mN) {
			return mM;
		}

		Integer[] result = checkEqual2Row(null, 0, false, 1);
		System.out.println("result:" + Arrays.toString(result));

		return result.length;
	}

	public Integer[] checkEqual2Row(Integer[] rowRange, int n1, boolean isFlip, int n2) {
		if (DEBUG) {
			System.out.print("checkEqual2Row ");
			System.out.print("rowRange:" + Arrays.toString(rowRange));
			System.out.print(", n1:" + n1);
			System.out.print(", isFlip:" + isFlip);
			System.out.println(", n2:" + n2);
		}

		if (mN == n2) {
			return rowRange;
		}

		List<Integer> resule = new ArrayList<>();
		List<Integer> resultFlip = new ArrayList<>();
		checkEqual2RowInternal(rowRange, n1, isFlip, n2, resule, resultFlip);

//		Integer[] case1 = checkEqual2Row(rowRange, n1, isFlip, n2, false);
		Integer[] case1 = resule.toArray(new Integer[] {});
		if (DEBUG) {
			System.out.print("case1.length:" + case1.length);
			System.out.println(", case1:" + Arrays.toString(case1));
		}

//		Integer[] case2 = checkEqual2Row(rowRange, n1, isFlip, n2, true);
		Integer[] case2 = resultFlip.toArray(new Integer[] {});
		if (DEBUG) {
			System.out.print("case2.length:" + case2.length);
			System.out.println(", case2:" + Arrays.toString(case2));
		}

		int length1 = case1.length;
		int length2 = case2.length;

		if (0 == length1 && 0 == length2) {
			return EMPTY;
		}

		if (length1 > length2) {
			return checkEqual2Row(case1, n1 + 1, false, n2 + 1);
		} else if (length1 < length2) {
			return checkEqual2Row(case2, n1 + 1, true, n2 + 1);
		} else {
			return checkEqual2Row2(case1, case2, n1 + 1, n2 + 1);
		}
	}

	public void checkEqual2RowInternal(Integer[] rowRange, int n1, boolean isFlip, int n2, List<Integer> resule,
			List<Integer> resultFlip) {
		if (DEBUG) {
			System.out.print("checkEqual2RowInternal - ");
			System.out.print("rowRange:" + Arrays.toString(rowRange));
			System.out.print(", n1:" + n1);
			System.out.print(", isFlip:" + isFlip);
			System.out.println(", n2:" + n2);
		}

		if (null != rowRange) {
			for (int i = 0, length = rowRange.length; i < length; i++) {
				int row = rowRange[i];
				int n1Value = mMatrix[row][n1];
				if (isFlip) {
					n1Value = (0 == n1Value ? 1 : 0);
				}
				int n2Value = mMatrix[row][n2];

				if (DEBUG) {
					System.out.print("mMatrix[" + row + "][" + n1 + "]:" + n1Value);
					System.out.println(", mMatrix[" + row + "][" + n2 + "]:" + n2Value);
				}

				if (n1Value == n2Value) {
					resule.add(row);
				} else {
					resultFlip.add(row);
				}
			}
		} else {
			for (int i = 0; i < mM; i++) {
				int n1Value = mMatrix[i][n1];
				if (isFlip) {
					n1Value = (0 == n1Value ? 1 : 0);
				}
				int n2Value = mMatrix[i][n2];

				if (DEBUG) {
					System.out.print("mMatrix[" + i + "][" + n1 + "]:" + n1Value);
					System.out.println(", mMatrix[" + i + "][" + n2 + "]:" + n2Value);
				}

				if (n1Value == n2Value) {
					resule.add(i);
				} else {
					resultFlip.add(i);
				}
			}
		}
	}

	public Integer[] checkEqual2Row2(Integer[] rowRange1, Integer[] rowRange2, int n1, int n2) {
		if (DEBUG) {
			System.out.print("checkEqual2Row2 = ");
			System.out.print("rowRange1:" + Arrays.toString(rowRange1));
			System.out.print(", rowRange2:" + Arrays.toString(rowRange2));
			System.out.print(", n1:" + n1);
			System.out.println(", n2:" + n2);
		}

		if (mN == n2) {
			return rowRange1;
		}

		Integer[] case1 = checkEqual2Row(rowRange1, n1, false, n2);
		if (DEBUG) {
			System.out.print("case1.length:" + case1.length);
			System.out.println(", case1:" + Arrays.toString(case1));
		}
		Integer[] case2 = checkEqual2Row(rowRange2, n1, true, n2);
		if (DEBUG) {
			System.out.print("case2.length:" + case2.length);
			System.out.println(", case2:" + Arrays.toString(case2));
		}

		int length1 = case1.length;
		int length2 = case2.length;

		if (0 == length1 && 0 == length2) {
			return EMPTY;
		}

		if (length1 > length2) {
			return checkEqual2Row(case1, n1 + 1, false, n2 + 1);
		} else if (length1 < length2) {
			return checkEqual2Row(case2, n1 + 1, true, n2 + 1);
		} else {
			return checkEqual2Row2(case1, case2, n1 + 1, n2 + 1);
		}
	}

	@Deprecated
	public boolean checkEqualNext(int value, int m, int n) {
		if (mN == n) {
			return true;
		}

		if (value == mMatrix[m][n]) {
			return checkEqualNext(value, m, n + 1);
		}
		return false;
	}

	@Deprecated
	public int arrayEqualRows(int[][] matrix) {
		int result = 0;

		for (int i = 0, lenght = matrix.length; i < lenght; i++) {
			if (checkEqualRow(matrix[i])) {
				result++;
			}
		}
		return result;

//		for (int i = 0, j = 0, m = matrix.length, n = matrix[0].length; i < m; j++) {
//			if (n == j) {
//				i++;
//				j = 0;
//				continue;
//			}
//
//		}

	}

	@Deprecated
	public boolean checkEqualRow(int[] row) {
		boolean result = true;
		int base = row[0];
		for (int i = 1, length = row.length; i < length; i++) {
			if (base != row[i]) {
				result = false;
				break;
			}
		}
		return result;

	}

	@Deprecated
	public Integer[] checkEqual2Row(Integer[] rowRange, int n1, boolean isN1Flip, int n2, boolean isN2Flip) {
		if (DEBUG) {
			System.out.print("rowRange:" + Arrays.toString(rowRange));
			System.out.print(", n1:" + n1);
			System.out.print(", isN1Flip:" + isN1Flip);
			System.out.print(", n2:" + n2);
			System.out.println(", isN2Flip:" + isN2Flip);
		}

		final List<Integer> equalRow = new ArrayList();

		if (null != rowRange) {
			for (int i = 0, length = rowRange.length; i < length; i++) {
				int row = rowRange[i];

				int n1Value = mMatrix[row][n1];
				if (isN1Flip) {
					n1Value = (0 == n1Value ? 1 : 0);
				}

				int n2Value = mMatrix[row][n2];
				if (isN2Flip) {
					n2Value = (0 == n2Value ? 1 : 0);
				}

				if (DEBUG) {
					System.out.print("mMatrix[" + row + "][" + n1 + "]:" + n1Value);
					System.out.println(", mMatrix[" + row + "][" + n2 + "]:" + n2Value);
				}

				if (n1Value == n2Value) {
					equalRow.add(row);
				}
			}
		} else {
			for (int i = 0; i < mM; i++) {
				int n1Value = mMatrix[i][n1];
				if (isN1Flip) {
					n1Value = (0 == n1Value ? 1 : 0);
				}

				int n2Value = mMatrix[i][n2];
				if (isN2Flip) {
					n2Value = (0 == n2Value ? 1 : 0);
				}

				if (DEBUG) {
					System.out.print("mMatrix[" + i + "][" + n1 + "]:" + n1Value);
					System.out.println(", mMatrix[" + i + "][" + n2 + "]:" + n2Value);
				}

				if (n1Value == n2Value) {
					equalRow.add(i);
				}
			}
		}

		return equalRow.toArray(new Integer[] {});
	}

	public static void main(String[] args) {
//		int[][] matrix = new int[][] { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };

//		int[][] matrix = new int[][] { { 0, 1 }, { 1, 1 } }; // int[2][2]
//		int[][] matrix = new int[][] { { 0, 1 }, { 1, 0 } }; // int[2][2]
		int[][] matrix = new int[][] { { 0, 0, 0 }, { 0, 0, 1 }, { 1, 1, 0 } }; // int[3][3]
//		System.out.println("matrix:" + Arrays.deepToString(matrix));
//		System.out.println("m:" + matrix.length);

		Q1072 solution = new Q1072();
//		Integer[] result1 = solution.checkEqual2Row(null, 0, 1, false);
//		System.out.println("result1:" + Arrays.toString(result1));
//		Integer[] result = solution.checkEqual2Row(result1, 1, 2, false);
//		System.out.print("maxEqualRowsAfterFlips:" + result.length);
//		System.out.println(", result:" + Arrays.toString(result));

//		System.out.println("result:" + solution.maxEqualRowsAfterFlips(matrix));
		System.out.println("result:" + solution.maxEqualRowsAfterFlips(sMatrix));

	}

//	public static final int[][] sMatrix = { { 0, 0, 1, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 1, 1, 0 },
//			{ 0, 0, 1, 0 }, { 0, 0, 1, 1 }, { 0, 0, 1, 0 }, { 1, 0, 1, 1 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 },
//			{ 1, 1, 1, 1 }, { 0, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 1, 1 }, { 0, 0, 0, 0 }, { 1, 0, 1, 1 },
//			{ 1, 1, 0, 1 }, { 1, 0, 0, 0 }, { 0, 1, 0, 1 }, { 0, 1, 0, 0 }, { 1, 0, 1, 0 }, { 0, 0, 0, 0 },
//			{ 0, 0, 0, 0 }, { 1, 0, 1, 1 }, { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 1 },
//			{ 0, 1, 0, 0 }, { 0, 0, 0, 0 } };

//	public static final int[][] sMatrix = { { 0, 1, 0, 1, 0 }, { 0, 1, 1, 1, 0 }, { 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 1 },
//			{ 1, 1, 0, 0, 0 }, { 1, 0, 1, 0, 0 }, { 0, 0, 0, 1, 1 }, { 1, 1, 1, 0, 0 }, { 1, 0, 0, 0, 0 },
//			{ 0, 1, 0, 0, 0 }, { 1, 0, 1, 1, 0 }, { 0, 1, 1, 0, 1 }, { 0, 1, 0, 0, 0 }, { 1, 1, 0, 0, 1 },
//			{ 1, 1, 1, 1, 1 }, { 0, 0, 1, 1, 0 }, { 1, 1, 1, 0, 0 }, { 1, 0, 0, 1, 0 }, { 1, 1, 0, 1, 0 },
//			{ 1, 1, 0, 0, 1 }, { 1, 0, 0, 1, 0 }, { 0, 0, 0, 0, 0 }, { 1, 1, 0, 1, 1 }, { 1, 1, 1, 1, 0 },
//			{ 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 1 }, { 0, 0, 1, 0, 0 }, { 1, 1, 0, 1, 1 }, { 0, 1, 1, 0, 1 },
//			{ 1, 1, 0, 1, 0 } };

	public static final int[][] sMatrix = { { 0, 1, 0, 1, 0 }, { 0, 1, 1, 1, 0 }, { 0, 1, 1, 1, 1 }, { 1, 0, 1, 0, 0 },
			{ 1, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0 }, { 1, 0, 1, 1, 0 }, { 0, 1, 1, 0, 1 }, { 0, 1, 0, 0, 0 },
			{ 1, 0, 0, 1, 0 }, { 1, 0, 0, 1, 0 }, { 0, 1, 1, 0, 1 } };
}
