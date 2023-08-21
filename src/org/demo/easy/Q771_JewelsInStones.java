package org.demo.easy;

/**
 * 771. 宝石与石头
 * https://leetcode.cn/problems/jewels-and-stones/
 * 
 * 给你一个字符串 jewels 代表石头中宝石的类型，另有一个字符串 stones 代表你拥有的石头。 stones 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * 字母区分大小写，因此 "a" 和 "A" 是不同类型的石头。
 * 
 * 示例 1：
 * 输入：jewels = "aA", stones = "aAAbbbb"
 * 输出：3
 * 
 * 示例 2：
 * 输入：jewels = "z", stones = "ZZ"
 * 输出：0 
 * 
 * 提示：
 * 1 <= jewels.length, stones.length <= 50
 * jewels 和 stones 仅由英文字母组成
 * jewels 中的所有字符都是 唯一的
 */
public class Q771_JewelsInStones {
	/*
	 * 时间: 0ms 击败 100.00%使用 Java 的用户
	 * 内存: 38.81MB 击败 63.37%使用 Java 的用户
	 */
	public int numJewelsInStones(String jewels, String stones) {
		char[] stoneArray = stones.toCharArray();
		int result = 0;
		for (char c : stoneArray) {
			if (-1 != jewels.indexOf(c)) {
				result++;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// 3
		String jewels1 = "aA", stones1 = "aAAbbbb";
		// 0
		String jewels2 = "z", stones2 = "ZZ";

		Q771_JewelsInStones solution = new Q771_JewelsInStones();
		long start;
		int result;

		System.out.println("jewels1:" + jewels1 + ", stones1:" + stones1);
		start = System.currentTimeMillis();
		result = solution.numJewelsInStones(jewels1, stones1);
		System.out.print("Time:" + (System.currentTimeMillis() - start) + ", [3] - Result:" + result + "\n");

		System.out.println("jewels2:" + jewels2 + ", stones2:" + stones2);
		start = System.currentTimeMillis();
		result = solution.numJewelsInStones(jewels2, stones2);
		System.out.print("Time:" + (System.currentTimeMillis() - start) + ", [0] - Result:" + result + "\n");
	}
}
