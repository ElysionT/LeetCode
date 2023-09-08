package org.demo.easy;

/**
 * 2651. 计算列车到站时间
 * https://leetcode.cn/problems/calculate-delayed-arrival-time
 * 
 * 给你一个正整数 arrivalTime 表示列车正点到站的时间（单位：小时），另给你一个正整数 delayedTime 表示列车延误的小时数。
 * 返回列车实际到站的时间。
 * 注意，该问题中的时间采用 24 小时制。
 * 
 * 示例 1：
 * 输入：arrivalTime = 15, delayedTime = 5
 * 输出：20
 * 解释：列车正点到站时间是 15:00 ，延误 5 小时，所以列车实际到站的时间是 15 + 5 = 20（20:00）。
 * 
 * 示例 2：
 * 输入：arrivalTime = 13, delayedTime = 11
 * 输出：0
 * 解释：列车正点到站时间是 13:00 ，延误 11 小时，所以列车实际到站的时间是 13 + 11 = 24（在 24 小时制中表示为 00:00 ，所以返回 0）。
 * 
 * 提示：
 * 1 <= arrivaltime < 24
 * 1 <= delayedTime <= 24
 */
public class Q2651_FindDelayedArrivalTime {

	/*
	 * 时间: 0ms 击败 100.00%使用 Java 的用户
	 * 内存: 37.63MB 击败 29.18%使用 Java 的用户
	 */
	public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
		return (arrivalTime + delayedTime) % 24;
	}

	public static void main(String[] args) {
		// 20
		int arrivalTime1 = 15, delayedTime1 = 5;
		// 0
		int arrivalTime2 = 13, delayedTime2 = 11;

		Q2651_FindDelayedArrivalTime solution = new Q2651_FindDelayedArrivalTime();
		long start;
		int result;

		System.out.println("arrivalTime1:" + arrivalTime1 + ", delayedTime1:" + delayedTime1);
		start = System.currentTimeMillis();
		result = solution.findDelayedArrivalTime(arrivalTime1, delayedTime1);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", [20] - Result:" + result + "\n");

		System.out.println("arrivalTime2:" + arrivalTime2 + ", delayedTime2:" + delayedTime2);
		start = System.currentTimeMillis();
		result = solution.findDelayedArrivalTime(arrivalTime2, delayedTime2);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", [0] - Result:" + result + "\n");
	}
}
