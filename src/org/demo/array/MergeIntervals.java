package org.demo.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

/**
 * 合并区间
 * https://leetcode.cn/leetbook/read/array-and-string/c5tv3/
 * 
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * 
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 
 * 示例 2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 
 * 提示：
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 */
public class MergeIntervals {
	public int[][] merge(int[][] intervals) {
		int length = intervals.length;
		List<int[]> result = new ArrayList<>(length);

		List<Integer> indexList = new LinkedList<>();
		for (int i = 0; i < length; i++) {
			indexList.add(i);
		}

		while (0 < indexList.size()) {
			int index = indexList.remove(0);
			int min = intervals[index][0];
			int max = intervals[index][1];
			for (int i = indexList.size() - 1; i >= 0; i--) {
				index = indexList.get(i);
				int min_ = intervals[index][0];
				int max_ = intervals[index][1];

				if (max_ < min || min_ > max)
					continue;

				min = Math.min(min, min_);
				max = Math.max(max, max_);
				indexList.remove(i);
			}
			result.add(new int[] { min, max });
		}

		return result.toArray(new int[][] {});
	}

	public static void main(String[] args) {
		// [[1,6],[8,10],[15,18]]
		int[][] intervals = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
		// [[1,5]]
		// int[][] intervals = { { 1, 4 }, { 4, 5 } };

		System.out.println("intervals:" + Arrays.deepToString(intervals));
		MergeIntervals solution = new MergeIntervals();
		long start = System.currentTimeMillis();
		int[][] result = solution.merge(intervals);
		System.out.println("Result:" + Arrays.deepToString(result) + ", Time:" + (System.currentTimeMillis() - start));

	}

}
