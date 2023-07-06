package org.demo.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角 II
 * https://leetcode.cn/leetbook/read/array-and-string/ctyt1/
 * 
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * 
 * 示例 1:
 * 输入: rowIndex = 3
 * 输出: [1,3,3,1]
 * 
 * 示例 2:
 * 输入: rowIndex = 0
 * 输出: [1]
 * 
 * 示例 3:
 * 输入: rowIndex = 1
 * 输出: [1,1]
 * 
 * 提示:
 * 0 <= rowIndex <= 33
 */
public class Yanghui2 {
	private static final boolean DEBUG = true;

	public List<Integer> getRow2(int rowIndex) {
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i <= rowIndex; i++) {
			result.add(getUpValue(i, rowIndex + 1));
		}

		return result;
	}

	public int getUpValue(int i, int j) {
		if (0 == i) {
			if (DEBUG)
				System.out.println("i:" + i + ", j:" + j + " - 1");
			return 1;
		}

		if (i < 0 || i == j) {
			if (DEBUG)
				System.out.println("i:" + i + ", j:" + j + " - 0");
			return 0;
		}

		int temp = getUpValue(i - 1, j - 1) + getUpValue(i, j - 1);
		if (DEBUG)
			System.out.println("i:" + i + ", j:" + j + " - " + temp);

		return temp;
	}

	/*
	 * 执行用时：1 ms, 在所有 Java 提交中击败了79.47%的用户
	 * 内存消耗：39.2 MB, 在所有 Java 提交中击败了58.52%的用户
	 * 通过测试用例：34 / 34
	 */
	public List<Integer> getRow(int rowIndex) {
		List<Integer> last = new ArrayList<>();
		last.add(1);
		for (int i = 1; i <= rowIndex; i++) {
			List<Integer> temp = new ArrayList<>();
			for (int j = 0; j <= i; j++) {
				int a = 0;
				if (j - 1 >= 0) {
					a = last.get(j - 1);
				}
				int b = 0;
				if (j < i) {
					b = last.get(j);
				}
				temp.add(a + b);
			}
			last = temp;
		}
		return last;
	}

	public static void main(String[] args) {
		// [1,3,3,1]
		int rowIndex1 = 3;
		// [1]
		int rowIndex2 = 0;
		// [1,1]
		int rowIndex3 = 1;

		long start;
		List<Integer> result;
		Yanghui2 solution = new Yanghui2();

		System.out.println("rowIndex1:" + rowIndex1);
		start = System.currentTimeMillis();
		result = solution.getRow2(rowIndex1);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", [1,3,3,1] - Result:" + result + "\n");

		System.out.println("rowIndex2:" + rowIndex2);
		start = System.currentTimeMillis();
		result = solution.getRow2(rowIndex2);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", [1] - Result:" + result + "\n");

		System.out.println("rowIndex3:" + rowIndex3);
		start = System.currentTimeMillis();
		result = solution.getRow2(rowIndex3);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", [1,1] - Result:" + result + "\n");
	}

}
