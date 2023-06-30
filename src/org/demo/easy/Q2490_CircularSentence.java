package org.demo.easy;

import java.util.Arrays;

/**
 * 2490. 回环句
 * https://leetcode.cn/problems/circular-sentence/
 * 
 * 句子 是由单个空格分隔的一组单词，且不含前导或尾随空格。
 * 例如，"Hello World"、"HELLO"、"hello world hello world" 都是符合要求的句子。
 * 单词 仅 由大写和小写英文字母组成。且大写和小写字母会视作不同字符。
 * 
 * 如果句子满足下述全部条件，则认为它是一个 回环句 ：
 * 单词的最后一个字符和下一个单词的第一个字符相等。
 * 最后一个单词的最后一个字符和第一个单词的第一个字符相等。
 * 例如，"leetcode exercises sound delightful"、"eetcode"、"leetcode eats soul" 都是回环句。然而，"Leetcode is cool"、
 * "happy Leetcode"、"Leetcode" 和 "I like Leetcode" 都 不 是回环句。
 * 
 * 给你一个字符串 sentence ，请你判断它是不是一个回环句。如果是，返回 true ；否则，返回 false 。
 * 
 * 示例 1：
 * 输入：sentence = "leetcode exercises sound delightful"
 * 输出：true
 * 解释：句子中的单词是 ["leetcode", "exercises", "sound", "delightful"] 。
 * - leetcode 的最后一个字符和 exercises 的第一个字符相等。
 * - exercises 的最后一个字符和 sound 的第一个字符相等。
 * - sound 的最后一个字符和 delightful 的第一个字符相等。
 * - delightful 的最后一个字符和 leetcode 的第一个字符相等。
 * 这个句子是回环句。
 * 
 * 示例 2：
 * 输入：sentence = "eetcode"
 * 输出：true
 * 解释：句子中的单词是 ["eetcode"] 。
 * - eetcode 的最后一个字符和 eetcode 的第一个字符相等。
 * 这个句子是回环句。
 * 
 * 示例 3：
 * 输入：sentence = "Leetcode is cool"
 * 输出：false
 * 解释：句子中的单词是 ["Leetcode", "is", "cool"] 。
 * - Leetcode 的最后一个字符和 is 的第一个字符 不 相等。
 * 这个句子 不 是回环句。
 * 
 * 提示：
 * 1 <= sentence.length <= 500
 * sentence 仅由大小写英文字母和空格组成
 * sentence 中的单词由单个空格进行分隔
 * 不含任何前导或尾随空格
 */
public class Q2490_CircularSentence {

	/*
	 * 执行用时：1 ms, 在所有 Java 提交中击败了96.22%的用户
	 * 内存消耗：40.2 MB, 在所有 Java 提交中击败了41.18%的用户
	 * 通过测试用例：266 / 266
	 */
	public boolean isCircularSentence(String sentence) {
		int length = sentence.length();
		if (sentence.charAt(0) != sentence.charAt(length - 1)) {
			return false;
		}

		for (int i = 1, limit = length - 1; i < limit; i++) {
			if (' ' == sentence.charAt(i) && sentence.charAt(i - 1) != sentence.charAt(i + 1)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		// true
		String sentence1 = "leetcode exercises sound delightful";
		// true
		String sentence2 = "eetcode";
		// false
		String sentence3 = "Leetcode is cool";

		long start;
		boolean result;
		Q2490_CircularSentence solution = new Q2490_CircularSentence();

		System.out.println("sentence1:" + sentence1);
		start = System.currentTimeMillis();
		result = solution.isCircularSentence(sentence1);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {true} - Result:" + result + "\n");

		System.out.println("sentence2:" + sentence2);
		start = System.currentTimeMillis();
		result = solution.isCircularSentence(sentence2);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {true} - Result:" + result + "\n");

		System.out.println("sentence3:" + sentence3);
		start = System.currentTimeMillis();
		result = solution.isCircularSentence(sentence3);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", {false} - Result:" + result + "\n");
	}
}
