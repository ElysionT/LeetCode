package org.demo.array;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import org.demo.QUtils;

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
//	public int[][] merge3(int[][] intervals) {
//		int length = intervals.length;
//		List<int[]> result = new LinkedList<>();
//		for (int i = 0; i < length; i++) {
//			int[] interval = intervals[i];
//			int min = interval[0];
//			int max = interval[1];
//			int size = result.size();
//			int insertAt = 0;
//			int[] ja;
//			int[] ka;
//			if (1 == size) {
//				ja = result.get(0);
//				int min_ = ja[0];
//				int max_ = ja[1];
//
//				if (max < min_) {
//					result.add(0, interval);
//				} else if (min > max_) {
//					result.add(interval);
//				} else {
//					min = Math.min(min, min_);
//					max = Math.max(max, max_);
//					ja[0] = min;
//					ja[1] = max;
//				}
//			} else {
//				for (int j = 0, k = j + 1, limit = size - 1; j < limit; j++, k++) {
//					ja = result.get(j);
//					ka = result.get(k);
//					int minj = ja[0];
//					int maxj = ja[1];
//
//					int mink = ka[0];
//					int maxk = ka[1];
//
//					if (max < minj) {
//						result.add(j, interval);
//						break;
//					} else if (maxj < min && max < mink) {
//						result.add(k, interval);
//						break;
//					} else if (maxk < min) {
//						if (k == limit) {
//							result.add(interval);
//							break;
//						}
//						continue;
//					} else {
//						if (max < mink) {
//							min = Math.min(min, minj);
//							max = Math.max(max, maxj);
//							ja[0] = min;
//							ja[1] = max;
//						} else if (maxj < min) {
//							min = Math.min(min, mink);
//							max = Math.max(max, maxk);
//							ka[0] = min;
//							ka[1] = max;
//						}
//
//					}
//
//				}
//			}
//
//			for (int j = 0, k = size - 1; j <= k; j++, k--) {
//				ja = result.get(j);
//				ka = result.get(k);
//				int minj = ja[0];
//				int maxj = ja[1];
//
//				int mink = ka[0];
//				int maxk = ka[1];
//
//				if (max < minj) {
//					insertAt = j;
//					break;
//				} else if (min > maxk) {
//					insertAt = k + 1;
//					break;
//				} else if (maxj < min && max < mink) {
//					insertAt = j + 1;
//					break;
//				} else {
//
//				}
//
//			}
//
//			for (int j = result.size() - 1; j >= 0; j--) {
//				int min_ = result.get(j)[0];
//				int max_ = result.get(j)[1];
//				if (max_ < min || min_ > max) {
//					continue;
//				}
//				min = Math.min(min, min_);
//				max = Math.max(max, max_);
//				result.remove(j);
//			}
//			result.add(new int[] { min, max });
//		}
//		return result.toArray(new int[][] {});
//	}

	public int[][] merge2(int[][] intervals) {
		int length = intervals.length;
		List<int[]> result = new ArrayList<>(length);
		for (int i = 0; i < length; i++) {
			int min = intervals[i][0];
			int max = intervals[i][1];
			for (int j = result.size() - 1; j >= 0; j--) {
				int min_ = result.get(j)[0];
				int max_ = result.get(j)[1];
				if (max_ < min || min_ > max) {
					continue;
				}
				min = Math.min(min, min_);
				max = Math.max(max, max_);
				result.remove(j);
			}
			result.add(new int[] { min, max });
		}
		return result.toArray(new int[][] {});
	}

	@Deprecated
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
			for (int i = indexList.size() - 1; i >= 0;) {
				index = indexList.get(i);
				int min_ = intervals[index][0];
				int max_ = intervals[index][1];

				if (max_ < min || min_ > max) {
					i--;
					continue;
				}

				min = Math.min(min, min_);
				max = Math.max(max, max_);
				indexList.remove(i);
				i = indexList.size() - 1;
			}
			result.add(new int[] { min, max });
		}

		return result.toArray(new int[][] {});
	}

	public static void main(String[] args) throws IOException {
		// [[1,6],[8,10],[15,18]]
		// int[][] intervals = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
		// [[1,5]]
		// int[][] intervals = { { 1, 4 }, { 4, 5 } };
		// [[0,5]]
		// int[][] intervals = { { 4, 5 }, { 1, 4 }, { 0, 1 } };

		final String[] stringArray = QUtils.getStringArrayFromFile("MergeIntervals", "\\],\\[");
		int length = stringArray.length;
		int[][] intervals = new int[length][2];
		String[] temp;
		for (int i = 0; i < length; i++) {
			temp = stringArray[i].split(",");
			intervals[i] = new int[] { Integer.valueOf(temp[0]), Integer.valueOf(temp[1]) };
		}

		System.out.println("intervals:" + Arrays.deepToString(intervals));
		MergeIntervals solution = new MergeIntervals();
		long start = System.currentTimeMillis();
		int[][] result = solution.merge2(intervals);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", Result:" + Arrays.deepToString(result));

	}

}
