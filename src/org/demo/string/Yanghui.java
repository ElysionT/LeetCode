package org.demo.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角
 * https://leetcode.cn/leetbook/read/array-and-string/cuj3m/
 * 
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * 
 * 示例 1:
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * 
 * 示例 2:
 * 输入: numRows = 1
 * 输出: [[1]]
 * 
 * 提示:
 * 1 <= numRows <= 30
 */
public class Yanghui {

	/*
	 * 执行用时：1 ms, 在所有 Java 提交中击败了96.91%的用户
	 * 内存消耗：39.7 MB, 在所有 Java 提交中击败了96.90%的用户
	 * 通过测试用例：30 / 30
	 */
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> result = new ArrayList<>();

		List<Integer> last = new ArrayList<>();
		last.add(1);
		result.add(last);
		for (int i = 1; i < numRows; i++) {
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
			result.add(temp);
			last = temp;
		}
		return result;
	}

	public static void main(String[] args) {
		// [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
		int numRows1 = 5;
		// [[1]]
		int numRows2 = 1;

		long start;
		List<List<Integer>> result;
		Yanghui solution = new Yanghui();

		System.out.println("numRows1:" + numRows1);
		start = System.currentTimeMillis();
		result = solution.generate(numRows1);
		System.out.println("Time:" + (System.currentTimeMillis() - start)
				+ ", [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]] - Result:" + result + "\n");

		System.out.println("numRows2:" + numRows2);
		start = System.currentTimeMillis();
		result = solution.generate(numRows2);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", [[1]] - Result:" + result + "\n");

	}

}
