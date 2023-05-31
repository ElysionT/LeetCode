package org.demo.array;

import java.util.Arrays;

/**
 * 搜索插入位置
 * https://leetcode.cn/leetbook/read/array-and-string/cxqdh/
 * 
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * 
 * 示例 1:
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 
 * 示例 2:
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 
 * 示例 3:
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 * 
 * 提示:
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 为 无重复元素 的 升序 排列数组
 * -104 <= target <= 104
 */
public class SearchOrInsert {
	private int[] mNums;
	private int mTarget;

	public int searchInsert(int[] nums, int target) {
		mNums = nums;
		mTarget = target;
		return searchInsertInternal(0, nums.length - 1);
	}

	private int searchInsertInternal(int start, int end) {
		if (start > end)
			return -1;

		int middle = (end + start) / 2;
		int value = mNums[middle];
		if (mTarget == value) {
			return middle;
		} else if (mTarget > value) {
			int temp = searchInsertInternal(middle + 1, end);
			if (-1 == temp) {
				return end + 1;
			} else {
				return temp;
			}
		} else {
			int temp = searchInsertInternal(start, middle - 1);
			if (-1 == temp) {
				return start;
			} else {
				return temp;
			}
		}
	}

	public static void main(String[] args) {
		// 2
//		int[] nums = { 1, 3, 5, 6 };
//		int target = 5;
		// // 1
//		 int[] nums = { 1, 3, 5, 6 };
//		 int target = 2;
		// // 4
		int[] nums = { 1, 3, 5, 6 };
		int target = 7;

		System.out.println("nums:" + Arrays.toString(nums) + ", Target:" + target);
		SearchOrInsert solution = new SearchOrInsert();
		long start = System.currentTimeMillis();
		int result = solution.searchInsert(nums, target);
		System.out.println("Result:" + result + ", Time:" + (System.currentTimeMillis() - start));
	}
}
