package org.demo.medium;

/**
 * 3. 无重复字符的最长子串
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 * 
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * 
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3 
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *      
 * 提示：
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 */
public class Q3 {

	public int lengthOfLongestSubstring(String s) {
		String result = new String();
		StringBuilder temp = new StringBuilder();
		char[] as = s.toCharArray();
		int k = 0;
		for (int i = 0, length = as.length; i < length; i++) {
			char a1 = as[i];
			if (0 == i) {
				temp.append(a1);
			} else if (0 == temp.length()) {
				temp.append(a1);
			} else if (-1 == (k = temp.indexOf(a1 + ""))) {
				temp.append(a1);
			} else {
				if (temp.length() > result.length()) {
					result = temp.toString();
				}
				temp.delete(0, k + 1);
				temp.append(a1);
			}
		}
		if (temp.length() > result.length()) {
			result = temp.toString();
		}
		return result.length();
	}

	public static void main(String[] args) {
		// 3
		String s = "abcabcbb";
		// 1
		// String s = "bbbbb";
		// 3
		// String s = "pwwkew";

		System.out.println("S:" + s);
		Q3 solution = new Q3();
		long start = System.currentTimeMillis();
		int result = solution.lengthOfLongestSubstring(s);
		System.out.println("Result:" + result + ", Time:" + (System.currentTimeMillis() - start));
	}

}
