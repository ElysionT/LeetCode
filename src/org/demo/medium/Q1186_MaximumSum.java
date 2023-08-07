package org.demo.medium;

import java.io.IOException;
import java.util.Arrays;

import org.demo.QUtils;

/**
 * 1186. 删除一次得到子数组最大和
 * https://leetcode.cn/problems/maximum-subarray-sum-with-one-deletion/
 * 
 * 给你一个整数数组，返回它的某个 非空
 * 子数组（连续元素）在执行一次可选的删除操作后，所能得到的最大元素总和。换句话说，你可以从原数组中选出一个子数组，并可以决定要不要从中删除一个元素（只能删一次哦），（删除后）子数组中至少应当有一个元素，然后该子数组（剩下）的元素总和是所有子数组之中最大的。
 * 注意，删除一个元素后，子数组 不能为空。
 * 
 * 示例 1：
 * 输入：arr = [1,-2,0,3]
 * 输出：4
 * 解释：我们可以选出 [1, -2, 0, 3]，然后删掉 -2，这样得到 [1, 0, 3]，和最大。
 * 
 * 示例 2：
 * 输入：arr = [1,-2,-2,3]
 * 输出：3
 * 解释：我们直接选出 [3]，这就是最大和。
 * 
 * 示例 3：
 * 输入：arr = [-1,-1,-1,-1]
 * 输出：-1
 * 解释：最后得到的子数组不能为空，所以我们不能选择 [-1] 并从中删去 -1 来得到 0。
 * 我们应该直接选择 [-1]，或者选择 [-1, -1] 再从中删去一个 -1。
 * 
 * 提示：
 * 1 <= arr.length <= 105
 * -104 <= arr[i] <= 104
 */
// TODO:
public class Q1186_MaximumSum {
	private static final boolean DEBUG = false;
	private static final boolean INFO = false;

	// 执行用时： 1067 ms , 在所有 Java 提交中击败了5.74%的用户
	// 内存消耗： 50 MB, 在所有 Java 提交中击败了 88.51%的用户
	// 通过测试用例： 36 / 36
	public int maximumSum4(int[] arr) {
		String msg = "0";
		int length = arr.length;
		int[] sumArray = new int[length];
		sumArray[0] = arr[0];
		int maximumSum = arr[0];
		int min = arr[0];
		for (int i = 1, temp = 0, sum_ = 0; i < length; i++) {
			temp = arr[i];
			if (temp < min) {
				min = temp;
			}
			sumArray[i] = sumArray[i - 1] + temp;
			sum_ = min < 0 ? sumArray[i] - min : sumArray[i];

			if (sum_ > maximumSum) {
				maximumSum = sum_;
				if (INFO)
					msg = "0-" + i;
			}
		}
		if (DEBUG) {
			System.out.println("sumArray:" + Arrays.toString(sumArray));
			System.out.println("maximumSum:" + maximumSum);
		}

		for (int i = 1; i < length; i++) {
			if (sumArray[i - 1] >= 0) {
				continue;
			}
			min = arr[i];
			for (int j = i + 1, sum = 0, temp = 0, sum_ = 0; j < length; j++) {
				temp = arr[j];
				if (temp < min) {
					min = temp;
				}
				sum = sumArray[j] - sumArray[i - 1];
				sum_ = min < 0 ? sum - min : sum;
				if (DEBUG)
					System.out.println("i:" + i + ", j:" + j + ", min:" + min + ", sum:" + sum + ", sum_:" + sum_
							+ ", maximumSum:" + maximumSum);
				if (sum_ > maximumSum) {
					maximumSum = sum_;
					if (INFO)
						msg = i + "-" + j;
				}
			}
		}

		if (INFO)
			System.out.println("msg:" + msg);
		return maximumSum;
	}

	// Error
	public int maximumSum3(int[] arr) {
		int min = arr[0];
		int sum = min;
		int maximumSum = sum;

		int temp;
		for (int i = 1, length = arr.length; i < length; i++) {
			temp = arr[i];
			if (temp < min) {
				min = temp;
			}
			sum += temp;

			int sum_ = min < 0 ? sum - min : sum;
			if (sum_ < temp) {
				sum_ = temp;
			}
			if (sum_ > maximumSum) {
				maximumSum = sum_;
			}
		}
		return maximumSum;
	}

