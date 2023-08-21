package org.demo.medium;

/**
 * 2771. 构造最长非递减子数组
 * https://leetcode.cn/problems/longest-non-decreasing-subarray-from-two-arrays/
 * 
 * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，长度均为 n 。
 * 让我们定义另一个下标从 0 开始、长度为 n 的整数数组，nums3 。对于范围 [0, n - 1] 的每个下标 i ，你可以将 nums1[i] 或 nums2[i] 的值赋给 nums3[i] 。
 * 
 * 你的任务是使用最优策略为 nums3 赋值，以最大化 nums3 中 最长非递减子数组 的长度。
 * 以整数形式表示并返回 nums3 中 最长非递减 子数组的长度。
 * 注意：子数组 是数组中的一个连续非空元素序列。
 * 
 * 示例 1：
 * 输入：nums1 = [2,3,1], nums2 = [1,2,1] 
 * 输出：2
 * 解释：构造 nums3 的方法之一是： 
 * nums3 = [nums1[0], nums2[1], nums2[2]] => [2,2,1]
 * 从下标 0 开始到下标 1 结束，形成了一个长度为 2 的非递减子数组 [2,2] 。 
 * 可以证明 2 是可达到的最大长度。
 * 
 * 示例 2：
 * 输入：nums1 = [1,3,2,1], nums2 = [2,2,3,4]
 * 输出：4
 * 解释：构造 nums3 的方法之一是：
 * nums3 = [nums1[0], nums2[1], nums2[2], nums2[3]] => [1,2,3,4]
 * 整个数组形成了一个长度为 4 的非递减子数组，并且是可达到的最大长度。
 * 
 * 示例 3：
 * 输入：nums1 = [1,1], nums2 = [2,2]
 * 输出：2
 * 解释：构造 nums3 的方法之一是： 
 * nums3 = [nums1[0], nums1[1]] => [1,1] 
 * 整个数组形成了一个长度为 2 的非递减子数组，并且是可达到的最大长度。
 * 
 * 提示：
 * 1 <= nums1.length == nums2.length == n <= 105
 * 1 <= nums1[i], nums2[i] <= 109
 */
public class TODO_Q2771_MaxNonDecreasingLength {

	public int maxNonDecreasingLength(int[] nums1, int[] nums2) {
		int length = nums1.length;
		int[] nums3 = new int[length];
		int last = -1;
		int result = 0;
		int count = 0;
		for (int i = 0; i < length; i++) {
			int num1 = nums1[i];
			int num2 = nums2[i];

			int min = Math.min(num1, num2);
			int max = Math.max(num1, num2);
			if (min >= last) {
				last = min;
				count++;
			} else if (max >= last) {
				last = max;
				count++;
			} else {
				result = Math.max(result, count);
				last = min;
				count = 1;
			}
			nums3[i] = last;
		}
		result = Math.max(result, count);
		return result;
	}

	public static void main(String[] args) {
		// 2
		int[] nums1 = { 2, 3, 1 }, nums2 = { 1, 2, 1 };
		// 4
		int[] nums3 = { 1, 3, 2, 1 }, nums4 = { 2, 2, 3, 4 };
		// 2
		int[] nums5 = { 1, 1 }, nums6 = { 2, 2 };
		// 2
		int[] nums7 = { 8, 7, 4 }, nums8 = { 13, 4, 4 };
		// 3
		int[] nums9 = { 11, 7, 7, 9 }, nums10 = { 19, 19, 1, 7 };

		TODO_Q2771_MaxNonDecreasingLength solution = new TODO_Q2771_MaxNonDecreasingLength();
		long start;
		int result;

//		System.out.println("nodes1_1:" + Arrays.toString(nodes1_1) + "nodes2_1:" + Arrays.toString(nodes2_1));
//		
		start = System.currentTimeMillis();
		result = solution.maxNonDecreasingLength(nums1, nums2);
		System.out.print("Time:" + (System.currentTimeMillis() - start) + ", [2] - Result:" + result + "\n");

		start = System.currentTimeMillis();
		result = solution.maxNonDecreasingLength(nums3, nums4);
		System.out.print("Time:" + (System.currentTimeMillis() - start) + ", [4] - Result:" + result + "\n");

		start = System.currentTimeMillis();
		result = solution.maxNonDecreasingLength(nums5, nums6);
		System.out.print("Time:" + (System.currentTimeMillis() - start) + ", [2] - Result:" + result + "\n");

		start = System.currentTimeMillis();
		result = solution.maxNonDecreasingLength(nums7, nums8);
		System.out.print("Time:" + (System.currentTimeMillis() - start) + ", [2] - Result:" + result + "\n");

		start = System.currentTimeMillis();
		result = solution.maxNonDecreasingLength(nums9, nums10);
		System.out.print("Time:" + (System.currentTimeMillis() - start) + ", [3] - Result:" + result + "\n");

	}

}
