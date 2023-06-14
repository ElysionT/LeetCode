package org.demo.medium;

/**
 * 5. 最长回文子串
 * https://leetcode.cn/problems/longest-palindromic-substring/
 * https://leetcode.cn/leetbook/read/array-and-string/conm7/
 * 
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 * 
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 * 
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 */

public class Q5_LongestPalindrome {
	private static final boolean DEBUG = false;

	public String longestPalindrome2(String s) {
		char[] sArray = s.toCharArray();
		int length = sArray.length;
		String result = "";
		int count = 0;
		int count_ = 0;
		for (int i = 0; i < length; i++) {
			int offset = 1;
			int j = longestPalindromeInternal(sArray, i, offset);
			// count_ = [i + j] - [i - offset - j] + 1 = offset + 2j + 1
			if (j >= 0) {
				count_ = offset + 2 * j + 1;
				if (count_ > count) {
					result = s.substring(i - offset - j, i + j + 1);
					count = count_;
				}
			}
			if (DEBUG)
				System.out.println("count_:" + count_ + ", j:" + j + ", i:" + i + ", offset:" + offset + ", count:"
						+ count + ", result:" + result);

			offset = 2;
			j = longestPalindromeInternal(sArray, i, offset);
			if (j >= 0) {
				count_ = offset + 2 * j + 1;
				if (count_ > count) {
					result = s.substring(i - offset - j, i + j + 1);
					count = count_;
				}
			}
			if (DEBUG)
				System.out.println("count_:" + count_ + ", j:" + j + ", i:" + i + ", offset:" + offset + ", count:"
						+ count + ", result:" + result);
		}

		if (0 == count && length > 0) {
			result = String.valueOf(s.charAt(0));
		}

		return result;
	}

	private int longestPalindromeInternal(char[] sArray, int i, int offset) {
		int length = sArray.length;
		int j = 0;
		for (;;) {
			int a = i - offset - j;
			int b = i + j;
			if (DEBUG)
				System.out.println("i:" + i + ", offset:" + offset + ", j:" + j + ", a:" + a + ", b:" + b);
			if (a < 0 || b >= length) {
				break;
			}
			if (sArray[a] == sArray[b]) {
				j++;
			} else {
				break;
			}
		}
		return j - 1;
	}

	@Deprecated
	String result = new String();

	@Deprecated
	public String longestPalindrome(String s) {
		int length = s.length();
		if (length < 3) {
			longestPalindromeSub(s);
			return result;
		}

		for (int i = 0; i < length; i++) {
			for (int j = length; j > i + 1; j--) {
				String temp = s.substring(i, j);
				if (temp.length() > result.length()) {
					longestPalindromeSub(temp);
				} else {
					break;
				}
			}
		}
		return result;
	}

	@Deprecated
	public boolean longestPalindromeSub(String s) {
		int length = s.length();

		if (1 == length) {
			if (0 == result.length()) {
				result = new String(s);
			}
			return true;
		}

		if (2 == length) {
			if (s.charAt(0) != s.charAt(1)) {
				if (0 == result.length()) {
					result = String.valueOf(s.charAt(0));
				}
			} else {
				if (length > result.length()) {
					result = new String(s);
				}
			}
			return true;
		}

		if (0 == length % 2) {
			for (int j = length / 2, i = j - 1; i >= 0; i--, j++) {
				if (s.charAt(i) != s.charAt(j)) {
					return false;
				}
			}
			if (length > result.length()) {
				result = new String(s);
			}
		} else {
			for (int j = length / 2 + 1, i = j - 2; i >= 0; i--, j++) {
				if (s.charAt(i) != s.charAt(j)) {
					return false;
				}
			}
			if (length > result.length()) {
				result = new String(s);
			}
		}
		return true;
	}

	public static void main(String[] args) {
		// "bab" / "aba"
		String s1 = "babad";
		// "bb"
		String s2 = "cbbd";
		// "a"
		String s3 = "a";

		Q5_LongestPalindrome solution = new Q5_LongestPalindrome();
		System.out.println("s1:" + s1);
		long start = System.currentTimeMillis();
		String result = solution.longestPalindrome2(s1);
		System.out.println("\"bab\" / \"aba\" - Result:" + result + ", Time:" + (System.currentTimeMillis() - start));

		System.out.println("s2:" + s2);
		start = System.currentTimeMillis();
		result = solution.longestPalindrome2(s2);
		System.out.println("\"bb\" - Result:" + result + ", Time:" + (System.currentTimeMillis() - start));

		System.out.println("s3:" + s3);
		start = System.currentTimeMillis();
		result = solution.longestPalindrome2(s3);
		System.out.println("\"a\" - Result:" + result + ", Time:" + (System.currentTimeMillis() - start));
	}
}
