package org.demo.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2460. 对数组执行操作
 * https://leetcode.cn/problems/apply-operations-to-an-array/
 * 
 * 给你一个下标从 0 开始的数组 nums ，数组大小为 n ，且由 非负 整数组成。
 * 你需要对数组执行 n - 1 步操作，其中第 i 步操作（从 0 开始计数）要求对 nums 中第 i 个元素执行下述指令：
 * 如果 nums[i] == nums[i + 1] ，则 nums[i] 的值变成原来的 2 倍，nums[i + 1] 的值变成 0 。否则，跳过这步操作。
 * 在执行完 全部 操作后，将所有 0 移动 到数组的 末尾 。
 * 例如，数组 [1,0,2,0,0,1] 将所有 0 移动到末尾后变为 [1,2,1,0,0,0] 。
 * 返回结果数组。
 * 注意 操作应当 依次有序 执行，而不是一次性全部执行。
 * 
 * 示例 1：
 * 输入：nums = [1,2,2,1,1,0]
 * 输出：[1,4,2,0,0,0]
 * 解释：执行以下操作：
 * - i = 0: nums[0] 和 nums[1] 不相等，跳过这步操作。
 * - i = 1: nums[1] 和 nums[2] 相等，nums[1] 的值变成原来的 2 倍，nums[2] 的值变成 0 。数组变成 [1,4,0,1,1,0] 。
 * - i = 2: nums[2] 和 nums[3] 不相等，所以跳过这步操作。
 * - i = 3: nums[3] 和 nums[4] 相等，nums[3] 的值变成原来的 2 倍，nums[4] 的值变成 0 。数组变成 [1,4,0,2,0,0] 。
 * - i = 4: nums[4] 和 nums[5] 相等，nums[4] 的值变成原来的 2 倍，nums[5] 的值变成 0 。数组变成 [1,4,0,2,0,0] 。
 * 执行完所有操作后，将 0 全部移动到数组末尾，得到结果数组 [1,4,2,0,0,0] 。
 * 
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[1,0]
 * 解释：无法执行任何操作，只需要将 0 移动到末尾。
 * 
 * 提示：
 * 2 <= nums.length <= 2000
 * 0 <= nums[i] <= 1000
 */
public class Q2460_ArrayApplyOperations {
	public int[] applyOperations(int[] nums) {
		int length = nums.length;
		int[] result = new int[length];
		int index = 0;
		for (int i = 1; i < length; i++) {
			int a = nums[i - 1];
			int b = nums[i];

			if (a == b) {
				if (0 != a)
					result[index++] = a * 2;
				nums[i] = 0;
				continue;
			}
			if (0 != a)
				result[index++] = a;
		}
		int a = nums[length - 1];
		if (0 != a)
			result[index++] = a;

		// for(; index < length;index++) {
		// result[index] = [0];
		// }

		return result;
	}

	public static void main(String[] args) {
		// [1,4,2,0,0,0]
//		int[] nums = { 1, 2, 2, 1, 1, 0 };
		// [1,0]
		int[] nums = { 0, 1 };

		System.out.println("nums:" + Arrays.toString(nums));
		Q2460_ArrayApplyOperations solution = new Q2460_ArrayApplyOperations();
		long start = System.currentTimeMillis();
		int[] result = solution.applyOperations(nums);
		System.out.println("Result:" + Arrays.toString(result) + ", Time:" + (System.currentTimeMillis() - start));

	}

}
