package org.demo;

/**
 *  重新格式化字符串
 * 
 * 给你一个混合了数字和字母的字符串 s，其中的字母均为小写英文字母。
 * 请你将该字符串重新格式化，使得任意两个相邻字符的类型都不同。也就是说，字母后面应该跟着数字，而数字后面应该跟着字母。
 * 请你返回 重新格式化后 的字符串；如果无法按要求重新格式化，则返回一个 空字符串 。
 *
 *  示例 1：
 *  输入：s = "a0b1c2"
 *  输出："0a1b2c"
 *  解释："0a1b2c" 中任意两个相邻字符的类型都不同。 "a0b1c2", "0a1b2c", "0c2a1b" 也是满足题目要求的答案。
*/
public class Q1417 {

	public static void main(String[] args) {
		String[] ss = new String[] { 
				"a0b1c2", 
				"covid2019",
				"ab123",
				"ec"
				};
		for (String s : ss) {
			System.out.println(s + ":" + reformat(s));
		}
	}
	
	public static String reformat(String s) {
		final int length;
		if (null == s || 0 == (length = s.length())) {
			return "";
		}

		char[] result = new char[length];
		int rL = 0;
		char[] digits = new char[length];
		int dL = 0;
		char[] characters = new char[length];
		int cL = 0;

		char c;
		for (int k = 0; k < length; k++) {
			c = s.charAt(k);
			boolean isDigit = ('0' <= c) && (c <= '9');
			if (isDigit) {
				digits[dL++] = c;
			} else {
				characters[cL++] = c;
			}
		}

		if (Math.abs(dL - cL) > 1) {
			return "";
		}

		boolean isDigit = dL >= cL;

		for (int i = -1, j = -1; i < dL && j < cL && rL < length; rL++) {
			result[rL] = isDigit ? digits[++i] : characters[++j];
			isDigit = !isDigit;
		}
		if (rL != length) {
			return "";
		}

		return new String(result);
    }

}
