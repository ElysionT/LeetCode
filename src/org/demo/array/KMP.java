package org.demo.array;

import java.util.Arrays;

/**
 * 字符串匹配算法：KMP
 * https://leetcode.cn/leetbook/read/array-and-string/cpoo6/
 * 
 * Knuth–Morris–Pratt（KMP）算法是一种改进的字符串匹配算法，它的核心是利用匹配失败后的信息，尽量减少模式串与主串的匹配次数以达到快速匹配的目的。
 * 
 * 实现 strStr()
 * https://leetcode.cn/leetbook/read/array-and-string/cm5e2/
 * 
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
 * 
 * 示例 1：
 * 输入：haystack = "sadbutsad", needle = "sad"
 * 输出：0
 * 解释："sad" 在下标 0 和 6 处匹配。
 * 第一个匹配项的下标是 0 ，所以返回 0 。
 * 
 * 示例 2：
 * 输入：haystack = "leetcode", needle = "leeto"
 * 输出：-1
 * 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。 
 * 
 * 提示：
 * 1 <= haystack.length, needle.length <= 104
 * haystack 和 needle 仅由小写英文字符组成
 */
public class KMP {
	private static final boolean DEBUG = true;

	public int strStr(String haystack, String needle) {
		char[] sArray = haystack.toCharArray();
		int sLength = haystack.length();
		char[] pArray = needle.toCharArray();
		int pLength = needle.length();
		int[] next = buildNext(pArray);
		if (DEBUG)
			System.out.println("next:" + Arrays.toString(next));
		for (int i = 0, j = 0; i < sLength;) {
			if (sArray[i] == pArray[j]) {
				i++;
				j++;
			} else {
				int temp = next[j];
				if (temp < 0) {
					i++;
				} else {
					j = temp;
				}
			}

			if (j == pLength) {
				return i - pLength;
			}
		}
		return -1;
	}

	public boolean match(String s, String p) {
		char[] sArray = s.toCharArray();
		int sLength = s.length();
		char[] pArray = p.toCharArray();
		int pLength = p.length();
		int[] next = buildNext(pArray);
		for (int i = 0, j = 0; i < sLength;) {
			if (sArray[i] == pArray[j]) {
				i++;
				j++;
			} else {
				int temp = next[j];
				if (temp < 0) {
					i++;
				} else {
					j = temp;
				}
			}

			if (j == pLength) {
				return true;
			}
		}

		return false;

	}

	public int[] buildNext(char[] pArray) {
		int length = pArray.length;
		int[] next = new int[length];
		int t = next[0] = -1;
		for (int j = 0; j < length - 1;) {
			if (t < 0 || pArray[t] == pArray[j]) {
				t++;
				j++;
				next[j] = t;
			} else {
				if (0 == t) {
					j++;
					next[j] = 0;
				} else {
					t = next[t];
				}
			}
		}
		return next;
	}

	public static void main(String[] args) {
		String s = "ACTGPACTGKACTGPACY";
		String p = "ACTGPACY";

		KMP kmp = new KMP();
		// int[] next = kmp.buildNext(p.toCharArray());
		// System.out.println("next:" + Arrays.toString(next));

		boolean isMatch = kmp.match(s, p);
		System.out.println("isMatch:" + isMatch);

		// 0
		String haystack1 = "sadbutsad";
		String needle1 = "sad";

		System.out.println("haystack1:" + haystack1 + ", needle1:" + needle1);
		long start = System.currentTimeMillis();
		int result = kmp.strStr(haystack1, needle1);
		System.out.println("0 - Result:" + result + ", Time:" + (System.currentTimeMillis() - start));

		// -1
		String haystack2 = "leetcode";
		String needle2 = "leeto";
		System.out.println("haystack2:" + haystack2 + ", needle2:" + needle2);
		start = System.currentTimeMillis();
		result = kmp.strStr(haystack2, needle2);
		System.out.println("-1 - Result:" + result + ", Time:" + (System.currentTimeMillis() - start));

		// 0
		String haystack3 = "sadbutsad";
		String needle3 = "sad";
		System.out.println("haystack3:" + haystack3 + ", needle3:" + needle3);
		start = System.currentTimeMillis();
		result = kmp.strStr(haystack3, needle3);
		System.out.println("0 - Result:" + result + ", Time:" + (System.currentTimeMillis() - start));

		// 9
		String haystack4 = "mississippi";
		String needle4 = "pi";
		System.out.println("haystack4:" + haystack4 + ", needle4:" + needle4);
		start = System.currentTimeMillis();
		result = kmp.strStr(haystack4, needle4);
		System.out.println("9 - Result:" + result + ", Time:" + (System.currentTimeMillis() - start));

		// 4
		String haystack5 = "aabaaabaaac";
		String needle5 = "aabaaac";
		System.out.println("haystack5:" + haystack5 + ", needle5:" + needle5);
		start = System.currentTimeMillis();
		result = kmp.strStr(haystack5, needle5);
		System.out.println("4 - Result:" + result + ", Time:" + (System.currentTimeMillis() - start));

		// -1
		String haystack6 = "adcadcaddcadde";
		String needle6 = "adcadde";
		System.out.println("haystack6:" + haystack6 + ", needle6:" + needle6);
		start = System.currentTimeMillis();
		result = kmp.strStr(haystack6, needle6);
		System.out.println("-1 - Result:" + result + ", Time:" + (System.currentTimeMillis() - start));
	}
}