	// Timeout
	public int maximumSum2(int[] arr) {

		int maximumSum = arr[0];
		for (int i = 0, length = arr.length; i < length; i++) {
			int min = arr[i];
			int sum = arr[i];
			int temp;
			for (int j = i + 1; j < length; j++) {
				temp = arr[j];
				sum += temp;
				if (temp < min) {
					min = temp;
				}

				int sum_ = min < 0 ? sum - min : sum;
				if (DEBUG)
					System.out.println("i:" + i + ", j:" + j + ", min:" + min + ", sum:" + sum + ", sum_:" + sum_
							+ ", maximumSum:" + maximumSum);
				if (sum_ > maximumSum) {
					maximumSum = sum_;
				}
			}
		}

		return maximumSum;
	}

	// Error
	public int maximumSum(int[] arr) {
		int k = 1;
		int maximumSum = arr[0];
		int sum = maximumSum;
		for (int i = 1, length = arr.length; i < length; i++) {
			int a = arr[i];
			if (sum < 0) {
				sum = a;
			} else {
				if (a >= 0) {
					sum += a;
				} else {
					if (k > 0) {
						k--;
					} else {
						if (sum > maximumSum) {
							maximumSum = sum;
						}
						sum = a;
						k = 1;
					}
				}
			}

			if (sum > maximumSum) {
				maximumSum = sum;
			}
		}

		return maximumSum;
	}

	public static void main(String[] args) throws IOException {
		// 4
		int[] arr1 = { 1, -2, 0, 3 };
		// 3
		int[] arr2 = { 1, -2, -2, 3 };
		// -1
		int[] arr3 = { -1, -1, -1, -1 };
		// 17
		int[] arr4 = { 8, -1, 6, -7, -4, 5, -4, 7, -6 };
		// 7
		int[] arr6 = { 1, -4, -5, -2, 5, 0, -1, 2 };
		// 12
		int[] arr8 = { 1, -2, 3, -4, 5, -6, 7 };

		Q1186_MaximumSum solution = new Q1186_MaximumSum();
		System.out.println("arr1:" + Arrays.toString(arr1));
		long start = System.currentTimeMillis();
		int result = solution.maximumSum4(arr1);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {4} - Result:" + result + "\n");

		System.out.println("arr2:" + Arrays.toString(arr2));
		start = System.currentTimeMillis();
		result = solution.maximumSum4(arr2);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {3} - Result:" + result + "\n");

		System.out.println("arr3:" + Arrays.toString(arr3));
		start = System.currentTimeMillis();
		result = solution.maximumSum4(arr3);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {-1} - Result:" + result + "\n");

		System.out.println("arr4:" + Arrays.toString(arr4));
		start = System.currentTimeMillis();
		result = solution.maximumSum4(arr4);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {17} - Result:" + result + "\n");

		// 1452443
		String[] tempArray = QUtils.getStringArrayFromFile("Q1186", ",");
		final int[] arr5 = new int[tempArray.length];
		for (int i = 0, length = arr5.length; i < length; i++) {
			arr5[i] = Integer.valueOf(tempArray[i]);
		}
		System.out.println("arr5:" + Arrays.toString(arr5));
		start = System.currentTimeMillis();
		result = solution.maximumSum4(arr5);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {1452443} - Result:" + result + "\n");

		System.out.println("arr6:" + Arrays.toString(arr6));
		start = System.currentTimeMillis();
		result = solution.maximumSum4(arr6);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {7} - Result:" + result + "\n");

		// 961792
		tempArray = QUtils.getStringArrayFromFile("Q1186_2", ",");
		final int[] arr7 = new int[tempArray.length];
		for (int i = 0, length = arr7.length; i < length; i++) {
			arr7[i] = Integer.valueOf(tempArray[i]);
		}
		System.out.println("arr7:" + Arrays.toString(arr7));
		start = System.currentTimeMillis();
		result = solution.maximumSum4(arr7);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {961792} - Result:" + result + "\n");

		System.out.println("arr8:" + Arrays.toString(arr8));
		start = System.currentTimeMillis();
		result = solution.maximumSum4(arr8);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {12} - Result:" + result + "\n");

	}
}
