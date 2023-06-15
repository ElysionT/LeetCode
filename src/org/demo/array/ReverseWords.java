
package org.demo.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 翻转字符串里的单词
 * https://leetcode.cn/leetbook/read/array-and-string/crmp5/
 * 
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 * 
 * 示例 1：
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 * 
 * 示例 2：
 * 输入：s = "  hello world  "/
 * 输出："world hello"
 * 解释：反转后的字符串中不能存在前导空格和尾随空格。
 * 
 * 示例 3：
 * 输入：s = "a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，反转后的字符串需要将单词间的空格减少到仅有一个。
 * 
 * 提示：
 * 1 <= s.length <= 104
 * s 包含英文大小写字母、数字和空格 ' '
 * s 中 至少存在一个 单词
 * 
 * 进阶：如果字符串在你使用的编程语言中是一种可变数据类型，请尝试使用 O(1) 额外空间复杂度的 原地 解法。
 */

public class ReverseWords {
	private static final boolean DEBUG = false;

	public String reverseWords(String s) {
		List<String> temp = new ArrayList<>();
		boolean isWord = false;
		char[] sArray = s.toCharArray();
		int length = sArray.length;
		int j = 0;
		for (int i = 0; i < length; i++) {
			char a = sArray[i];
			if (DEBUG)
				System.out.println("a:" + a + ": " + (int) a + ", i:" + i);
			if (' ' == a) {
				if (isWord) {
					temp.add(s.substring(j, i));
					isWord = false;
				}
			} else {
				if (!isWord) {
					j = i;
					isWord = true;
				}
			}
		}
		if (isWord) {
			temp.add(s.substring(j));
		}

		if (DEBUG)
			System.out.println("temp:" + temp);
		StringBuilder result = new StringBuilder();
		for (int i = temp.size() - 1; i >= 0; i--) {
			result.append(temp.get(i));
			if (0 != i) {
				result.append(' ');
			}
		}
		return result.toString();
	}

	public static void main(String[] args) {
		// "blue is sky the"
		String s1 = "the sky is blue";
		// "world hello"
		String s2 = "  hello world  ";
		// "example good a"
		String s3 = "a good   example";

		final ReverseWords solution = new ReverseWords();
		System.out.println("s1:" + s1);
		long start = System.currentTimeMillis();
		String result = solution.reverseWords(s1);
		System.out.println("\"blue is sky the\" - Result:" + result + ", Time:" + (System.currentTimeMillis() - start));

		System.out.println("s2:" + s2);
		start = System.currentTimeMillis();
		result = solution.reverseWords(s2);
		System.out.println("\"world hello\" - Result:" + result + ", Time:" + (System.currentTimeMillis() - start));

		System.out.println("s3:" + s3);
		start = System.currentTimeMillis();
		result = solution.reverseWords(s3);
		System.out.println("\"example good a\" - Result:" + result + ", Time:" + (System.currentTimeMillis() - start));
	}
}
