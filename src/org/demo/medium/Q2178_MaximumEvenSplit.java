package org.demo.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 2178. 拆分成最多数目的正偶数之和
 * https://leetcode.cn/problems/maximum-split-of-positive-even-integers/
 * 
 * 给你一个整数 finalSum 。请你将它拆分成若干个 互不相同 的正偶数之和，且拆分出来的正偶数数目 最多 。
 * 比方说，给你 finalSum = 12 ，那么这些拆分是 符合要求 的（互不相同的正偶数且和为 finalSum）：(2 + 10) ，(2 + 4 + 6) 和 (4 + 8) 。它们中，(2 + 4 + 6)
 * 包含最多数目的整数。注意 finalSum 不能拆分成 (2 + 2 + 4 + 4) ，因为拆分出来的整数必须互不相同。
 * 请你返回一个整数数组，表示将整数拆分成 最多 数目的正偶数数组。如果没有办法将 finalSum 进行拆分，请你返回一个 空 数组。你可以按 任意 顺序返回这些整数。
 * 
 * 示例 1：
 * 输入：finalSum = 12
 * 输出：[2,4,6]
 * 解释：以下是一些符合要求的拆分：(2 + 10)，(2 + 4 + 6) 和 (4 + 8) 。
 * (2 + 4 + 6) 为最多数目的整数，数目为 3 ，所以我们返回 [2,4,6] 。
 * [2,6,4] ，[6,2,4] 等等也都是可行的解。
 * 
 * 示例 2：
 * 输入：finalSum = 7
 * 输出：[]
 * 解释：没有办法将 finalSum 进行拆分。
 * 所以返回空数组。
 * 
 * 示例 3：
 * 输入：finalSum = 28
 * 输出：[6,8,2,12]
 * 解释：以下是一些符合要求的拆分：(2 + 26)，(6 + 8 + 2 + 12) 和 (4 + 24) 。
 * (6 + 8 + 2 + 12) 有最多数目的整数，数目为 4 ，所以我们返回 [6,8,2,12] 。
 * [10,2,4,12] ，[6,2,4,16] 等等也都是可行的解。
 * 
 * 提示：
 * 1 <= finalSum <= 1010
 */
public class Q2178_MaximumEvenSplit {
	/*
	 * 执行用时：8 ms, 在所有 Java 提交中击败了100.00%的用户
	 * 内存消耗：53.9 MB, 在所有 Java 提交中击败了77.55%的用户
	 * 通过测试用例：56 / 56
	 */
	public List<Long> maximumEvenSplit4(long finalSum) {
		// Result: [2,4,6,...,2(n-1),(finalSum - SUM{2...2(n-1)})]
		if (0 != finalSum % 2) {
			return new ArrayList<>(0);
		}
		// n <= (Math.sqrt(4 * finalSum + 1) - 1) / 2;
		final long n = (long) ((Math.sqrt(4 * finalSum + 1) - 1) / 2);
		final List<Long> queue = new ArrayList<>();
		for (int i = 1; i < n; i++) {
			queue.add((long) (2 * i));
		}
		// Sum(2,4,6,8,...,2n) = n * (n + 1)
		long sum = n * (n - 1);
		queue.add(finalSum - sum);

		return queue;
	}

	// Timeout
	// Time:14760
	public List<Long> maximumEvenSplit3(long finalSum) {
		List<Long> queue = new ArrayList<>();
		if (0 != finalSum % 2) {
			return queue;
		}
		queue.add(finalSum);

		int i = 0;
		long val = 0l, min = 0l;
		while (i < queue.size()) {
			val = queue.get(i);
			i++;
			for (long a = min + 2, b = val - a; a < b; a += 2, b -= 2) {
				if (!queue.contains(a) && !queue.contains(b)) {
					queue.remove(--i);
					queue.add(a);
					queue.add(b);
					min = a;
					break;
				}
			}
		}
		return queue;
	}

	// Timeout
	// Time:12178 LinkedList
	// Time:3894 ArrayList
	public List<Long> maximumEvenSplit2(long finalSum) {
		List<Long> queue = new ArrayList<>();
		if (0 != finalSum % 2) {
			return queue;
		}
		queue.add(finalSum);

		int i = 0;
		long val = 0l;
		while (i < queue.size()) {
			val = queue.get(i);
			i++;
			for (long a = 2, b = val - 2; a < b; a += 2, b -= 2) {
				if (!queue.contains(a) && !queue.contains(b)) {
					queue.remove(--i);
					queue.add(a);
					queue.add(b);
					break;
				}
			}
		}
		return queue;
	}

	// Error
	public List<Long> maximumEvenSplit(long finalSum) {
		List<Long> queue = new LinkedList<>();
		if (0 != finalSum % 2) {
			return queue;
		}
		queue.add(finalSum);

		int i = 0;
		long val = 0l, valHalf = 0l;
		while (i < queue.size()) {
			val = queue.get(i);
			valHalf = val / 2;
			if (0 == valHalf % 2) {
				for (int j = 1;; j++) {
					long a = valHalf - j * 2;
					long b = valHalf + j * 2;
					if (0 == a) {
						i++;
						break;
					}
					if (queue.contains(a) || queue.contains(b)) {
						continue;
					}
					queue.remove(i);
					queue.add(a);
					queue.add(b);
					break;
				}
			} else {
				for (int j = 0;; j++) {
					long a = valHalf - 1 - j * 2;
					long b = valHalf + 1 + j * 2;
					if (0 == a) {
						i++;
						break;
					}
					if (queue.contains(a) || queue.contains(b)) {
						continue;
					}
					queue.remove(i);
					queue.add(a);
					queue.add(b);
					break;
				}
			}

		}
		return queue;
	}

	public static void main(String[] args) {
		// [2,4,6]
		long finalSum1 = 12l;
		// []
		long finalSum2 = 7l;
		// [6,8,2,12]
		long finalSum3 = 28l;
		// 30
		long finalSum4 = 936l;
		// 1928
		long finalSum5 = 3722146l;
		// 83150
		long finalSum6 = 6914017674l;

		long start;
		List<Long> result;
		Q2178_MaximumEvenSplit solution = new Q2178_MaximumEvenSplit();

		System.out.println("finalSum1:" + finalSum1);
		start = System.currentTimeMillis();
		result = solution.maximumEvenSplit4(finalSum1);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", [2,4,6] - Result:" + result + "\n");

		System.out.println("finalSum2:" + finalSum2);
		start = System.currentTimeMillis();
		result = solution.maximumEvenSplit4(finalSum2);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", [] - Result:" + result + "\n");

		System.out.println("finalSum3:" + finalSum3);
		start = System.currentTimeMillis();
		result = solution.maximumEvenSplit4(finalSum3);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", [6,8,2,12] - Result:" + result + "\n");

		System.out.println("finalSum4:" + finalSum4);
		start = System.currentTimeMillis();
		result = solution.maximumEvenSplit4(finalSum4);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", Length:30 - Result:" + result.size()
				+ result + "\n");

		System.out.println("finalSum5:" + finalSum5);
		start = System.currentTimeMillis();
		result = solution.maximumEvenSplit4(finalSum5);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", Length:1928 - Result:" + result.size()
				+ result + "\n");

		System.out.println("finalSum6:" + finalSum6);
		start = System.currentTimeMillis();
		result = solution.maximumEvenSplit4(finalSum6);
		System.out.println(
				"Time:" + (System.currentTimeMillis() - start) + ", Length:83150 - Result:" + result.size() + "\n");
	}

}
